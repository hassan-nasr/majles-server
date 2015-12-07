package com.idehgostar.makhsan.domain.user;

import com.idehgostar.makhsan.core.auth.TokenData;
import com.idehgostar.makhsan.core.auth.TokenManager;
import com.idehgostar.makhsan.core.encryption.CipherUtils;
import com.idehgostar.makhsan.core.services.ApplicationService;
import com.idehgostar.makhsan.core.services.ApplicationServiceManager;
import com.idehgostar.makhsan.domain.exceptoin.InvalidParameterException;
import com.idehgostar.makhsan.domain.role.Role;
import com.idehgostar.makhsan.domain.user.Auth.AuthenticateInfo;
import com.idehgostar.makhsan.domain.user.Auth.AuthenticationFailedException;
import core.dao.GenericDao;
import core.service.GenericManagerImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hassan on 02/11/2015.
 */
public class UserManagerImpl extends GenericManagerImpl<User,Long> implements UserManager {

    @Autowired
    ApplicationServiceManager applicationServiceManager;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CipherUtils cipherUtils;
    @Autowired
    private PasswordUtils passwordUtils;
    @Autowired
    private TokenManager tokenManager;
    private long tokenValidMinutes = 60*24*60;

    public UserManagerImpl(GenericDao<User, Long> genericDao) {
        super(genericDao);
    }

    @Override
    public AuthenticateInfo authenticateUser(String email, String password, String serviceName) throws AuthenticationFailedException {
//        loading user
        User user = userDao.loadUserByUserEmail(email);
        if(user == null)
            throw new AuthenticationFailedException("User Not Found");
        String hashPassword = passwordUtils.hashPassword(user.getSalt(), password);
        if(hashPassword == null || !hashPassword.equals(user.getHashedPassword())){
            throw new AuthenticationFailedException("Wrong Password");
        }
//        getting service info
        ApplicationService applicationService =null;
        if(serviceName != null)
            applicationService = applicationServiceManager.loadServiceByName(serviceName);
        else
            applicationService = applicationServiceManager.loadDefaultService();
//        input is ok creating response
        AuthenticateInfo ret = new AuthenticateInfo();
        Set<String> rolesSet = new HashSet();
        for (Role role : user.getRoles()) {
            rolesSet.add(role.getRoleName());
            ret.getRoles().add(role.getRoleName());
        }

        ret.setEmail(user.getEmail());
        ret.setExpireDate(getExpireDateForNewlyCreatedToken().getTime());
        TokenData tokenData = new TokenData(user.getId().toString(), rolesSet, new Date(), getExpireDateForNewlyCreatedToken(),null);
        String accessToken = tokenManager.createTokenString(tokenData, applicationService);
        ret.setAccessToken(accessToken);
        ret.setUserId(user.getId().toString());
        return ret;
    }

    private Date getExpireDateForNewlyCreatedToken() {
        Date date = new Date();
        date.setTime(date.getTime()+tokenValidMinutes*60*1000);
        return date;
    }
    @Override
    public User createUser(String email, String firstName, String lastName, String password, List<Role> roles) throws UserCreationException, InvalidParameterException {
// validating user info
        validateEmail(email);
        firstName = validateName(firstName);
        lastName = validateName(lastName);
        validatePassword(password, email);
// check for existing user
        User registered = userDao.loadUserByUserEmail(email);
        if(registered!=null)
            throw new UserCreationException("User Exists");
// creating user
        User user = new User();
        user.setEmail(email);
        user.setFirstnam(firstName);
        user.setLastname(lastName);
        String salt = RandomStringUtils.random(64,true,false);
        user.setHashedPassword(passwordUtils.hashPassword(salt,password));
        user.setSalt(salt);
        user.setCreationDate(new Date());
        if(roles!=null)
            user.setRoles(new HashSet<Role>(roles));
        user = userDao.save(user);
        return user;
    }

    private void validatePassword(String password, String email) throws InvalidParameterException {
        if(password.isEmpty())
            throw new InvalidParameterException("Password Is Not Good");
    }

    private String validateName(String name) throws InvalidParameterException {
        name = name.trim();
        if(name.isEmpty())
            throw new InvalidParameterException("Empty first name/last name");
        return name;

    }

    private void validateEmail(String email) throws InvalidParameterException {
        if(!EmailValidator.getInstance().isValid(email))
            throw new InvalidParameterException("Invalid Email");
    }
}
