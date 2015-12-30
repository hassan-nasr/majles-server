package ir.hassannasr.majles.domain.user;

import core.dao.GenericDao;

import java.util.Map;

/**
 * Created by hassan on 02/11/2015.
 */

public interface PhoneConnectionDao extends GenericDao<PhoneConnection, Long> {

    Map<String, PhoneConnection> getConnectionsFrom(String phone);

    Map<String, PhoneConnection> getConnectionsTo(String phone);

    boolean isConnectionExist(String owner, String friend);
}
