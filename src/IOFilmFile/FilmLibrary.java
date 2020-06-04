package IOFilmFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class FilmLibrary {
	
	private static final String moviesfile="movies/Movies_DB.txt";
	private static final String seriesfile="series/Series_DB.txt";
	private filmReadWrite auth = new filmReadWrite();
	private ArrayList<Film> films = new ArrayList<>();
	private ArrayList<filmImage> images = new ArrayList<>();
	
	// Constructor
	public FilmLibrary() {
		films.addAll(auth.readMovieFile(moviesfile));
		films.addAll(auth.readSeriesFile(seriesfile));
	}

	// return array list Film
	public ArrayList<Film> getFilms() {
		return films;
	}
	
	// return array list Movie
	public ArrayList<Movie> getMovies() {
		ArrayList<Movie> movies = new ArrayList<>();
		for(Film F : films)
			if(F.getType().equals("movie"))
				movies.add((Movie)F);
		return movies;
	}
	
	// return array list Series
	public ArrayList<Series> getSeries() {
		ArrayList<Series> series = new ArrayList<>();
		for(Film F : films)
			if(F.getType().equals("series"))
				series.add((Series)F);
		return series;
	}
	
	// **Load the ArrayList Film back to files
	public void updateMovieStore(String type) {
		try {
			ArrayList<Film> movies = new ArrayList<>();
			for(Film F : films) {
				if(F.getType().equals("movie"))
					movies.add(F);
			}
			
			auth.updateFilmsFile(this, movies, moviesfile);
			for(filmImage I : images) {
				BufferedImage image = ImageIO.read(I.getFile());
				if(I.getType().equals("movie"))
					ImageIO.write(image, "jpg", new File("movies/Img/"+ I.getName() +".jpg"));
			}
		} catch (Exception e) {
			System.out.println("Error update movie file: "+ e);
		}
	}
	
	public void updateSeriesStore(String type) {
		try {
			ArrayList<Film> series = new ArrayList<>();
			for(Film F : films) {
				if(F.getType().equals("series"))
					series.add(F);
			}
			
			auth.updateFilmsFile(this, series, seriesfile);
			for(filmImage I : images) {
				BufferedImage image = ImageIO.read(I.getFile());
				if(I.getType().equals("series"))
					ImageIO.write(image, "jpg", new File("series/Img/"+ I.getName() +".jpg"));
			}
		} catch (Exception e) {
			System.out.println("Error update series file: "+ e);
		}
	}
	
	// Save image to local file
	public void saveImage(String name, File file, String type) {
		String n = name.replaceAll(" ", "");
		filmImage img = new filmImage(n, file, type);
		images.add(img);
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
	public boolean deleteFilm(Film F) {
		if(films.remove(F)) {
			File filmImg = null;
			if(F.getType().equals("movie"))
				filmImg = new File("movies/Img/"+ F.getIcon());
			else
				filmImg = new File("series/Img/"+ F.getIcon());
			
		    if (filmImg.delete()) { 
		      System.out.println("Deleted the file: " + filmImg.getName());
		    } else {
		      System.out.println("Failed to delete the file.");
		    }
			return true;
		}
		return false;
	}
	
	// Add new film
	public boolean insertFilm(Film F) {
		// check for errors
		if(this.have(F))
			return false;
		if(checkDuplicate(F))
			return false;
		String name = F.getName().replaceAll(" ", "");
		F.setIcon(name + ".jpg");
		String content = F.getContent().replaceAll("\n", " ");
		F.setContent(content);
		films.add(F);
		return true;
	}
	
	// Update film
	public boolean updateFilm(Film F, Film newF) {
		if(have(newF)) return false;
		//if(checkDuplicate(newF)) return false;
		if(!F.getType().equals(newF.getType())) return false;
		String name = newF.getName().replaceAll(" ", "");
		newF.setIcon(name + ".jpg");
		String content = newF.getContent().replaceAll("\n", " ");
		newF.setContent(content);
		// find the location of Film F in films
		int index = 0;
		for(Film film : films) {
			if(film.equals(F)) break;
			index++;
		}
		// change the info of the film in position index
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
			boolean flag = true;
			String Fname = F.getGenre().toLowerCase();
			for(int i = 0; i < gen.size(); i++)
				if(!Fname.contains(gen.get(i).toLowerCase()))
					flag = false;
			if(flag && !film.contains(F))
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
