/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edwardhehe.blog.Controller;

import cn.edwardhehe.blog.entity.Article;
import cn.edwardhehe.blog.entity.User;
import cn.edwardhehe.blog.service.ArticleServices;
import cn.edwardhehe.blog.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    UserServices userServices;
    @Autowired
    ArticleServices articleServices;

    @RequestMapping("")
    public String admin(Model model) {
        List<Article> articles = articleServices.list();
        model.addAttribute("articles", articles);

        return "admin/index";
    }


    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, User user, Model model) {
        if (userServices.login(request.getParameter("username"), request.getParameter("password"))) {
            System.out.println("登陆成功");
        }
        return "admin/index";
    }

    @RequestMapping(value = "/delete/{id}")
    public String doDelete(@PathVariable(name = "id") String id, Model model) {
        articleServices.delete(id);
        List<Article> articles = articleServices.list();
        model.addAttribute("articles", articles);

        return "admin/index";
    }

}
