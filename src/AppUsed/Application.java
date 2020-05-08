package AppUsed;
import java.util.ArrayList;
import IOFilmFile.*;
import UserPack.*;

public class Application {
	
	private User user;
	private boolean access;
	private FilmLibrary movieBook;
	private userReadWrite userManage;

	public Application() {
		user = null;
		access = false;
		movieBook = null;
		userManage = null;
	}
	
	// If login success, the program can start
	public boolean login(String email, String password) {
		user = userManage.getUser(email, password);
		if(user == null || access)
			access = false;
		else {
			access = true;
			movieBook = new FilmLibrary();
			if(user.getType().equals("admin"))
				userManage = new userReadWrite();
		}
		return access;
	}
	
	// Please check for password and email before you call this function
	public boolean signIn(String name, String firstname, String email, String pas) {
		Member mem = new Member(name, firstname, email, pas);
		if(userManage.insertUser(mem))
			return true;
		return false;
	}
	
	public void logOut() {
		access = false;
		user = null;
		movieBook = null;
		userManage = null;
	}
	
	public Film[] readFilm() {
		if(access) {
			ArrayList<Film> films = movieBook.getFilms();
			Film[] filmlib = new Film[films.size()];
			for(int i = 0; i < films.size(); i++)
				filmlib[i] = films.get(i);
			return filmlib;
		}
		return null;
	}
	
	// Just for admins
	public User getUser() {
		return user;
	}
	
	public FilmLibrary getLibrary() {
		return movieBook;
	}
	
	boolean getAccess() {
		return access;
	}
}
