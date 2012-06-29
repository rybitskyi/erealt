package biz.rageintegro.erealt.web;

import biz.rageintegro.erealt.domain.Region;
import biz.rageintegro.erealt.domain.Street;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/address/**")
@Controller
public class AddressController {

    @RequestMapping("/address/region")
    public ModelAndView regions(@RequestParam("districtId") Long districtId) {
        ModelAndView mav = new ModelAndView(JsonView.instance);
        mav.addObject(JsonView.JSON_ROOT, Region.findRegionsByDistrict(districtId));
        return mav;
    }

    @RequestMapping("/address/street")
    public ModelAndView streets(@RequestParam("regionId") Long regionId) {
        ModelAndView mav = new ModelAndView(JsonView.instance);
        mav.addObject(JsonView.JSON_ROOT, Street.findStreetsByRegion(regionId));
        return mav;
    }
}
