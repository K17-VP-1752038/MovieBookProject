package UserPack;

// phải đọc theo UTF-8
public abstract class User {

	private String email = "";
	private String firstname;
	private String name;
	private String password;
	protected userReadWrite urw = new userReadWrite();
	
	public String getEmail() {
		return email;
	}
	
	public String getName() {
		return name;
	}
	
	public String getFirstName() {
		return firstname;
	}
	
	public String getPassword() {
		return password;
	}
	
	// SET methods
	public void setEmail(String e) {
		email = e;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public void setFirstName(String n) {
		firstname = n;
	}
	
	public void setPassword(String pas) {
		password = pas;
	}
	
	// Admin and member can do
	public User login(String email, String password) {
		User U = urw.getUser(email);
		if(U != null)
			if(U.getPassword().equals(password))
				return U;
		return null;
	}
	
	public boolean updateUserPassword(String oldpass, String newpass) {
		if(newpass.length() < 6)
			return false;
		if(oldpass.equals(getPassword())) {
			if(urw.updateUserPassword(getEmail(), newpass))
				return true;
		}
		return false;
	}
	
	public boolean updateUserPassword(String pass) {
		if(pass.length() < 6)
			return false;
		if(urw.updateUserPassword(getEmail(), pass))
			return true;
		return false;
	}
	
	public void updateUserInfo(User U) {
		urw.updateUserInfo(U);
	}
	
	public boolean isExist(String email) {
		if(urw.isExist(email))
			return true;
		return false;
	}
	
	public abstract String getType();
	public abstract String getId();
	abstract void setId(String id);
	public abstract String toString();
}
