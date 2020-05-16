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
	public int getEpisode() {
		return Fepisode;
	}
	
	// ham setters
	void setEpisode(int ep) {
		Fepisode = ep;
	}

	@Override
	public String showFilm() {
		String res = "";
		res += getName() + "\n"+ getIcon() + "\n"+ getGenre() + "\n" + getDirector() + "\n"+ getDate() +"\n" + getEpisode()+"\n"+getContent();
		return res;
	}

	@Override
	public String getType() {
		return "series";
	}

	@Override
	public String toString() {
		return getName();
	}
}