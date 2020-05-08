package IOFilmFile;

public class Series extends Film {
	private int Fepisode;

	Series() {
		Fname = null;
		Ficon = null;
		Fgenre = null;
		Fdirector = null;
		Fdate = null;
		Fcontent = null;
		Fepisode = 0;
	}
	
	Series(Series B) {
		Fname = B.Fname;
		Ficon = B.Ficon;
		Fgenre = B.Fgenre;
		Fdirector = B.Fdirector;
		Fdate = B.Fdate;
		Fcontent = B.Fcontent;
		Fepisode = B.Fepisode;
	}
	
	Series(String name, String icon, String genre, String director, String date, String content, int season, int episode){
		Fcontent = content;
		Ficon = icon;
		Fname = name;
		Fdate = date;
		Fdirector = director;
		Fepisode = episode;
		Fgenre = genre;
	}
	
	// ham getters
	int getEpisode() {
		return Fepisode;
	}
	
	// ham setters
	public void setEpisode(int ep) {
		Fepisode = ep;
	}

	@Override
	String showFilm() {
		String res = "";
		res += Fname + "\n"+ Ficon + "\n"+ Fgenre + "\n" + Fdirector + "\n"+ Fdate +"\n"+ Fcontent +"\n"+ Fepisode;
		return res;
	}

	@Override
	String getType() {
		return "series";
	}
}