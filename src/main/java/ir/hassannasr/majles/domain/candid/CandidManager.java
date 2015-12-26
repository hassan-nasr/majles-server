package ir.hassannasr.majles.domain.candid;

import core.service.GenericManager;
import ir.hassannasr.majles.domain.hozeh.SubHozeh;

import java.util.List;

/**
 * Created by hassan on 02/11/2015.
 */
public interface CandidManager extends GenericManager<Candid, Long> {

    List<SubHozeh> getAllHozeh();
}
