package dbfill;


import com.idehgostar.makhsan.domain.init.MakhsanDataFillerHelper;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MakhsanTestDataFiller extends com.idehgostar.makhsan.domain.init.BaseDataFiller {


    public MakhsanTestDataFiller(boolean isTestMode) {
        super(true, getDataFillerHelper(isTestMode));
    }

    private static MakhsanDataFillerHelper getDataFillerHelper(boolean isTestMode) {
        MakhsanDataFillerHelper makhsanDataFillerHelper = new MakhsanDataFillerHelper(isTestMode);
        return makhsanDataFillerHelper;
    }

    public static void main(String[] args) throws Exception {


        MakhsanTestDataFiller initDataFiller = new MakhsanTestDataFiller(Boolean.valueOf(args[0]));
        initDataFiller.fillAll();
    }


    private void fillAll() throws IOException, URISyntaxException, SQLException {
        System.out.println("inserting test data ...");


        EntityManager entityManager = createEntityManager();
        entityManager.getTransaction().begin();

        // INSERT OBJECT HERE

        entityManager.getTransaction().commit();
        entityManager.close();

        System.out.println("########### Done ###########");
    }


    private List<Map<String, String>> readJsonData(String fileName) throws IOException, URISyntaxException {

        String path = this.getClass().getResource("data/" + fileName + ".json").getPath();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        String content = new String(Files.readAllBytes(Paths.get(path)));
        List<Map<String, String>> data = new ArrayList<>();

        return mapper.readValue(content, data.getClass());


    }


}
