package UserPack;

// Nhớ đọc theo UTF-8
public class Member extends User{

//	static int count;
	private String id;
	
	public Member() {
		id = null;
		setName("member");
		setFirstName(null);
		setEmail(null);
		setPassword("1");
	}
	
	public Member(String n, String fn, String e, String pas) {
		id = null;
		setName(n);
		setFirstName(fn);
		setEmail(e);
		setPassword(pas);
	}
	
	public String getType() {
		return "member";
	}
	
	// Member can sign up
	public boolean signUp(String name, String firstname, String email, String pas) {
		if(!email.contains("@") || !email.contains(".") || pas.length()<6)
			return false;
		if(urw.insertUser(new Member(name, firstname, email, pas)))
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		String s = "";
		s += "ID: "+ getId()+ "\nMember: "+getFirstName() +" "+ getName() + "\nEmail: "+ getEmail();
		return s;
	}

	public String getId() {
		return id;
	}

	void setId(String id) {
		this.id = id;
	}

}
