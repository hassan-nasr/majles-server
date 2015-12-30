package ir.hassannasr.majles.domain.user;

import core.dao.GenericDaoImpl;

import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hassan on 02/11/2015.
 */
public class PhoneConnectionDaoImpl extends GenericDaoImpl<PhoneConnection, Long> implements PhoneConnectionDao {
    public PhoneConnectionDaoImpl() {
        super(PhoneConnection.class);
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
    public boolean isConnectionExist(String owner, String friend) {
        final List resultList = entityManager.createNamedQuery("isConnectionExist")
                .setParameter("ownerPhone", owner)
                .setParameter("friendPhone", friend)
                .getResultList();
        return !resultList.isEmpty();
    }
}
