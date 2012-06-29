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

@RequestMapping("/saleland/**")
//@Controller
public class SaleLandController extends LandController {
    public static final String SALE_LAND_URL = "/" + WebConstants.SALE_LAND;

    protected SaleLandController() {
        super(OperationType.SALE_LAND);
    }

    @RequestMapping(value = SALE_LAND_URL, method = RequestMethod.POST)
    public String create(@Valid SaleLand saleLand, BindingResult result, ModelMap modelMap) {
        return super.create(saleLand, result, modelMap);
    }

    @RequestMapping(value = SALE_LAND_URL + "/form", method = RequestMethod.GET)
    public String createForm(ModelMap modelMap) {
        return super.createForm(new SaleLand(), modelMap);
    }

    @RequestMapping(value = SALE_LAND_URL, method = RequestMethod.GET)
    @Override
    public String list() {
        return super.list();
    }

    @Override
    protected Query getListQuery(SearchCriteria searchCriteria) {
        return SaleLand.findSaleLandsBySearchCriteria(searchCriteria);
    }

    @Override
    protected long getListCount(SearchCriteria searchCriteria) {
        return SaleLand.findSaleLandsBySearchCriteriaCount(searchCriteria);
    }

    @RequestMapping(value = SALE_LAND_URL + "/{district}", method = RequestMethod.GET)
    public String listByDistrict(@PathVariable("district") String district,
                                 @RequestParam(value = "page", required = false) Integer page, ModelMap modelMap) {
        return super.listByDistrict(SaleLand.class, district, page, modelMap);
    }

    @RequestMapping(value = SALE_LAND_URL + "/{district}/{region}", method = RequestMethod.GET)
    public String listByDistrictAndRegion(@PathVariable("district") String district,
                                          @PathVariable("region") String region,
                                          @RequestParam(value = "page", required = false) Integer page,
                                          ModelMap modelMap) {
        return super.listByDistrictAndRegion(SaleLand.class, district, region, page, modelMap);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid SaleLand saleFlat, BindingResult result, ModelMap modelMap) {
        return super.update(saleFlat, result, modelMap);
    }

    @Override
    protected void delete(Long id) {
        SaleLand.findSaleLand(id).remove();
    }

    @RequestMapping(value = SALE_LAND_URL + "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page) {
        return super.delete(SaleLand.class, id, page);
    }

    //todo: refactor
    @RequestMapping(value = SALE_LAND_URL + "/find", method = RequestMethod.GET)
    public String findObjects(@RequestParam(value = "page", required = false) Integer page,
                              @RequestParam(value = "district", required = false) District district,
                              @RequestParam(value = "region", required = false) Region region,
                              @RequestParam(value = "minPrice", required = false) Integer minPrice,
                              @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
                              ModelMap modelMap) {
        return super.findObjects(SaleLand.class, page, district, region, minPrice, maxPrice);
    }

}
