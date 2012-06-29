package biz.rageintegro.erealt.web;

import java.util.ArrayList;
import java.util.List;

import biz.rageintegro.erealt.domain.AbstractObject;
import biz.rageintegro.erealt.domain.District;
import biz.rageintegro.erealt.domain.Region;
import biz.rageintegro.erealt.domain.SaleFlat;
import biz.rageintegro.erealt.domain.SearchCriteria;
import biz.rageintegro.erealt.domain.News;
import biz.rageintegro.erealt.reference.RoomCountType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/**")
@Controller
public class IndexController {
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String list(ModelMap modelMap) {
        return "forward:/pages/welcome.jsf";
    }

    @Deprecated
    public String listOld(ModelMap modelMap) {
        modelMap.addAttribute("recentobjects", AbstractObject.findRecentObjects());
        modelMap.addAttribute("date_format", org.joda.time.format.DateTimeFormat.patternForStyle("S-", org.springframework.context.i18n.LocaleContextHolder.getLocale()));
        modelMap.addAttribute("districts", District.findAllDistricts());
        modelMap.addAttribute("roomcounttype_enum", RoomCountType.class.getEnumConstants());
        modelMap.addAttribute("searchCriteria", searchCriteria);
        List<String> objectTypes = new ArrayList<String>();
        objectTypes.add("saleflat");
        objectTypes.add("salehouse");
        objectTypes.add("saleland");
        objectTypes.add("leaseflat");
        objectTypes.add("leasehouse");
        modelMap.addAttribute("objectTypes", objectTypes);
        NewsHelper.addRecentNewsToModelMap(modelMap);
        return "index";
    }

	@RequestMapping(value = "/find", method = RequestMethod.POST)
    public String findObjects(@RequestParam("objectType") String objectType,
    		@RequestParam(value = "district", required = false) District district, 
    		@RequestParam(value = "region", required = false) Region region, 
    		@RequestParam(value = "roomCount", required = false) RoomCountType roomCount, 
    		@RequestParam(value = "minPrice", required = false) Integer minPrice,
    		@RequestParam(value = "maxPrice", required = false) Integer maxPrice,
            ModelMap modelMap) {
		searchCriteria.setObjectType(objectType);
		searchCriteria.setDistrict(district);
        searchCriteria.setRegion(region);
        searchCriteria.setRoomCount(roomCount);
        searchCriteria.setMinPrice(minPrice); 
        searchCriteria.setMaxPrice(maxPrice); 
        return "redirect:/" + objectType;
    }

    @Autowired
    private SearchCriteria searchCriteria;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringNullableEditor stringNullableEditor = new StringNullableEditor();
        binder.registerCustomEditor(RoomCountType.class, stringNullableEditor);
    }
}
