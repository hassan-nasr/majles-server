package ir.hassannasr.majles.domain.user;

import core.service.GenericManager;
import ir.hassannasr.majles.domain.candid.Candid;
import ir.hassannasr.majles.domain.exceptoin.InvalidParameterException;

import java.util.List;
import java.util.Set;

/**
 * Created by hassan on 02/11/2015.
 */
public interface UserManager extends GenericManager<User
        , Long> {

    public User createNewUser(Long userId, String phone);

    public Endorse endorse(User user, Candid c, String context, Integer credit) throws InvalidParameterException;

    List<User> getWithPhoneNumber(Set<String> intersect);
}
