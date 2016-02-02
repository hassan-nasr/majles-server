package ir.hassannasr.majles.domain.candid;

import ir.hassannasr.majles.domain.base.BaseObject;
import ir.hassannasr.majles.domain.hozeh.SubHozeh;
import ir.hassannasr.majles.domain.idea.Idea;
import ir.hassannasr.majles.domain.priority.PriorityItem;
import ir.hassannasr.majles.domain.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by hassan on 07/12/2015.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "findCandidWithUserIds", query = "from Candid c where c.userId in :userIds"),
        @NamedQuery(name = "getHozehById", query = "from SubHozeh c where c.id=:id"),
        @NamedQuery(name = "selectCandidWithHozehId", query = "select c from Candid c where c.subHozehObj.id=:hozehId"),
        @NamedQuery(name = "searchCandidByDoreh", query = "select c from Candid c left join c.dorehHistoryEntities e where e.doreh like :doreh and c.majles=:isMajles order by  c.name"),
        @NamedQuery(name = "countRay", query = "select c,(select count(*) from c.myRay) as co from Candid c where c.subHozehObj.id=:subHozehId order by co desc"),

})
public class Candid extends BaseObject {
    Long id;
    Boolean joined = false;
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
    EndorseCount endorseCount;
    List<PriorityItem> priorityItems;
    List<Idea> ideas;
    List<Long> supporterIds;
    List<String> imageAlbumIds;
    String program;
    String slogan;
    String contactInfo;
    Set<User> myRay;
    private String subHozeh;
    private String bio;
    private String website;
    private String languages;
    private SubHozeh subHozehObj;
    private List<DorehHistoryEntity> dorehHistoryEntities = new ArrayList<>();
    private Boolean isMajles = true;
    private Boolean isCandid = true;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "myChoseCandids")
//    @JoinTable(name="user_choiceCandid", joinColumns=@JoinColumn(name="mychosecandids_id"), inverseJoinColumns=@JoinColumn(name="User_id"))
    public Set<User> getMyRay() {
        return myRay;
    }

    public void setMyRay(Set<User> myRay) {
        this.myRay = myRay;
    }

    @Lob
    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Lob
    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    @Lob
    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogen) {
        this.slogan = slogen;
    }

    @ElementCollection
    public List<String> getImageAlbumIds() {
        return imageAlbumIds;
    }

    public void setImageAlbumIds(List<String> imageAlbumIds) {
        this.imageAlbumIds = imageAlbumIds;
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

    public Boolean getJoined() {
        return joined;
    }

    public void setJoined(Boolean joined) {
        this.joined = joined;
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

    @ManyToOne
    public SubHozeh getSubHozehObj() {
        return subHozehObj;
    }

    public void setSubHozehObj(SubHozeh subHozehObj) {
        this.subHozehObj = subHozehObj;
    }

    @Embedded
    public EndorseCount getEndorseCount() {
        if (endorseCount == null)
            endorseCount = new EndorseCount();
        return endorseCount;
    }

    public void setEndorseCount(EndorseCount endorseCount) {
        this.endorseCount = endorseCount;
    }

    public String getRss() {
        return rss;
    }

    public void setRss(String rss) {
        this.rss = rss;
    }

    @ElementCollection
    public List<Long> getSupporterIds() {
        return supporterIds;
    }

    public void setSupporterIds(List<Long> supproters) {
        this.supporterIds = supproters;
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

        Candid candid = (Candid) o;

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
