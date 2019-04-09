package top.afanee.blog.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import top.afanee.blog.cache.CategoryCache;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author AFan
 * @since 2019-03-29
 */
@TableName("fanblog_knowledge")
public class Knowledge extends Model<Knowledge> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "knowledge_id", type = IdType.AUTO)
    private Integer knowledgeId;
    /**
     * 组ID
     */
    @TableField("p_category_id")
    private Integer pCategoryId;
    /**
     * 类型ID
     */
    @TableField("category_id")
    private Integer categoryId;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 内容摘要
     */
    private String summary;
    /**
     * 作者ID
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 作者头像
     */
    @TableField("user_icon")
    private String userIcon;
    /**
     * 作者名字
     */
    @TableField("user_name")
    private String userName;
    /**
     * 发表时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT+8")
    @TableField("create_time")
    private Date createTime;
    /**
     * 评论人数
     */
    @TableField("comment_count")
    private Integer commentCount;
    /**
     * 阅读人数
     */
    @TableField("read_count")
    private Integer readCount;
    /**
     * 收藏人数
     */
    @TableField("collection_count")
    private Integer collectionCount;
    /**
     * 喜欢人数
     */
    @TableField("like_count")
    private Integer likeCount;
    @TableField("topic_image")
    private String topicImage;
    /**
     * 文章缩列图
     */
    @TableField("topic_image_thum")
    private String topicImageThum;
    /**
     * 0是未审核 1是审核
     */
    private Integer status;
    
    
    //非数据库字段
    @TableField(exist = false)
    private String categoryName;    //分类名称
    
    public String getCategoryName() {
        return CategoryCache.getCategoryById(categoryId).getName();
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public Integer getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(Integer knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public Integer getpCategoryId() {
        return pCategoryId;
    }

    public void setpCategoryId(Integer pCategoryId) {
        this.pCategoryId = pCategoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(Integer collectionCount) {
        this.collectionCount = collectionCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String getTopicImage() {
        return topicImage;
    }

    public void setTopicImage(String topicImage) {
        this.topicImage = topicImage;
    }

    public String getTopicImageThum() {
        return topicImageThum;
    }

    public void setTopicImageThum(String topicImageThum) {
        this.topicImageThum = topicImageThum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.knowledgeId;
    }

    @Override
    public String toString() {
        return "Knowledge{" +
        ", knowledgeId=" + knowledgeId +
        ", pCategoryId=" + pCategoryId +
        ", categoryId=" + categoryId +
        ", title=" + title +
        ", content=" + content +
        ", summary=" + summary +
        ", userId=" + userId +
        ", userIcon=" + userIcon +
        ", userName=" + userName +
        ", createTime=" + createTime +
        ", commentCount=" + commentCount +
        ", readCount=" + readCount +
        ", collectionCount=" + collectionCount +
        ", likeCount=" + likeCount +
        ", topicImage=" + topicImage +
        ", topicImageThum=" + topicImageThum +
        ", status=" + status +
        "}";
    }
}
