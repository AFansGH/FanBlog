package top.afanee.blog.po.query;

import top.afanee.blog.po.enums.ArticleType;
import top.afanee.blog.po.enums.OrderByEnum;

public class CollectionQuery extends BaseQuery {
	private Integer articleId;
	
	private ArticleType articleType;
	
	private Integer userId;
	
	private String title;
	
	private String startDate;
	
	private String endDate;
	
	private OrderByEnum orderBy;
	

	public CollectionQuery() {
		super();
	}

	public CollectionQuery(Integer articleId, ArticleType articleType,
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public OrderByEnum getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(OrderByEnum orderBy) {
		this.orderBy = orderBy;
	}
	
	
	
}
