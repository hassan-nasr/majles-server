package ir.hassannasr.majles.domain.user;

import core.dao.GenericDaoImpl;
import ir.hassannasr.majles.domain.candid.Candid;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by hassan on 02/11/2015.
 */
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {
    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public Endorse loadEndorseByUserCandidContext(User user, Candid c, String context) {
        final Query query = entityManager.createNamedQuery("loadEndorseByUserCandidContext");
        query.setParameter("candidId", c.getId())
                .setParameter("endorseContext", context)
                .setParameter("userId", user.getId());
        final List<Endorse> result = query.getResultList();
        if (result.size() > 0)
            return result.get(0);
        return null;
    }

    @Override
    public List<User> getWithPhoneNumber(Set<String> intersect) {
        if(intersect.size()==0)
            return new ArrayList<>();
        return (List<User>) getEntityManager().createNamedQuery("loadUsersWithPhone").setParameter("phone", intersect).getResultList();
    }

    @Override
    public List<User> getVerifiedWithPhoneNumber(Set<String> intersect) {
        if(intersect.size()==0)
            return new ArrayList<>();
        return (List<User>) getEntityManager().createNamedQuery("loadValidUsersWithPhone").setParameter("phone", intersect).getResultList();
    }

    @Override
    public List<User> findVerifiedWithQuery(String text, Integer from, Integer count) {
        text = "%" + text + "%";
        return entityManager.createNamedQuery("findVerifiedWithQuery")
                .setParameter("query", text)
                .setFirstResult(from)
                .setMaxResults(count)
                .getResultList();
    }

    @Override
    public Boolean increaseReferee(String refereePhone, Long invite_badge) {
        if (refereePhone == null)
            return false;
        return entityManager.createNamedQuery("increaseReferee")
                .setParameter("phone", refereePhone)
                .setParameter("amount", invite_badge)
                .executeUpdate() > 0;
    }

    @Override
    public List<User> getWithRssUsers() {
        return entityManager.createNamedQuery("withRssUsers").getResultList();
    }
}
