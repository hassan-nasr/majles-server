package ir.hassannasr.majles.services.user;

import com.idehgostar.makhsan.core.auth.TokenManager;
import com.idehgostar.makhsan.core.encryption.CipherUtils;
import com.idehgostar.makhsan.core.services.ApplicationService;
import com.idehgostar.makhsan.core.services.ApplicationServiceManager;
import com.sun.jersey.multipart.FormDataParam;
import ir.hassannasr.majles.domain.candid.Candid;
import ir.hassannasr.majles.domain.candid.CandidManager;
import ir.hassannasr.majles.domain.candid.HozehDao;
import ir.hassannasr.majles.domain.exceptoin.InvalidParameterException;
import ir.hassannasr.majles.domain.user.*;
import ir.hassannasr.majles.services.BaseWS;
import ir.hassannasr.majles.services.response.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by hassan on 20/12/2015.
 */

@Service
@Path("/user")
public class UserWS extends BaseWS {


    @Autowired
    UserManager userManager;
    @Autowired
    CandidManager candidManager;
    @Autowired
    TokenManager tokenManager;
    @Autowired
    HozehDao hozehDao;
    @Autowired
    PhoneConnectionDao phoneConnectionDao;

    @Autowired
    ApplicationServiceManager applicationServiceManager;
    @Autowired
    CipherUtils cipherUtils;

