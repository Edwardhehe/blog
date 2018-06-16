package cn.edwardhehe.blog.dao;

import cn.edwardhehe.blog.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangchenghao on 2017/7/31.
 */
@Repository
public interface ArticleDao extends JpaRepository<Article, String>{

    List<Article> findAllByCategory_Name(String name);

    @Query("from Article where title like %:title%")
    List<Article> findByTitleLike(@Param("title") String title);

    @Query("from Article where category = category")
    List<Article> findByCategory(@Param("categoryId")String categoryId);
}
