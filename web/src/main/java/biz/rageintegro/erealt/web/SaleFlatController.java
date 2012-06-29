package biz.rageintegro.erealt.web;

import biz.rageintegro.erealt.domain.*;
import biz.rageintegro.erealt.reference.RoomCountType;
import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.validation.Valid;

@RequestMapping("/saleflat/**")
//@Controller
public class SaleFlatController extends FlatController<SaleFlat> {

    public static final String SALE_FLAT_URL = "/" + WebConstants.SALE_FLAT;

    private final static Logger logger = Logger.getLogger(SaleFlatController.class);

    protected SaleFlatController() {
        super(OperationType.SALE_FLAT);
    }

    @RequestMapping(value = SALE_FLAT_URL, method = RequestMethod.POST)
    public String create(@Valid SaleFlat saleFlat, BindingResult result, ModelMap modelMap) {
        return super.create(saleFlat, result, modelMap);
    }

    @RequestMapping(value = SALE_FLAT_URL + "/form", method = RequestMethod.GET)
    public String createForm(ModelMap modelMap) {
        NewsHelper.addRecentNewsToModelMap(modelMap);
        return super.createForm(new SaleFlat(), modelMap);
    }

    @RequestMapping(value = SALE_FLAT_URL, method = RequestMethod.GET)
    @Override
    public String list() {
        return super.list();
    }

    @Override
    protected Query getListQuery(SearchCriteria searchCriteria) {
        return SaleFlat.findSaleFlatsBySearchCriteria(searchCriteria);
    }

    @Override
    protected long getListCount(SearchCriteria searchCriteria) {
        return SaleFlat.findSaleFlatsBySearchCriteriaCount(searchCriteria);
    }

    @RequestMapping(value = SALE_FLAT_URL + "/{district}", method = RequestMethod.GET)
    public String listByDistrict(@PathVariable("district") String district,
                                 @RequestParam(value = "page", required = false) Integer page,
                                 ModelMap modelMap) {
        return super.listByDistrict(SaleFlat.class, district, page, modelMap);
    }

    @RequestMapping(value = SALE_FLAT_URL + "/{district}/{region}", method = RequestMethod.GET)
    public String listByDistrictAndRegion(@PathVariable("district") String district,
                                          @PathVariable("region") String region,
                                          @RequestParam(value = "page", required = false) Integer page,
                                          ModelMap modelMap) {
        return super.listByDistrictAndRegion(SaleFlat.class, district, region, page, modelMap);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid SaleFlat saleFlat, BindingResult result, ModelMap modelMap) {
        return super.update(saleFlat, result, modelMap);
    }

    @Override
    protected void delete(Long id) {
        SaleFlat.findSaleFlat(id).remove();
    }

    @RequestMapping(value = SALE_FLAT_URL + "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page) {
        return super.delete(SaleFlat.class, id, page);
    }

    //todo: refactor
    @RequestMapping(value = SALE_FLAT_URL + "/find", method = RequestMethod.GET)
    public String findObjects(@RequestParam(value = "page", required = false) Integer page,
                              @RequestParam(value = "district", required = false) District district,
                              @RequestParam(value = "region", required = false) Region region,
                              @RequestParam(value = "roomCount", required = false) RoomCountType roomCount,
                              @RequestParam(value = "minPrice", required = false) Integer minPrice,
                              @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
                              ModelMap modelMap) {
        getSearchCriteria().setRoomCount(roomCount);
        return super.findObjects(SaleFlat.class, page, district, region, minPrice, maxPrice);
    }
}
