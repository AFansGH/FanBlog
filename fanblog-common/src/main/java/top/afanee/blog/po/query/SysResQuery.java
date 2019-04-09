package top.afanee.blog.po.query;

public class SysResQuery extends BaseQuery {

	private Integer id;
	
	private Integer type;
	
	private Integer pid;
	
	private Integer enabled;
	
	
	
	

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}


}
