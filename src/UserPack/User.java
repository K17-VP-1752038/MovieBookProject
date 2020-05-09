package UserPack;

// phải đọc theo UTF-8
public abstract class User {

	private String email;
	private String firstname;
	private String name;
	private String password;
	protected userReadWrite urw = new userReadWrite();
	
	String getEmail() {
		return email;
	}
	
	String getName() {
		return name;
	}
	
	String getFirstName() {
		return firstname;
	}
	
	String getPassword() {
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
		return urw.getUser(email, password);
	}
	
	public boolean updateUserPassword(String oldpass, String newpass) {
		if(password.length() < 6)
			return false;
		if(oldpass.equals(getPassword())) {
			if(urw.updateUserPassword(this, newpass))
				return true;
		}
		return false;
	}
	
	public void updateUserInfo() {
		urw.updateUserInfo(this);
	}
	
	public abstract String getType();
	abstract String getId();
	abstract void setId(String id);
	public abstract String toString();
}
