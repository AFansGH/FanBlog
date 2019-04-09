package top.afanee.blog.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

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
@TableName("fanblog_ask")
public class Ask extends Model<Ask> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ask_id", type = IdType.AUTO)
    private Integer askId;
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
    @TableField("ask_image")
    private String askImage;
    /**
     * 文章缩列图
     */
    @TableField("ask_image_thum")
    private String askImageThum;
    /**
     * 赏分
     */
    private Integer mark;
    /**
     * 最佳回复id
     */
    @TableField("best_answer_id")
    private Integer bestAnswerId;
    /**
     * 最佳答案作者ID
     */
    @TableField("best_answer_user_id")
    private Integer bestAnswerUserId;
    /**
     * 最佳作者头像
     */
    @TableField("best_answer_user_icon")
    private String bestAnswerUserIcon;
    /**
     * 最佳答案作者
     */
    @TableField("best_answer_user_name")
    private String bestAnswerUserName;
    /**
     * 0为已解决1为解决
     */
    @TableField("solve_type")
    private Integer solveType;
    
    
    /*//非数据库字段
    @TableField(exist = false)
    private String categoryName;    //分类名称
    
    public String getCategoryName() {
        return CategoryCache.getCategoryById(categoryId).getName();
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
*/

    public Integer getAskId() {
        return askId;
    }

    public void setAskId(Integer askId) {
        this.askId = askId;
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

    public String getAskImage() {
        return askImage;
    }

    public void setAskImage(String askImage) {
        this.askImage = askImage;
    }

    public String getAskImageThum() {
        return askImageThum;
    }

    public void setAskImageThum(String askImageThum) {
        this.askImageThum = askImageThum;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Integer getBestAnswerId() {
        return bestAnswerId;
    }

    public void setBestAnswerId(Integer bestAnswerId) {
        this.bestAnswerId = bestAnswerId;
    }

    public Integer getBestAnswerUserId() {
        return bestAnswerUserId;
    }

    public void setBestAnswerUserId(Integer bestAnswerUserId) {
        this.bestAnswerUserId = bestAnswerUserId;
    }

    public String getBestAnswerUserIcon() {
        return bestAnswerUserIcon;
    }

    public void setBestAnswerUserIcon(String bestAnswerUserIcon) {
        this.bestAnswerUserIcon = bestAnswerUserIcon;
    }

    public String getBestAnswerUserName() {
        return bestAnswerUserName;
    }

    public void setBestAnswerUserName(String bestAnswerUserName) {
        this.bestAnswerUserName = bestAnswerUserName;
    }

    public Integer getSolveType() {
        return solveType;
    }

    public void setSolveType(Integer solveType) {
        this.solveType = solveType;
    }

    @Override
    protected Serializable pkVal() {
        return this.askId;
    }

    @Override
    public String toString() {
        return "Ask{" +
        ", askId=" + askId +
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
        ", askImage=" + askImage +
        ", askImageThum=" + askImageThum +
        ", mark=" + mark +
        ", bestAnswerId=" + bestAnswerId +
        ", bestAnswerUserId=" + bestAnswerUserId +
        ", bestAnswerUserIcon=" + bestAnswerUserIcon +
        ", bestAnswerUserName=" + bestAnswerUserName +
        ", solveType=" + solveType +
        "}";
    }
}
