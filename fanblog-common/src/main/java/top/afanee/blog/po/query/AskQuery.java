package top.afanee.blog.po.query;

import top.afanee.blog.po.enums.OrderByEnum;
import top.afanee.blog.po.enums.SolveEnum;

public class AskQuery extends BaseQuery {
	private Integer askId;
	
	private Integer userId;
	
	private SolveEnum solveType;
	
	private boolean showContent;
	
	private OrderByEnum orderBy;
	
	private String startDate;
	
	private String endDate;
	
	private Integer pCategoryId;
	
	private Integer categoryId;

	private Integer topCount;
	
	public Integer getAskId() {
		return askId;
	}

	public void setAskId(Integer askId) {
		this.askId = askId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public SolveEnum getSolveType() {
		return solveType;
	}

	public void setSolveType(SolveEnum solveType) {
		this.solveType = solveType;
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

	public Integer getTopCount() {
		return topCount;
	}

	public void setTopCount(Integer topCount) {
		this.topCount = topCount;
	}
	
}
