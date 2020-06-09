package IOFilmFile;


public abstract class Film{
	
//	private static final long serialVersionUID = 1L;
	protected String Fname;
	protected String Ficon;
	protected String Fgenre;
	protected String Fdirector;
	protected String Fdate;
	protected String Fcontent;
	
	
	// SET methods
	public void setName(String name) {
		this.Fname = name;
	}
	
	public void setIcon(String icon) {
		this.Ficon = icon;
	}
	
	public void setGenre(String genre) {
		this.Fgenre = genre;
	}
	
	public void setDirector(String director) {
		this.Fdirector = director;
	}
	
	public void setDate(String date) {
		this.Fdate = date;
	}
	
	public void setContent(String content) {
		this.Fcontent = content;
	}
	
	// GET method
	public String getName() {
		return this.Fname;
	}
	
	public String getGenre() {
		return this.Fgenre;
	}
	
	public String getDirector() {
		return this.Fdirector;
	}
	
	public String getDate() {
		return this.Fdate;
	}
	
	public String getContent() {
		return this.Fcontent;
	}
	
	public String getIcon() {
		return this.Ficon;
	}
	
	// Return film to string
	public abstract String showFilm();
	public abstract String toString();
	
	// Get type of film
	public abstract String getType();
}