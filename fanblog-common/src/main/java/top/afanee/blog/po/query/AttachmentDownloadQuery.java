package top.afanee.blog.po.query;

public class AttachmentDownloadQuery extends BaseQuery {
	private Integer userId;
	
	private Integer attachmentId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}
	
	
}
