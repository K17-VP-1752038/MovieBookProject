import java.util.ArrayList;

import IOFilmFile.Film;
import IOFilmFile.FilmLibrary;
import UserPack.*;

public class Application {

	private static final String moviesfile="movies/Movies_DB.txt";
	private static final String seriesfile="series/Series_DB.txt";
	private static final String userfile ="user.xml";
	
	private User user;
	private boolean access;
	private FilmLibrary movieBook = new FilmLibrary(moviesfile, seriesfile);
	private userReadWrite userManage = new userReadWrite();
	
	public boolean login(String email, String password) {
		user = userManage.getUser(email, password, userfile);
		if(user == null)
			access = false;
		else
			access = true;
		return access;
	}
	
	public boolean signIn(String name, String firstname, String email, String pas) {
//		if(!email.contains("@"))
//			return false;
		Member mem = new Member(name, firstname, email, pas);
		if(userManage.insertUser(mem, userfile))
			return true;
		return false;
	}
	
	public void logOut() {
		access = false;
		user = null;
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
	
	boolean getAccess() {
		return access;
	}
}
