package rocks.testbuild.entities;

import java.util.Date;

/**
 * Created by nnet on 6/27/15.
 */
public class User {
	private long id;
	private String apiKey;
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String status;
	private Date createdAt;
	private Date lastLoginAt;

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getLastLoginAt() {
		return lastLoginAt;
	}

	public void setLastLoginAt(Date lastLoginAt) {
		this.lastLoginAt = lastLoginAt;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (id != user.id) return false;
		if (apiKey != null ? !apiKey.equals(user.apiKey) : user.apiKey != null) return false;
		if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null)
			return false;
		if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null)
			return false;
		if (username != null ? !username.equals(user.username) : user.username != null)
			return false;
		if (email != null ? !email.equals(user.email) : user.email != null) return false;
		if (status != null ? !status.equals(user.status) : user.status != null) return false;
		if (createdAt != null ? !createdAt.equals(user.createdAt) : user.createdAt != null)
			return false;
		return !(lastLoginAt != null ? !lastLoginAt.equals(user.lastLoginAt) : user.lastLoginAt != null);

	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (apiKey != null ? apiKey.hashCode() : 0);
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (username != null ? username.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (status != null ? status.hashCode() : 0);
		result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
		result = 31 * result + (lastLoginAt != null ? lastLoginAt.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", apiKey='" + apiKey + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", status='" + status + '\'' +
				", createdAt=" + createdAt +
				", lastLoginAt=" + lastLoginAt +
				'}';
	}
}
