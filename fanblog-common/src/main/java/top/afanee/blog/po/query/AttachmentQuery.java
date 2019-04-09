package top.afanee.blog.po.query;

import top.afanee.blog.po.enums.FileTopicType;

public class AttachmentQuery extends BaseQuery {
	private Integer attachmentId;

    private Integer topicId;
    
    private FileTopicType fileTopicType;

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

	public FileTopicType getFileTopicType() {
		return fileTopicType;
	}

	public void setFileTopicType(FileTopicType fileTopicType) {
		this.fileTopicType = fileTopicType;
	}
    
    
}
