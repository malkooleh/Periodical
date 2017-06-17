package ua.nure.malko.Periodical.db.bean;

import ua.nure.malko.Periodical.db.entity.Entity;

public class UserSubscriptionBean extends Entity {
	
	private static final long serialVersionUID = 7L;

	private long subscriptionId;
	
	private String userFirstName;

	private String userLastName;

	private int subscriptionBill;
	
	private String statusName;

	public long getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public int getSubscriptionBill() {
		return subscriptionBill;
	}

	public void setSubscriptionBill(int subscriptionBill) {
		this.subscriptionBill = subscriptionBill;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public String toString() {
		return "Subscription [subscriptionId=" + subscriptionId + ", userFirstName="
				+ userFirstName + ", userLastName=" + userLastName
				+ ", subscriptionBill=" + subscriptionBill + ", statusName=" + statusName
				+ "]";
	}
}
