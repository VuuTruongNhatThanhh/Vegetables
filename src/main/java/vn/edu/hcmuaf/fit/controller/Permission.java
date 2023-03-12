package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.model.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "Permission")
public class Permission implements Filter {
    private String authorization;

    public void init(FilterConfig config) throws ServletException {
//        FilterConfig fConfig = null;
//        this.authorization = fConfig.getInitParameter("authorization");
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(request, response);
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resq = (HttpServletResponse) response;
//        HttpSession session = req.getSession();

    }

}

