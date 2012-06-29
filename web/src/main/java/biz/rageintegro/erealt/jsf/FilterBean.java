package biz.rageintegro.erealt.jsf;

import biz.rageintegro.erealt.domain.*;
import biz.rageintegro.erealt.reference.RoomCountType;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * User: yury.ribitsky
 * Date: 6/12/12
 */
public class FilterBean {
    public static final int PAGE_SIZE = 10;

    private LazyDataModel model;
    private List<District> districtList;
    private List<Region> regionList;

    private SearchCriteria searchCriteria;

    @PostConstruct
    public void init() {
        districtList = District.findAllDistricts();
        if (searchCriteria.getDistrict() == null && !districtList.isEmpty()) {
            searchCriteria.setDistrict(districtList.get(0));
        }
        populateRegions();
        model = new ListDataModel(searchCriteria);
        model.setRowCount((int) AbstractObject.countAbstractObjects(searchCriteria));
        model.setPageSize(PAGE_SIZE);
    }

    public List<District> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<District> districtList) {
        this.districtList = districtList;
    }

    public List<Region> getRegionList() {
        return regionList;
    }

    public void setRegionList(List<Region> regionList) {
        this.regionList = regionList;
    }

    public void handleDistrictChange() {
        populateRegions();
    }

    private void populateRegions() {
        if (searchCriteria.getDistrict() != null && searchCriteria.getDistrict().getId() != null) {
            regionList = Region.findRegionsByDistrict(searchCriteria.getDistrict().getId());
        } else {
            regionList = Collections.emptyList();
        }
    }

    public LazyDataModel getModel() {
        return model;
    }

    public SearchCriteria getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }
}
