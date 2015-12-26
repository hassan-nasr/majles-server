package ir.hassannasr.majles.domain.candid;

import core.dao.GenericDaoImpl;
import ir.hassannasr.majles.domain.hozeh.SubHozeh;

/**
 * Created by hassan on 02/11/2015.
 */
public class HozehDaoImpl extends GenericDaoImpl<SubHozeh, Long> implements HozehDao {
    public HozehDaoImpl() {
        super(SubHozeh.class);
    }

}
