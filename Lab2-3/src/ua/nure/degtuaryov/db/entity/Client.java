package ua.nure.degtuaryov.db.entity;

/**
 * 
 * Client class entity from database, extends Entity
 * 
 * @author Degtuaryow
 *
 */
public class Client extends Entity {

	private static final long serialVersionUID = -3253253233256288L;

	private String password;

	private String login;

	private String firstName;

	private String email;

	private String secondName;

	private String surname;

	private long roleId;

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

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", password=" + password + ", firstName=" + firstName + "," + " email=" + email
				+ ", lastName=" + secondName + ", surname=" + surname + ", roleId=" + roleId + "]";
	}

}
