package top.afanee.blog.entities;

import java.util.Date;
import javax.persistence.*;

@Table(name = "fanblog_user_friend")
public class UserFriend {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Id
    @Column(name = "friend_user_id")
    private Integer friendUserId;

    @Column(name = "user_icon")
    private String userIcon;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "friend_user_icon")
    private String friendUserIcon;

    @Column(name = "friend_user_name")
    private String friendUserName;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return friend_user_id
     */
    public Integer getFriendUserId() {
        return friendUserId;
    }

    /**
     * @param friendUserId
     */
    public void setFriendUserId(Integer friendUserId) {
        this.friendUserId = friendUserId;
    }

    /**
     * @return user_icon
     */
    public String getUserIcon() {
        return userIcon;
    }

    /**
     * @param userIcon
     */
    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return friend_user_icon
     */
    public String getFriendUserIcon() {
        return friendUserIcon;
    }

    /**
     * @param friendUserIcon
     */
    public void setFriendUserIcon(String friendUserIcon) {
        this.friendUserIcon = friendUserIcon;
    }

    /**
     * @return friend_user_name
     */
    public String getFriendUserName() {
        return friendUserName;
    }

    /**
     * @param friendUserName
     */
    public void setFriendUserName(String friendUserName) {
        this.friendUserName = friendUserName;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}