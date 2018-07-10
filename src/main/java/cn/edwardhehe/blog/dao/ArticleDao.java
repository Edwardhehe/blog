package cn.edwardhehe.blog.dao;

import cn.edwardhehe.blog.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Edwardhehe
 */
@Repository
public interface ArticleDao extends JpaRepository<Article, String> {

    /**
     * 搜索论文题目
     *
     * @param title
     * @return
     */
    @Query("from Article where title like %:title%")
    List<Article> findByTitleLike(@Param("title") String title);

    /**
     * 根据文章类型寻找文章
     *
     * @param categoryId
     * @return
     */
    @Query("from Article where category = category")
    List<Article> findByCategory(@Param("categoryId") String categoryId);
}
