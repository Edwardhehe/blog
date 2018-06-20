/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edwardhehe.blog.interceptor;

import cn.edwardhehe.blog.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession(false);
        //如果看文章或者要登陆那就不拦截

        if (session== null) {
            //没有session id 就重新定向到登陆界面
            response.sendRedirect("/admin/login");
            return false;
        } else {
            Object user=session.getAttribute("user");
            if(user instanceof User){
                return true;
            }else{
                response.sendRedirect("/admin/login");
                return false;
            }
        }
    }
}
