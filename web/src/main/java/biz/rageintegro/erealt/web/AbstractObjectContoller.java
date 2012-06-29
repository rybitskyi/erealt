package biz.rageintegro.erealt.web;

import biz.rageintegro.erealt.domain.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import javax.persistence.Query;
import javax.validation.Valid;
import java.util.List;

/**
 * User: yury.ribitsky
 * Date: 8/8/11
 */

abstract public class AbstractObjectContoller<T extends AbstractObject> extends GenericContoller{
    private final static Logger logger = Logger.getLogger(AbstractObjectContoller.class);

    private OperationType operationType;

    protected AbstractObjectContoller(OperationType operationType) {
        this.operationType = operationType;
    }

    @Autowired
    private PhotoManager photoManager;

    @Autowired
    private SearchCriteria searchCriteria;

    public PhotoManager getPhotoManager() {
        return photoManager;
    }

    public void setPhotoManager(PhotoManager photoManager) {
        this.photoManager = photoManager;
    }

    public SearchCriteria getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    protected void initModelMap(Class clazz, ModelMap modelMap) {
        modelMap.addAttribute("pageTitle", "label." + AbstractObject.getSystemName(clazz));
        modelMap.addAttribute("pageDescription", "page." + AbstractObject.getSystemName(clazz) + ".description");
        modelMap.addAttribute("pageKeywords", "page." + AbstractObject.getSystemName(clazz) + ".keywords");
        modelMap.addAttribute("districts", District.findAllDistricts());
        modelMap.addAttribute("searchCriteria", searchCriteria);
        modelMap.addAttribute("userContext", getUserContext());
        modelMap.addAttribute("date_format", org.joda.time.format.DateTimeFormat.patternForStyle("S-", org.springframework.context.i18n.LocaleContextHolder.getLocale()));
    }

    protected String create(@Valid T object, BindingResult result, ModelMap modelMap) {
        if (object == null) throw new IllegalArgumentException("A object is required");
        String username = null;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
            username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        }
        AccessUser user = AccessUser.findAccessUserByName(username);
        if (user == null) {
            throw new IllegalArgumentException("An user object is required");
        }
        object.setAccessUser(user);
        if (result.hasErrors()) {
            initModelMap(object.getClass(), modelMap);
            modelMap.addAttribute("districts", District.findAllDistricts());
            modelMap.addAttribute("regions", Region.findRegionsByDistrict(object.getDistrict().getId()));
            modelMap.addAttribute("streets", Street.findStreetsByRegion(object.getRegion().getId()));
            modelMap.addAttribute("object", object);
            return object.getSystemName() + "/create";
        }
        object.persist();
        NewsHelper.addRecentNewsToModelMap(modelMap);
        return "redirect:/property/" + object.getExtId();
    }

    public String createForm(T object, ModelMap modelMap) {
        initModelMap(object.getClass(), modelMap);
        modelMap.addAttribute("object", object);
        List<District> districts = District.findAllDistricts();
        modelMap.addAttribute("districts", districts);
        List<Region> regions = districts.size() > 0 ? Region.findRegionsByDistrict(districts.get(0).getId()) : null;
        modelMap.addAttribute("regions", regions);
        if (regions != null) {
            modelMap.addAttribute("streets", regions.size() > 0 ? Street.findStreetsByRegion(regions.get(0).getId()) : null);	
        }
        else {
            modelMap.addAttribute("streets", null);
        }
        NewsHelper.addRecentNewsToModelMap(modelMap);
        return object.getSystemName() + "/create";
    }

    abstract protected Query getListQuery(SearchCriteria searchCriteria);

    abstract protected long getListCount(SearchCriteria searchCriteria);

    protected void processList(Class clazz, Integer page, ModelMap modelMap) {
logger.debug("begin processList");
        initModelMap(clazz, modelMap);
        modelMap.addAttribute("districts", District.findAllDistricts());
        int sizeNo = 35;
        Query query;
        query = getListQuery(searchCriteria);
logger.debug("processList searchCriteria" + searchCriteria.toString());
        long countObjects = getListCount(searchCriteria);
logger.debug("processList countObjects" + countObjects);
        modelMap.addAttribute("objects", query.setFirstResult(page == null ? 0 : (page.intValue() - 1) * sizeNo).setMaxResults(sizeNo).getResultList());
        float nrOfPages = (float) countObjects / sizeNo;
        modelMap.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        NewsHelper.addRecentNewsToModelMap(modelMap);
    }

    public String list(Class clazz, Integer page, ModelMap modelMap) {
        processList(clazz, page, modelMap);
        return AbstractObject.getSystemName(clazz) + "/list";
    }

    public String listByDistrict(Class clazz, String district, Integer page, ModelMap modelMap) {
        searchCriteria.setDistrict(District.findDistrictByTranslitCaption(district));
        searchCriteria.setRegion(null);
        processList(clazz, page, modelMap);
        return AbstractObject.getSystemName(clazz) + "/list";
    }

    public String listByDistrictAndRegion(Class clazz, String district, String region, Integer page, ModelMap modelMap) {
        searchCriteria.setDistrict(District.findDistrictByTranslitCaption(district));
        searchCriteria.setRegion(Region.findRegionByTranslitCaption(district, region));
        processList(clazz, page, modelMap);
        return AbstractObject.getSystemName(clazz) + "/list";
    }

    public String update(T object, BindingResult result, ModelMap modelMap) {
        if (object == null) throw new IllegalArgumentException("An object is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("object", object);
            modelMap.addAttribute("accessusers", AccessUser.findAllAccessUsers());
            modelMap.addAttribute("districts", District.findAllDistricts());
            modelMap.addAttribute("regions", Region.findAllRegions());
            modelMap.addAttribute("streets", Street.findAllStreets());
            return object.getSystemName() + "/update";
        }
        object.merge();
        return "redirect:/" + object.getSystemName() + "/" + object.getExtId();
    }

    abstract protected  void delete(Long id);

    public String delete(Class clazz, Long id, Integer page) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        delete(id);
        return "redirect:/" + AbstractObject.getSystemName(clazz) + "?page=" + ((page == null) ? "1" : page.toString());
    }

    public String findObjects(Class clazz, Integer page, District district,
                              Region region,
                              Integer minPrice,
                              Integer maxPrice) {
        searchCriteria.setMinPrice(minPrice);
        searchCriteria.setMaxPrice(maxPrice);
        StringBuffer sb = new StringBuffer();
        if (district != null) {
            sb.append("/" + district.getTranslitCaption());
        }
        if (district != null && region != null) {
            sb.append("/" + region.getTranslitCaption());
        }
        return "redirect:/" + AbstractObject.getSystemName(clazz) + sb.toString();
    }

    public String list() {
        getSearchCriteria().clear();
        getSearchCriteria().setOperationType(getOperationType());
        return "forward:/pages/list.jsf";
    }

    public OperationType getOperationType() {
        return operationType;
    }
}
