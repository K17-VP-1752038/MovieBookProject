package IOFilmFile;

import java.io.File;

public class filmImage {

	private String name;
	private File file;
	private String type;
	
	public filmImage(String n, File f, String t) {
		// TODO Auto-generated constructor stub
		name = n;
		file = f;
		type = t;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
