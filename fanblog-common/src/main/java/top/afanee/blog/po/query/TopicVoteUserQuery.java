package top.afanee.blog.po.query;

public class TopicVoteUserQuery extends BaseQuery {
	private Integer userId;
	private Integer voteId;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getVoteId() {
		return voteId;
	}
	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
	}
	
	
}
