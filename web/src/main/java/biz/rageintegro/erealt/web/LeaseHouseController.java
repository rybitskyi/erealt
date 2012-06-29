package biz.rageintegro.erealt.web;

import biz.rageintegro.erealt.domain.*;
import biz.rageintegro.erealt.reference.RoomCountType;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Query;
import javax.validation.Valid;

@RequestMapping("/leasehouse/**")
//@Controller
public class LeaseHouseController extends HouseController{
    public static final String LEASE_HOUSE_URL = "/" + WebConstants.LEASE_HOUSE;

    protected LeaseHouseController() {
        super(OperationType.LEASE_HOUSE);
    }

    @RequestMapping(value = LEASE_HOUSE_URL, method = RequestMethod.POST)
    public String create(@Valid LeaseHouse leaseHouse, BindingResult result, ModelMap modelMap) {
        return super.create(leaseHouse, result, modelMap);
    }

    @RequestMapping(value = LEASE_HOUSE_URL + "/form", method = RequestMethod.GET)
    public String createForm(ModelMap modelMap) {
        return super.createForm(new LeaseHouse(), modelMap);
    }

    @RequestMapping(value = LEASE_HOUSE_URL, method = RequestMethod.GET)
    @Override
    public String list() {
        return super.list();
    }

    @Override
    protected Query getListQuery(SearchCriteria searchCriteria) {
        return LeaseHouse.findLeaseHousesBySearchCriteria(searchCriteria);
    }

    @Override
    protected long getListCount(SearchCriteria searchCriteria) {
        return LeaseHouse.findLeaseHousesBySearchCriteriaCount(searchCriteria);
    }

    @RequestMapping(value = LEASE_HOUSE_URL + "/{district}", method = RequestMethod.GET)
    public String listByDistrict(@PathVariable("district") String district,
		@RequestParam(value = "page", required = false) Integer page,
        ModelMap modelMap) {
        return super.listByDistrict(LeaseHouse.class, district, page, modelMap);
    }

    @RequestMapping(value = LEASE_HOUSE_URL + "/{district}/{region}", method = RequestMethod.GET)
    public String listByDistrictAndRegion(@PathVariable("district") String district,
		@PathVariable("region") String region,
		@RequestParam(value = "page", required = false) Integer page,
        ModelMap modelMap) {
        return super.listByDistrictAndRegion(LeaseHouse.class, district, region, page, modelMap);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid LeaseHouse saleFlat, BindingResult result, ModelMap modelMap) {
        return super.update(saleFlat, result, modelMap);
    }

    @Override
    protected void delete(Long id) {
        LeaseHouse.findLeaseHouse(id).remove();
    }

    @RequestMapping(value = LEASE_HOUSE_URL + "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page) {
        return super.delete(LeaseHouse.class, id, page);
    }

	//todo: refactor
    @RequestMapping(value = LEASE_HOUSE_URL + "/find", method = RequestMethod.GET)
    public String findObjects(@RequestParam(value = "page", required = false) Integer page,
    		@RequestParam(value = "district", required = false) District district,
    		@RequestParam(value = "region", required = false) Region region,
    		@RequestParam(value = "roomCount", required = false) RoomCountType roomCount,
    		@RequestParam(value = "minPrice", required = false) Integer minPrice,
    		@RequestParam(value = "maxPrice", required = false) Integer maxPrice,
    		ModelMap modelMap) {
        getSearchCriteria().setRoomCount(roomCount);
		return super.findObjects(LeaseHouse.class, page, district, region, minPrice, maxPrice);
    }

}
