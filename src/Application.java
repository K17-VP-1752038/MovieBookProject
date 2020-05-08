import IOFilmFile.FilmLibrary;
import UserPack.*;

public class Application {

	private static final String moviesfile="movies/Movies_DB.txt";
	private static final String seriesfile="series/Series_DB.txt";
	private static final String userfile="user.xml";
	
	private User user;
	private boolean access;
	private FilmLibrary movieBook = new FilmLibrary(moviesfile, seriesfile);
	private userReadWrite userManage = new userReadWrite();
	
	boolean login(String email, String password) {
		user = userManage.getUser(email, password, userfile);
		if(user == null)
			access = false;
		else
			access = true;
		return access;
	}
	
	boolean signIn(String name, String firstname, String email, String pas) {
		Member mem = new Member(name, firstname, email, pas);
		if(userManage.insertUser(mem, userfile))
			return true;
		return false;
	}
	
	void logOut() {
		access = false;
		user = null;
	}
	
	
	
	boolean getAccess() {
		return access;
	}
}
