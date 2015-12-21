package ir.hassannasr.majles.domain.user;

import core.dao.GenericDao;
import ir.hassannasr.majles.domain.candid.Candid;

import javax.persistence.EntityManager;

/**
 * Created by hassan on 02/11/2015.
 */

public interface UserDao extends GenericDao<User, Long> {
    public EntityManager getEntityManager();

    public Endorse loadEndorseByUserCandidContext(User user, Candid c, String context);
}
