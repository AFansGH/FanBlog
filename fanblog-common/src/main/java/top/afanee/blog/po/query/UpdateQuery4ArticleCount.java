package top.afanee.blog.po.query;

public class UpdateQuery4ArticleCount extends BaseQuery {
	
	private Integer articleId = 0;
	
	private boolean addReadCount;
	
	private boolean addLikeCount;
	
	private boolean addCommentCount;
	
	private boolean addCollectionCount;
	



	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public boolean isAddReadCount() {
		return addReadCount;
	}

	public void setAddReadCount(boolean addReadCount) {
		this.addReadCount = addReadCount;
	}

	public boolean isAddLikeCount() {
		return addLikeCount;
	}

	public void setAddLikeCount(boolean addLikeCount) {
		this.addLikeCount = addLikeCount;
	}

	public boolean isAddCommentCount() {
		return addCommentCount;
	}

	public void setAddCommentCount(boolean addCommentCount) {
		this.addCommentCount = addCommentCount;
	}

	public boolean isAddCollectionCount() {
		return addCollectionCount;
	}

	public void setAddCollectionCount(boolean addCollectionCount) {
		this.addCollectionCount = addCollectionCount;
	}
	
	
}
