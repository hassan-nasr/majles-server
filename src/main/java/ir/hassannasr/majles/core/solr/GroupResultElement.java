package ir.hassannasr.majles.core.solr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hasan
 * Date: 9/19/13
 * Time: 4:17 PM
 * Class for holding a result element from solr
 *
 */
public class GroupResultElement<T extends ResultElement> {
    /**
     * group identifier
     */
    private Long groupId;

    /**
     * list of result elements
     */
    private List<T> resultElements = new ArrayList<T>();

    /**
     * list of result elements
     */
    public List<T> getResultElements() {
        return resultElements;
    }

    public void setResultElements(List<T> resultElements) {
        this.resultElements = resultElements;
    }

    public void add(T resultElement) {
        resultElements.add(resultElement);
    }

    /**
     * group identifier
     */
    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