    @GET
    @Path("/userInfo")
    @Produces("application/json")
    public Response getMyInfo(@QueryParam("id") Long userId, @QueryParam("referee") String refereePhone) {
        try {
            if (getUserInSite() == null)
                return sendError("NotLoggedIn");
            if (userId.equals(Long.parseLong(getUserInSite()))) {
                User user = null;
                try {
                    user = userManager.get(userId);
                } catch (Exception e) {
                }
                if (user == null) {
                    user = userManager.createNewUser(userId, getTokenData().getPhone(), refereePhone);
                    user.setSubHozeh(null);
                } else if (user.getSubHozeh() == null) {
                    user.setSubHozeh(hozehDao.get(1l));
                }
                return Response.ok(getJsonCreator().getJson(new UserView(user, candidManager, true))).build();
            } else {
                try {
                    User requestedUser = userManager.get(userId);
                    if (requestedUser.getSubHozeh() == null)
                        requestedUser.setSubHozeh(hozehDao.load(1L));
                    if (requestedUser.getVerified())
                        return Response.ok(new UserView(requestedUser, candidManager, false)).build();
                    User userInSite = userManager.get(Long.valueOf(getUserInSite()));
                    if (phoneConnectionDao.getConnection(requestedUser.getPhone(), userInSite.getPhone()) != null) {
                        return Response.ok(new UserView(requestedUser, candidManager, false)).build();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return sendError("خطای دسترسی");
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                sendError("ServerError");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    @GET
    @Path("/setHozeh")
    @Produces("application/json")
    public Response setHozeh(@QueryParam("hozehId") Long hozehId) {
        try {
            if (getUserInSite() == null)
                return sendError("NotLoggedIn");
            final User user = userManager.get(Long.valueOf(getUserInSite()));
            if (user == null) {
                return sendError("UserNotFound");
            }
            user.setSubHozeh(hozehDao.load(hozehId));
            Set<Candid> newChose = new HashSet<>();
            for (Candid candid : user.getMyChoseCandids()) {
                if (candid.getSubHozehObj().getId().equals(hozehId))
                    newChose.add(candid);
                else
                    user.getMyFollowingCandids().add(candid);
            }
            user.setMyChoseCandids(newChose);
            userManager.save(user);
            return Response.ok(new SimpleResponse(SimpleResponse.Status.Success, "Done")).build();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                sendError("ServerError");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    @Path("/addEndorse")
    @GET
    @Produces("application/json")
    public Response addEndorse(@QueryParam("candidId") Long candidId, @QueryParam("context") String context, @QueryParam("credit") Integer credit) {
        try {

            if (getUserInSite() == null) {
                return sendError("NotLoggedIn");
            }
            Long userId = Long.parseLong(getUserInSite());
            User user = userManager.get(userId);

            if (user.getEndorseCredit() < credit) {
                return sendError("شما اعتبار کافی برای این کار ندارید");
            }
            Candid c = candidManager.get(candidId);
            if (c == null) {
                return sendError("candidNotFount");
            }
            final Endorse endorse = userManager.endorse(user, c, context, credit);
            EndorseResponse response = new EndorseResponse();
            response.setEndorse(endorse);
            candidManager.refresh(c);
//            candidManager.evict(c);
//            c=candidManager.get(c.getId());
            response.setCandid(new CandidView(c));
            response.setUser(new UserView(user, candidManager, true));
            return Response.ok(getJsonCreator().getJson(response)).build();

        } catch (IOException | InvalidParameterException e) {
            e.printStackTrace();
            try {
                return sendError(e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    @GET
    @Path("/addCandidToList")
    @Produces("application/json")
    public Response addCandidToList(@QueryParam("candidId") Long candidId, @QueryParam("listName") String listName) throws IOException {
        if (getUserInSite() == null)
            return sendError("NotLoggedIn");
        User user = userManager.get(Long.valueOf(getUserInSite()));
        final Candid candid = candidManager.load(candidId);
        if ("choose".equals(listName)) {
            Integer capacity = user.getSubHozeh().getCapacity();
            if (user.getMyChoseCandids().size() >= capacity)
                return sendError("ظرفیت لیست انتخابی تکمیل است");
            else if (!user.getSubHozeh().getId().equals(candid.getSubHozehObj().getId()))
                return sendError("این کاندید در حوزه انتخابی شما نیست تنها می‌توانید آن را به لیست پیگیری اضافه کنید و یا حوزه خود را تغییر دهید");
            else {
                user.getMyChoseCandids().add(candid);
                user = userManager.save(user);
                return Response.ok(getJsonCreator().getJson(new UserView(user, candidManager, true))).build();
            }
        }
        if ("follow".equals(listName)) {
            user.getMyFollowingCandids().add(candid);
            user = userManager.save(user);
            return Response.ok(getJsonCreator().getJson(new UserView(user, candidManager, true))).build();
        }
        return sendError("خطا در انتخاب لیست");
    }

    @GET
    @Path("/removeCandidFromList")
    @Produces("application/json")
    public Response removeCandidFromList(@QueryParam("candidId") Long candidId, @QueryParam("listName") String listName) throws IOException {
        if (getUserInSite() == null)
            return sendError("NotLoggedIn");
        User user = userManager.get(Long.valueOf(getUserInSite()));
        if ("choose".equals(listName)) {
            user.getMyChoseCandids().remove(candidManager.load(candidId));
            user = userManager.save(user);
            return Response.ok(getJsonCreator().getJson(new UserView(user, candidManager, true))).build();
        }
        if ("follow".equals(listName)) {
            user.getMyFollowingCandids().remove(candidManager.load(candidId));
            user = userManager.save(user);
            return Response.ok(getJsonCreator().getJson(new UserView(user, candidManager, true))).build();
        }
        return sendError("خطا در انتخاب لیست");
    }

    @POST
    @Path("/uploadContacts")
    @Produces("application/json")
    @Consumes("application/json")
    public Response uploadContacts(List<String> phones) throws IOException {
        if (getUserInSite() == null)
            return sendError("NotLoggedIn");
        final User user = userManager.get(Long.valueOf(getUserInSite()));
        final Map<String, PhoneConnection> currentConnections = phoneConnectionDao.getConnectionsFrom(user.getPhone());
        Set<String> added = new HashSet<>();
        for (int i = 0; i < phones.size(); i++) {
            String phone = new Normalizer().normalizePhone(phones.get(i));

            if (!currentConnections.containsKey(phone) && !added.contains(phone)) {
                phoneConnectionDao.save(new PhoneConnection(user.getPhone(), phone));
                added.add(phone);
            }
        }
        return sendSuccess("Done");
    }

    @GET
    @Path("/getMyFriends")
    @Produces("application/json")
    public Response getMyFriends() throws IOException {
        if (getUserInSite() == null)
            return sendError("NotLoggedIn");
        final User user = userManager.get(Long.valueOf(getUserInSite()));
        Set<User> users = phoneConnectionDao.getMyConnections(user);
        List<UserSimpleView> ret = new ArrayList<>();
        for (User user1 : users) {
            ret.add(new UserSimpleView(user1, null));
        }
        return Response.ok(ret).build();
    }

    @GET
    @Path("/search")
    @Produces("application/json")
    public Response getChannels(@QueryParam("query") String text,
                                @QueryParam("from") @DefaultValue("0") Integer from,
                                @QueryParam("count") @DefaultValue("1000") Integer count) throws IOException {
        if (getUserInSite() == null)
            return sendError("NotLoggedIn");
        if (text == null)
            text = "";
        final List<User> result = userManager.findVerifiedWithQuery(text, from, count);
        final Map<Long, Candid> candidsMap = candidManager.getCandidsMap(result);
        List<UserSimpleView> ret = new ArrayList<>();
        for (User user : result) {
            ret.add(new UserSimpleView(user, candidsMap.get(user.getId())));
        }
        return Response.ok(ret).build();
    }


    @POST
    @Path("/uploadInfo")
    @Produces("application/json")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response publish(@QueryParam("name") String text,
                            @FormDataParam("picture") InputStream uploadedInputStream
    ) throws IOException {
        if (getUserInSite() == null)
            return sendError("NotLoggedIn");
        User user = userManager.get(Long.valueOf(getUserInSite()));
        if (user == null)
            return sendError("NotLoggedIn");
        user.setName(text);
        user = userManager.save(user, uploadedInputStream);
        return Response.ok(new UserView(user, candidManager, true)).build();
    }


    @GET
    @Path("/alterFriend")
    @Produces("application/json")
    public Response alterFriend(@QueryParam("phone") String phone, @QueryParam("add") Boolean add) throws IOException {
        if (getUserInSite() == null)
            return sendError("NotLoggedIn");
        User user = userManager.get(Long.valueOf(getUserInSite()));
        if (user == null)
            return sendError("NotLoggedIn");
        Set<String> friend = new HashSet<>();
        friend.add(phone);
        final List<User> withPhoneNumber = userManager.getWithPhoneNumber(friend);

        if (withPhoneNumber.size() == 0 || !withPhoneNumber.get(0).getVerified())
            return sendError("NotAllowed");
        if (add)
            phoneConnectionDao.addFriend(user, phone);
        else
            phoneConnectionDao.removeFriend(user, phone);
        return sendSuccess("Done");
    }

    @GET
    @Path("/chargeUser")
    @Produces("application/json")
    public Response chargeUser(@QueryParam("request") String query) throws IOException {
        try {
            final ApplicationService applicationService = applicationServiceManager.loadDefaultService();
            final String decrypt = cipherUtils.decrypt(query, applicationService.getPrivateKeyExponent().substring(10, 10 + 16));
            ObjectMapper mapper = new ObjectMapper();
            Cipher e = Cipher.getInstance("AES");
            SecretKeySpec secretKey = new SecretKeySpec("lsdjf".getBytes(), "AES");
            e.init(2, secretKey);
            final ChargeRequest chargeRequest = mapper.readValue(decrypt, ChargeRequest.class);
            User user = userManager.getWithPhoneNumber(new HashSet<String>(Arrays.asList(chargeRequest.getPhone()))).get(0);
            if (chargeRequest.getType().equals("AD")) {
                user.setVerifiedCredit(user.getVerifiedCredit() + chargeRequest.getAmount());
            } else {
                user.setEndorseCredit(user.getEndorseCredit() + chargeRequest.getAmount() / userManager.getCreditPrice());
            }
            userManager.save(user);
            userManager.flush();
            return sendSuccess("Done");
        }catch (Exception e){
            e.printStackTrace();
            return sendError(e.getMessage());
        }
    }


    @GET
    @Path("/checkUser")
    @Produces("application/json")
    public Response userExist(@QueryParam("phone") String phone) throws IOException {
        try {
            if (phone == null || phone.isEmpty())
                return sendError("false");
            final HashSet<String> set = new HashSet<>();
            set.add(phone);
            final List<User> withPhoneNumber = userManager.getWithPhoneNumber(set);
            if (withPhoneNumber.size() == 0)
                return sendError("false");
            return sendSuccess("true");
        } catch (Exception e) {
            e.printStackTrace();
            return sendError(e.getMessage());
        }
    }



    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}
