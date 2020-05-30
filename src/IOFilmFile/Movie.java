package IOFilmFile;

public class Movie extends Film {

	private int Fduration;
	
	public Movie() {
		Fname = null;
		Ficon = null;
		Fgenre = null;
		Fdirector = null;
		Fduration = 0;
		Fdate = null;
		Fcontent = null;
	}
	
	Movie(Movie B) {
		this.Fcontent = B.Fcontent;
		this.Ficon = B.Ficon;
		this.Fname = B.Fname;
		this.Fdate = B.Fdate;
		this.Fdirector = B.Fdirector;
		this.Fduration = B.Fduration;
		this.Fgenre = B.Fgenre;
	}
	
	public Movie(String name, String icon, String genre, String director, int time, String date, String content){
		Fcontent = content;
		Ficon = icon;
		Fname = name;
		Fdate = date;
		Fdirector = director;
		Fduration = time;
		Fgenre = genre;
	}
	
	public int getDuration() {
		return this.Fduration;
	}
	
	void setDuration(int d) {
		this.Fduration = d;
	}

	@Override
	public String showFilm() {
		String res = "";
		res += getName() + "\n"+ getIcon() + "\n"+ getGenre() + "\n" + getDirector() + "\n" + getDuration() +"\n"+ getDate() +"\n"+ getContent();
		return res;
	}

	@Override
	public String getType() {
		return "movie";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName();
	}
}
