package com.idehgostar.makhsan.domain.permission;

import com.idehgostar.makhsan.domain.role.Role;
import core.dao.GenericDao;

/**
 * Created by hassan on 02/11/2015.
 */
public interface PermissionDao extends GenericDao<Role, Long> {
    /**
     * loads a role with its name
     *
     * @param roleName
     * @return
     */
    Permission loadByPermissionName(String roleName);
}
