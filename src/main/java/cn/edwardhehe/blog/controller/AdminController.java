

package cn.edwardhehe.blog.controller;

import cn.edwardhehe.blog.entity.Article;
import cn.edwardhehe.blog.entity.Category;
import cn.edwardhehe.blog.entity.User;
import cn.edwardhehe.blog.service.ArticleServices;
import cn.edwardhehe.blog.service.CategoryServices;
import cn.edwardhehe.blog.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author lihao
 */
@Controller
@RequestMapping("/admin")

public class AdminController {

    @Autowired
    UserServices userServices;
    @Autowired
    ArticleServices articleServices;
    @Autowired
    CategoryServices categoryServices;

    //管理员界面

    /**
     * 映射到文章浏览主页，显示文章内容
     *
     * @param model
     * @return
     */
    @RequestMapping("")
    public String admin(Model model) {
        List<Article> articles = articleServices.list();
        model.addAttribute("articles", articles);

        return "admin/index";
    }

    /**
     * 映射登陆界面
     * @param model
     * @return
     */
    //登陆界面
    @RequestMapping("/login")
    public String login(Model model) {
        String message = "请输入账号密码";
        //登陆状态信息
        model.addAttribute("message", message);
        return "admin/login";
    }

    /**
     * @param request
     * @param user
     * @param model
     * @return
     */
    //登陆动作
    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, User user, Model model) {

        if (userServices.login(request.getParameter("username"), request.getParameter("password"))) {
            //登陆成功

            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            //设置超时时间
            session.setMaxInactiveInterval(60 * 60 * 4);
            //登陆成功则继续显示管理员页面
            List<Article> articles = articleServices.list();
            model.addAttribute("articles", articles);
            return "admin/index";
        } else {
            String message = "登陆失败";
            //状态信息
            model.addAttribute("message", message);
            return "admin/login";
        }

    }

    /**
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete/{id}")
    public String doDelete(@PathVariable(name = "id") String id, Model model) {
        articleServices.delete(id);
        List<Article> articles = articleServices.list();
        model.addAttribute("articles", articles);

        return "admin/index";
    }


    /**
     *
     * @param model
     * @return
     */
    @RequestMapping("/write")
    public String writeArticle(Model model) {
        model.addAttribute("article", new Article());
        List<Category> categories = categoryServices.list();
        model.addAttribute("categories", categories);
        return "admin/write";
    }

    /**
     * 存储文章
     *
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String upLoadArticle(@ModelAttribute(value = "article") Article article,
                                HttpServletRequest request) {
        //生成写文章试件
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);

        //存储文章
        String categoryName = request.getParameter("category-selector");
        Category category = categoryServices.fingdByName(categoryName);
        article.setDate(dateString);
        article.setCategory(category);
        //默认前50个字为Summery
        article.setSummary(article.getContent().substring(0, 50));
        articleServices.save(article);
        return "admin/index";
    }

}
