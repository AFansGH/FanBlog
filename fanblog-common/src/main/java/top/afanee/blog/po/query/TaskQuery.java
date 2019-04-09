package top.afanee.blog.po.query;

public class TaskQuery extends BaseQuery {
	private Integer id;
	
    private String taskClassz;

    private String taskMethod;
    
    

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTaskClassz() {
		return taskClassz;
	}

	public void setTaskClassz(String taskClassz) {
		this.taskClassz = taskClassz;
	}

	public String getTaskMethod() {
		return taskMethod;
	}

	public void setTaskMethod(String taskMethod) {
		this.taskMethod = taskMethod;
	}
    
    
}
