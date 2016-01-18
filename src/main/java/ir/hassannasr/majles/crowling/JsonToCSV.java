package ir.hassannasr.majles.crowling;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * Created by hassan on 11/12/2015.
 */
public class JsonToCSV {
    public static void main(String[] args) throws IOException {
        InputStream memberInfo = new FileInputStream("./output/memberInfo.json");
        PrintStream out = new PrintStream("./output/memberInfo.csv");
        ObjectMapper mapper = new ObjectMapper();
        final List<Member> members = mapper.readValue(memberInfo, new TypeReference<List<Member>>() {
        });
        out.println("id,name,age,birthplace,dorehInMajles,doctor?,howzeh?,hozeh,subHozeh,votes,percent");
        for (Member member : members) {
            out.print(member.getMember_id() + ",");
            out.print(member.getName() + ",");
            out.print(((member.getBirthYear() != null) ? (1395 - member.getBirthYear()) : "") + ",");
            out.print(((member.getBirthPlace() != null) ? member.getBirthPlace() : "") + ",");
            out.print(((member.getDorehHistories() != null) ? member.getDorehHistories().size() : 0) + ",");
            out.print(isDoctor(member) + ",");
            out.print(isHowzeh(member) + ",");
            out.print(((member.getDorehHistories() != null && member.getDorehHistories().size() > 0) ? member.getDorehHistories().get(0).getHozeh() : "") + ",");
            out.print(((member.getDorehHistories() != null && member.getDorehHistories().size() > 0) ? member.getDorehHistories().get(0).getSubHozeh() : "") + ",");
            out.print(((member.getDorehHistories() != null && member.getDorehHistories().size() > 0) ? member.getDorehHistories().get(0).getVotes() : "") + ",");
            out.print(((member.getDorehHistories() != null && member.getDorehHistories().size() > 0) ? member.getDorehHistories().get(0).getPrecent() : "") + ",");
            out.println();
        }
        out.close();

    }

    private static boolean isHowzeh(Member member) {
        for (String s : member.getStudy()) {
            if (s.contains("حوزه"))
                return true;
        }
        return false;
    }

    private static boolean isDoctor(Member member) {
        for (String s : member.getStudy()) {
            if (s.contains("دكتر"))
                return true;
        }
        return false;
    }
}
