package ir.hassannasr.majles.services.admin;

import ir.hassannasr.majles.crowling.DorehHistory;
import ir.hassannasr.majles.crowling.Member;
import ir.hassannasr.majles.domain.candid.Candid;
import ir.hassannasr.majles.domain.candid.CandidManager;
import ir.hassannasr.majles.domain.candid.DorehHistoryEntity;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hassan on 15/12/2015.
 */
@Service
@Path("/admin")
public class ImportWS {

    @Autowired
    CandidManager candidManager;

    @Path("/importMember")
    public String importMember() throws IOException {
        InputStream memberInfo = new FileInputStream("D:\\Projects\\Majles\\server\\output\\memberInfo.json");
        ObjectMapper mapper = new ObjectMapper();
        final List<Member> members = mapper.readValue(memberInfo, new TypeReference<List<Member>>() {
        });
        for (Member member : members) {
            Candid candid = new Candid();
            if (member.getName().startsWith("آقای") || member.getName().startsWith("خانم"))
                member.setName(member.getName().substring(4).trim());
            candid.setName(member.getName());
            candid.setImageId(member.getImageFileName());
            if (member.getDorehHistories().size() > 0) {
                candid.setHozeh(member.getDorehHistories().get(0).getHozeh());
                candid.setSubHozeh(member.getDorehHistories().get(0).getSubHozeh());
            }
            if (member.getBirthYear() != null)
                candid.setAge(1395 - member.getBirthYear());
            candid.setBornLocation(member.getBirthPlace());
            candid.setDorehInMajles(member.getDorehHistories().size());
            StringBuilder study = new StringBuilder();
            for (String s : member.getStudy()) {
                study.append(s).append("\n");
            }
            StringBuilder resume = new StringBuilder();
            for (String s : member.getResume()) {
                resume.append(s).append("\n");
            }
            candid.setStudyField(study.toString());
            candid.setResume(resume.toString());
            candid.setBio(member.getBio());
            candid.setWebsite(member.getWebsite());
            candid.setLanguages(member.getLanguages().stream().collect(Collectors.joining(",")));

            for (DorehHistory dorehHistory : member.getDorehHistories()) {
                DorehHistoryEntity dhe = new DorehHistoryEntity();
                dhe.setVoteCount(dorehHistory.getVotes());
                dhe.setVotePercent(dorehHistory.getPrecent());
                dhe.setHozeh(dorehHistory.getHozeh());
                dhe.setSubHozeh(dorehHistory.getSubHozeh());
                dhe.setDoreh(dorehHistory.getDoreh());
                dhe.setCommisions(dorehHistory.getCommisions().stream().collect(Collectors.joining("\n")));
                dhe.setLegalDrafts(dorehHistory.getLegalDraft().stream().collect(Collectors.joining("\n")));
                candid.getDorehHistoryEntities().add(dhe);
            }
            System.err.println(candid.getName());
            candidManager.save(candid);
        }
        return null;
    }
}
