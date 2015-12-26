package ir.hassannasr.majles.domain.candid;

import core.dao.GenericDao;
import core.service.GenericManagerImpl;
import ir.hassannasr.majles.domain.hozeh.SubHozeh;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by hassan on 02/11/2015.
 */
public class CandidManagerImpl extends GenericManagerImpl<Candid, Long> implements CandidManager {

    @Autowired
    private CandidDao candidDao;
    @Autowired
    private HozehDao hozehDao;

    public CandidManagerImpl(GenericDao<Candid, Long> genericDao) {
        super(genericDao);
    }


    @Override
    public List<SubHozeh> getAllHozeh() {
        return hozehDao.getAll();
    }
}
