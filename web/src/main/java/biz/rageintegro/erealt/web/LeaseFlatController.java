package biz.rageintegro.erealt.web;

import biz.rageintegro.erealt.domain.*;
import biz.rageintegro.erealt.reference.RoomCountType;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.validation.Valid;

@RequestMapping("/leaseflat/**")
//@Controller
public class LeaseFlatController extends FlatController<LeaseFlat> {

    public static final String LEASE_FLAT_URL = "/" + WebConstants.LEASE_FLAT;

    protected LeaseFlatController() {
        super(OperationType.LEASE_FLAT);
    }

    @RequestMapping(value = LEASE_FLAT_URL, method = RequestMethod.POST)
    public String create(@Valid LeaseFlat leaseFlat, BindingResult result, ModelMap modelMap) {
        return super.create(leaseFlat, result, modelMap);
    }

    @RequestMapping(value = LEASE_FLAT_URL + "/form", method = RequestMethod.GET)
    public String createForm(ModelMap modelMap) {
        NewsHelper.addRecentNewsToModelMap(modelMap);
        return super.createForm(new LeaseFlat(), modelMap);
    }

    @RequestMapping(value = LEASE_FLAT_URL, method = RequestMethod.GET)
    @Override
    public String list() {
        return super.list();
    }

    @Override
    protected Query getListQuery(SearchCriteria searchCriteria) {
        return LeaseFlat.findLeaseFlatsBySearchCriteria(searchCriteria);
    }

    @Override
    protected long getListCount(SearchCriteria searchCriteria) {
        return LeaseFlat.findLeaseFlatsBySearchCriteriaCount(searchCriteria);
    }

    @RequestMapping(value = LEASE_FLAT_URL + "/{district}", method = RequestMethod.GET)
    public String listByDistrict(@PathVariable("district") String district,
                                 @RequestParam(value = "page", required = false) Integer page,
                                 ModelMap modelMap) {
        return super.listByDistrict(LeaseFlat.class, district, page, modelMap);
    }

    @RequestMapping(value = LEASE_FLAT_URL + "/{district}/{region}", method = RequestMethod.GET)
    public String listByDistrictAndRegion(@PathVariable("district") String district,
                                          @PathVariable("region") String region,
                                          @RequestParam(value = "page", required = false) Integer page,
                                          ModelMap modelMap) {
        return super.listByDistrictAndRegion(LeaseFlat.class, district, region, page, modelMap);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid LeaseFlat leaseFlat, BindingResult result, ModelMap modelMap) {
        return super.update(leaseFlat, result, modelMap);
    }

    @Override
    protected void delete(Long id) {
        LeaseFlat.findLeaseFlat(id).remove();
    }

    @RequestMapping(value = LEASE_FLAT_URL + "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page) {
        return super.delete(LeaseFlat.class, id, page);
    }

    //todo: refactor
    @RequestMapping(value = LEASE_FLAT_URL + "/find", method = RequestMethod.GET)
    public String findObjects(@RequestParam(value = "page", required = false) Integer page,
                              @RequestParam(value = "district", required = false) District district,
                              @RequestParam(value = "region", required = false) Region region,
                              @RequestParam(value = "roomCount", required = false) RoomCountType roomCount,
                              @RequestParam(value = "minPrice", required = false) Integer minPrice,
                              @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
                              ModelMap modelMap) {
        getSearchCriteria().setRoomCount(roomCount);
        return super.findObjects(LeaseFlat.class, page, district, region, minPrice, maxPrice);
    }
}
