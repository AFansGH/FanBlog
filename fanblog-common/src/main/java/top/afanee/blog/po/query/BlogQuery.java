package top.afanee.blog.po.query;

import top.afanee.blog.po.enums.BlogStatusEnum;
import top.afanee.blog.po.enums.OrderByEnum;

public class BlogQuery extends BaseQuery{
	private String title;
	
	private Integer blogId;
	
	private Integer userId;
	
	private Integer categoryId;
	
	private BlogStatusEnum status;
	
	private boolean showContent;
	
	private OrderByEnum orderBy;
	
	private String startDate;
	
	private String endDate;
	
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getBlogId() {
		return blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public BlogStatusEnum getStatus() {
		return status;
	}

	public void setStatus(BlogStatusEnum status) {
		this.status = status;
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
	
	
}
