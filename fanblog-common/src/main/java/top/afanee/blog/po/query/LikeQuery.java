package top.afanee.blog.po.query;

import top.afanee.blog.po.enums.ArticleType;

public class LikeQuery extends BaseQuery {
	private Integer articleId;
	
	private ArticleType articleType;
	
	private Integer userId;
	
	private String title;
	

	public LikeQuery() {
		super();
	}

	public LikeQuery(Integer articleId, ArticleType articleType,
			Integer userId) {
		super();
		this.articleId = articleId;
		this.articleType = articleType;
		this.userId = userId;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public ArticleType getArticleType() {
		return articleType;
	}

	public void setArticleType(ArticleType articleType) {
		this.articleType = articleType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
}
