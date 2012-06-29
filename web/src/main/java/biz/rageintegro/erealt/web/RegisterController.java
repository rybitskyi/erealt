package biz.rageintegro.erealt.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;

import biz.rageintegro.erealt.domain.AccessUser;

import java.util.ResourceBundle;
import java.util.Formatter;

@RequestMapping("/register/**")
@Controller
public class RegisterController {

    private String emailText;

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private SimpleMailMessage outTemplateMessage;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterForm(ModelMap modelMap) {
        return "forward:/pages/register.jsf";
    }
/*

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerNewUser(@Valid AccessUser user, BindingResult result, ModelMap modelMap) {
        if (user == null) throw new IllegalArgumentException("A user is required");
        if (AccessUser.existRegisteredUser(user.getName())) {
            modelMap.addAttribute("userAlreadyExists", "User is already exists.");
            modelMap.addAttribute("user", user);
            return "register/newuser";
        }
        if (result.hasErrors()) {
            modelMap.addAttribute("user", user);
            return "register/newuser";
        }
        AccessUser oldUser = AccessUser.findAccessUserByName(user.getName());
        if (oldUser != null) {
        	oldUser.setCaption(user.getCaption());
        	oldUser.setPassword(user.getPassword());        	
        	oldUser.setTelephone(user.getTelephone());
        	oldUser.setVersion(1);  //todo: check it
        	oldUser.merge();
        }
        else {
        	user.persist();
        }
        return "redirect:/register/success";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String success(ModelMap modelMap) {
        modelMap.addAttribute("pageTitle", "security.register.title");
        return "register/success";
    }
*/

    @RequestMapping(value = "/register/recoverPassword", method = RequestMethod.GET)
    public String showRecoverPasswordForm(ModelMap modelMap) {
        modelMap.addAttribute("pageTitle", "security.recoverPassword.title");
        modelMap.addAttribute("user", new AccessUser());
        return "register/recoverPassword";
    }

    @RequestMapping(value = "/register/recoverPassword", method = RequestMethod.POST)
    public String recoverPassword(@Valid AccessUser user, BindingResult result, ModelMap modelMap) {
        if (user == null) throw new IllegalArgumentException("A user is required");
        if (user.getName() == null) throw new IllegalArgumentException("A username is required");
        AccessUser realUser = AccessUser.findAccessUserByName(user.getName());
        if (realUser == null) {
            modelMap.addAttribute("user", user);
            return "register/notExistUsername";
        } else {
            sendEmail(realUser);
            modelMap.addAttribute("user", realUser);
            return "register/successRecoverPassword";
        }
    }

    public String getEmailText() {
        return emailText;
    }

    public void setEmailText(String emailText) {
        this.emailText = emailText;
    }

    private void sendEmail(AccessUser user) {
        SimpleMailMessage msg = new SimpleMailMessage(outTemplateMessage);
        ResourceBundle bundle = ResourceBundle.getBundle("email");
        String str = bundle.getString("security.recover.emailText");
        str = str.replaceAll("\\{0\\}|\\{1\\}", user.getPassword());
        msg.setText(str);
        msg.setTo(user.getEmail());
        mailSender.send(msg);
    }
}
