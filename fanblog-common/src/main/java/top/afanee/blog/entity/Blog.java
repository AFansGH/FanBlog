package top.afanee.blog.entity;

import com.baomidou.mybatisplus.enums.IdType;

import top.afanee.blog.po.enums.BlogStatusEnum;

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
@TableName("fanblog_blog")
public class Blog extends Model<Blog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "blog_id", type = IdType.AUTO)
    private Integer blogId;
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
    @TableField("blog_image")
    private String blogImage;
    /**
     * 博客缩列图
     */
    @TableField("blog_image_thum")
    private String blogImageThum;
    /**
     * 0是草稿 1是发布
     */
    @TableField("status")
    private BlogStatusEnum status;
    
    
    //=======================非数据库字段=================
    /**
     * 博客附件
     */
    @TableField(exist = false)
    private Attachment attachment;
    
    
    public Attachment getAttachment() {
        return attachment;
    }


    
    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }


    public BlogStatusEnum getStatus() {
        return status;
    }

    
    public void setStatus(BlogStatusEnum status) {
        this.status = status;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
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

    public String getBlogImage() {
        return blogImage;
    }

    public void setBlogImage(String blogImage) {
        this.blogImage = blogImage;
    }

    public String getBlogImageThum() {
        return blogImageThum;
    }

    public void setBlogImageThum(String blogImageThum) {
        this.blogImageThum = blogImageThum;
    }


    @Override
    protected Serializable pkVal() {
        return this.blogId;
    }

    @Override
    public String toString() {
        return "Blog{" +
        ", blogId=" + blogId +
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
        ", blogImage=" + blogImage +
        ", blogImageThum=" + blogImageThum +
        ", status=" + status +
        "}";
    }
}
