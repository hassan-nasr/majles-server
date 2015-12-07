package com.idehgostar.makhsan.domain.init;

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
public class MakhsanDataFillerHelper implements DataFillerHelper {
    private List<String> l = new ArrayList<String>();

    public MakhsanDataFillerHelper(boolean isTestMode) {

        l.add("classpath*:applicationContext-makhsan-solr.xml");
        l.add("classpath*:applicationContext-makhsan-persistence.xml");
        if (isTestMode) {
            l.add("classpath*:applicationContext-makhsan-resource-test.xml");
        } else {
            l.add("classpath*:applicationContext-makhsan-resource.xml");
        }

        l.add("classpath*:applicationContext-makhsan-service.xml");
        l.add("classpath*:applicationContext-makhsan-security.xml");
        l.add("classpath*:applicationContext-makhsan.xml");
    }

    @Override
    public List<String> getMoreApplicationContextList() {
        return l;
    }
}
