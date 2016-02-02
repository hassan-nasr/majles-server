package ir.hassannasr.majles.domain.candid;

import core.dao.GenericDao;
import core.service.GenericManagerImpl;
import ir.hassannasr.majles.domain.hozeh.SubHozeh;
import ir.hassannasr.majles.domain.user.User;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public Map<Long, Long> getPriceMap() {
        Map<Long, Long> ret = new TreeMap<>();
        final List<SubHozeh> allHozeh = getAllHozeh();
        Long totalPrice = 0L;
        for (SubHozeh hozeh : allHozeh) {
            totalPrice += hozeh.getPrice();
            ret.put(hozeh.getId(), hozeh.getPrice());
        }
        ret.put(0L, totalPrice);
        return ret;
    }

    @Override
    public Map<Long, Candid> getCandidsMap(Collection<User> result) {
        if (result == null || result.size() == 0)
            return new HashMap<>();
        List<Long> userIds = result.stream().map(User::getId).collect(Collectors.toList());
        if (userIds.size() == 0)
            return new HashMap<>();
        List<Candid> candids = candidDao.findCandidWithUserIds(userIds);
        Map<Long, Candid> candidMap = new HashMap<>();
        for (Candid candid : candids) {
            candidMap.put(candid.getUserId(), candid);
        }
        return candidMap;
    }

    @Override
    public List<Candid> searchByDoreh(Integer doreh, Boolean isMajles) {
        final String dorehString = getDorehSring(doreh);
        return candidDao.searchByDoreh(dorehString,isMajles);
    }

    @Override
    public List<Candid> searchStatistic(String context, Long subHozehId) {
        return candidDao.searchStatistic(context, subHozehId);
    }

    @Override
    public List<Pair<Long, Candid>> searchRay(Long subHozehId) {
        return candidDao.countRay(subHozehId);

    }

    @Override
    public SubHozeh getHozeh(Long id) {
        return candidDao.getHozeh(id);
    }

    protected String getDorehSring(Integer doreh) {
        String dorehString = null;
        switch (doreh){
            case 1:
                dorehString="اول";
                break;
            case 2:
                dorehString="دوم";
                break;
            case 3:
                dorehString="سوم";
                break;
            case 4:
                dorehString="چهارم";
                break;
            case 5:
                dorehString="پنجم";
                break;
            case 6:
                dorehString="ششم";
                break;
            case 7:
                dorehString="هفتم";
                break;
            case 8:
                dorehString="هشتم";
                break;
            case 9:
                dorehString="نهم";
                break;
            case 10:
                dorehString="دهم";
                break;
        }
        return dorehString;
    }

}
