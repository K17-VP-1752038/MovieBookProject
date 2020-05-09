package UserPack;

import AppUsed.Application;

public class Main {

	public static void main(String[] args) {
		try {
			Application app = new Application();
			if(app.signUp("Minh Nguyệt", "Nguyễn", "miknguyet99@gmail.com", "cuncun99"))
				System.out.println(app.getUser());
			else
				System.out.println("Error input or account already exist!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
