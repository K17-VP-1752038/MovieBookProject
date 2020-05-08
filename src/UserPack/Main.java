package UserPack;

import java.util.ArrayList;

// Phải lưu file dưới dạng UFT-8
public class Main {

	public static void main(String[] args) {

		User user1 = new Admin("Moon", "Nguyen", "miknguyet99@gmail.com", "cuncun99");
		
		ArrayList<User> users = ((Admin) user1).readUsers("user.xml");
		for(User U : users)
			System.out.println(U);
		System.out.println("--------------\n"+user1+"\n-----------------");
		
		ArrayList<User> usersHasName = ((Admin) user1).searchUsers("user.xml", "trang");
		for(User U : usersHasName)
			System.out.println(U);
	}

}