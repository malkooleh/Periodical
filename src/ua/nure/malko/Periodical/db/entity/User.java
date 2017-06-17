package ua.nure.malko.Periodical.db.entity;

public class User extends Entity {

	private static final long serialVersionUID = 2L;
	
	private String login;
	
	private String password;
	
	private String firstName;
	
	private String lastName;

	private int roleId;
	
	private int account;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account += account;
	}

	@Override
	public String toString() {
		return "User [login=" + login  
				+ ", firstName=" + firstName 
				+ ", lastName=" + lastName
				+ ", roleId=" + roleId
				+ ", account=" + account
				+ "]";
	}
	
}
