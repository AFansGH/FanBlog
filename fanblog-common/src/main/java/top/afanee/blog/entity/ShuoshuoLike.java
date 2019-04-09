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
 * @since 2019-03-31
 */
@TableName("fanblog_shuoshuo_like")
public class ShuoshuoLike extends Model<ShuoshuoLike> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("shuoshuo_id")
    private Integer shuoshuoId;
    @TableField("user_id")
    private Integer userId;
    @TableField("user_icon")
    private String userIcon;
    @TableField("user_name")
    private String userName;
    @TableField("create_time")
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShuoshuoId() {
        return shuoshuoId;
    }

    public void setShuoshuoId(Integer shuoshuoId) {
        this.shuoshuoId = shuoshuoId;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ShuoshuoLike{" +
        ", id=" + id +
        ", shuoshuoId=" + shuoshuoId +
        ", userId=" + userId +
        ", userIcon=" + userIcon +
        ", userName=" + userName +
        ", createTime=" + createTime +
        "}";
    }
}
