package top.afanee.blog.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author AFan
 * @since 2019-04-07
 */
@TableName("fanblog_blog_category")
public class BlogCategory extends Model<BlogCategory> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;
    private Integer userId;
    private String name;
    private Integer rank;

    
    //================非数据库字段=========
    @TableField(exist = false)
    private Integer blogCount;

    
    public Integer getBlogCount() {
        return blogCount;
    }

    
    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    protected Serializable pkVal() {
        return this.categoryId;
    }

    @Override
    public String toString() {
        return "BlogCategory{" +
        ", categoryId=" + categoryId +
        ", userId=" + userId +
        ", name=" + name +
        ", rank=" + rank +
        "}";
    }
}
