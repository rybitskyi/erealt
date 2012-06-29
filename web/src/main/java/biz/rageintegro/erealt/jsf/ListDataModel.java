package biz.rageintegro.erealt.jsf;

import biz.rageintegro.erealt.domain.AbstractObject;
import biz.rageintegro.erealt.domain.SearchCriteria;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.List;
import java.util.Map;

/**
 * User: yury.ribitsky
 * Date: 6/14/12
 */
public class ListDataModel extends LazyDataModel<AbstractObject>{
    private SearchCriteria searchCriteria;

    public ListDataModel(SearchCriteria searchCriteria) {
        if (searchCriteria == null) {
            throw new RuntimeException("Argument searchCriteria is null");
        }
        this.searchCriteria = searchCriteria;
    }

    @Override
    public List<AbstractObject> load(int first, int pageSize, String sortField,
                                     SortOrder sortOrder, Map<String, String> filters) {
        setRowCount((int) AbstractObject.countAbstractObjects(searchCriteria));
        return AbstractObject.findAbstractObjects(searchCriteria).setFirstResult(first).setMaxResults(pageSize).getResultList();
    }
}
