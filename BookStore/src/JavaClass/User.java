package JavaClass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_login")

public class User {
	@Id
	
	@Column(name="FirstName")
	private String FirstName;

	@Column(name="LastName")
	private String LastName;
	
	@Column(name="Email")
	private String Email;
	
	@Column(name="UserName")
	private String UserName;
	
	@Column(name="Pass")
	private String Pass;

	public User() {
		
	}
	
	public User (String F,String L,String E,String U,String P) {
		this.FirstName = F;
		this.LastName = L;
		this.Email = E;
		this.UserName = U;
		this.Pass = P;
	}
	
	public String getFirstName() {
		return FirstName;
	}

	public String getLastName() {
		return LastName;
	}

	public String getEmail() {
		return Email;
	}

	public String getUserName() {
		return UserName;
	}

	public String getPass() {
		return Pass;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public void setPass(String pass) {
		Pass = pass;
	}
	
}
