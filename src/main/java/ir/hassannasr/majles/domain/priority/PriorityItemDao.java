package ir.hassannasr.majles.domain.priority;

import core.dao.GenericDao;
import ir.hassannasr.majles.domain.candid.Candid;

/**
 * Created by hassan on 02/11/2015.
 */

public interface PriorityItemDao extends GenericDao<PriorityItem, Long> {
    PriorityItem findBy(Candid candid, PriorityType priorityType);
}
