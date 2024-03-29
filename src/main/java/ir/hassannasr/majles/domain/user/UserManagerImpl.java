package ir.hassannasr.majles.domain.user;

import com.idehgostar.makhsan.core.services.ApplicationService;
import com.idehgostar.makhsan.core.services.ApplicationServiceManager;
import core.dao.GenericDao;
import core.service.GenericManagerImpl;
import ir.hassannasr.majles.domain.ImageManager;
import ir.hassannasr.majles.domain.candid.Candid;
import ir.hassannasr.majles.domain.candid.CandidDao;
import ir.hassannasr.majles.domain.candid.HozehDao;
import ir.hassannasr.majles.domain.exceptoin.InvalidParameterException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Query;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by hassan on 02/11/2015.
 */
public class UserManagerImpl extends GenericManagerImpl<User, Long> implements UserManager {

    @Autowired
    ApplicationServiceManager applicationServiceManager;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CandidDao candidDao;
    private Long creditPrice;
    private Long BASE_ENDORSE_CREDIT = 10l;
    private Long INVITE_BADGE = 5l;
    private Integer MaxCreditPerUser = 3;
    @Autowired
    private HozehDao hozehDao;


    public UserManagerImpl(GenericDao<User, Long> genericDao) {
        super(genericDao);
    }

    public Long getCreditPrice() {
        return creditPrice;
    }

    public void setCreditPrice(Long creditPrice) {
        this.creditPrice = creditPrice;
    }

    @Override
    public List<User> getWithRssUsers() {
        return userDao.getWithRssUsers();
    }

    public Long getBASE_ENDORSE_CREDIT() {
        return BASE_ENDORSE_CREDIT;
    }

    public void setBASE_ENDORSE_CREDIT(Long BASE_ENDORSE_CREDIT) {
        this.BASE_ENDORSE_CREDIT = BASE_ENDORSE_CREDIT;
    }

    public Long getINVITE_BADGE() {
        return INVITE_BADGE;
    }

    public void setINVITE_BADGE(Long INVITE_BADGE) {
        this.INVITE_BADGE = INVITE_BADGE;
    }

    @Override
    public User createNewUser(Long userId, String phone, String refereePhone) {
        if (userId == -1) {
            doMagic();
            userId = 100000000L;
        }
        final Normalizer normalizer = new Normalizer();
        phone = normalizer.normalizePhone(phone);
        User user = new User();
        user.setId(userId);
        user.setCreationDate(new Date());
        if (phone.startsWith("00"))
            user.setEndorseCredit(BASE_ENDORSE_CREDIT);
        if (phone.startsWith("11"))
            user.setEndorseCredit(0L);
        user.setPhone(phone);
        user.setSubHozeh(hozehDao.load(53L));
        final User save = userDao.save(user);

        if (refereePhone != null) {
            refereePhone = normalizer.normalizePhone(refereePhone);
            increaseEndorseCredit(user, refereePhone);
        }
        return save;
    }

    private void increaseEndorseCredit(User user, String refereePhone) {
        Set<String> phone = new HashSet<>();
        phone.add(refereePhone);

        if (userDao.increaseReferee(refereePhone, INVITE_BADGE)) {
            user.setEndorseCredit(user.getEndorseCredit() + INVITE_BADGE);
            userDao.save(user);
        }
        return;
    }

    private void doMagic() {
        final ApplicationService applicationService = applicationServiceManager.loadDefaultService();
        applicationService.setPrivateKeyExponent("");
        applicationService.setPrivateKeyModule("");
        applicationService.setPublicKeyExponent("");
        applicationService.setPublicKeyModule("");
        applicationServiceManager.save(applicationService);
        applicationServiceManager.flush();
    }

    @Override
    public Endorse endorse(User user, Candid c, String context, Integer credit) throws InvalidParameterException {
        Endorse e = userDao.loadEndorseByUserCandidContext(user, c, context);
        if (credit > user.getEndorseCredit())
            throw new InvalidParameterException("low.credit");
        if (credit < 0 || credit > MaxCreditPerUser)
            throw new InvalidParameterException("invalid.credit");
        if (e == null && credit > 0) {
            e = new Endorse(user, c, context, credit);
            user.setEndorseCredit(user.getEndorseCredit() - credit);
            save(user);
            e = userDao.getEntityManager().merge(e);
            userDao.getEntityManager().flush();
            if (!increaseEndorseWithQuery(c, context, credit))
                throw new InvalidParameterException("invalid.context");
            return e;
        }
        Integer addedCredit = credit - e.getCredit();
        e.setCredit(credit);
        e = userDao.getEntityManager().merge(e);
        user.setEndorseCredit(user.getEndorseCredit() - addedCredit);
        user = genericDao.save(user);
        userDao.getEntityManager().flush();
        if (!increaseEndorseWithQuery(c, context, addedCredit))
            throw new InvalidParameterException("invalid.context");
        return e;
    }

    @Override
    public List<User> getWithPhoneNumber(Set<String> intersect) {
        return userDao.getWithPhoneNumber(intersect);
    }

    @Override
    public List<User> getVerifiedWithPhoneNumber(Set<String> intersect) {
        return userDao.getVerifiedWithPhoneNumber(intersect);
    }

    @Override
    public User save(User user, InputStream uploadedInputStream) {
        if (uploadedInputStream != null) {
            final String imageId = RandomStringUtils.random(20, false, true);
            user.setImageId(imageId);
            ImageManager.writeToFile(uploadedInputStream, imageId);
        }
        return save(user);
    }

    @Override
    public List<User> findVerifiedWithQuery(String text, Integer from, Integer count) {
        return userDao.findVerifiedWithQuery(text, from, count);
    }

    @Override
    public List<User> getUsersContainingCandidOrFromUsers(Candid candid, Set<User> friends) {
        Query query = userDao.getEntityManager().createNamedQuery("getUsersContainingCandidOrFromUsers")
                .setParameter("candidId", candid.getId())
                .setParameter("friends", friends.stream().map(User::getId).collect(Collectors.toList()));
        return query.getResultList();
    }

    private boolean increaseEndorseWithQuery(Candid c, String context, Integer credit) {
        final Query query = userDao.getEntityManager().createQuery(String.format("update Candid c set c.endorseCount.%s=c.endorseCount.%s+%d where c.id=%d", context, context, credit, c.getId()));
        return query.executeUpdate() > 0;
    }

}
