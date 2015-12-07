package com.idehgostar.makhsan.domain.init;


import core.base.constants.Constants;
import core.user.user.User;
import core.user.user.UserProperties;
import dbfill.DataFillerHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: abolfazl
 * Date: 10/18/13
 * Time: 9:34 AM
 */
public abstract class BaseDataFiller {

    protected final DataFillerHelper dataFillerHelper;
    private final PasswordEncoder passwordEncoder;
    private final EntityManagerFactory entityManagerFactory;
    private final DataSource dataSource;
    protected ApplicationContext applicationContext;
    /**
     * <p>It is loaded from db at the very beginning. It should not be empty in any case other than completely empty db. In this case -1 is inserted
     * before going on.</p>
     * <p>When inserting new data, a version is provided with the new data. If the version is larger than db version, then update is done otherwise ignored!
     * If no version is provided, 0 is assumed which works only on completely empty databases that have version -1.</p>
     * <p>Just before the filler finishes its job, it updated the version in db to <code>getVersionOfFiller</code> so the next time the filler is run,
     * only new data with version higher than this version are inserted </p>
     */
    protected int fillerVersionOfDb;

    public BaseDataFiller(boolean initMode, DataFillerHelper dataFillerHelper) {
        this.dataFillerHelper = dataFillerHelper;
        Constants.IS_INIT_DATA_FILLER_MODE = initMode;

        List<String> xmlsL = new ArrayList<String>();

        xmlsL.addAll(dataFillerHelper.getMoreApplicationContextList());

        this.applicationContext = new ClassPathXmlApplicationContext(xmlsL.toArray(new String[xmlsL.size()]));


        entityManagerFactory = applicationContext.getBean("entityManagerFactory", EntityManagerFactory.class);
        dataSource = applicationContext.getBean("dataSource", DataSource.class);
        passwordEncoder = applicationContext.getBean("passwordEncoder", PasswordEncoder.class);

        EntityManager em = createEntityManager();

        List<FillerVersion> fvl = em.createQuery("from " + FillerVersion.class.getName()).getResultList();
        if (fvl.size() > 0) {
            fillerVersionOfDb = fvl.get(0).getVer();
        } else {
            fillerVersionOfDb = -1;
            FillerVersion fv = new FillerVersion(fillerVersionOfDb);
            em.merge(fv);
        }

        em.close();
    }


    public EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    protected void addSimpleSystemConfig(EntityManager entityManager, String module, String name, String value, String type, String regex) {
        /*SystemConfig systemConfig = new SystemConfig();
        systemConfig.setModule(module);
        systemConfig.setName(name);
        systemConfig.setValue(value);
        systemConfig.setType(type);
        systemConfig.setRegex(regex);
        mergeAndFlush(entityManager, systemConfig);*/
    }

    protected <T> T mergeAndFlush(EntityManager entityManager, T object) {
        return mergeAndFlush(entityManager, object, 0);
    }

    protected <T> T mergeAndFlush(EntityManager entityManager, T object, int newDataVersion) {
        if (newDataVersion > fillerVersionOfDb) {
            object = entityManager.merge(object);
            entityManager.flush();
            return object;
        } else {
            return object;
        }
    }

    protected User createUserPropertiesForUser(EntityManager entityManager, User user, UserProperties props) {
        props.setUser(user);
        props = entityManager.merge(props);

        List<UserProperties> propList = new ArrayList<UserProperties>();
        propList.add(props);
        user.setUserPropertiesList(propList);
        user = entityManager.merge(user);

        return user;
    }

    protected String getDbName() throws IOException, URISyntaxException {
        String dbName = "NOTFOUNDDB";

        URL url = this.getClass().getResource("/jdbc.properties");
        BufferedReader f = new BufferedReader(new FileReader(new File(url.toURI())));
        String l = f.readLine();
        while (l != null) {
            if (l.trim().length() > 0) {
                String[] p = l.split("=");
                if ("url".equals(p[0])) {
                    int edIdx = p[1].indexOf('?');
                    int stIdx = p[1].substring(0, edIdx).lastIndexOf('/');
                    dbName = p[1].substring(stIdx + 1, edIdx);
                    break;
                }
            }
            l = f.readLine();
        }
        return dbName;
    }
}
