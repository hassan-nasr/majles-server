package ir.hassannasr.majles.services.response;

import ir.hassannasr.majles.domain.base.BaseObject;
import ir.hassannasr.majles.domain.candid.Candid;
import ir.hassannasr.majles.domain.candid.Degree;
import ir.hassannasr.majles.domain.candid.DorehHistoryEntity;
import ir.hassannasr.majles.domain.candid.EndorseCount;
import ir.hassannasr.majles.domain.idea.Idea;
import ir.hassannasr.majles.domain.priority.PriorityItem;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hassan on 07/12/2015.
 */
public class CandidView extends BaseObject {
    Long id;
    String name;
    Integer age;
    String nid;
    String hozeh;
    String bornLocation;
    Degree degree;
    String studyField;
    String gerayesh;
    Integer dorehInMajles;
    String imageId;
    String resume;
    String rss;

    String code;
    Long userId;
    List<PriorityItem> priorityItems;
    List<Idea> ideas;
    List<CandidSimpleView> supporters = new ArrayList<>();
    List<CandidEndorse> endorseList = new ArrayList<>();
    List<UserSimpleView> supportedUsers = new ArrayList<>();
    List<String> imageAlbumIds;
    String program;
    String slogan;
    String contactInfo;
    private String subHozeh;
    private String bio;
    private String website;
    private String languages;
    private List<DorehHistoryEntity> dorehHistoryEntities = new ArrayList<>();
    private Boolean isMajles = true;
    private Boolean isCandid = true;

    public CandidView(Candid candid) {
        this.id = candid.getId();
        this.name = candid.getName();
        this.age = candid.getAge();
        this.nid = candid.getNid();
        this.hozeh = candid.getHozeh();
        this.bornLocation = candid.getBornLocation();
        this.degree = candid.getDegree();
        this.studyField = candid.getStudyField();
        this.gerayesh = candid.getGerayesh();
        this.dorehInMajles = candid.getDorehInMajles();
        this.imageId = candid.getImageId();
        this.resume = candid.getResume();
        this.rss = candid.getRss();
        this.priorityItems = candid.getPriorityItems();
        this.ideas = candid.getIdeas();
        this.bio = candid.getBio();
        this.subHozeh = candid.getSubHozeh();
        this.website = candid.getWebsite();
        this.languages = candid.getLanguages();
        this.dorehHistoryEntities = candid.getDorehHistoryEntities();
        this.isCandid = candid.getCandid();
        this.isMajles = candid.getMajles();
        this.userId = candid.getUserId();
        this.code = candid.getCode();
        this.imageAlbumIds = candid.getImageAlbumIds();
        this.program = candid.getProgram();
        this.slogan = candid.getSlogan();
        this.contactInfo = candid.getContactInfo();
        for (DorehHistoryEntity dorehHistoryEntity : this.dorehHistoryEntities) {
            dorehHistoryEntity.setLegalDrafts("");
        }
        try {
            for (Field field : EndorseCount.class.getDeclaredFields()) {
                Long value = (Long) field.get(candid.getEndorseCount());
                if (value == null)
                    value = 0L;
                endorseList.add(new CandidEndorse(field.getName(), value));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<String> getImageAlbumIds() {
        return imageAlbumIds;
    }

    public void setImageAlbumIds(List<String> imageAlbumIds) {
        this.imageAlbumIds = imageAlbumIds;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public Boolean getCandid() {
        return isCandid;
    }

    public void setCandid(Boolean candid) {
        isCandid = candid;
    }

    public Boolean getMajles() {
        return isMajles;
    }

    public void setMajles(Boolean majles) {
        isMajles = majles;
    }

    public List<UserSimpleView> getSupportedUsers() {
        return supportedUsers;
    }

    public void setSupportedUsers(List<UserSimpleView> supportedUsers) {
        this.supportedUsers = supportedUsers;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<CandidEndorse> getEndorseList() {
        return endorseList;
    }

    public void setEndorseList(List<CandidEndorse> endorseList) {
        this.endorseList = endorseList;
    }

    public String getRss() {
        return rss;
    }

    public void setRss(String rss) {
        this.rss = rss;
    }

    public List<CandidSimpleView> getSupporters() {
        return supporters;
    }

    public void setSupporters(List<CandidSimpleView> supporters) {
        this.supporters = supporters;
    }

    @OneToMany(mappedBy = "candid")
    public List<Idea> getIdeas() {
        return ideas;
    }

    public void setIdeas(List<Idea> ideas) {
        this.ideas = ideas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CandidView candid = (CandidView) o;

        return !(id != null ? !id.equals(candid.id) : candid.id != null);

    }

    @ElementCollection(fetch = FetchType.EAGER)
    public List<DorehHistoryEntity> getDorehHistoryEntities() {
        return dorehHistoryEntities;
    }

    public void setDorehHistoryEntities(List<DorehHistoryEntity> dorehHIstoryEntities) {
        this.dorehHistoryEntities = dorehHIstoryEntities;
    }

    @OneToMany(mappedBy = "candid")
    public List<PriorityItem> getPriorityItems() {
        return priorityItems;
    }

    public void setPriorityItems(List<PriorityItem> priorityItems) {
        this.priorityItems = priorityItems;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Condid{" +
                "id=" + id +
                ", firstName='" + name + '\'' +
                ", nid='" + nid + '\'' +
                '}';
    }

    @Override
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String firstName) {
        this.name = firstName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getHozeh() {
        return hozeh;
    }

    public void setHozeh(String hozeh) {
        this.hozeh = hozeh;
    }

    public String getBornLocation() {
        return bornLocation;
    }

    public void setBornLocation(String bornLocation) {
        this.bornLocation = bornLocation;
    }

    @Enumerated(EnumType.STRING)
    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    @Lob
    public String getStudyField() {
        return studyField;
    }

    public void setStudyField(String studyField) {
        this.studyField = studyField;
    }

    public String getGerayesh() {
        return gerayesh;
    }

    public void setGerayesh(String gerayesh) {
        this.gerayesh = gerayesh;
    }

    public Integer getDorehInMajles() {
        return dorehInMajles;
    }

    public void setDorehInMajles(Integer dorehInMajles) {
        this.dorehInMajles = dorehInMajles;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageID) {
        this.imageId = imageID;
    }

    public String getSubHozeh() {
        return subHozeh;
    }

    public void setSubHozeh(String subHozeh) {
        this.subHozeh = subHozeh;
    }

    @Lob
    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    @Lob
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }


}
