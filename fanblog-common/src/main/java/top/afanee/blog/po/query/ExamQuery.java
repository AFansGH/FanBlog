package top.afanee.blog.po.query;

import top.afanee.blog.po.enums.StatusEnum;

public class ExamQuery extends BaseQuery{
	private StatusEnum status;
	private Integer categoryId;
	private Integer examId;
	private String[] examIds;
	private Boolean showAnalyse;
	private Integer examMaxTitle;

	private String startDate;
	
	private String endDate;
	
	
	
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
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getExamId() {
		return examId;
	}
	public void setExamId(Integer examId) {
		this.examId = examId;
	}
	public String[] getExamIds() {
		return examIds;
	}
	public void setExamIds(String[] examIds) {
		this.examIds = examIds;
	}
	public Boolean getShowAnalyse() {
		return showAnalyse;
	}
	public void setShowAnalyse(Boolean showAnalyse) {
		this.showAnalyse = showAnalyse;
	}
	public Integer getExamMaxTitle() {
		return examMaxTitle;
	}
	public void setExamMaxTitle(Integer examMaxTitle) {
		this.examMaxTitle = examMaxTitle;
	}
	
	
}
