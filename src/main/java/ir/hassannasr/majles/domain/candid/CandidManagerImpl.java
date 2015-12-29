package ir.hassannasr.majles.domain.candid;

import core.dao.GenericDao;
import core.service.GenericManagerImpl;
import ir.hassannasr.majles.domain.hozeh.SubHozeh;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hassan on 02/11/2015.
 */
public class CandidManagerImpl extends GenericManagerImpl<Candid, Long> implements CandidManager {

    static Map<Long, Pair<Date, Candid>> cache = new HashMap<>();
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

    @Override
    public Candid getCached(Long candidId) {
        Pair<Date, Candid> ret = cache.get(candidId);
        if (ret != null && ret.getLeft().getTime() > (new Date().getTime() - 1000 * 60 * 60 * 10))
            return ret.getRight();
        ret = new ImmutablePair<>(new Date(), get(candidId));
        if (ret.getRight() != null)
            cache.put(candidId, ret);
        return ret.getRight();
    }
}
