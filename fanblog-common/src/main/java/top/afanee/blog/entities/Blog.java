package top.afanee.blog.entities;

import java.util.Date;
import javax.persistence.*;

@Table(name = "fanblog_blog")
public class Blog {
    @Id
    @Column(name = "blog_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blogId;

    /**
     * 类型ID
     */
    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * 标题
     */
    private String title;

    /**
     * 作者ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 作者头像
     */
    @Column(name = "user_icon")
    private String userIcon;

    /**
     * 作者名字
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 发表时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 评论人数
     */
    @Column(name = "comment_count")
    private Integer commentCount;

    /**
     * 阅读人数
     */
    @Column(name = "read_count")
    private Integer readCount;

    /**
     * 收藏人数
     */
    @Column(name = "collection_count")
    private Integer collectionCount;

    /**
     * 喜欢人数
     */
    @Column(name = "like_count")
    private Integer likeCount;

    /**
     * 0是草稿 1是发布
     */
    private Integer status;

    /**
     * 内容
     */
    private String content;

    /**
     * 内容摘要
     */
    private String summary;

    @Column(name = "blog_image")
    private String blogImage;

    /**
     * 博客缩列图
     */
    @Column(name = "blog_image_thum")
    private String blogImageThum;

    /**
     * @return blog_id
     */
    public Integer getBlogId() {
        return blogId;
    }

    /**
     * @param blogId
     */
    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    /**
     * 获取类型ID
     *
     * @return category_id - 类型ID
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置类型ID
     *
     * @param categoryId 类型ID
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取作者ID
     *
     * @return user_id - 作者ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置作者ID
     *
     * @param userId 作者ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取作者头像
     *
     * @return user_icon - 作者头像
     */
    public String getUserIcon() {
        return userIcon;
    }

    /**
     * 设置作者头像
     *
     * @param userIcon 作者头像
     */
    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    /**
     * 获取作者名字
     *
     * @return user_name - 作者名字
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置作者名字
     *
     * @param userName 作者名字
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取发表时间
     *
     * @return create_time - 发表时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置发表时间
     *
     * @param createTime 发表时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取评论人数
     *
     * @return comment_count - 评论人数
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * 设置评论人数
     *
     * @param commentCount 评论人数
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * 获取阅读人数
     *
     * @return read_count - 阅读人数
     */
    public Integer getReadCount() {
        return readCount;
    }

    /**
     * 设置阅读人数
     *
     * @param readCount 阅读人数
     */
    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    /**
     * 获取收藏人数
     *
     * @return collection_count - 收藏人数
     */
    public Integer getCollectionCount() {
        return collectionCount;
    }

    /**
     * 设置收藏人数
     *
     * @param collectionCount 收藏人数
     */
    public void setCollectionCount(Integer collectionCount) {
        this.collectionCount = collectionCount;
    }

    /**
     * 获取喜欢人数
     *
     * @return like_count - 喜欢人数
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * 设置喜欢人数
     *
     * @param likeCount 喜欢人数
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * 获取0是草稿 1是发布
     *
     * @return status - 0是草稿 1是发布
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0是草稿 1是发布
     *
     * @param status 0是草稿 1是发布
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取内容摘要
     *
     * @return summary - 内容摘要
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置内容摘要
     *
     * @param summary 内容摘要
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return blog_image
     */
    public String getBlogImage() {
        return blogImage;
    }

    /**
     * @param blogImage
     */
    public void setBlogImage(String blogImage) {
        this.blogImage = blogImage;
    }

    /**
     * 获取博客缩列图
     *
     * @return blog_image_thum - 博客缩列图
     */
    public String getBlogImageThum() {
        return blogImageThum;
    }

    /**
     * 设置博客缩列图
     *
     * @param blogImageThum 博客缩列图
     */
    public void setBlogImageThum(String blogImageThum) {
        this.blogImageThum = blogImageThum;
    }
}