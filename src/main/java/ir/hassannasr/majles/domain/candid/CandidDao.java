package ir.hassannasr.majles.domain.candid;

import core.dao.GenericDao;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * Created by hassan on 02/11/2015.
 */

public interface CandidDao extends GenericDao<Candid, Long> {
    List<Candid> findCandidWithUserIds(List<Long> userIds);
    List<Candid> searchByDoreh(String dorehString, Boolean isMajles);

    List<Candid> searchStatistic(String context, Long subHozehId);

    List<Pair<Long, Candid>> countRay(Long subHozehId);
}
