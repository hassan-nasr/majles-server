package com.idehgostar.makhsan.domain.user;

import core.dao.GenericDao;

/**
 * Created by hassan on 02/11/2015.
 */

public interface UserDao extends GenericDao<User,Long> {
    public User loadUserByUserEmail(String email);
}
