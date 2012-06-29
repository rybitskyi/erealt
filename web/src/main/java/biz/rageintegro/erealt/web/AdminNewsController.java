package biz.rageintegro.erealt.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import biz.rageintegro.erealt.domain.News;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "admin/news", automaticallyMaintainView = true, formBackingObject = News.class)
@RequestMapping("/admin/news/**")
@Controller
public class AdminNewsController {
}
