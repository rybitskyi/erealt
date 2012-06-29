package biz.rageintegro.erealt.web;

import biz.rageintegro.erealt.domain.PhotoManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

/**
 * User: yury.ribitsky
 * Date: 8/8/11
 */
@Component
public class MainMenuFilter implements Filter {

    public static String[] URLS = {WebConstants.SALE_FLAT,
            WebConstants.SALE_HOUSE,
            WebConstants.SALE_LAND,
            WebConstants.LEASE_FLAT,
            WebConstants.LEASE_HOUSE,
            WebConstants.PRESS,
            WebConstants.SERVICES};

    private final static Logger logger = Logger.getLogger(MainMenuFilter.class);

    @Autowired
    private UserContext userContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userContext.getSelectedMainMenu();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String[] arr = request.getRequestURI().split("/");
        if (logger.isDebugEnabled()) {
            logger.debug("err.length = " + arr.length);
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            for (String url : URLS) {
                if (url.equals(arr[i])) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("url = " + url);
                    }
                    userContext.setSelectedMainMenu(url);
                    break;
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
