package ir.hassannasr.majles.domain.candid;

import core.dao.GenericDaoImpl;
import ir.hassannasr.majles.domain.hozeh.SubHozeh;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
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

    @Override
    public List<Pair<Long, Candid>> countRay(Long subHozehId) {
        List<Object[]> candids =
                (List<Object[]>) entityManager.createNamedQuery("countRay").setParameter("subHozehId", subHozehId)
                        .setMaxResults(1000)
                        .getResultList();
        List<Pair<Long, Candid>> ret = new ArrayList<>();

        for (Object[] candid : candids) {
            ret.add(new MutablePair<>((long) candid[1], (Candid) candid[0]));
        }
//        entityManager.create
        return ret;
    }

    @Override
    public SubHozeh getHozeh(Long id) {
        try {
            return (SubHozeh) entityManager.createNamedQuery("getHozehById").setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
