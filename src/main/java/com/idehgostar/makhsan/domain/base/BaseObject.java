package com.idehgostar.makhsan.domain.base;

import core.bp.view.EntityViewDescription;
import core.dao.GenericDao;
import core.utils.util.RequestUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;


/**
 * Base class for Model objects. Child objects should implement toString(),
 * equals() and hashCode().
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public abstract class BaseObject implements Serializable {

    public static String describe(String className, Long id ,EntityViewDescription e, EntityManager entityManager) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        if(id == null)
            return "-";
        String ret= "";
        Class objectClass = Class.forName(className);
        BaseObject object = (BaseObject) entityManager.find(objectClass,id);
        boolean first=true ;
        for(String property: e.getPropertyViewList()){
            if(!first) {
                ret += ", ";
            }
            else
                first =  false;
            Method m = object.getClass().getMethod("get"+property.substring(0,1).toUpperCase()+property.substring(1));
            Object value = m.invoke(object);
            ret += value == null?"*":value.toString();
        }
        return ret;
    }

    /**
     * Returns a multi-line String with key=value pairs.
     * @return a String representation of this class.
     */
    public abstract String toString();

    /**
     * Compares object equality. When using Hibernate, the primary key should
     * not be a part of this comparison.
     * @param o object to compare to
     * @return true/false based on equality tests
     */
    public abstract boolean equals(Object o);

    /**
     * When you override equals, you should override hashCode. See "Why are
     * equals() and hashCode() importation" for more information:
     * http://www.hibernate.org/109.html
     * @return hashCode
     */
    public abstract int hashCode();

    public abstract Long getId();

    public abstract void setId(Long id);

    protected String[] parseStringToList(String string) {
        if (string == null || string.length() < 2)
            return new String[0];
        return string.substring(1, string.length()-1).split("/");
    }

    protected String concatListToString(String[] ids) {
        if (ids == null)
            return "";

        Arrays.sort(ids);

        String res = "";
        for (int i = 0; i < ids.length; i++) {
            res += "/" + ids[i];
        }
        if (ids.length > 0)
            res += "/";
        return res;
    }

    protected <T extends GenericDao> T getDao(Class<T> narratedHadithDaoClass, String name) {
        return (T) WebApplicationContextUtils.getRequiredWebApplicationContext(RequestUtils.getServletContext()).getBean(name);
    }

}

