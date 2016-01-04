package ir.hassannasr.majles.services.user;

import com.sun.jersey.multipart.FormDataParam;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
        return Response.ok(new PostView(post)).build();
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
            Set<User> users = phoneConnectionDao.getMyConnections(user);
            userIds = users.stream().map(User::getId).collect(Collectors.toList());
        } else {
            userIds = new ArrayList<>();
            User friend = userManager.get(friendId);
            if (friend.getVerified() || phoneConnectionDao.getConnection(friend.getPhone(), user.getPhone()) != null)
                userIds.add(friendId);
            else
                return sendError("شما دسترسی به اخبار این کاربر ندارید");
        }
        final List<Post> result = postManager.loadPostByUserIds(userIds, older, newer, count);
        List<PostView> postViews = result.stream().map(PostView::new).collect(Collectors.toList());
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
        List<PostView> postViews = new ArrayList<>();
        for (Post post : result) {
            postViews.add(new PostView(post));
        }
        return Response.ok(postViews).build();
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
        List<PostView> postViews = new ArrayList<>();
        for (Post post : result) {
            postViews.add(new PostView(post));
        }
        return Response.ok(postViews).build();

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
}
