package ir.hassannasr.majles.domain.candid;

import core.dao.GenericDao;
import core.service.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hassan on 02/11/2015.
 */
public class CandidManagerImpl extends GenericManagerImpl<Candid, Long> implements CandidManager {

    @Autowired
    private CandidDao candidDao;

    public CandidManagerImpl(GenericDao<Candid, Long> genericDao) {
        super(genericDao);
    }


}
