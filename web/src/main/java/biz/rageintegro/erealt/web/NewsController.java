package biz.rageintegro.erealt.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import biz.rageintegro.erealt.domain.News;

import java.io.*;
import java.net.*;

@RequestMapping("/news/**")
@Controller
public class NewsController {

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String list(ModelMap modelMap) {
        modelMap.addAttribute("pageTitle", "label.news");
		NewsHelper.addNewsToModelMap(modelMap);
        return "news/list";
    }

    @RequestMapping(value = "/news/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, ModelMap modelMap) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        modelMap.addAttribute("pageTitle", "label.news");
        News news = News.findNews(id);
		if (news == null) throw new IllegalArgumentException("An News Object can't be found for id=" + id);
		modelMap.addAttribute("newsdate", news.getCreationDate());
        modelMap.addAttribute("news_content",  getNewsContent("http://www.erealt.com.ua/news-repo/" + id + ".jspx"));
        modelMap.addAttribute("sourceref", "http://www." + news.getSourceref());
        modelMap.addAttribute("sourcename", news.getSourceref());
        NewsHelper.addRecentNewsToModelMap(modelMap);
        return "news/show";
    }

    private String getNewsContent(String url) {
        String l_Content = "";

        if (url  != null && url .length() > 0) {
            try {
                l_Content = GetContent(url ).toString();
            } catch (Exception e) {
                l_Content = e.toString();
            }
        }
        return l_Content;
    }

    private StringBuffer GetContent(String a_Url) throws Exception {
        URL l_URL = new URL(a_Url);
        BufferedReader l_Reader = new BufferedReader(new InputStreamReader(l_URL.openStream(), "UTF-8"));
        StringBuffer l_Result = new StringBuffer("");
        String l_InputLine = null;

        while ((l_InputLine = l_Reader.readLine()) != null)
            l_Result.append(l_InputLine);

        l_Reader.close();
        return (l_Result);
    }
}
