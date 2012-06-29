package biz.rageintegro.erealt.jsf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: yury.ribitsky
 * Date: 6/19/12
 */
@ManagedBean
@RequestScoped
@Component
public class LoginBean {
    private String userName;
    private String password;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void authenticate() {
        try {
            final String userName = getUserName();
            final String password = getPassword();
            final UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
                    userName, password);

            final HttpServletRequest request = getRequest();
            authReq.setDetails(new WebAuthenticationDetails(request));

            /* perform authentication
            */
            final Authentication auth = authenticationManager.authenticate(authReq);

            /* initialize the security context.
            */
            final SecurityContext secCtx = SecurityContextHolder.getContext();
            secCtx.setAuthentication(auth);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login is success"));

            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            Object savedRequest = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("SPRING_SECURITY_SAVED_REQUEST_KEY");
            if (savedRequest != null) {
                response.sendRedirect(((org.springframework.security.web.savedrequest.DefaultSavedRequest) savedRequest).getRequestURI());
            }            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(),
                    e.getMessage()));
        }
    }

    private HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
