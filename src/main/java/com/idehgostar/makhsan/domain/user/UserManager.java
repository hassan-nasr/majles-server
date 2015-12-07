package com.idehgostar.makhsan.domain.user;

import com.idehgostar.makhsan.domain.exceptoin.InvalidParameterException;
import com.idehgostar.makhsan.domain.role.Role;
import com.idehgostar.makhsan.domain.user.Auth.AuthenticateInfo;
import com.idehgostar.makhsan.domain.user.Auth.AuthenticationFailedException;
import core.service.GenericManager;

import java.util.List;

/**
 * Created by hassan on 02/11/2015.
 */
public interface UserManager extends GenericManager<User,Long> {
    public AuthenticateInfo authenticateUser(String email,String password, String serviceName) throws AuthenticationFailedException;

    /**
     * creates a user
     * @param email
     * @param firstName
     * @param lastName
     * @param password
     * @param roles
     * @return
     * @throws UserCreationException
     * @throws InvalidParameterException
     */
    public User createUser(String email, String firstName, String lastName, String password, List<Role> roles) throws UserCreationException, InvalidParameterException;
}
