package com.idehgostar.makhsan.domain.init;


import com.idehgostar.makhsan.core.services.ApplicationService;
import com.idehgostar.makhsan.domain.permission.Permission;
import com.idehgostar.makhsan.domain.role.Role;
import com.idehgostar.makhsan.domain.user.PasswordUtils;
import com.idehgostar.makhsan.domain.user.User;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;

/**
 * Created by Abolfazl on 8/17/2015.
 */
public class MakhsanInitDataFiller extends BaseDataFiller {


    private final static String MAKHSAN_ADMIN_ROLE_NAME = "Makhsan_Admin";
    private final static String CREATE_USER_PERMISSION_NAME = "Create_User";
    private final static String ASSIGN_ROLE_PERMISSION_NAME = "Assign_Role";

    public MakhsanInitDataFiller(boolean isTestMode) {
        super(true, getDataFillerHelper(isTestMode));
    }

    private static MakhsanDataFillerHelper getDataFillerHelper(boolean isTestMode) {
        MakhsanDataFillerHelper makhsanDataFillerHelper = new MakhsanDataFillerHelper(isTestMode);
        return makhsanDataFillerHelper;
    }


    public static void main(String[] args) throws Exception {

        boolean isTestMode = Boolean.valueOf(args[0]);
        if (args.length > 1) {
            if (args[1].equals("create")) {
                MakhsanInitDataFiller createDbFiller = new MakhsanInitDataFiller(Boolean.valueOf(isTestMode));
                EntityManager entityManager = createDbFiller.createEntityManager();
                entityManager.close();
            } else if (args[1].equals("drop")) {
                MakhsanInitDataFiller dropDbFiller = new MakhsanInitDataFiller(Boolean.valueOf(isTestMode));
                Connection c;
                try {
                    c = dropDbFiller.getDataSource().getConnection();
                    Statement s = c.createStatement();
                    try {

                        System.out.println("dropping database... ");
                        if (!isTestMode) {
                            s.executeUpdate("DROP DATABASE " + dropDbFiller.getDbName());
                        } else {
                            s.executeUpdate("SHUTDOWN");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    s.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        MakhsanInitDataFiller initDataFiller = new MakhsanInitDataFiller(Boolean.valueOf(isTestMode));
        initDataFiller.fillAll();
    }


    private Role fillPermissionsAndRole(EntityManager entityManager) {
        // create Permissions
        Permission Create_User = new Permission(1l, CREATE_USER_PERMISSION_NAME, "User can create another user");
        Permission Assign_Role = new Permission(2l, ASSIGN_ROLE_PERMISSION_NAME, "User can create Add/Remove Others Roles");
        Create_User = mergeAndFlush(entityManager, Create_User);
        Assign_Role = mergeAndFlush(entityManager, Assign_Role);


        // crate Roles
        Role Makhsan_Admin = new Role(null, MAKHSAN_ADMIN_ROLE_NAME);
        Makhsan_Admin.getPermissions().add(Create_User);
        Makhsan_Admin.getPermissions().add(Assign_Role);
        Makhsan_Admin = mergeAndFlush(entityManager, Makhsan_Admin);
        return Makhsan_Admin;
    }

    private void fillAdminUser(EntityManager entityManager, Role adminRole) {
        User makhsanAdmin = new User();
        makhsanAdmin.setFirstnam("makhsan");
        makhsanAdmin.setLastname("admin");
        makhsanAdmin.setEmail("admin@makhsan.com");
        makhsanAdmin.setCreationDate(new Date());
        final String salt = "123ASDHNEqwert";
        makhsanAdmin.setSalt(salt);
        makhsanAdmin.setHashedPassword(new PasswordUtils().hashPassword(salt, "namespace1392"));
        makhsanAdmin.setRoles(new HashSet<Role>(Arrays.asList(adminRole)));
        mergeAndFlush(entityManager, makhsanAdmin);
    }

    public void fillAll() throws IOException {
        System.out.println("create database and inserting initial data...");


        EntityManager em = createEntityManager();
        em.getTransaction().begin();
//      FILL DATA HERE
        final Role adminRole = fillPermissionsAndRole(em);
        fillAdminUser(em, adminRole);
        fillDefaultService(em);

//      DONE
        em.getTransaction().commit();
        em.close();
        System.out.println("########### Done ###########");

    }

    private void fillDefaultService(EntityManager em) throws IOException {
        System.err.println("creating default service");
        Properties serviceProperties = new Properties();
        serviceProperties.load(this.getClass().getResourceAsStream("defaultService.properties"));
        ApplicationService s = new ApplicationService();
        s.setName(serviceProperties.getProperty("name", "defaultService"));
        s.setPublicKeyModule(serviceProperties.getProperty("publicKeyModule"));
        s.setPublicKeyExponent(serviceProperties.getProperty("publicKeyExponent"));
        s.setPrivateKeyModule(serviceProperties.getProperty("privateKeyModule"));
        s.setPrivateKeyExponent(serviceProperties.getProperty("privateKeyExponent"));
        mergeAndFlush(em, s);
    }


}
