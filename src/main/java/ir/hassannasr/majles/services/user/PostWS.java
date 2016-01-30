package ir.hassannasr.majles.services.user;

import com.sun.jersey.multipart.FormDataParam;
import ir.hassannasr.majles.domain.candid.Candid;
import ir.hassannasr.majles.domain.candid.CandidManager;
import ir.hassannasr.majles.domain.hozeh.SubHozeh;
import ir.hassannasr.majles.domain.post.Post;
import ir.hassannasr.majles.domain.post.PostManager;
import ir.hassannasr.majles.domain.user.PhoneConnectionDao;
import ir.hassannasr.majles.domain.user.User;
import ir.hassannasr.majles.domain.user.UserManager;
import ir.hassannasr.majles.services.BaseWS;
import ir.hassannasr.majles.services.response.PostView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by hassan on 01/01/2016.
 */
@Service
@Path("/post")
public class PostWS extends BaseWS {

    @Autowired
    UserManager userManager;

    @Autowired
    PostManager postManager;

    @Autowired
    CandidManager candidManager;

    @Autowired
    PhoneConnectionDao phoneConnectionDao;

    @POST
    @Path("/publish")
    @Produces("application/json")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response publish(@QueryParam("text") String text,
                            @QueryParam("link") String link,
                            @FormDataParam("sponsored") Boolean sponsored,
                            @FormDataParam("picture") InputStream uploadedInputStream,
//                                         @FormDataParam("picture") FormDataContentDisposition contentDispositionHeader,
                            @FormDataParam("hozehId") Long subHozehId) throws IOException {
        if (getUserInSite() == null)
            return sendError("NotLoggedIn");
        User user = userManager.get(Long.valueOf(getUserInSite()));
        if (user == null)
            return sendError("NotLoggedIn");
        Post post = new Post();
        post.setText(text);
        post.setLink(link);
        post.setUser(user);
        Long price = 0L;
        if (user.getVerified()) {
            post.setSubHozehId(subHozehId);
            post.setSponsored(sponsored);
            if (subHozehId == null)
                subHozehId = 0L;
            if (sponsored) {
                price = candidManager.getPriceMap().get(subHozehId);
                if (user.getVerifiedCredit() < price)
                    return sendError("اعتبار شما کافی نیست");
            }
        }
        post.setPublishDate(new Date());
//        if (user.getVerified() == null || !user.getVerified()) {
//            uploadedInputStream = null;
//        }
        post = postManager.save(post, uploadedInputStream, price);
        final Candid candid = candidManager.getCandidsMap(Arrays.asList(post.getUser())).get(post.getUser().getId());
        return Response.ok(new PostView(post, candid)).build();
    }


    @GET
    @Path("/getMyFriendsStream")
    @Produces("application/json")
    public Response getMyFriendsStream(@QueryParam("older") Long older,
                                       @QueryParam("newer") Long newer,
                                       @QueryParam("count") @DefaultValue("50") Integer count,
                                       @QueryParam("friendId") Long friendId
    ) throws IOException {
        if (getUserInSite() == null)
            return sendError("NotLoggedIn");
        final User user = userManager.get(Long.valueOf(getUserInSite()));
        List<Long> userIds;
        if (friendId == null) {

            Set<Candid> interestedCandids = new HashSet<>();
            if (user.getMyFollowingCandids() != null)
                interestedCandids.addAll(user.getMyFollowingCandids());
            if (user.getMyChoseCandids() != null)
                interestedCandids.addAll(user.getMyChoseCandids());
            Set<User> users = phoneConnectionDao.getMyConnections(user);
            userIds = users.stream().map(User::getId).collect(Collectors.toList());
            for (Candid interestedCandid : interestedCandids) {
                if (interestedCandid.getUserId() != null)
                    userIds.add(interestedCandid.getUserId());
            }
        } else {
            userIds = new ArrayList<>();
            User friend = userManager.get(friendId);
            if (friend.getVerified() || phoneConnectionDao.getConnection(friend.getPhone(), user.getPhone()) != null)
                userIds.add(friendId);
            else
                return sendError("شما دسترسی به اخبار این کاربر ندارید");
        }
        final List<Post> result = postManager.loadPostByUserIds(userIds, older, newer, count);
        return createPostViewList(result);
    }

