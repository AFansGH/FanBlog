package top.afanee.blog.entity;

import com.baomidou.mybatisplus.enums.IdType;
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
@TableName("fanblog_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    private String email;
    @TableField("user_name")
    private String userName;
    private String password;
    @TableField("user_icon")
    private String userIcon;
    @TableField("user_bg")
    private String userBg;
    private String age;
    private String sex;
    private String characters;
    private Integer mark;
    private String address;
    private String work;
    private Date birthday;
    @TableField("register_time")
    private Date registerTime;
    @TableField("last_login_time")
    private Date lastLoginTime;
    @TableField("activation_code")
    private String activationCode;
    @TableField("account_state")
    private Integer accountState;

    
    //非数据库字段
    @TableField(exist = false)
    private Integer userPage = 1 ;
    
    public Integer getUserPage() {
        return userPage;
    }
    
    public void setUserPage(Integer userPage) {
        this.userPage = userPage;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserBg() {
        return userBg;
    }

    public void setUserBg(String userBg) {
        this.userBg = userBg;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Integer getAccountState() {
        return accountState;
    }

    public void setAccountState(Integer accountState) {
        this.accountState = accountState;
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "User{" +
        ", userId=" + userId +
        ", email=" + email +
        ", userName=" + userName +
        ", password=" + password +
        ", userIcon=" + userIcon +
        ", userBg=" + userBg +
        ", age=" + age +
        ", sex=" + sex +
        ", characters=" + characters +
        ", mark=" + mark +
        ", address=" + address +
        ", work=" + work +
        ", birthday=" + birthday +
        ", registerTime=" + registerTime +
        ", lastLoginTime=" + lastLoginTime +
        ", activationCode=" + activationCode +
        ", accountState=" + accountState +
        "}";
    }
}
