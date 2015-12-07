package com.idehgostar.makhsan.domain.singleaccesstoken;


import com.idehgostar.makhsan.domain.base.BaseObject;
import com.idehgostar.makhsan.domain.user.User;
import org.hibernate.annotations.Index;

import javax.persistence.*;
import java.util.Date;

/**
 * a token to use for single access of a user (without password), activation,  and registration attempt
 * Created by hasan on 9/12/14.
 */
@Entity
@NamedQueries({
        @NamedQuery(name="loadValidAccessToken", query = "From SingleAccessToken s where s.token = :token and s.expirationDate >= :expDate")
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class SingleAccessToken extends BaseObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Index(name="singleaccesstoken.token")
    String token;

    Integer used;

    @Enumerated(EnumType.STRING)
    Type type;

    @ManyToOne
    User user;

    @Temporal(TemporalType.TIMESTAMP)
    Date expirationDate=new Date();

    Integer allowedUseTimes=1;

    String data;


    public SingleAccessToken() {
    }

    public SingleAccessToken(User user, String token, Type type, Integer validMinutes, Integer allowedUseTimes, String data) {
        this.user = user;
        this.token = token;
        this.type = type;
        this.allowedUseTimes = allowedUseTimes;
        this.data = data;
        expirationDate = new Date(new Date().getTime()+validMinutes*60*1000);
        used=0;
    }

    public SingleAccessToken(User user, String token, Type type, Integer validMinutes){
        this(user, token, type, validMinutes, 1,null);
    }

    /**
     * @return extra data in this token
     */
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return token string that should be given to client
     */
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the number of time this token is used so far
     */
    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    /**
     * type of this token
     * @return
     */
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    /**
     * not available for registration
     * @return  owner user for this token
     */
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SingleAccessToken)) return false;

        SingleAccessToken that = (SingleAccessToken) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "singleaccesstoken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", used=" + used +
                ", action='" + type + '\'' +
                ", user=" + user +
                ", expirationDate=" + expirationDate +
                '}';
    }

    /**
     * uses the token for another time ( increase use time )
     * @return false if exceeds use times and true other wise
     */
    public boolean use() {
        if(used==allowedUseTimes)
            return false;
        used++;
        return true;
    }

    public enum Type {
        Validate, ResetPassword , Register
    }
}
