package cn.edwardhehe.blog.Controller;

import cn.edwardhehe.blog.entity.Article;
import cn.edwardhehe.blog.service.ArticleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleServices services;

    /*
    访问/articles返回front下面的index
     */
    @RequestMapping("")
    public String list(Model model){
        List<Article> articles = services.list();
        model.addAttribute("articles", articles);

        return "front/index";
    }
}
