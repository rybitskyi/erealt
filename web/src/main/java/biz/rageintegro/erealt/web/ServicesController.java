package biz.rageintegro.erealt.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/services/**")
@Controller
public class ServicesController extends GenericContoller{

    @RequestMapping(value = "/services", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        initModelMap("services", modelMap);
        return "services/index";
    }

    @RequestMapping(value = "/services/expertise", method = RequestMethod.GET)
    public String expertise(ModelMap modelMap) {
        initModelMap("services", modelMap);
        return "services/expertise";
    }

    @RequestMapping(value = "/services/development", method = RequestMethod.GET)
    public String development(ModelMap modelMap) {
        initModelMap("services", modelMap);
        return "services/development";
    }
}
