package cn.edwardhehe.blog.dao;

import cn.edwardhehe.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CommentsDao extends JpaRepository<Comment, String> {
    /**
     * 根据文章id加载评论
     *
     * @param articleId
     * @return
     */

    @Query("from Comment where articleId= :articleId")
    List<Comment> findAllByArticleId(@Param("articleId") String articleId);

}
