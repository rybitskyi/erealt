package biz.rageintegro.erealt.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/contact/**")
@Controller
public class ContactController {
    @RequestMapping(value = "/contact/index", method = RequestMethod.GET)
    public String list(ModelMap modelMap) {
        return "contact/index";
    }
}
