package cn.edwardhehe.blog.service;


import cn.edwardhehe.blog.dao.ArticleDao;
import cn.edwardhehe.blog.dao.CategoryDao;
import cn.edwardhehe.blog.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lihao
 */
@Service
public class ArticleServices {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private CategoryDao categoryDao;


    public Article getById(String id) {
        Article article = articleDao.getOne(id);
        return article;
    }

    public List<Article> getByCategory(String categoryId) {
        List<Article> articles = articleDao.findByCategory(categoryId);
        return articles;
    }

    public List<Article> list() {
        return articleDao.findAll();
    }

    public void delete(String id) {
        articleDao.deleteById(id);
    }

    public void save(Article article) {
        articleDao.save(article);
    }

    public List<Article> search(String key) {
        return articleDao.findByTitleLike(key);
    }

}
