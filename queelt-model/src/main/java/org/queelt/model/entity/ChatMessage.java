package org.queelt.model.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

public class ChatMessage {
	@Id
	private String id;
	private String userId;
	private String userName;
	private String ip;
	private String chatFriendsId;
	private String message;
	private List<Markup> markups;

	public String getChatFriendsId() {
		return chatFriendsId;
	}

	public void setChatFriendsId(String chatFriendsId) {
		this.chatFriendsId = chatFriendsId;
	}

	private Date createDate;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Markup> getMarkups() {
		return markups;
	}

	public void setMarkups(List<Markup> markups) {
		this.markups = markups;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
