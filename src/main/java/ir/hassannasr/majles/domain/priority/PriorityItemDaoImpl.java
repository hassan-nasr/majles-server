package ir.hassannasr.majles.domain.priority;

import core.dao.GenericDaoImpl;
import ir.hassannasr.majles.domain.candid.Candid;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by hassan on 02/11/2015.
 */
public class PriorityItemDaoImpl extends GenericDaoImpl<PriorityItem, Long> implements PriorityItemDao {
    public PriorityItemDaoImpl() {
        super(PriorityItem.class);
    }

    @Override
    public PriorityItem findBy(Candid candid, PriorityType priorityType) {
        final Query query = entityManager.createNamedQuery("findWithCandidAndType")
                .setParameter("candid", candid)
                .setParameter("type", priorityType);
        query.setMaxResults(1);
        final List resultList = query.getResultList();
        if (!(resultList.size() == 0))
            return (PriorityItem) resultList.get(0);
        return null;
    }
}
