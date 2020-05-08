import java.util.ArrayList;

import UserPack.*;

public class Main {

	// hỏi chấm
	public static void main(String[] args) {

		User user1 = new Admin("Minh Nguyet", "Nguyen", "miknguyet99@gmail.com", "cuncun99");
		
		ArrayList<User> users = ((Admin) user1).readUsers("user.xml");
		for(User U : users)
			System.out.println(U);
		
		System.out.println("---------------------------------");
		
//		if(((Admin) user1).deleteUser(users.get(3)))
//			System.out.println("Done");
		
		Application app = new Application();
		if(app.signIn("Minh Nguyet", "Nguyen", "miknguyet99@gmail.com", "cuncun99"))
			System.out.println("Done");
		else
			System.out.println("Errorr");
	
		
	}

}