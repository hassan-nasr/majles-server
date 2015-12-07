package com.idehgostar.makhsan.domain.role;

import com.idehgostar.makhsan.domain.base.BaseObject;
import com.idehgostar.makhsan.domain.permission.Permission;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * user roles
 * Created by hassan on 02/11/2015.
 */
@Entity
@NamedQueries({
        @NamedQuery(name="loadRoleByRoleName", query = "from Role r where r.roleName=:roleName")
})
public class Role extends BaseObject{
    Long id;
    String roleName;
    Set<Permission> permissions = new HashSet<>(    );
    public Role(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Role() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;
        if (roleName != null ? !roleName.equals(role.roleName) : role.roleName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }
}

