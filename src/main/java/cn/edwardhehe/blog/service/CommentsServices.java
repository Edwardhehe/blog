package cn.edwardhehe.blog.service;

import cn.edwardhehe.blog.dao.CommentsDao;
import cn.edwardhehe.blog.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lihao
 */
@Service
public class CommentsServices {
    @Autowired
    CommentsDao commentsDao;

    /**
     * 为这篇文章罗列全部的评论
     *
     * @param articleId
     * @return
     */
    public List<Comment> listComments(String articleId) {
        List<Comment> comments = commentsDao.findAllByArticleId(articleId);
        return comments;
    }

    /**
     * 给评论点赞
     * 事务，获取失败就回滚
     *
     * @param commentId
     */
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void supportComment(String commentId) {
        Comment comment = commentsDao.getOne(commentId);
        comment.setSupport(comment.getSupport() + 1);
        commentsDao.save(comment);
    }

    /**
     * 给不支持评论
     * 事务，获取失败就回滚
     *
     * @param commentId
     */
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void opposeComment(String commentId) {
        Comment comment = commentsDao.getOne(commentId);
        comment.setOppose(comment.getOppose() + 1);
        commentsDao.save(comment);
    }

    /**
     * 存储新的评论
     *
     * @param comment
     */
    public void saveComment(Comment comment) {
        commentsDao.save(comment);
    }


}
