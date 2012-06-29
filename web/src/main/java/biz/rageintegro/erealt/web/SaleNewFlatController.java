package biz.rageintegro.erealt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import biz.rageintegro.erealt.domain.SaleNewFlat;
import biz.rageintegro.erealt.domain.SearchCriteria;
import biz.rageintegro.erealt.domain.PhotoManager;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "salenewflat", automaticallyMaintainView = true, formBackingObject = SaleNewFlat.class, update = false, delete = false)
@RequestMapping("/salenewflat/**")
@Controller
public class SaleNewFlatController {

    @Autowired
    private PhotoManager photoManager;

    @Autowired
    private SearchCriteria searchCriteria;
    
}
