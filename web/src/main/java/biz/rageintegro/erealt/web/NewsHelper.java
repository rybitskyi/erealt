package biz.rageintegro.erealt.web;

import biz.rageintegro.erealt.domain.News;
import org.springframework.ui.ModelMap;

public class NewsHelper {

	public static void addNewsToModelMap(ModelMap modelMap) {
		modelMap.addAttribute("news", News.findAllNews());
		modelMap.addAttribute("news_creationDate_date_format", org.joda.time.format.DateTimeFormat.patternForStyle("S-", org.springframework.context.i18n.LocaleContextHolder.getLocale()));        
	}

	public static void addRecentNewsToModelMap(ModelMap modelMap) {
		modelMap.addAttribute("recentnews", News.findNewsEntries(0, 9));
		modelMap.addAttribute("news_creationDate_date_format", org.joda.time.format.DateTimeFormat.patternForStyle("S-", org.springframework.context.i18n.LocaleContextHolder.getLocale()));        
	}
}
