package crowling;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hassan on 09/12/2015.
 */
public class Member {
    String name;
    Long member_id;
    String birthPlace;
    Integer birthYear;
    List<String> languages = new ArrayList<>();
    List<String> resume = new ArrayList<>();
    List<String> study = new ArrayList<>();
    List<DorehHistory> dorehHistories = new ArrayList<>();

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public List<DorehHistory> getDorehHistories() {
        return dorehHistories;
    }

    public void setDorehHistories(List<DorehHistory> dorehHistories) {
        this.dorehHistories = dorehHistories;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Long getMember_id() {
        return member_id;
    }

    public void setMember_id(Long member_id) {
        this.member_id = member_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getResume() {
        return resume;
    }

    public void setResume(List<String> resume) {
        this.resume = resume;
    }

    public List<String> getStudy() {
        return study;
    }

    public void setStudy(List<String> study) {
        this.study = study;
    }
}
