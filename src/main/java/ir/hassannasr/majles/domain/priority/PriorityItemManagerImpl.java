package ir.hassannasr.majles.domain.priority;

import core.service.GenericManagerImpl;
import ir.hassannasr.majles.domain.exceptoin.IncompleteDataException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hassan on 02/11/2015.
 */
public class PriorityItemManagerImpl extends GenericManagerImpl<PriorityItem, Long> implements PriorityItemManager {


    @Autowired
    PriorityItemDao priorityItemDao;

    public PriorityItemManagerImpl(PriorityItemDao genericDao) {
        super(genericDao);
    }


    @Override
    public PriorityItem add(PriorityItem priorityItem) throws DuplicateException, IncompleteDataException {
        if (priorityItem.getCandid() == null || priorityItem.getPriorityType() == null || priorityItem.getImportance() == null)
            throw new IncompleteDataException();
        final PriorityItem duplicate = priorityItemDao.findBy(priorityItem.getCandid(), priorityItem.getPriorityType());
        if (duplicate != null)
            throw new DuplicateException();
        return save(priorityItem);
    }
}
