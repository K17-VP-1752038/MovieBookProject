package AppUsed;
import java.util.ArrayList;
import IOFilmFile.*;
import UserPack.*;

public class Application {
	
	private User user;
	private boolean access;
	private FilmLibrary movieBook;

	public Application() {
		user = new Member();
		access = false;
		movieBook = null;
	}
	
	// If login success, the program can start
	public boolean login(String email, char[] password) {
		User U = new Member();
		user = U.login(email, convertToString(password));
		if(user != null) {
			access = true;
			movieBook = new FilmLibrary();
		}
		return access;
	}
	
	// Please check for password and email before you call this function
	public boolean signUp(String name, String firstname, String email, char[] pas) {
		user = new Member();
		if(!getAccess()) {
			if(((Member)user).signUp(name, firstname, email, convertToString(pas)))
				return true;
		}
		return false;
	}
	
	public void logout() {
		access = false;
		user = null;
		movieBook = null;
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
	
	// Search films for each type
	public Film[] searchByKeyWord(String kw, String type) {
		if(getAccess()) {
			ArrayList<Film> films = movieBook.searchByName(kw);
			ArrayList<Film> filmlib = new ArrayList<>();
			for(int i = 0; i < films.size(); i++)
				if(films.get(i).getType().equals(type))
					filmlib.add(films.get(i));
			
			System.out.println(filmlib.size());
			Film[] f = new Film[filmlib.size()];
			for(int i = 0; i < filmlib.size(); i++)
				f[i] = filmlib.get(i);
			
			return f;
		}
		return null;
	}
	
	// Search films by Genres
	public Film[] searchByGenre(ArrayList<String> gen) {
		if(getAccess()) {
			ArrayList<Film> films = movieBook.searchByGenre(gen);
			Film[] filmlib = new Film[films.size()];
			for(int i = 0; i < films.size(); i++)
				filmlib[i] = films.get(i);
			return filmlib;
		}
		return null;
	}
	
	// Update password when user forgot
	public boolean updatePassword(String email, char[] pass) {
		if(email.equals(null))
			return false;
		getUser().setEmail(email);
		System.out.println(convertToString(pass));
		if (getUser().updateUserPassword(convertToString(pass)))
			return true;
		return false;
	}
	
	// User change password
	public boolean updatePassword(char[] oldpass, char[] pass) {
		if (getAccess()) {
			if (user.updateUserPassword(convertToString(oldpass), convertToString(pass)))
				return true;
		}
		return false;
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
	
	
	// GET methods
	public FilmLibrary getLibrary() {
		if(getAccess())
			return movieBook;
		return null;
	}
	
	public User getUser() {
		return user;
	}
	
	private boolean getAccess() {
		return access;
	}
	
	private String convertToString(char[] arr) {
		String s = "";
		for(char i : arr) {
			s += i;
		}
		return s;
	}
}
