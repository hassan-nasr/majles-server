package com.idehgostar.makhsan.domain.role;

import core.dao.GenericDao;

/**
 * Created by hassan on 02/11/2015.
 */
public interface RoleDao extends GenericDao<Role,Long> {
    /**
     * loads a role with its name
     * @param roleName
     * @return
     */
    Role loadByRoleName(String roleName);
}
