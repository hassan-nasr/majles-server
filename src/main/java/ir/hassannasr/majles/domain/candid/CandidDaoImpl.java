package ir.hassannasr.majles.domain.candid;

import core.dao.GenericDaoImpl;

/**
 * Created by hassan on 02/11/2015.
 */
public class CandidDaoImpl extends GenericDaoImpl<Candid, Long> implements CandidDao {
    public CandidDaoImpl() {
        super(Candid.class);
    }

}
