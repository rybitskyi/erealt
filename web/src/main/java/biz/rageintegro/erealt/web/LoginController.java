package biz.rageintegro.erealt.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;

//@RequestMapping("/login/**")
@Controller
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "forward:/pages/login.jsf";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(ModelMap modelMap) {
        modelMap.addAttribute("pageTitle", "security.login.title");
        return "login";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String processLogin(@RequestParam("j_username")String username,
                               @RequestParam("j_password")String password,
                               ModelMap modelMap) {
        modelMap.addAttribute("pageTitle", "security.login.title");
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
            authToken.setDetails(authentication.getDetails());

            Authentication newAuth = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(newAuth);
        } catch (UsernameNotFoundException unfe) {
            //todo: show error message
        }
        return null;
    }
}
