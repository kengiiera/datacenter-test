package com.recharges.recharge_frontend.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String currentPath = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();

        if (currentPath.startsWith(contextPath + "/javax.faces.resource/")
                || currentPath.endsWith("login.xhtml")) {
            chain.doFilter(request, response);
            return;
        }

        Object token = httpRequest.getSession().getAttribute("token");
        if (token == null || !(token instanceof String) || ((String) token).isEmpty()) {
            httpResponse.sendRedirect(contextPath + "/login.xhtml");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}


