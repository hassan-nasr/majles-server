package com.idehgostar.makhsan.domain.user;

import com.idehgostar.makhsan.domain.base.BaseObject;
import com.idehgostar.makhsan.domain.role.Role;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by hassan on 02/11/2015.
 */
@Entity
@NamedQueries({
@NamedQuery(name="loadUserWithEmail", query = "from User u where u.email=:email")
        })
public class User extends BaseObject {
    private Long id;
    private String email;
    private String firstnam;
    private String lastname;
    private String hashedPassword;
    private Date creationDate;
    private Set<Role> roles;
    private String salt;

    public User() {
    }

    /**
     *
     * @return user email
     */
    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return user firstname
     */
    public String getFirstnam() {
        return firstnam;
    }

    public void setFirstnam(String firstnam) {
        this.firstnam = firstnam;
    }

    /**
     *
     * @return user last name
     */
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     *
     * @return hashed password for the user
     */
    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    /**
     *
     * @return user creation date
     */
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return user roles
     */
    @ManyToMany
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstnam='" + firstnam + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
