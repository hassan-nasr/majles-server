package ir.hassannasr.majles.domain.user;

import core.dao.GenericDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Query;
import java.util.*;

/**
 * Created by hassan on 02/11/2015.
 */
public class PhoneConnectionDaoImpl extends GenericDaoImpl<PhoneConnection, Long> implements PhoneConnectionDao {
    @Autowired
    UserManager userManager;
    public PhoneConnectionDaoImpl() {
        super(PhoneConnection.class);
    }

    public Set<User> getMyConnections(User user) {
        final Map<String, PhoneConnection> toConnections = getConnectionsTo(user.getPhone());
        final Map<String, PhoneConnection> fromConnectins = getConnectionsFrom(user.getPhone());
        final List<User> verified = userManager.getVerifiedWithPhoneNumber(fromConnectins.keySet());
        Set<String> s1 = toConnections.keySet();
        Set<String> s2 = fromConnectins.keySet();
        if (s1.size() > s2.size()) {
            Set<String> temp = s1;
            s1 = s2;
            s2 = temp;
        }
        Set<String> intersect = new HashSet<>();
        for (String s : s1) {
            if (s2.contains(s))
                intersect.add(s);
        }
        intersect.remove(user.getPhone());
        List<User> friends = userManager.getWithPhoneNumber(intersect);
        Set<User> ret = new HashSet<>(friends);
        ret.addAll(verified);
        return ret;
    }

    @Override
    public void addFriend(User user, String phone) {
        PhoneConnection currentCunnection = getConnection(user.getPhone(), phone);
        if (currentCunnection == null) {
            save(new PhoneConnection(user.getPhone(), phone));
        }
    }

    @Override
    public void removeFriend(User user, String phone) {
        entityManager.createNamedQuery("removeFriend")
                .setParameter("ownerPhone", user.getPhone())
                .setParameter("friendPhone", phone)
                .executeUpdate();
    }


    @Override
    public Map<String, PhoneConnection> getConnectionsFrom(String phone) {
        final Query query = entityManager.createNamedQuery("getPhoneConnectionsFrom").setParameter("ownerPhone", phone);
        final List<PhoneConnection> result = query.getResultList();
        Map<String, PhoneConnection> ret = new HashMap<>();
        for (PhoneConnection phoneConnection : result) {
            ret.put(phoneConnection.friendPhone, phoneConnection);
        }
        return ret;
    }

    @Override
    public Map<String, PhoneConnection> getConnectionsTo(String phone) {
        final Query query = entityManager.createNamedQuery("getPhoneConnectionsTo").setParameter("friendPhone", phone);
        final List<PhoneConnection> result = query.getResultList();
        Map<String, PhoneConnection> ret = new HashMap<>();
        for (PhoneConnection phoneConnection : result) {
            ret.put(phoneConnection.ownerPhone, phoneConnection);
        }
        return ret;
    }

    @Override
    public PhoneConnection getConnection(String owner, String friend) {
        final List resultList = entityManager.createNamedQuery("isConnectionExist")
                .setParameter("ownerPhone", owner)
                .setParameter("friendPhone", friend)
                .getResultList();
        if (resultList.size() == 0)
            return null;
        return (PhoneConnection) resultList.get(0);
    }
}
