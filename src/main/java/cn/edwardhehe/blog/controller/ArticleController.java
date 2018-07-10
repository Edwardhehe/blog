package cn.edwardhehe.blog.controller;

import cn.edwardhehe.blog.entity.Article;
import cn.edwardhehe.blog.entity.Category;
import cn.edwardhehe.blog.entity.Comment;
import cn.edwardhehe.blog.service.ArticleServices;
import cn.edwardhehe.blog.service.CategoryServices;
import cn.edwardhehe.blog.service.CommentsServices;
import cn.edwardhehe.blog.utils.TokenProccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 访问article的模板映射
 *
 * @author lihao
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleServices articleServices;
    @Autowired
    CategoryServices categoryServices;
    @Autowired
    CommentsServices commentsServices;


    @RequestMapping("")
    public String list(Model model) {
        List<Article> articles = articleServices.list();
        List<Category> categories = categoryServices.list();
        model.addAttribute("articles", articles);
        model.addAttribute("categories", categories);
        return "front/index";
    }

    /**
     * 获取文章内容及文章对应的评论
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/details/{id}")
    public String getArticlesDetails(Model model, @PathVariable(name = "id") String id) {
        Article article = articleServices.getById(id);
        List<Comment> comments = commentsServices.listComments(id);
        model.addAttribute("comment", new Comment());
        model.addAttribute("article", article);
        model.addAttribute("comments", comments);
        return "front/articleDetails";
    }

    /**
     * 提交评论
     *
     * @param model
     * @param comment
     * @param request
     * @return
     */
    @RequestMapping(value = "/submitComment", method = RequestMethod.POST)
    public String submitComment(Model model,
                                @ModelAttribute(value = "comment") Comment comment,
                                HttpServletRequest request) {

        //验证是否为重复提交
        String token = TokenProccessor.getInstance().makeToken(comment.getContent());
        request.setAttribute("token", token);


        if (!isRepeatSubmit(request)) {
            comment.setCreateTime(new Date());
            commentsServices.saveComment(comment);
        }

        String articleId = comment.getArticleId();
        //提取文章的id
        Article article = articleServices.getById(articleId);
        List<Comment> comments = commentsServices.listComments(articleId);
        model.addAttribute("article", article);
        model.addAttribute("comments", comments);
        model.addAttribute("comment", new Comment());
        return "front/articleDetails";
    }


    /**
     * 服务器端利用session防止重复提交
     *
     * @param request
     * @return TRUE 为重复提交 false为不是重复提交
     */
    private boolean isRepeatSubmit(HttpServletRequest request) {
        //获取session,没有则生成session
        HttpSession session = request.getSession(true);

        String serverToken = (String) session.getAttribute("token");
        String clientToken = (String) request.getAttribute("token");
        if (serverToken == null) {
            //如果服务器端没有令牌则为初次提交
            session.setAttribute("token", clientToken);
            return false;
        } else if (serverToken.equals(clientToken)) {
            //服务器端的session和客户端session一致，则认为提交了相同内容
            return true;
        } else {
            session.setAttribute("token", clientToken);
            return false;
        }
    }
}
