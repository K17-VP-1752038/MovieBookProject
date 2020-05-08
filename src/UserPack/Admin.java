package UserPack;

import java.util.ArrayList;

//import IOFilmFile.*;

public class Admin extends User {

//	private int count;
	private String id;
	
	private userReadWrite userManage = new userReadWrite();
	
	Admin() {
		id = null;
		name = "admin";
		firstname = null;
		email = null;
		password = "1";
	}
	
	public Admin(String n, String fn, String e, String pas) {
		id = null;
		name = n;
		firstname = fn;
		email = e;
		password = pas;
	}
	
	String getType() {
		return "admin";
	}

	// Admin được quyền xem danh sách các users
	public ArrayList<User> readUsers() {
		return userManage.ReadUserList();
	}
	
	// Admin được xóa member
	public boolean deleteUser(User user) {
		if(userManage.deleteUser(user))
			return true;
		return false;
	}
	
	// Admin được tìm danh sách user theo tên 
	ArrayList<User> searchUsers(String file, String keyword) {
		ArrayList<User> list = new ArrayList<>();
		String k = keyword.toLowerCase();
		
		list.addAll(userManage.searchById(k));
		list.addAll(userManage.searchByName(k));
		list.addAll(userManage.searchByEmail(k));

		return list;
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
