package ua.nure.malko.Periodical.db.entity;

public class Subscription extends Entity {

	private static final long serialVersionUID = 4L;

	private int bill;

	private Long userId;

	private int statusId;

	public int getBill() {
		return bill;
	}

	public void setBill(int bill) {
		this.bill = bill;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	@Override
	public String toString() {
		return "Subscription [bill=" + bill + ", userId=" + userId + ", statusId="
				+ statusId + ", getId()=" + getId() + "]";
	}

}