    protected Response createPostViewList(List<Post> result) {

        Map<Long, Post> basePosts = new HashMap<>();
        List<Long> originalPostIds = new ArrayList<>();
        for (Post post : result) {
            if (post.getBasePostId() != null) {
                originalPostIds.add(post.getBasePostId());
            }
        }
        basePosts = postManager.getPostMap(originalPostIds);
        Set<User> users = new HashSet<>();
        for (Post post : result) {
            users.add(post.getUser());
        }
        for (Post post : basePosts.values()) {
            users.add(post.getUser());
        }
        Map<Long, Candid> candids = candidManager.getCandidsMap(users);
        List<PostView> postViews = new ArrayList<>();
        for (Post post : result) {
            if (post.getBasePostId() == null)
                postViews.add(new PostView(post, candids.get(post.getUser().getId())));
            else {
                final Post basePost = basePosts.get(post.getBasePostId());
                postViews.add(new PostView(post, basePost, candids.get(post.getUser().getId()), candids.get(basePost.getUser().getId())));
            }
        }
        return Response.ok(postViews).build();
    }


    @GET
    @Path("/getMyPosts")
    @Produces("application/json")
    public Response getMyPosts(@QueryParam("older") Long older,
                               @QueryParam("newer") Long newer,
                               @QueryParam("count") @DefaultValue("50") Integer count
    ) throws IOException {
        if (getUserInSite() == null)
            return sendError("NotLoggedIn");

        final List<Post> result = postManager.loadMyPost(Long.valueOf(getUserInSite()), older, newer, count);
        return createPostViewList(result);
    }

    @GET
    @Path("/getSponsoredPosts")
    @Produces("application/json")
    public Response getSponsoredPosts(@QueryParam("older") Long older,
                                      @QueryParam("newer") Long newer,
                                      @QueryParam("count") @DefaultValue("50") Integer count) throws IOException {
        if (getUserInSite() == null)
            return sendError("NotLoggedIn");
        final User user = userManager.get(Long.valueOf(getUserInSite()));
        SubHozeh hozeh = user.getSubHozeh();
        final List<Post> result = postManager.loadSponsoredPosts(hozeh, older, newer, count);
        return createPostViewList(result);

    }


    @GET
    @Path("/getSponsoredPostPrices")
    @Produces("application/json")
    public Response getSponsoredPostPrices() throws IOException {
        if (getUserInSite() == null)
            return sendError("NotLoggedIn");
        if (!userManager.get(Long.valueOf(getUserInSite())).getVerified())
            return sendError("AccessDenied");

        return Response.ok(candidManager.getPriceMap()).build();
    }

    @GET
    @Path("/republish")
    @Produces("application/json")
    public Response republish(@QueryParam("post_id") Long postId) throws IOException {
        try {
            if (getUserInSite() == null)
                return sendError("NotLoggedIn");
            final Post source = postManager.get(postId);
            if (source == null)
                return sendError("NotFound");
            if (source.getBasePostId() != null)
                return sendError("NotAllowed");
            if (!source.getUser().getVerified())
                return sendError("تنها امکان باز نشر پست‌های کاربران تاييد شده وجود دارد");
            if (source.getUser().getId().equals(Long.parseLong(getUserInSite())))
                return sendError("امکان باز ارسال پست خودتان وجود ندارد");
            Post post = new Post();
            post.setUser(userManager.load(Long.valueOf(getUserInSite())));
            post.setBasePostId(postId);
            post.setPublishDate(new Date());
            post.setDeleted(false);
            final Post save = postManager.save(post);
            return sendSuccess("Done");
        } catch (Exception e) {
            return sendError("خطا در انتشار پست");
        }
    }
}
