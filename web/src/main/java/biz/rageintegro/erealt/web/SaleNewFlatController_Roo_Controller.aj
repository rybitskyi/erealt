package biz.rageintegro.erealt.web;

import biz.rageintegro.erealt.domain.District;
import biz.rageintegro.erealt.domain.Flat;
import biz.rageintegro.erealt.domain.Region;
import biz.rageintegro.erealt.domain.SaleFlat;
import biz.rageintegro.erealt.domain.SaleNewFlat;
import biz.rageintegro.erealt.domain.Street;
import biz.rageintegro.erealt.domain.AccessUser;
import biz.rageintegro.erealt.reference.RoomCountType;
import java.lang.Long;
import java.lang.String;
import java.util.List;

import javax.persistence.Query;
import javax.validation.Valid;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

privileged aspect SaleNewFlatController_Roo_Controller {
    
    @RequestMapping(value = "/salenewflat", method = RequestMethod.POST)
    public String SaleNewFlatController.create(@Valid SaleNewFlat saleNewFlat, BindingResult result, ModelMap modelMap) {
        if (saleNewFlat == null) throw new IllegalArgumentException("A saleNewFlat is required");
        String username = null;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
            username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        }
        AccessUser user = AccessUser.findAccessUserByName(username);
        if (user == null) {
            throw new IllegalArgumentException("An user object is required");
        }
        saleNewFlat.setAccessUser(user);
        if (result.hasErrors()) {
            modelMap.addAttribute("pageTitle", "label.salenewflat");
            modelMap.addAttribute("saleNewFlat", saleNewFlat);
            modelMap.addAttribute("districts", District.findAllDistricts());
            modelMap.addAttribute("regions", Region.findAllRegions());
            modelMap.addAttribute("streets", Street.findAllStreets());
            modelMap.addAttribute("roomcounttype_enum", RoomCountType.class.getEnumConstants());
            modelMap.addAttribute("saleNewFlat_creationDate_date_format", org.joda.time.format.DateTimeFormat.patternForStyle("S-", org.springframework.context.i18n.LocaleContextHolder.getLocale()));
            return "salenewflat/create";
        }
        saleNewFlat.persist();
        return "redirect:/property/" + saleNewFlat.getExtId();
    }
    
    @RequestMapping(value = "/salenewflat/form", method = RequestMethod.GET)
    public String SaleNewFlatController.createForm(ModelMap modelMap) {
        modelMap.addAttribute("pageTitle", "label.salenewflat");
        modelMap.addAttribute("saleNewFlat", new SaleNewFlat());
        modelMap.addAttribute("districts", District.findAllDistricts());
        modelMap.addAttribute("regions", Region.findAllRegions());
        modelMap.addAttribute("streets", Street.findAllStreets());
        modelMap.addAttribute("roomcounttype_enum", RoomCountType.class.getEnumConstants());
        modelMap.addAttribute("saleNewFlat_creationDate_date_format", org.joda.time.format.DateTimeFormat.patternForStyle("S-", org.springframework.context.i18n.LocaleContextHolder.getLocale()));
        return "salenewflat/create";
    }
    
    private void SaleNewFlatController.processList(Integer page, ModelMap modelMap) {
    	modelMap.addAttribute("pageTitle", "label.salenewflat");
        modelMap.addAttribute("pageDescription", "page.salenewflat.description");
        modelMap.addAttribute("pageKeywords", "page.salenewflat.keywords");
        modelMap.addAttribute("districts", District.findAllDistricts());
        modelMap.addAttribute("searchCriteria", searchCriteria);
        int sizeNo = 35;
        Query query;
        long countSaleNewFlats;
        if (searchCriteria.isEmpty()) {
        	query = SaleNewFlat.findSaleNewFlat();
        	countSaleNewFlats = SaleNewFlat.countSaleNewFlats();
        }
        else {
        	query = SaleNewFlat.findSaleNewFlatsBySearchCriteria(searchCriteria);
        	countSaleNewFlats = SaleNewFlat.findSaleNewFlatsBySearchCriteriaCount(searchCriteria);        	
        }        
        
        modelMap.addAttribute("salenewflats", query.setFirstResult(page == null ? 0 : (page.intValue() - 1) * sizeNo).setMaxResults(sizeNo).getResultList());
        float nrOfPages = (float) countSaleNewFlats / sizeNo;
        modelMap.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        modelMap.addAttribute("saleNewFlat_creationDate_date_format", org.joda.time.format.DateTimeFormat.patternForStyle("S-", org.springframework.context.i18n.LocaleContextHolder.getLocale()));
        modelMap.addAttribute("roomcounttype_enum", RoomCountType.class.getEnumConstants());   	
    }
    
    @RequestMapping(value = "/salenewflat", method = RequestMethod.GET)
    public String SaleNewFlatController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,
        @RequestParam(value = "district", required = false) District district, 
        @RequestParam(value = "region", required = false) Region region,
        @RequestParam(value = "roomCount", required = false) RoomCountType roomCount, ModelMap modelMap) {
        processList(page, modelMap);
        return "salenewflat/list";
    }
    
    @RequestMapping(value = "/salenewflat/{district}", method = RequestMethod.GET)
    public String SaleNewFlatController.listByDistrict(@PathVariable("district") String district,
		@RequestParam(value = "page", required = false) Integer page, 
        ModelMap modelMap) {
		searchCriteria.setDistrict(District.findDistrictByTranslitCaption(district));
        processList(page, modelMap);
        return "salenewflat/list";
    }
    
    @RequestMapping(value = "/salenewflat/{district}/{region}", method = RequestMethod.GET)
    public String SaleNewFlatController.listByDistrictAndRegion(@PathVariable("district") String district,
		@PathVariable("region") String region,
		@RequestParam(value = "page", required = false) Integer page, 
        ModelMap modelMap) {
		searchCriteria.setDistrict(District.findDistrictByTranslitCaption(district));
        searchCriteria.setRegion(Region.findRegionByTranslitCaption(district, region));
        processList(page, modelMap);
        return "salenewflat/list";
    }
    
    @RequestMapping(value = "/salenewflat/{id}", method = RequestMethod.DELETE)
    public String SaleNewFlatController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        SaleNewFlat.findSaleNewFlat(id).remove();
        return "redirect:/salenewflat?page=" + ((page == null) ? "1" : page.toString());
    }
    
    @RequestMapping(value = "/salenewflat/find", method = RequestMethod.GET)
    public String SaleNewFlatController.findSaleNewFlats(@RequestParam(value = "page", required = false) Integer page,
    		@RequestParam(value = "district", required = false) District district, 
    		@RequestParam(value = "region", required = false) Region region, 
    		@RequestParam(value = "roomCount", required = false) RoomCountType roomCount, 
    		@RequestParam(value = "minPrice", required = false) Integer minPrice,
    		@RequestParam(value = "maxPrice", required = false) Integer maxPrice,
    		ModelMap modelMap) {
        searchCriteria.setRoomCount(roomCount);
        searchCriteria.setMinPrice(minPrice); 
        searchCriteria.setMaxPrice(maxPrice); 
        StringBuffer sb = new StringBuffer();
		if (district != null) {
			sb.append("/" + district.getTranslitCaption());
		}
        if (district != null && region != null) {
			sb.append("/" + region.getTranslitCaption());
		}
		return "redirect:/salenewflat" + sb.toString();
    }    
}
