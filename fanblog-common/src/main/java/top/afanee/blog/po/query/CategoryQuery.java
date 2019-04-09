package top.afanee.blog.po.query;

public class CategoryQuery extends BaseQuery{
	private Integer categoryId;
	private Integer pid;
	private String showInBbs;
	private String showInQuestion;
	private String showInKnowledge;
	private String showInExam;
	private String startDate;
	private String endDate;
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getShowInBbs() {
		return showInBbs;
	}
	public void setShowInBbs(String showInBbs) {
		this.showInBbs = showInBbs;
	}
	public String getShowInQuestion() {
		return showInQuestion;
	}
	public void setShowInQuestion(String showInQuestion) {
		this.showInQuestion = showInQuestion;
	}
	public String getShowInKnowledge() {
		return showInKnowledge;
	}
	public void setShowInKnowledge(String showInKnowledge) {
		this.showInKnowledge = showInKnowledge;
	}
	public String getShowInExam() {
		return showInExam;
	}
	public void setShowInExam(String showInExam) {
		this.showInExam = showInExam;
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
