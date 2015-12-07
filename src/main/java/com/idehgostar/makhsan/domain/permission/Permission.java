package com.idehgostar.makhsan.domain.permission;

import com.idehgostar.makhsan.domain.base.BaseObject;

import javax.persistence.*;

/**
 * permissions of each Role
 * Created by hassan on 08/11/2015.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "loadPermissionByName", query = "from Permission p where p.name=:permissionName")
})
public class Permission extends BaseObject {
    public static String ASSIGN_ROLE = "Assign_Role";
    public static String CREATE_USER = "Create_User";
    Long id;
    String name;
    String description;


    public Permission(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Permission() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
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
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Permission that = (Permission) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
