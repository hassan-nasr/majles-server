package ir.hassannasr.majles.domain.idea;

import ir.hassannasr.majles.domain.base.BaseObject;
import ir.hassannasr.majles.domain.candid.Candid;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by hassan on 08/12/2015.
 */
@Entity
public class Idea extends BaseObject {
    Candid candid;
    Long id;
    String title;
    String description;

    @ManyToOne()
    public Candid getCandid() {
        return candid;
    }

    public void setCandid(Candid candid) {
        this.candid = candid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    @Id
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Idea{" +
                "candid=" + candid +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Idea idea = (Idea) o;

        return !(id != null ? !id.equals(idea.id) : idea.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
