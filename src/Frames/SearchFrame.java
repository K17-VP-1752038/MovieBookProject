package Frames;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionListener;
import AppUsed.Application;
import IOFilmFile.*;

import javax.swing.event.ListSelectionEvent;

// phải lưu kiểu UTF-8
public class SearchFrame implements ActionListener, ListSelectionListener{

	private Application app = new Application();
	private JFrame frmMovieBook;
	private JTextField keyWord;
	private JButton btnSearch;
	private JList<Film> resultList;
	private JPanel showPane;
	private JScrollPane scrollPane;
	
	private JLabel Fimg = new JLabel("");
	private JLabel Fname = new JLabel("");
	private JLabel Ftype = new JLabel("");
	private JLabel Fdirector = new JLabel("");
	private JLabel Fdate = new JLabel("");
	private JLabel Ftime = new JLabel("");
	private JTextArea Fcontent = new JTextArea();
	private final JScrollPane scrollPane_list = new JScrollPane();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			new SearchFrame();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public SearchFrame() {
		// Giả sử login, login xong mới có thể sử dụng chương trình
		if(app.login("winterheartlove@gmail.com", "beobeo"));
			initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMovieBook = new JFrame();
		frmMovieBook.setTitle("Movie Book");
		frmMovieBook.setBounds(100, 100, 737, 470);
		frmMovieBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMovieBook.getContentPane().setLayout(null);
		
		keyWord = new JTextField();
		keyWord.setBounds(71, 35, 271, 26);
		keyWord.addActionListener(this);
		frmMovieBook.getContentPane().add(keyWord);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(this);
		btnSearch.setBounds(345, 35, 89, 26);
		frmMovieBook.getContentPane().add(btnSearch);
		scrollPane_list.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_list.setBounds(21, 100, 173, 309);
		
		frmMovieBook.getContentPane().add(scrollPane_list);
		
		resultList = new JList<Film>();
		scrollPane_list.setViewportView(resultList);
		resultList.setBorder(new LineBorder(Color.LIGHT_GRAY));
		resultList.addListSelectionListener(this);
		resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		resultList.setFont(new Font("Arial", Font.PLAIN, 12));
		resultList.setListData(app.readFilm());
		
		createShowFilmPanel();
		scrollPane = new JScrollPane(showPane);
		
		Fcontent.setWrapStyleWord(true);
		Fcontent.setLineWrap(true);
		
		scrollPane.setBounds(211, 100, 488, 309);
		scrollPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		frmMovieBook.getContentPane().add(scrollPane);
		
		frmMovieBook.setVisible(true);
	}
	
	// make option for list searched
	String[] makeListOption(ArrayList<Film> list) {
		String names[] = new String[list.size()];
		for(int i = 0; i < list.size(); i++) {
			names[i] = list.get(i).getName();
		}
		return names;
	}
	
	// Create panel to show movie's information
	private void createShowFilmPanel() {
		showPane = new JPanel();
		showPane.setBackground(Color.WHITE);
		showPane.setLayout(new BoxLayout(showPane, BoxLayout.Y_AXIS));
		
		showPane.add(Fimg);
		showPane.add(Fname);
		showPane.add(Ftype);
		showPane.add(Fdirector);
		showPane.add(Fdate);
		showPane.add(Ftime);
		showPane.add(Fcontent);
	}
	
	// Create label to show movie's image
	private void createFilmImg(String imgName, String type) {
		Fimg.setSize(120, 180);
		
		BufferedImage img;
		try {
			if(type.equals("movie"))
				img = (BufferedImage) ImageIO.read(new File("movies/Img/"+ imgName));
			else
				img = (BufferedImage) ImageIO.read(new File("series/Img/"+ imgName));
			ImageIcon icon = new ImageIcon(img.getScaledInstance(120, 180, Image.SCALE_SMOOTH));
			Fimg.setIcon(icon);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Event for List selection
	private void changeFilmShow(Film filmShow) {
		
		createFilmImg(filmShow.getIcon(), filmShow.getType());
		Fname.setText(filmShow.getName().toUpperCase());
		Ftype.setText("Genre: " + filmShow.getGenre());
		Fdirector.setText("Directed by: "+ filmShow.getDirector());
		Fdate.setText("In theaters: "+ filmShow.getDate());
		if(filmShow.getType().equals("movie"))
			Ftime.setText("Runtime: "+ ((Movie)filmShow).getDuration() + " minutes");
		else
			Ftime.setText("Episode: "+ ((Series) filmShow).getEpisode());
		
		Fcontent.setText(filmShow.getContent());
		
	}
	
	// Event for button Search
	public void actionPerformed(ActionEvent e) {
		Film[] filmList = app.searchByKeyWord(keyWord.getText());
			
		resultList.setListData(filmList);
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if(resultList.getSelectedIndex() != -1) {
			Film filmShow = resultList.getSelectedValue();
			changeFilmShow(filmShow);
		}
		
	}
}