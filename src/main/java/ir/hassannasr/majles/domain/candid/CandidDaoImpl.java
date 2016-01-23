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

    @Override
    public List<Candid> searchByDoreh(String dorehString, Boolean isMajles) {
        return entityManager.createNamedQuery("searchCandidByDoreh").setParameter("doreh","%"+dorehString+"%").setParameter("isMajles",isMajles).getResultList();
    }

    @Override
    public List<Candid> searchStatistic(String context, Long subHozehId) {
        return (List<Candid>) entityManager.createQuery("from Candid c where  c.subHozehObj=" + subHozehId + " order by c.endorseCount." + context + " desc")
                .setMaxResults(1000)
                .getResultList();
    }
}
