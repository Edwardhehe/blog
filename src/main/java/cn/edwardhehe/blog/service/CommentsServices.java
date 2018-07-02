package cn.edwardhehe.blog.service;

import cn.edwardhehe.blog.dao.CommentsDao;
import cn.edwardhehe.blog.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lihao
 */
@Service
public class CommentsServices {
    @Autowired
    CommentsDao commentsDao;

    /**
     * 寻找
     *
     * @param articleId
     * @return
     */
    List<Comment> list(String articleId) {
        List<Comment> comments = commentsDao.findAllByArticleId(articleId);
        return comments;
    }


}
