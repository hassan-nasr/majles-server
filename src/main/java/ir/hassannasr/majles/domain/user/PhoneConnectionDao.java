package ir.hassannasr.majles.domain.user;

import core.dao.GenericDao;

import java.util.Map;
import java.util.Set;

/**
 * Created by hassan on 02/11/2015.
 */

public interface PhoneConnectionDao extends GenericDao<PhoneConnection, Long> {

    void removeFriend(User user, String phone);

    Map<String, PhoneConnection> getConnectionsFrom(String phone);

    Map<String, PhoneConnection> getConnectionsTo(String phone);

    PhoneConnection getConnection(String owner, String friend);

    Set<User> getMyConnections(User user);

    void addFriend(User user, String phone);
}
