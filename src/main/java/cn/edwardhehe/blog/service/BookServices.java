package cn.edwardhehe.blog.service;


import cn.edwardhehe.blog.dao.ArticleDao;
import cn.edwardhehe.blog.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServices {

    @Autowired
    private ArticleDao articleDao;
    /*
    获取文章
     */
    public Article getById(String id){
        Article article = articleDao.getOne(id);
        return article;
    }
    /*
    罗列文章
     */
    public List<Article> list(){
        return articleDao.findAll();
    }
    /*
    删除文章
     */
    public void delete(String id){
        articleDao.deleteById(id);
    }

    /*
    存储文章
     */
    public void save(Article article){
        articleDao.save(article);
    }

    /*
    搜索文章
     */
    public List<Article> search(String key){
        return articleDao.findByTitleLike(key);
    }

}