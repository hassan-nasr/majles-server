package ir.hassannasr.majles.domain.user;

import core.dao.GenericDao;
import ir.hassannasr.majles.domain.candid.Candid;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

/**
 * Created by hassan on 02/11/2015.
 */

public interface UserDao extends GenericDao<User, Long> {
    public EntityManager getEntityManager();

    public Endorse loadEndorseByUserCandidContext(User user, Candid c, String context);

    public List<User> getWithPhoneNumber(Set<String> intersect);

    public List<User> getVerifiedWithPhoneNumber(Set<String> intersect);

    List<User> findVerifiedWithQuery(String text, Integer from, Integer count);

    Boolean increaseReferee(String refereePhone, Long invite_badge);

    List getWithRssUsers();
}
