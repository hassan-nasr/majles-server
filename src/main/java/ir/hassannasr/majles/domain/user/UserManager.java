package ir.hassannasr.majles.domain.user;

import core.service.GenericManager;
import ir.hassannasr.majles.domain.candid.Candid;
import ir.hassannasr.majles.domain.exceptoin.InvalidParameterException;

/**
 * Created by hassan on 02/11/2015.
 */
public interface UserManager extends GenericManager<User
        , Long> {

    public User createNewUser(Long userId);

    public Endorse endorse(User user, Candid c, String context, Integer credit) throws InvalidParameterException;
}
