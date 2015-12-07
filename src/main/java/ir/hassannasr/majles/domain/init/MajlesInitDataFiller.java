package ir.hassannasr.majles.domain.init;


import com.idehgostar.makhsan.core.services.ApplicationService;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by Abolfazl on 8/17/2015.
 */
public class MajlesInitDataFiller extends BaseDataFiller {


    public MajlesInitDataFiller(boolean isTestMode) {
        super(true, getDataFillerHelper(isTestMode));
    }

    private static MajlesDataFillerHelper getDataFillerHelper(boolean isTestMode) {
        MajlesDataFillerHelper majlesDataFillerHelper = new MajlesDataFillerHelper(isTestMode);
        return majlesDataFillerHelper;
    }


    public static void main(String[] args) throws Exception {

        boolean isTestMode = Boolean.valueOf(args[0]);
        if (args.length > 1) {
            if (args[1].equals("create")) {
                MajlesInitDataFiller createDbFiller = new MajlesInitDataFiller(Boolean.valueOf(isTestMode));
                EntityManager entityManager = createDbFiller.createEntityManager();
                entityManager.close();
            } else if (args[1].equals("drop")) {
                MajlesInitDataFiller dropDbFiller = new MajlesInitDataFiller(Boolean.valueOf(isTestMode));
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

        MajlesInitDataFiller initDataFiller = new MajlesInitDataFiller(Boolean.valueOf(isTestMode));
        initDataFiller.fillAll();
    }



    public void fillAll() throws IOException {
        System.out.println("create database and inserting initial data...");


        EntityManager em = createEntityManager();
        em.getTransaction().begin();
//      FILL DATA HERE
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
