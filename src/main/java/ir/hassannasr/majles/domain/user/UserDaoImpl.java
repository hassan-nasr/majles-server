package ir.hassannasr.majles.domain.user;

import core.dao.GenericDaoImpl;
import ir.hassannasr.majles.domain.candid.Candid;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
        return (List<User>) getEntityManager().createNamedQuery("loadUsersWithPhone").setParameter("phone", intersect).getResultList();
    }
}
