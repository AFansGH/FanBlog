package top.afanee.blog.entities;

import java.util.Date;
import javax.persistence.*;

@Table(name = "fanblog_sign_in")
public class SignIn {
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_icon")
    private String userIcon;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "sign_date")
    private Date signDate;

    @Column(name = "sign_time")
    private Date signTime;

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
     * @return sign_date
     */
    public Date getSignDate() {
        return signDate;
    }

    /**
     * @param signDate
     */
    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    /**
     * @return sign_time
     */
    public Date getSignTime() {
        return signTime;
    }

    /**
     * @param signTime
     */
    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }
}