package cn.edwardhehe.blog.controller;

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
 * @author lihao
 */
@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryServices categoryServices;

    @Autowired
    ArticleServices articleServices;

    @RequestMapping("/{categoryId}")
    public String getArticlesByCategory(Model model,
                                        @PathVariable(name = "categoryId") String categoryId) {
        List<Article> articles = articleServices.getByCategory(categoryId);
        List<Category> categories = categoryServices.list();
        model.addAttribute("articles", articles);
        model.addAttribute("categories", categories);

        return "front/index";
    }
}
