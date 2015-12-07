package com.idehgostar.makhsan.domain.role;

import core.service.GenericManager;

/**
 * Created by hassan on 02/11/2015.
 */
public interface RoleManager extends GenericManager<Role,Long> {
    public Role getRoleByName(String role);

    RoleDao getRoleDao();
}
