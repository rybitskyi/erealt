package biz.rageintegro.erealt.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/design/**")
@Controller
public class DesignController {

    @RequestMapping(value = "/design", method = RequestMethod.GET)
    public String list(ModelMap modelMap) {
        modelMap.addAttribute("pageTitle", "label.design");
		NewsHelper.addRecentNewsToModelMap(modelMap);
        return "design/list";
    }

    @RequestMapping(value = "/design/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, ModelMap modelMap) {
        if (id == null) throw new IllegalArgumentException("An Design Identifier is required");
        modelMap.addAttribute("designCaption", "Совет по Строительству и Дизайну"); //todo: distinguish caption for different advices
        modelMap.addAttribute("pageTitle", "label.design");
        modelMap.addAttribute("designpage", id + ".jspx");
        NewsHelper.addRecentNewsToModelMap(modelMap);
        return "design/show";
    }
}
