

package cn.edwardhehe.blog.interceptor;

import cn.edwardhehe.blog.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author lihao
 */
public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession(false);
        //如果看文章或者要登陆那就不拦截

        if (session == null) {
            //没有session id 就重新定向到登陆界面
            response.sendRedirect("/admin/login");
            return false;
        } else {
            Object user = session.getAttribute("user");
            if (user instanceof User) {
                return true;
            } else {
                response.sendRedirect("/admin/login");
                return false;
            }
        }
    }
}
