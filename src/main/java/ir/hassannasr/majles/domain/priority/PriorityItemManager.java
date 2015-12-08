package ir.hassannasr.majles.domain.priority;

import core.service.GenericManager;
import ir.hassannasr.majles.domain.exceptoin.IncompleteDataException;

/**
 * Created by hassan on 02/11/2015.
 */
public interface PriorityItemManager extends GenericManager<PriorityItem, Long> {

    public PriorityItem add(PriorityItem priorityItem) throws DuplicateException, IncompleteDataException;
}
