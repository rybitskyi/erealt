package biz.rageintegro.erealt.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import biz.rageintegro.erealt.domain.News;

@RequestMapping("/press/**")
@Controller
public class PressController extends GenericContoller {

    @RequestMapping(value = "/press", method = RequestMethod.GET)
    public String list(ModelMap modelMap) {
        initModelMap("press", modelMap);
        return "press/index";
    }
}
