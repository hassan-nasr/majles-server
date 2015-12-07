package ir.hassannasr.majles.domain.candid;

import ir.hassannasr.majles.domain.base.BaseObject;

import javax.persistence.*;

/**
 * Created by hassan on 07/12/2015.
 */
@Entity
public class Candid extends BaseObject {
    Long id;
    String firstName;
    String lastName;
    Integer age;
    String nid;
    String hozeh;
    String bornLocation;
    Degree degree;
    String studyFiled;
    String gerayesh;
    Integer dorehInMajles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Candid candid = (Candid) o;

        return !(id != null ? !id.equals(candid.id) : candid.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Condid{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getStudyFiled() {
        return studyFiled;
    }

    public void setStudyFiled(String studyFiled) {
        this.studyFiled = studyFiled;
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
}
