package top.afanee.blog.po.query;

import top.afanee.blog.po.enums.OrderByEnum;

public class TopicQuery extends BaseQuery{
	private Integer topicId;
	
	private Integer userId;
	
	private boolean showContent;
	
	private OrderByEnum orderBy;
	
	private String startDate;
	
	private String endDate;
	
	private Integer pCategoryId;
	
	private Integer categoryId;

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public boolean isShowContent() {
		return showContent;
	}

	public void setShowContent(boolean showContent) {
		this.showContent = showContent;
	}

	public OrderByEnum getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(OrderByEnum orderBy) {
		this.orderBy = orderBy;
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

	public Integer getpCategoryId() {
		return pCategoryId;
	}

	public void setpCategoryId(Integer pCategoryId) {
		this.pCategoryId = pCategoryId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	
	
}
