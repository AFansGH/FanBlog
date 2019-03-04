package top.afanee.blog.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import top.afanee.blog.po.enums.DateTimePatternEnum;
import top.afanee.blog.utils.DateUtil;

@Table(name = "fanblog_ask")
public class Ask {
    @Id
    @Column(name = "ask_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer askId;

    /**
     * 组ID
     */
    @Column(name = "p_category_id")
    private Integer pCategoryId;

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
    
    @Transient 
    private String createTimeString;
    
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
     * 赏分
     */
    private Integer mark;

    /**
     * 最佳回复id
     */
    @Column(name = "best_answer_id")
    private Integer bestAnswerId;

    /**
     * 最佳答案作者ID
     */
    @Column(name = "best_answer_user_id")
    private Integer bestAnswerUserId;

    /**
     * 最佳作者头像
     */
    @Column(name = "best_answer_user_icon")
    private String bestAnswerUserIcon;

    /**
     * 最佳答案作者
     */
    @Column(name = "best_answer_user_name")
    private String bestAnswerUserName;

    /**
     * 0为已解决1为解决
     */
    @Column(name = "solve_type")
    private Integer solveType;

    /**
     * 内容
     */
    private String content;

    /**
     * 内容摘要
     */
    private String summary;

    @Column(name = "ask_image")
    private String askImage;

    /**
     * 文章缩列图
     */
    @Column(name = "ask_image_thum")
    private String askImageThum;

    /**
     * @return ask_id
     */
    public Integer getAskId() {
        return askId;
    }

    /**
     * @param askId
     */
    public void setAskId(Integer askId) {
        this.askId = askId;
    }

    /**
     * 获取组ID
     *
     * @return p_category_id - 组ID
     */
    public Integer getpCategoryId() {
        return pCategoryId;
    }

    /**
     * 设置组ID
     *
     * @param pCategoryId 组ID
     */
    public void setpCategoryId(Integer pCategoryId) {
        this.pCategoryId = pCategoryId;
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
    
    public String getCreateTimeString() {
        SimpleDateFormat sdf = new SimpleDateFormat(DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern());
        return DateUtil.friendly_time(sdf.format(createTime));
    }

    public void setCreateTimeString(String createTimeString) {
        this.createTimeString = createTimeString;
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
     * 获取赏分
     *
     * @return mark - 赏分
     */
    public Integer getMark() {
        return mark;
    }

    /**
     * 设置赏分
     *
     * @param mark 赏分
     */
    public void setMark(Integer mark) {
        this.mark = mark;
    }

    /**
     * 获取最佳回复id
     *
     * @return best_answer_id - 最佳回复id
     */
    public Integer getBestAnswerId() {
        return bestAnswerId;
    }

    /**
     * 设置最佳回复id
     *
     * @param bestAnswerId 最佳回复id
     */
    public void setBestAnswerId(Integer bestAnswerId) {
        this.bestAnswerId = bestAnswerId;
    }

    /**
     * 获取最佳答案作者ID
     *
     * @return best_answer_user_id - 最佳答案作者ID
     */
    public Integer getBestAnswerUserId() {
        return bestAnswerUserId;
    }

    /**
     * 设置最佳答案作者ID
     *
     * @param bestAnswerUserId 最佳答案作者ID
     */
    public void setBestAnswerUserId(Integer bestAnswerUserId) {
        this.bestAnswerUserId = bestAnswerUserId;
    }

    /**
     * 获取最佳作者头像
     *
     * @return best_answer_user_icon - 最佳作者头像
     */
    public String getBestAnswerUserIcon() {
        return bestAnswerUserIcon;
    }

    /**
     * 设置最佳作者头像
     *
     * @param bestAnswerUserIcon 最佳作者头像
     */
    public void setBestAnswerUserIcon(String bestAnswerUserIcon) {
        this.bestAnswerUserIcon = bestAnswerUserIcon;
    }

    /**
     * 获取最佳答案作者
     *
     * @return best_answer_user_name - 最佳答案作者
     */
    public String getBestAnswerUserName() {
        return bestAnswerUserName;
    }

    /**
     * 设置最佳答案作者
     *
     * @param bestAnswerUserName 最佳答案作者
     */
    public void setBestAnswerUserName(String bestAnswerUserName) {
        this.bestAnswerUserName = bestAnswerUserName;
    }

    /**
     * 获取0为已解决1为解决
     *
     * @return solve_type - 0为已解决1为解决
     */
    public Integer getSolveType() {
        return solveType;
    }

    /**
     * 设置0为已解决1为解决
     *
     * @param solveType 0为已解决1为解决
     */
    public void setSolveType(Integer solveType) {
        this.solveType = solveType;
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
     * @return ask_image
     */
    public String getAskImage() {
        return askImage;
    }

    /**
     * @param askImage
     */
    public void setAskImage(String askImage) {
        this.askImage = askImage;
    }

    /**
     * 获取文章缩列图
     *
     * @return ask_image_thum - 文章缩列图
     */
    public String getAskImageThum() {
        return askImageThum;
    }

    /**
     * 设置文章缩列图
     *
     * @param askImageThum 文章缩列图
     */
    public void setAskImageThum(String askImageThum) {
        this.askImageThum = askImageThum;
    }
}