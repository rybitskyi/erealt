package biz.rageintegro.erealt.web;

import biz.rageintegro.erealt.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

/**
 * User: yury.ribitsky
 * Date: 8/8/11
 */

abstract public class GenericContoller<T> {

    protected GenericContoller() {
    }

    @Autowired
    private UserContext userContext;

    public UserContext getUserContext() {
        return userContext;
    }

    public void setUserContext(UserContext userContext) {
        this.userContext = userContext;
    }

    protected void initModelMap(Class clazz, ModelMap modelMap) {
        initModelMap(AbstractObject.getSystemName(clazz), modelMap);
    }

    protected void initModelMap(String systemName, ModelMap modelMap) {
        modelMap.addAttribute("pageTitle", "label." + systemName);
        modelMap.addAttribute("pageDescription", "page." + systemName + ".description");
        modelMap.addAttribute("pageKeywords", "page." + systemName + ".keywords");
        modelMap.addAttribute("userContext", userContext);
        modelMap.addAttribute("date_format", org.joda.time.format.DateTimeFormat.patternForStyle("S-", org.springframework.context.i18n.LocaleContextHolder.getLocale()));
        NewsHelper.addNewsToModelMap(modelMap);
    }
}
