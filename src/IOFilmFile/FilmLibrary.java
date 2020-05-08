package IOFilmFile;

import java.util.ArrayList;

public class FilmLibrary {
	
	private ArrayList<Film> films = new ArrayList<>();
	private filmReadWrite auth = new filmReadWrite();
	
	// Constructor
	public FilmLibrary(String movieFile, String seriesFile) {
		films.addAll(auth.readMovieFile(movieFile));
		films.addAll(auth.readSeriesFile(seriesFile));
	}

	ArrayList<Film> getFilms() {
		return films;
	}
	
	// Load the ArrayList Film back to files
	void updateStore(String movieFile, String seriesFile) {
		try {
			auth.updateFilmsFile(this, movieFile, seriesFile);
		} catch (Exception e) {
			System.out.println("Error update film file: "+ e);
		}
	}
	
	// Check if the film existed, return true 
	private boolean have(Film F) {
		if(films.contains(F))
			return true;
		return false;
	}
	
	// If there's duplicate error, return true
	private boolean checkDuplicate(Film F) {
		for(Film film : films) {
			if(film.getName().equals(F.getName()) && film.getDirector().equals(F.getDirector())) {
				return true;
			}
		}
		return false;
	}
	
	// Delete film
	boolean deleteFilm(Film F) { 
		if(films.remove(F)) {
			return true;
		}
		return false;
	}
	
	// Add new film
	boolean insertFilm(Film F) {
		// check for errors
		if(this.have(F))
			return false;
		if(checkDuplicate(F))
			return false;
		films.add(F);
		return true;
	}
	
	// Update film
	boolean updateFilm(Film F, Film newF) {
		if(have(newF)) return false;
		if(checkDuplicate(newF)) return false;
		if(! F.getType().equals(newF.getType())) return false;
		
		// find the location of Film F in films
		int index = 0;
		for(Film film : films) {
			if(film.equals(F)) break;
			index++;
		}
		films.set(index, newF);
		return true;
	}
	
	//----Search film---------------------------------
	ArrayList<Film> searchByName(String name) {
		ArrayList<Film> film = new ArrayList<>();
		for(Film F : films) {
			String Fname = F.getName().toLowerCase();
			if(Fname.contains(name.toLowerCase()))
				film.add(F);
		}
		return film;
	}
	
	ArrayList<Film> searchByDirector(String dir) {
		ArrayList<Film> film = new ArrayList<>();
		for(Film F : films) {
			String Fname = F.getDirector().toLowerCase();
			if(Fname.contains(dir.toLowerCase()))
				film.add(F);
		}
		return film;
	}
	
	ArrayList<Film> searchByGenre(String gen) {
		ArrayList<Film> film = new ArrayList<>();
		for(Film F : films) {
			String Fname = F.getGenre().toLowerCase();
			if(Fname.contains(gen.toLowerCase()))
				film.add(F);
		}
		return film;
	}
	//------------------------------------------------
	
	// return string
	String showFilms() {
		String res = "";
		int n = films.size();
		for(int i = 0; i < n; i++) {
			if(i == (n-1))
				res += films.get(i).showFilm();
			res += films.get(i).showFilm() + "\n";
		}
		return res;
	}
}
