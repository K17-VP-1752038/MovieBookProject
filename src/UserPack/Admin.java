package UserPack;

import java.util.ArrayList;

// Nhớ đọc theo UTF-8

public class Admin extends User {

//	private int count;
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
	
	// Admin được tìm danh sách user theo tên 
	public ArrayList<User> searchUsers(String keyword) {
		ArrayList<User> list = new ArrayList<>();
		String k = keyword.toLowerCase();

		list.addAll(urw.searchById(k));
		list.addAll(urw.searchByName(k));
		list.addAll(urw.searchByEmail(k));

		return list;
	}
	// Admin được quyền xem danh sách các users
	public ArrayList<User> readUsers() {
		return urw.ReadUserList();
	}
			
	// Admin được xóa member
	public boolean deleteUser(User user, String pass) {
		if(pass.equals(getPassword()))
			return false;
		if(urw.deleteUser(user))
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
