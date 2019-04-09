package top.afanee.blog.po.query;

public class UserQuery extends BaseQuery {
    private Integer userid;

    private String email;

    private String userName;
    
	private String startDate;
	
	private String endDate;
	
	private String loginStartDate;
	
	private String loginEndDate;
	



	public String getLoginStartDate() {
		return loginStartDate;
	}

	public void setLoginStartDate(String loginStartDate) {
		this.loginStartDate = loginStartDate;
	}

	public String getLoginEndDate() {
		return loginEndDate;
	}

	public void setLoginEndDate(String loginEndDate) {
		this.loginEndDate = loginEndDate;
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

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    
}
