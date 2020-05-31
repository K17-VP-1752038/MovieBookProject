package UserPack;

import java.util.ArrayList;

// Nhớ đọc theo UTF-8

public class Admin extends User {

	private String id;
		
	Admin() {
		id = null;
		setName("admin");
		setFirstName(null);
		setEmail(null);
		setPassword("1");
	}
	
	Admin(String n, String fn, String e, String pas) {
		id = null;
		setName(n);
		setFirstName(fn);
		setEmail(e);
		setPassword(pas);
	}
	
	public String getType() {
		return "admin";
	}
	
	// Admin được tìm danh sách user 
	public ArrayList<User> searchUsers(String keyword) {
		String k = keyword.toLowerCase();

		ArrayList<User> list = urw.searchByKeyword(k);

		System.out.println("list: "+ list.size());
		return list;
	}
	
	// Admin được quyền xem danh sách các users
	public ArrayList<User> readUsers() {
		return urw.ReadUserList();
	}
	
	private User findUser(String ID) {
		for(User U : readUsers())
			if(U.getId().equals(ID))
				return U;
		return null;
	}
	
	// Admin được xóa member
	public boolean deleteUser(String ID, String pass) {
		if(!pass.equals(getPassword()))
			return false;
		if(findUser(ID) == null)
			return false;
		if(urw.deleteUser(findUser(ID)))
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		String res = "";
		res += "ID: "+ getId()+"\nAdmin: "+getFirstName() +" "+ getName() + "\nEmail: "+ getEmail();
		return res;
	}

	public String getId() {
		return id;
	}

	void setId(String id) {
		this.id = id;
	}

}
