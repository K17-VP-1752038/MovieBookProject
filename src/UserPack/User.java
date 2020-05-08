package UserPack;

public abstract class User {

	protected String type;
	protected String email;
	protected String firstname;
	protected String name;
	protected String password;
	
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
	void setEmail(String e) {
		email = e;
	}
	
	void setName(String n) {
		name = n;
	}
	
	void setFirstName(String n) {
		firstname = n;
	}
	
	void setPassword(String pas) {
		password = pas;
	}
	
	abstract String getType();
	abstract String getId();
	abstract void setId(String id);
	public abstract String toString();
}
