package org.queelt.model.entity;

import java.util.Date;
import java.util.List;

public class UserFriends {
	private List<String> userId;
	private boolean accept;
	private Date dateInvitation;
	private Date invitationConfirmationDate;
	public List<String> getUserId() {
		return userId;
	}
	public void setUserId(List<String> userId) {
		this.userId = userId;
	}
	public boolean getAccept() {
		return accept;
	}
	public void setAccept(boolean accept) {
		this.accept = accept;
	}
	public Date getDateInvitation() {
		return dateInvitation;
	}
	public void setDateInvitation(Date dateInvitation) {
		this.dateInvitation = dateInvitation;
	}
	public Date getInvitationConfirmationDate() {
		return invitationConfirmationDate;
	}
	public void setInvitationConfirmationDate(Date invitationConfirmationDate) {
		this.invitationConfirmationDate = invitationConfirmationDate;
	}
	
}
