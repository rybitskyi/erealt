package biz.rageintegro.erealt.web;

import biz.rageintegro.erealt.domain.News;
import java.lang.Long;
import java.lang.String;
import javax.validation.Valid;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

privileged aspect AdminNewsController_Roo_Controller {
    
    @RequestMapping(value = "/admin/news", method = RequestMethod.POST)
    public String AdminNewsController.create(@Valid News news, BindingResult result, ModelMap modelMap) {
        if (news == null) throw new IllegalArgumentException("A news is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("news", news);
            modelMap.addAttribute("news_creationDate_date_format", org.joda.time.format.DateTimeFormat.patternForStyle("S-", org.springframework.context.i18n.LocaleContextHolder.getLocale()));
            return "admin/news/create";
        }
        news.persist();
        return "redirect:/admin/news/" + news.getId();
    }
    
    @RequestMapping(value = "/admin/news/form", method = RequestMethod.GET)
    public String AdminNewsController.createForm(ModelMap modelMap) {
        modelMap.addAttribute("news", new News());
        modelMap.addAttribute("news_creationDate_date_format", org.joda.time.format.DateTimeFormat.patternForStyle("S-", org.springframework.context.i18n.LocaleContextHolder.getLocale()));
        return "admin/news/create";
    }
    
    @RequestMapping(value = "/admin/news/{id}", method = RequestMethod.GET)
    public String AdminNewsController.show(@PathVariable("id") Long id, ModelMap modelMap) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        modelMap.addAttribute("news_creationDate_date_format", org.joda.time.format.DateTimeFormat.patternForStyle("S-", org.springframework.context.i18n.LocaleContextHolder.getLocale()));
        modelMap.addAttribute("news", News.findNews(id));
        return "admin/news/show";
    }
    
    @RequestMapping(value = "/admin/news", method = RequestMethod.GET)
    public String AdminNewsController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, ModelMap modelMap) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            modelMap.addAttribute("news", News.findNewsEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) News.countNews() / sizeNo;
            modelMap.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            modelMap.addAttribute("news", News.findAllNews());
        }
        modelMap.addAttribute("news_creationDate_date_format", org.joda.time.format.DateTimeFormat.patternForStyle("S-", org.springframework.context.i18n.LocaleContextHolder.getLocale()));
        return "admin/news/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String AdminNewsController.update(@Valid News news, BindingResult result, ModelMap modelMap) {
        if (news == null) throw new IllegalArgumentException("A news is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("news", news);
            modelMap.addAttribute("news_creationDate_date_format", org.joda.time.format.DateTimeFormat.patternForStyle("S-", org.springframework.context.i18n.LocaleContextHolder.getLocale()));
            return "admin/news/update";
        }
        news.merge();
        return "redirect:/admin/news/" + news.getId();
    }
    
    @RequestMapping(value = "/admin/news/{id}/form", method = RequestMethod.GET)
    public String AdminNewsController.updateForm(@PathVariable("id") Long id, ModelMap modelMap) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        modelMap.addAttribute("news", News.findNews(id));
        modelMap.addAttribute("news_creationDate_date_format", org.joda.time.format.DateTimeFormat.patternForStyle("S-", org.springframework.context.i18n.LocaleContextHolder.getLocale()));
        return "admin/news/update";
    }
    
    @RequestMapping(value = "/admin/news/{id}", method = RequestMethod.DELETE)
    public String AdminNewsController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        News.findNews(id).remove();
        return "redirect:/admin/news?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
    }
    
}
