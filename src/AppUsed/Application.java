package AppUsed;
import java.util.ArrayList;
import IOFilmFile.*;
import UserPack.*;

public class Application {
	
	private User user;
	private boolean access;
	private FilmLibrary movieBook;

	public Application() {
		user = null;
		access = false;
		movieBook = null;
	}
	
	// If login success, the program can start
	public boolean login(String email, String password) {
		User U = new Member();
		user = U.login(email, password);
		if(user != null) {
			access = true;
			movieBook = new FilmLibrary();
		}
		return access;
	}
	
	// Please check for password and email before you call this function
	public boolean signUp(String name, String firstname, String email, String pas) {
		user = new Member();
		if(!getAccess()) {
			if(((Member)user).signUp(name, firstname, email, pas)) {
				login(email, pas);
				return true;
			}
		}
		return false;
	}
	
	public void logout() {
		access = false;
		user = null;
		movieBook = null;
		if(isAdmin())
			getLibrary().updateStore();
	}
	
	public Film[] readFilm() {
		if(getAccess()) {
			ArrayList<Film> films = movieBook.getFilms();
			Film[] filmlib = new Film[films.size()];
			for(int i = 0; i < films.size(); i++)
				filmlib[i] = films.get(i);
			return filmlib;
		}
		return null;
	}
	
	public Film[] readMovie() {
		if(getAccess()) {
			ArrayList<Movie> films = movieBook.getMovies();
			Film[] filmlib = new Film[films.size()];
			for(int i = 0; i < films.size(); i++)
				filmlib[i] = films.get(i);
			return filmlib;
		}
		return null;
	}
	
	public Film[] readSeries() {
		if(getAccess()) {
			ArrayList<Series> films = movieBook.getSeries();
			Film[] filmlib = new Film[films.size()];
			for(int i = 0; i < films.size(); i++)
				filmlib[i] = films.get(i);
			return filmlib;
		}
		return null;
	}
	
	// Search films by Name and Director
	public Film[] searchByKeyWord(String kw) {
		if(getAccess()) {
			ArrayList<Film> films = movieBook.searchByName(kw);
			films.addAll(movieBook.searchByDirector(kw));
			Film[] filmlib = new Film[films.size()];
			for(int i = 0; i < films.size(); i++)
				filmlib[i] = films.get(i);
			return filmlib;
		}
		return null;
	}
	
	//---Just for admins---------------------------
	public boolean isAdmin() {
		if(user.getType().equals("admin"))
			return true;
		return false;
	}
	
//	// Admin được tìm danh sách user theo tên 
//	public ArrayList<User> searchUsers(String keyword) {
//		if(isAdmin() && getAccess()) {
//			return ((Admin)user).searchUsers(keyword);
//		}
//		return null;
//	}
//	// Admin được quyền xem danh sách các users
//	public ArrayList<User> readUsers() {
//		if(isAdmin() && getAccess()) {
//			return ((Admin)user).readUsers();
//		}
//		return null;
//	}
//		
//	// Admin được xóa member, nhung phai nhap password
//	public boolean deleteUser(User user, String pw) {
//		if(isAdmin() && getAccess()) {
//			if(((Admin)user).deleteUser(user, pw))
//				return true;
//		}
//		return false;
//	}
//	//--------------------------------------------
	
	public FilmLibrary getLibrary() {
		if(getAccess())
			return movieBook;
		return null;
	}
	
	public User getUser() {
		if(getAccess())
			return user;
		return null;
	}
	
	private boolean getAccess() {
		return access;
	}
}
