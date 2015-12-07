package ir.hassannasr.majles.domain.init;

import dbfill.DataFillerHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: abolfazl
 * Date: 11/15/13
 * Time: 11:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class MajlesDataFillerHelper implements DataFillerHelper {
    private List<String> l = new ArrayList<String>();

    public MajlesDataFillerHelper(boolean isTestMode) {

        l.add("classpath*:applicationContext-majles-solr.xml");
        l.add("classpath*:applicationContext-majles-persistence.xml");
        if (isTestMode) {
            l.add("classpath*:applicationContext-majles-resource-test.xml");
        } else {
            l.add("classpath*:applicationContext-majles-resource.xml");
        }

        l.add("classpath*:applicationContext-majles-service.xml");
        l.add("classpath*:applicationContext-majles-security.xml");
        l.add("classpath*:applicationContext-majles.xml");
    }

    @Override
    public List<String> getMoreApplicationContextList() {
        return l;
    }
}
