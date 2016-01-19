package ir.hassannasr.majles.domain.user;

import core.service.GenericManager;
import ir.hassannasr.majles.domain.candid.Candid;
import ir.hassannasr.majles.domain.exceptoin.InvalidParameterException;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

/**
 * Created by hassan on 02/11/2015.
 */
public interface UserManager extends GenericManager<User
        , Long> {

    public User createNewUser(Long userId, String phone, String refereePhone);

    public Endorse endorse(User user, Candid c, String context, Integer credit) throws InvalidParameterException;

    List<User> getWithPhoneNumber(Set<String> intersect);

    List<User> getVerifiedWithPhoneNumber(Set<String> intersect);

    User save(User user, InputStream uploadedInputStream);

    List<User> findVerifiedWithQuery(String text, Integer from, Integer count);

    List<User> getUsersContainingCandidOrFromUsers(Candid candid,Set<User> friends);

    public Long getCreditPrice();

}
