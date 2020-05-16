package IOFilmFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// Read write film file
public class filmReadWrite {
	
	public ArrayList<Movie> readMovieFile(String filename) {
		ArrayList<Movie> list = new ArrayList<Movie>();
		try (Scanner scan = new Scanner(new File(filename))){
			while(scan.hasNext()) {
				Movie f = new Movie();
				f.setName(scan.nextLine());
				f.setIcon(scan.nextLine());
				f.setGenre(scan.nextLine());
				f.setDirector(scan.nextLine());
				f.setDuration(scan.nextInt());
				scan.nextLine();
				f.setDate(scan.nextLine());
				f.setContent(scan.nextLine());
				
				list.add(f);
			}
		} catch (Exception e) {
			System.out.println("Error read movie file: " + e);
		}
		return list;
	}
	
	public ArrayList<Series> readSeriesFile(String filename) {
		ArrayList<Series> list = new ArrayList<Series>();
		try (Scanner scan = new Scanner(new File(filename))){
			while(scan.hasNext()) {
				Series f = new Series();
				f.setName(scan.nextLine());
				f.setIcon(scan.nextLine());
				f.setGenre(scan.nextLine());
				f.setDirector(scan.nextLine());
				f.setDate(scan.nextLine());
				f.setEpisode(scan.nextInt());
				scan.nextLine();
				f.setContent(scan.nextLine());
				
				list.add(f);
			}
		} catch (Exception e) {
			System.out.println("Error read series file: " + e);
		}
		return list;
	}
	
//	public void insertFilmFile(String filename, Movie newMovie) {
//		try (PrintWriter pw = new PrintWriter(new File(filename))){
//			pw.println(newMovie.getName());
//			pw.println(newMovie.getIcon());
//			pw.println(newMovie.getGenre());
//			pw.println(newMovie.getDirector());
//			pw.println(newMovie.getDate());
//			pw.println(newMovie.getDuration());
//			pw.println(newMovie.getContent());
//		} catch (Exception e) {
//			System.out.println("Error write file!");
//		}
//	}
	
	public void updateFilmsFile(FilmLibrary lib, ArrayList<Film> films, String file) {
		try(BufferedWriter fw = new BufferedWriter(new FileWriter(file))) {
			fw.write(lib.showFilms(films));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}