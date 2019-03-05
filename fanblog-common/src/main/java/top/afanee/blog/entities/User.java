package top.afanee.blog.entities;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Table(name = "fanblog_user")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    

    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @Column(name = "user_name")
    @NotEmpty(message = "账号不能为空")
    @Pattern(regexp = "[a-zA-Z0-9_]{5,18}$", message = "账号为字母开头，允许6-18字节，允许字母数字下划线")
    private String userName;
    
    @NotEmpty(message = "密码不能为空")
    @Pattern(regexp = "[a-zA-Z0-9_]{5,18}$", message = "密码长度允许6-18字节，支持字母数字下划线")
    private String password;

    @Column(name = "user_icon")
    private String userIcon;

    @Column(name = "user_bg")
    private String userBg;

    private String age;

    private String sex;

    private String characters;

    private Integer mark;

    private String address;

    private String work;

    private Date birthday;

    @Column(name = "register_time")
    private Date registerTime;

    @Column(name = "last_login_time")
    private Date lastLoginTime;

    @Column(name = "activation_code")
    private String activationCode;
    
    @Column(name = "account_state")
    private Boolean accountState;

    
    public Boolean getAccountState() {
        return accountState;
    }

    
    public void setAccountState(Boolean accountState) {
        this.accountState = accountState;
    }

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
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return user_bg
     */
    public String getUserBg() {
        return userBg;
    }

    /**
     * @param userBg
     */
    public void setUserBg(String userBg) {
        this.userBg = userBg;
    }

    /**
     * @return age
     */
    public String getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return characters
     */
    public String getCharacters() {
        return characters;
    }

    /**
     * @param characters
     */
    public void setCharacters(String characters) {
        this.characters = characters;
    }

    /**
     * @return mark
     */
    public Integer getMark() {
        return mark;
    }

    /**
     * @param mark
     */
    public void setMark(Integer mark) {
        this.mark = mark;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return work
     */
    public String getWork() {
        return work;
    }

    /**
     * @param work
     */
    public void setWork(String work) {
        this.work = work;
    }

    /**
     * @return birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return register_time
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * @param registerTime
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * @return last_login_time
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * @param lastLoginTime
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * @return activation_code
     */
    public String getActivationCode() {
        return activationCode;
    }

    /**
     * @param activationCode
     */
    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
}