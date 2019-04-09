package top.afanee.blog.entity;

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
@TableName("fanblog_user_friend")
public class UserFriend extends Model<UserFriend> {

    private static final long serialVersionUID = 1L;

    @TableId("user_id")
    private Integer userId;
    @TableField("friend_user_id")
    private Integer friendUserId;
    @TableField("user_icon")
    private String userIcon;
    @TableField("user_name")
    private String userName;
    @TableField("friend_user_icon")
    private String friendUserIcon;
    @TableField("friend_user_name")
    private String friendUserName;
    @TableField("create_time")
    private Date createTime;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(Integer friendUserId) {
        this.friendUserId = friendUserId;
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

    public String getFriendUserIcon() {
        return friendUserIcon;
    }

    public void setFriendUserIcon(String friendUserIcon) {
        this.friendUserIcon = friendUserIcon;
    }

    public String getFriendUserName() {
        return friendUserName;
    }

    public void setFriendUserName(String friendUserName) {
        this.friendUserName = friendUserName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "UserFriend{" +
        ", userId=" + userId +
        ", friendUserId=" + friendUserId +
        ", userIcon=" + userIcon +
        ", userName=" + userName +
        ", friendUserIcon=" + friendUserIcon +
        ", friendUserName=" + friendUserName +
        ", createTime=" + createTime +
        "}";
    }
}
