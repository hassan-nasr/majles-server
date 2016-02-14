package ir.hassannasr.majles.crowling;

import ir.hassannasr.majles.domain.candid.Candid;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by hassan on 31/01/2016.
 */
public class IranRayCrowler {
    static String input = "./output/candidates4.json";

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        final List<IranRayCandid> candids = mapper.readValue(new File(input), new TypeReference<List<IranRayCandid>>() {
        });
        Map<Long, Long> hozehMap = readHozehMap("./output/IranRayHozehIdMap.txt");

        for (IranRayCandid ircandid : candids) {
            Candid candid = new Candid();
            candid.setId(ircandid.id);

        }
    }

    private static Map<Long, Long> readHozehMap(String s) throws FileNotFoundException {
        Scanner cin = new Scanner(new File(s));
        Map<Long, Long> ret = new HashMap<>();

        while (cin.hasNextLine()) {
            final long value = cin.nextLong();
            cin.skip("\\s+:\\s+");
            final long key = cin.nextLong();
            ret.put(key, value);
        }
        return ret;

    }
}
