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
	void setName(String name) {
		this.Fname = name;
	}
	void setIcon(String icon) {
		this.Ficon = icon;
	}
	void setGenre(String genre) {
		this.Fgenre = genre;
	}
	void setDirector(String director) {
		this.Fdirector = director;
	}
	void setDate(String date) {
		this.Fdate = date;
	}
	void setContent(String content) {
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
	abstract String showFilm();
	// Get type of film
	abstract String getType();
}