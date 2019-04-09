package top.afanee.blog.po.query;

import top.afanee.blog.po.enums.MessageStatus;
import top.afanee.blog.po.enums.OrderByEnum;


public class MessageQuery extends BaseQuery {
	private Integer id;
	
	private MessageStatus status;
	
	private Integer receivedUserId;
	
	private Integer[] ids;
	
	private String startDate;
	
	private String endDate;
	
	private OrderByEnum orderBy;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MessageStatus getStatus() {
		return status;
	}

	public void setStatus(MessageStatus status) {
		this.status = status;
	}

	public Integer getReceivedUserId() {
		return receivedUserId;
	}

	public void setReceivedUserId(Integer receivedUserId) {
		this.receivedUserId = receivedUserId;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
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

	public OrderByEnum getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(OrderByEnum orderBy) {
		this.orderBy = orderBy;
	}
	
	
	
}
