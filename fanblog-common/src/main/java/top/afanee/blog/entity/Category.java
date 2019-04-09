package top.afanee.blog.entity;

import com.baomidou.mybatisplus.enums.IdType;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author AFan
 * @since 2019-03-30
 */
@TableName("fanblog_category")
public class Category extends Model<Category> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;
    /**
     * 父节点id
     */
    private Integer pid;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 分类描述
     */
    private String desc;
    /**
     * 分类排名
     */
    private Integer rank;
    /**
     * 该分类下0允许发帖1不允许发帖
     */
    @TableField("allow_post")
    private Integer allowPost;
    /**
     * 是否是论坛分类Y是N不是
     */
    @TableField("show_in_bbs")
    private String showInBbs;
    /**
     * 是否是问答分类Y是N不是
     */
    @TableField("show_in_question")
    private String showInQuestion;
    /**
     * 是否是知识库分类Y是N不是
     */
    @TableField("show_in_knowledge")
    private String showInKnowledge;
    /**
     * 是否是考试区分类Y是N不是
     */
    @TableField("show_in_exam")
    private String showInExam;

    //非数据库字段
    @TableField(exist=false)
    List<Category> children = new ArrayList<Category>();
    
    public List<Category> getChildren() {
        return children;
    }

    
    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getAllowPost() {
        return allowPost;
    }

    public void setAllowPost(Integer allowPost) {
        this.allowPost = allowPost;
    }

    public String getShowInBbs() {
        return showInBbs;
    }

    public void setShowInBbs(String showInBbs) {
        this.showInBbs = showInBbs;
    }

    public String getShowInQuestion() {
        return showInQuestion;
    }

    public void setShowInQuestion(String showInQuestion) {
        this.showInQuestion = showInQuestion;
    }

    public String getShowInKnowledge() {
        return showInKnowledge;
    }

    public void setShowInKnowledge(String showInKnowledge) {
        this.showInKnowledge = showInKnowledge;
    }

    public String getShowInExam() {
        return showInExam;
    }

    public void setShowInExam(String showInExam) {
        this.showInExam = showInExam;
    }

    @Override
    protected Serializable pkVal() {
        return this.categoryId;
    }

    @Override
    public String toString() {
        return "Category{" +
        ", categoryId=" + categoryId +
        ", pid=" + pid +
        ", name=" + name +
        ", desc=" + desc +
        ", rank=" + rank +
        ", allowPost=" + allowPost +
        ", showInBbs=" + showInBbs +
        ", showInQuestion=" + showInQuestion +
        ", showInKnowledge=" + showInKnowledge +
        ", showInExam=" + showInExam +
        "}";
    }
}
