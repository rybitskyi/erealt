package biz.rageintegro.erealt.web;

import biz.rageintegro.erealt.domain.District;
import biz.rageintegro.erealt.domain.OperationType;
import biz.rageintegro.erealt.domain.Region;
import biz.rageintegro.erealt.domain.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: yury.ribitsky
 * Date: 6/21/12
 */
@Controller
public class EntityController {
    @Autowired
    private SearchCriteria searchCriteria;

    @RequestMapping(value = "/{operationType}", method = RequestMethod.GET)
    public String list(@PathVariable("operationType") String operationType) {
        searchCriteria.clear();
        searchCriteria.setOperationType(getOperationTypeFromKey(operationType));
        return "forward:/pages/list.jsf";
    }

    @RequestMapping(value = "/{operationType}/{district}", method = RequestMethod.GET)
    public String list(@PathVariable("operationType") String operationType,
                       @PathVariable("district") String district) {
        searchCriteria.clear();
        searchCriteria.setOperationType(getOperationTypeFromKey(operationType));
        searchCriteria.setDistrict(District.findDistrictByTranslitCaption(district));
        return "forward:/pages/list.jsf";
    }

    @RequestMapping(value = "/{operationType}/{district}/{region}", method = RequestMethod.GET)
    public String list(@PathVariable("operationType") String operationType,
                       @PathVariable("district") String district,
                       @PathVariable("region") String region) {
        searchCriteria.clear();
        searchCriteria.setOperationType(getOperationTypeFromKey(operationType));
        searchCriteria.setDistrict(District.findDistrictByTranslitCaption(district));
        searchCriteria.setRegion(Region.findRegionByTranslitCaption(district, region));
        return "forward:/pages/list.jsf";
    }

    private OperationType getOperationTypeFromKey(String key) {
        if (key == null) {
            return null;
        }
        for (OperationType ot : OperationType.values()) {
            if (ot.getKey().equalsIgnoreCase(key)) {
                return ot;
            }
        }
        return null;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String list() {
        return "forward:/pages/create.jsf";
    }
}
