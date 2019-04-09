package top.afanee.blog.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import top.afanee.blog.cache.CategoryCache;
import top.afanee.blog.utils.DateUtil;

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
@TableName("fanblog_topic")
public class Topic extends Model<Topic> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "topic_id", type = IdType.AUTO)
    private Integer topicId;
    /**
     * 0是普通贴 1是投票贴
     */
    @TableField("topic_type")
    private Integer topicType;
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
     * 最后评论时间
     */
    @TableField("last_comment_time")
    private Date lastCommentTime;
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
    /**
     * 0是普通帖 1是置顶帖
     */
    private Integer grade;
    /**
     * 0是非精华 1是精华
     */
    private Integer essence;
    @TableField("topic_image")
    private String topicImage;
    /**
     * 文章缩列图
     */
    @TableField("topic_image_thum")
    private String topicImageThum;
    
    //非数据库字段
    @TableField(exist = false)
    private Integer boolNew;        //是否是新发表
    
    @TableField(exist = false)
    private String categoryName;    //分类名称

    public Integer getBoolNew() {
        if(DateUtil.daysBetween(createTime, new Date()) > 2){
                    return 0;
            }
            return 1;
    }
    public void setBoolNew(Integer boolNew) {
        this.boolNew = boolNew;
    }
    
    
    public String getCategoryName() {
        return CategoryCache.getCategoryById(categoryId).getName();
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getTopicType() {
        return topicType;
    }

    public void setTopicType(Integer topicType) {
        this.topicType = topicType;
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

    public Date getLastCommentTime() {
        return lastCommentTime;
    }

    public void setLastCommentTime(Date lastCommentTime) {
        this.lastCommentTime = lastCommentTime;
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getEssence() {
        return essence;
    }

    public void setEssence(Integer essence) {
        this.essence = essence;
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

    @Override
    protected Serializable pkVal() {
        return this.topicId;
    }

    @Override
    public String toString() {
        return "Topic{" +
        ", topicId=" + topicId +
        ", topicType=" + topicType +
        ", pCategoryId=" + pCategoryId +
        ", categoryId=" + categoryId +
        ", title=" + title +
        ", content=" + content +
        ", summary=" + summary +
        ", userId=" + userId +
        ", userIcon=" + userIcon +
        ", userName=" + userName +
        ", createTime=" + createTime +
        ", lastCommentTime=" + lastCommentTime +
        ", commentCount=" + commentCount +
        ", readCount=" + readCount +
        ", collectionCount=" + collectionCount +
        ", likeCount=" + likeCount +
        ", grade=" + grade +
        ", essence=" + essence +
        ", topicImage=" + topicImage +
        ", topicImageThum=" + topicImageThum +
        "}";
    }
}
