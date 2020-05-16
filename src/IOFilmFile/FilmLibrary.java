package IOFilmFile;

import java.util.ArrayList;

public class FilmLibrary {
	
	private static final String moviesfile="movies/Movies_DB.txt";
	private static final String seriesfile="series/Series_DB.txt";
	private filmReadWrite auth = new filmReadWrite();
	private ArrayList<Film> films = new ArrayList<>();
	private ArrayList<Movie> movies = new ArrayList<>();
	private ArrayList<Series> series = new ArrayList<>();
	
	
	// Constructor
	public FilmLibrary() {
		movies = auth.readMovieFile(moviesfile);
		series = auth.readSeriesFile(seriesfile);
		
		films.addAll(movies);
		films.addAll(series);
	}

	// return array list Film
	public ArrayList<Film> getFilms() {
		return films;
	}
	
	// return array list Movie
	public ArrayList<Movie> getMovies() {
		return movies;
	}
	
	// return array list Series
	public ArrayList<Series> getSeries() {
		return series;
	}
	
	// **Load the ArrayList Film back to files
	public void updateStore() {
		try {
			ArrayList<Film> movies = new ArrayList<>();
			ArrayList<Film> series = new ArrayList<>();
			for(Film F : films) {
				if(F.getType().equals("movie"))
					movies.add(F);
				else
					series.add(F);
			}
			
			auth.updateFilmsFile(this, movies, moviesfile);
			auth.updateFilmsFile(this, series, seriesfile);
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
	public ArrayList<Film> searchByName(String name) {
		ArrayList<Film> film = new ArrayList<>();
		for(Film F : films) {
			String Fname = F.getName().toLowerCase();
			if(Fname.contains(name.toLowerCase()))
				film.add(F);
		}
		return film;
	}
	
	public ArrayList<Film> searchByDirector(String dir) {
		ArrayList<Film> film = new ArrayList<>();
		for(Film F : films) {
			String Fname = F.getDirector().toLowerCase();
			if(Fname.contains(dir.toLowerCase()))
				film.add(F);
		}
		return film;
	}
	
	// For an array of genre
	public ArrayList<Film> searchByGenre(ArrayList<String> gen) {
		ArrayList<Film> film = new ArrayList<>();
		for(Film F : films) {
			String Fname = F.getGenre().toLowerCase();
			for(int i = 0; i < gen.size(); i++)
				if(Fname.contains(gen.get(i).toLowerCase()))
					film.add(F);
		}
		return film;
	}
	//------------------------------------------------
	
	// return string
	String showFilms(ArrayList<Film> films) {
		String res = "";
		int n = films.size();
		for(int i = 0; i < n; i++) {
			if(i == (n-1))
				res += films.get(i).showFilm();
			else
				res += films.get(i).showFilm() + "\n";
		}
		return res;
	}
}
