package ir.hassannasr.majles.domain.priority;

import ir.hassannasr.majles.domain.base.BaseObject;
import ir.hassannasr.majles.domain.candid.Candid;

import javax.persistence.*;

/**
 * Created by hassan on 08/12/2015.
 */


@NamedQueries({
        @NamedQuery(name = "findWithCandidAndType", query = "from PriorityItem p where p.candid=:candid and p.priorityType=:type")
})
@Entity
public class PriorityItem extends BaseObject {
    private Candid candid;
    private Long id;
    private PriorityType priorityType;
    private Integer importance = 1;
    private String description;

    @ManyToOne
    public Candid getCandid() {
        return candid;
    }

    public void setCandid(Candid candid) {
        this.candid = candid;
    }

    @Override
    @Id
    @GeneratedValue()
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    public PriorityType getPriorityType() {
        return priorityType;
    }

    public void setPriorityType(PriorityType priorityType) {
        this.priorityType = priorityType;
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        if (importance > 5)
            importance = 5;
        if (importance < 1)
            importance = 1;
        this.importance = importance;
    }

    @Override
    public String toString() {
        return "PriorityItem{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", priorityType=" + priorityType +
                ", importance=" + importance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PriorityItem that = (PriorityItem) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
