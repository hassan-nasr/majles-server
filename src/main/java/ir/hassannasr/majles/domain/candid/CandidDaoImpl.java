package ir.hassannasr.majles.domain.candid;

import core.dao.GenericDaoImpl;

import java.util.List;

/**
 * Created by hassan on 02/11/2015.
 */
public class CandidDaoImpl extends GenericDaoImpl<Candid, Long> implements CandidDao {
    public CandidDaoImpl() {
        super(Candid.class);
    }

    @Override
    public List<Candid> findCandidWithUserIds(List<Long> userIds) {
        return entityManager.createNamedQuery("findCandidWithUserIds").setParameter("userIds",userIds).getResultList();
    }
}
