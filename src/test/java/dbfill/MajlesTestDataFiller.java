package dbfill;


import ir.hassannasr.majles.domain.candid.Candid;
import ir.hassannasr.majles.domain.init.MajlesDataFillerHelper;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;


public class MajlesTestDataFiller extends ir.hassannasr.majles.domain.init.BaseDataFiller {


    public MajlesTestDataFiller(boolean isTestMode) {
        super(true, getDataFillerHelper(isTestMode));
    }

    private static MajlesDataFillerHelper getDataFillerHelper(boolean isTestMode) {
        MajlesDataFillerHelper majlesDataFillerHelper = new MajlesDataFillerHelper(isTestMode);
        return majlesDataFillerHelper;
    }

    public static void main(String[] args) throws Exception {


        MajlesTestDataFiller initDataFiller = new MajlesTestDataFiller(Boolean.valueOf(args[0]));
        initDataFiller.fillAll();
    }


    private void fillAll() throws IOException, URISyntaxException, SQLException {
        System.out.println("inserting test data ...");


        EntityManager entityManager = createEntityManager();
        entityManager.getTransaction().begin();

        insertSampleCandids(entityManager);

        entityManager.getTransaction().commit();
        entityManager.close();

        System.out.println("########### Done ###########");
    }

    private void insertSampleCandids(EntityManager entityManager) throws IOException, URISyntaxException {
        final List<Candid> candids = (List<Candid>) readJsonData("candid", new TypeReference<List<Candid>>() {
        });
        for (Candid candid : candids) {
            entityManager.merge(candid);
        }
    }


    private Object readJsonData(String fileName, TypeReference type) throws IOException, URISyntaxException {

        InputStream content = this.getClass().getResourceAsStream("data/" + fileName + ".json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        return mapper.readValue(content, type);


    }


}
