package com.idehgostar.makhsan.domain.permission;

import com.idehgostar.makhsan.domain.role.Role;
import core.dao.GenericDaoImpl;
import org.apache.log4j.Logger;

import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Created by hassan on 02/11/2015.
 */
public class PermissionDaoImpl extends GenericDaoImpl<Role, Long> implements PermissionDao {
    public PermissionDaoImpl() {
        super(Role.class);
    }

    @Override
    public Permission loadByPermissionName(String permissionName) {
        Query query = entityManager.createNamedQuery("loadPermissionByName");
        query.setParameter("permissionName", permissionName);
        try {
            return (Permission) query.getSingleResult();
        } catch (NoResultException e) {
            Logger.getLogger(this.getClass()).warn(e);
            return null;
        }
    }
}
