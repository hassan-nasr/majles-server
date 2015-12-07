package com.idehgostar.makhsan.domain.user;

import core.dao.GenericDaoImpl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Created by hassan on 02/11/2015.
 */
public class UserDaoImpl extends GenericDaoImpl<User,Long> implements UserDao{
    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User loadUserByUserEmail(String email) {
        Query q = entityManager.createNamedQuery("loadUserWithEmail");
        q.setParameter("email",email);
        try {
            return (User) q.getSingleResult();
        }catch(NoResultException e) {
            return null;
        }
    }
}
