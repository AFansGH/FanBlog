package top.afanee.blog.po.query;

import top.afanee.blog.po.enums.BlogStatusEnum;

public class BlogCategoryQuery extends BaseQuery{
	private Integer userId;
	
	private Integer categoryId;
	
	private BlogStatusEnum status;
	
	public BlogStatusEnum getStatus() {
		return status;
	}

	public void setStatus(BlogStatusEnum status) {
		this.status = status;
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
	
	
	
}
