package com.idehgostar.makhsan.domain.permission;

import com.idehgostar.makhsan.domain.role.Role;
import core.service.GenericManagerImpl;

/**
 * Created by hassan on 02/11/2015.
 */
public class PermissionManagerImpl extends GenericManagerImpl<Role, Long> implements PermissionManager {

    public static Permission ASSIGN_ROLE;
    public static Permission CREATE_USER;

    public PermissionManagerImpl(PermissionDao dao) {
        super(dao);
        ASSIGN_ROLE = dao.loadByPermissionName("Assign_Role");
        CREATE_USER = dao.loadByPermissionName("Create_User");
    }
}
