package ir.hassannasr.majles.domain.user;

import ir.hassannasr.majles.domain.base.BaseObject;

import javax.persistence.*;

/**
 * Created by hassan on 30/12/2015.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "isConnectionExist", query = "from PhoneConnection p where p.ownerPhone=:ownerPhone and p.friendPhone=:friendPhone"),
        @NamedQuery(name = "getPhoneConnectionsFrom", query = "from PhoneConnection p where p.ownerPhone=:ownerPhone "),
        @NamedQuery(name = "getPhoneConnectionsTo", query = "from PhoneConnection p where p.friendPhone=:friendPhone "),
        @NamedQuery(name = "removeFriend", query = "delete from PhoneConnection p where p.friendPhone=:friendPhone  and p.ownerPhone=:ownerPhone")

})
public class PhoneConnection extends BaseObject {
    Long id;
    String ownerPhone;
    String friendPhone;

    public PhoneConnection(String ownerPhone, String friendPhone) {

        this.ownerPhone = ownerPhone;

        this.friendPhone = new Normalizer().normalizePhone(friendPhone);
    }

    public PhoneConnection() {
    }

    public String getFriendPhone() {
        return friendPhone;
    }

    public void setFriendPhone(String friendPhone) {
        this.friendPhone = friendPhone;
    }

    @Override
    @GeneratedValue
    @Id
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneConnection that = (PhoneConnection) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (ownerPhone != null ? !ownerPhone.equals(that.ownerPhone) : that.ownerPhone != null) return false;
        return !(friendPhone != null ? !friendPhone.equals(that.friendPhone) : that.friendPhone != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ownerPhone != null ? ownerPhone.hashCode() : 0);
        result = 31 * result + (friendPhone != null ? friendPhone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PhoneConnection{" +
                "friendPhone='" + friendPhone + '\'' +
                ", id=" + id +
                ", ownerPhone='" + ownerPhone + '\'' +
                '}';
    }
}
