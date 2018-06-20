package cn.edwardhehe.blog.Controller;

import cn.edwardhehe.blog.entity.Article;
import cn.edwardhehe.blog.entity.Category;
import cn.edwardhehe.blog.service.ArticleServices;
import cn.edwardhehe.blog.service.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Edwardhehe
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleServices articleServices;
    @Autowired
    CategoryServices categoryServices;

    /*
    访问/articles返回front下面的index
     */
    @RequestMapping("")
    public String list(Model model){
        List<Article> articles = articleServices.list();
        List<Category> categories=categoryServices.list();
        model.addAttribute("articles", articles);
        model.addAttribute("categories", categories);
        return "front/index";
    }

    @RequestMapping("/details/{id}")
    public String getArticlesDetails(Model model,@PathVariable(name = "id") String id){
        Article article=articleServices.getById(id);

        model.addAttribute("article", article);
        return "front/articleDetails";
    }
}
