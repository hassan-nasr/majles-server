package ir.hassannasr.majles.domain.candid;

import core.service.GenericManager;
import ir.hassannasr.majles.domain.hozeh.SubHozeh;
import ir.hassannasr.majles.domain.user.User;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by hassan on 02/11/2015.
 */
public interface CandidManager extends GenericManager<Candid, Long> {

    List<SubHozeh> getAllHozeh();

    Candid getCached(Long candidId);

    Map<Long, Long> getPriceMap();

    Map<Long, Candid> getCandidsMap(Collection<User> result);

    List<Candid> searchByDoreh(Integer Doreh, Boolean isMajles);

    List<Candid> searchStatistic(String context, Long subHozehId);

    List<Pair<Long, Candid>> searchRay(Long subHozehId);

    SubHozeh getHozeh(Long id);
}
