package ir.hassannasr.majles.domain.hozeh;

import ir.hassannasr.majles.domain.base.BaseObject;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by hassan on 26/12/2015.
 */
@Entity
public class SubHozeh extends BaseObject {

    Long id;
    String hozeh;
    String name;
    Integer capacity;
    Boolean majles;
    private Long price = 0L;

    public Boolean getMajles() {
        return majles;
    }

    public void setMajles(Boolean majles) {
        this.majles = majles;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getHozeh() {
        return hozeh;
    }

    public void setHozeh(String hozeh) {
        this.hozeh = hozeh;
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

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SubHozeh{" +
                "hozeh='" + hozeh + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubHozeh subHozeh = (SubHozeh) o;

        return !(id != null ? !id.equals(subHozeh.id) : subHozeh.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
