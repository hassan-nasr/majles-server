package ir.hassannasr.majles.domain.user;

import core.dao.GenericDao;
import core.service.GenericManagerImpl;
import ir.hassannasr.majles.domain.candid.Candid;
import ir.hassannasr.majles.domain.candid.CandidDao;
import ir.hassannasr.majles.domain.exceptoin.InvalidParameterException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by hassan on 02/11/2015.
 */
public class UserManagerImpl extends GenericManagerImpl<User, Long> implements UserManager {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CandidDao candidDao;


    private Long BASE_ENDORSE_CREDIT = 10l;
    private Integer MaxCreditPerUser = 3;

    public UserManagerImpl(GenericDao<User, Long> genericDao) {
        super(genericDao);
    }

    public Long getBASE_ENDORSE_CREDIT() {
        return BASE_ENDORSE_CREDIT;
    }

    public void setBASE_ENDORSE_CREDIT(Long BASE_ENDORSE_CREDIT) {
        this.BASE_ENDORSE_CREDIT = BASE_ENDORSE_CREDIT;
    }

    @Override
    public User createNewUser(Long userId, String phone) {
        phone = new Normalizer().normalizePhone(phone);
        User user = new User();
        user.setId(userId);
        user.setCreationDate(new Date());
        user.setEndorseCredit(BASE_ENDORSE_CREDIT);
        user.setPhone(phone);
        final User save = userDao.save(user);
        return save;
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

    private boolean increaseEndorseWithQuery(Candid c, String context, Integer credit) {
        final Query query = userDao.getEntityManager().createQuery(String.format("update Candid c set c.endorseCount.%s=c.endorseCount.%s+%d where c.id=%d", context, context, credit, c.getId()));
        return query.executeUpdate() > 0;
    }

}
