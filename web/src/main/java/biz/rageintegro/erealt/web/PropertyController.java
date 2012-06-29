package biz.rageintegro.erealt.web;

import biz.rageintegro.erealt.domain.AbstractObject;

import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.netbeans.saas.RestResponse;
import org.netbeans.saas.google.GoogleMapService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/property/**")
@Controller
public class PropertyController extends GenericContoller {

    @RequestMapping(value = "/property/{extId}", method = RequestMethod.GET)
    public String show(@PathVariable("extId") String extId, ModelMap modelMap) {
        String arr[] = extId.split("-");
        Long id = arr.length > 0 ? Long.parseLong(arr[0]) : null;
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        AbstractObject object = AbstractObject.findAbstractObject(id);
        modelMap.addAttribute("object", object);

/*todo: process it
        String dType = object.getClass().getSimpleName();
        getUserContext().setSelectedMainMenu(object.getSystemName());
        initModelMap(object.getClass(), modelMap);
        modelMap.addAttribute("pageTitleParams", object.getFullAddressName());
        modelMap.addAttribute("pageKeywordsValue", getPageKeywords(object));
        modelMap.addAttribute("pageKeywords", null); //todo: remove it
        modelMap.addAttribute("propertyPageDescription", getPageDescription(object));
        modelMap.addAttribute("map", getGoogleMap(object));
        modelMap.addAttribute("relatedObjects", getRelatedObjects(object));
        NewsHelper.addRecentNewsToModelMap(modelMap);
*/
        return "forward:/pages/property.jsf";
    }
/*

    @RequestMapping(value = "/property", method = RequestMethod.GET)
    public String showByParam(@RequestParam(value = "id") Long id, ModelMap modelMap) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        AbstractObject object = AbstractObject.findAbstractObject(id);
        modelMap.addAttribute("object", object);
        return "forward:/pages/property.jsf";
    }
*/

    @Deprecated
    public String show2(@PathVariable("extId") String extId, ModelMap modelMap) {
        String arr[] = extId.split("-");
        Long id = arr.length > 0 ? Long.parseLong(arr[0]) : null;
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        AbstractObject object = AbstractObject.findAbstractObject(id);
        String dType = object.getClass().getSimpleName();
        getUserContext().setSelectedMainMenu(object.getSystemName());
        initModelMap(object.getClass(), modelMap);
        modelMap.addAttribute("pageTitleParams", object.getFullAddressName());
        modelMap.addAttribute("pageKeywordsValue", getPageKeywords(object));
        modelMap.addAttribute("pageKeywords", null); //todo: remove it
        modelMap.addAttribute("propertyPageDescription", getPageDescription(object));
        modelMap.addAttribute("map", getGoogleMap(object));
        modelMap.addAttribute("relatedObjects", getRelatedObjects(object));
        modelMap.addAttribute("object", object);
        NewsHelper.addRecentNewsToModelMap(modelMap);
        return dType.toLowerCase() + "/show";
    }

    private String getPageKeywords(AbstractObject object) {
        if (object == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (object.getStreet() != null) {
            sb.append(object.getStreet().getCaption());
        }
        if (object.getStreetObjectNo() != null) {
            sb.append(" ").append(object.getStreetObjectNo());
        }
        if (object.getRegion() != null) {
            sb.append(", ").append(object.getRegion().getCaption());
        }
        sb.append(", erealt");
        if (object.getDistrict() != null) {
            sb.append(", ").append(object.getDistrict().getCaption());
        }
        return sb.toString();
    }

    private String getPageDescription(AbstractObject object) {
        if (object == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (object.getDistrict() != null) {
            sb.append(object.getDistrict().getCaption());
        }
        if (object.getRegion() != null) {
            sb.append(" ").append(object.getRegion().getCaption());
        }
        if (object.getStreet() != null) {
            sb.append(" ").append(object.getStreet().getCaption());
        }
        if (object.getStreetObjectNo() != null) {
            sb.append(" ").append(object.getStreetObjectNo());
        }
        sb.append(" ").append(object.getDescription());
        return sb.toString().length() > 500 ? sb.toString().substring(0, 500) : sb.toString();
    }

    public String getGoogleMap(AbstractObject object) {
        if (object == null) {
            throw new NullPointerException("Argument object can't be null");
        }
        try {
            String address = object.getMapAddressName();
            java.lang.Integer zoom = 15;
            RestResponse result = GoogleMapService.getGoogleMap(address, zoom);
            return result.getDataAsString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    private List<AbstractObject> getRelatedObjects(AbstractObject object) {
         //todo: show relevant data
        List<AbstractObject> l1 = AbstractObject.findManualEntries(0, 3);
        List<AbstractObject> l2 = AbstractObject.findSourceEntriesOnlyPhoto("aviso", 0, 2);
        l1.addAll(l2);
        return l1;
    }
}
