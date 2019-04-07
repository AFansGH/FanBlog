package top.afanee.blog.po.model;


public class SessionUser {
    
    private Integer userId;
    private String userName;
    private String userIcon;
    
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userid) {
        this.userId = userid;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserIcon() {
        return userIcon;
    }
    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }
}
