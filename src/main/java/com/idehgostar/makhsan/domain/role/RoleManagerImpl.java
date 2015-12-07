package com.idehgostar.makhsan.domain.role;

import core.service.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hassan on 02/11/2015.
 */
public class RoleManagerImpl extends GenericManagerImpl<Role,Long> implements RoleManager {

    public static Role Makhsan_Admin;
    @Autowired
    RoleDao roleDao;
    private Map<String, Role> roleCache = new HashMap<>();

    public RoleManagerImpl(RoleDao dao) {
        super(dao);
        Makhsan_Admin = dao.loadByRoleName("Makhsan_Admin");
    }

    @Override
    public Role getRoleByName(String role) {
        Role ret = roleCache.get(role);
        if(ret==null) {
            ret = roleDao.loadByRoleName(role);
            if(ret != null)
                roleCache.put(role,ret);
        }
        return ret;
    }

    @Override
    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
}
