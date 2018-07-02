package cn.edwardhehe.blog.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户评论实体类
 *
 * @author lihao
 */
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", columnDefinition = "varchar(64) binary")
    private String id;

    @Column(name = "article_id", columnDefinition = "varchar(64) binary")
    private String articleId;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "support")
    private int support;

    @Column(name = "oppose")
    private int oppose;

    /**
     * 注解生成时间
     */
    @Column(name = "create_time")
    @CreatedDate
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSupport() {
        return support;
    }

    public void setSupport(int support) {
        this.support = support;
    }

    public int getOppose() {
        return oppose;
    }

    public void setOppose(int oppose) {
        this.oppose = oppose;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
