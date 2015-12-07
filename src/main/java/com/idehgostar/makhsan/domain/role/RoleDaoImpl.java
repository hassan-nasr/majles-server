package com.idehgostar.makhsan.domain.role;

import core.dao.GenericDaoImpl;
import org.apache.log4j.Logger;

import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Created by hassan on 02/11/2015.
 */
public class RoleDaoImpl extends GenericDaoImpl<Role,Long> implements RoleDao {
    public RoleDaoImpl() {
        super(Role.class);
    }

    @Override
    public Role loadByRoleName(String roleName) {
        Query query = entityManager.createNamedQuery("loadRoleByRoleName");
        query.setParameter("roleName",roleName);
        try {
            return (Role) query.getSingleResult();
        }catch (NoResultException e){
            Logger.getLogger(this.getClass()).warn(e);
            return null;
        }
    }
}
