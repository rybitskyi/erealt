package biz.rageintegro.erealt.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import biz.rageintegro.erealt.domain.AccessUser;
import biz.rageintegro.erealt.security.SecurityHelper;

import javax.validation.Valid;
import java.util.ResourceBundle;

@RequestMapping("/member/**")
@Controller
public class MemberController {

    @RequestMapping(value = "/member", method = RequestMethod.GET)
    public String main(ModelMap modelMap) {
        return "redirect:/member/myobjects";
    }

    @RequestMapping(value = "/member/myobjects", method = RequestMethod.GET)
    public String showMyObjects(ModelMap modelMap) {
        modelMap.addAttribute("pageTitle", "member.cabinet.title");
        modelMap.addAttribute("pageDescription", "member.cabinet.myobjects");

        modelMap.addAttribute("myobjects", SecurityHelper.getCurrentUser().findAllUserObjects());

        modelMap.addAttribute("myObject_creationDate_date_format", org.joda.time.format.DateTimeFormat.patternForStyle("S-", org.springframework.context.i18n.LocaleContextHolder.getLocale()));
        return "member/myObjects";
    }

    @RequestMapping(value = "/member/settings", method = RequestMethod.GET)
    public String showSettings(ModelMap modelMap) {
        modelMap.addAttribute("pageTitle", "member.cabinet.settings");
        modelMap.addAttribute("pageDescription", "member.cabinet.settings");
        modelMap.addAttribute("user", SecurityHelper.getCurrentUser());
        return "member/settings";
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String updateSettings(@Valid AccessUser user, BindingResult result, ModelMap modelMap) {
        if (user == null) throw new IllegalArgumentException("A user is required");
        if (result.hasErrors()) {
            //todo: show changesettings errors
        }
        user.merge();
        return "redirect:/member/changesettingssuccess";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String changeSettingsSuccess(ModelMap modelMap) {
        modelMap.addAttribute("pageTitle", "member.cabinet.settings");
        modelMap.addAttribute("pageDescription", "member.cabinet.settings");
        modelMap.addAttribute("user", SecurityHelper.getCurrentUser());
        return "member/changeSettingsSuccess";
    }
}
