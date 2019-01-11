package com.droidsandfriends;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        UserService userService = UserServiceFactory.getUserService();
        if (userService.isUserLoggedIn()) {
            User user = userService.getCurrentUser();
            boolean isAdmin = userService.isUserAdmin();
            request.setAttribute("userId", user.getUserId());
            request.setAttribute("username", user.getEmail());
            request.setAttribute("isAdmin", isAdmin);
            request.setAttribute("logoutUrl", userService.createLogoutURL(request.getRequestURI()));
        } else {
            String requestUri = request.getRequestURI();
            String loginUrl = userService.createLoginURL(requestUri);
            if (!loginUrl.startsWith(requestUri)) {
                // Redirect to login page.
                response.sendRedirect(loginUrl);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
