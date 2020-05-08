package UserPack;

public class Member extends User{

//	static int count;
	private String id;
	
	public Member() {
		id = null;
		name = "member";
		firstname = null;
		email = null;
		password = "1";
	}
	
	public Member(String n, String fn, String e, String pas) {
		id = null;
		name = n;
		firstname = fn;
		email = e;
		password = pas;
	}
	
	public String getType() {
		return "member";
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
