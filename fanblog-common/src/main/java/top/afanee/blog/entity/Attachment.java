package top.afanee.blog.entity;

import com.baomidou.mybatisplus.enums.IdType;

import top.afanee.blog.po.enums.FileTopicType;

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
@TableName("fanblog_attachment")
public class Attachment extends Model<Attachment> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "attachment_id", type = IdType.AUTO)
    private Integer attachmentId;
    /**
     * 帖子ID
     */
    private Integer topicId;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件地址
     */
    private String fileUrl;
    /**
     * 下载所需积分
     */
    private Integer downloadMark;
    /**
     * 下载次数
     */
    private Integer downloadCount;
    /**
     * T附件属于论坛 B属于博客
     */
    private FileTopicType topicType;
    
    
    
    
    public FileTopicType getTopicType() {
        return topicType;
    }

    
    public void setTopicType(FileTopicType topicType) {
        this.topicType = topicType;
    }

    public Integer getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getDownloadMark() {
        return downloadMark;
    }

    public void setDownloadMark(Integer downloadMark) {
        this.downloadMark = downloadMark;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }


    @Override
    protected Serializable pkVal() {
        return this.attachmentId;
    }

    @Override
    public String toString() {
        return "Attachment{" +
        ", attachmentId=" + attachmentId +
        ", topicId=" + topicId +
        ", fileName=" + fileName +
        ", fileUrl=" + fileUrl +
        ", downloadMark=" + downloadMark +
        ", downloadCount=" + downloadCount +
        ", topicType=" + topicType +
        "}";
    }
}
