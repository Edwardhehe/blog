package cn.edwardhehe.blog.Controller;

import cn.edwardhehe.blog.entity.Article;
import cn.edwardhehe.blog.entity.Category;
import cn.edwardhehe.blog.service.ArticleServices;
import cn.edwardhehe.blog.service.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    /*
    通过文章类型选取显示页面
     */


    /*
    写markDown文章界面
     */
    @RequestMapping("/write")
    public String writeArticle(Model model){
        model.addAttribute("article", new Article());
        return "front/write";
    }

    /**
     * 存储文章
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String upLoadArticle(@ModelAttribute(value = "article") Article article){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        article.setDate(dateString);
        articleServices.save(article);
        return "front/write";
    }
}
