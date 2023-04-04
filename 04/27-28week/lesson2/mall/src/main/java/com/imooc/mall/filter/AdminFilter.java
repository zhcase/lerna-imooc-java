package com.imooc.mall.filter;

import com.imooc.mall.common.Constant;
import com.imooc.mall.model.pojo.User;
import com.imooc.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 管理员校验过滤器
 */
public class AdminFilter implements Filter {

    @Autowired
    UserService userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession httpSession = request.getSession();
        User currentUser = (User) httpSession.getAttribute(Constant.IMOOC_MALL_USER);
        if (currentUser == null) {
            PrintWriter out = new HttpServletResponseWrapper((HttpServletResponse) servletResponse).getWriter();
            out.write("{\n" +
                    "\t\"status\": 10007,\n" +
                    "\t\"msg\": \"NEED_LOGIN\",\n" +
                    "\t\"data\": null\n" +
                    "}");
            out.flush();
            out.close();
            return;
        }
        // 校验是否是管理员
        boolean adminRole = userService.checkAdminRole(currentUser);
        if (adminRole) {
//            是管理员执行操作
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            PrintWriter out = new HttpServletResponseWrapper((HttpServletResponse) servletResponse).getWriter();
            out.write("{\n" +
                    "\t\"status\": 10009,\n" +
                    "\t\"msg\": \"NEED_ADMIN\",\n" +
                    "\t\"data\": null\n" +
                    "}");
            out.flush();
            out.close();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
