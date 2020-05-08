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
import IOFilmFile.Film;
import IOFilmFile.filmReadWrite;

import javax.swing.event.ListSelectionEvent;

public class SearchFrame implements ActionListener, ListSelectionListener{

	Application app = new Application();
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
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
			SearchFrame window = new SearchFrame();
			window.frmMovieBook.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public SearchFrame() {
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
		
		resultList = new JList<Film>();
		resultList.setBorder(new LineBorder(Color.LIGHT_GRAY));
		resultList.addListSelectionListener(this);
		resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		resultList.setFont(new Font("Arial", Font.PLAIN, 12));
		resultList.setBounds(21, 100, 173, 309);
		frmMovieBook.getContentPane().add(resultList);
		
		createShowFilmPanel();
		scrollPane = new JScrollPane(showPane);
		
		Fcontent.setWrapStyleWord(true);
		Fcontent.setLineWrap(true);
		
		scrollPane.setBounds(211, 100, 488, 309);
		scrollPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		frmMovieBook.getContentPane().add(scrollPane);
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
	private void createFilmImg(String imgName) {
		Fimg.setSize(120, 180);
		
		BufferedImage img;
		try {
			img = (BufferedImage) ImageIO.read(new File("movies/Img/"+ imgName));
			
			ImageIcon icon = new ImageIcon(img.getScaledInstance(120, 180, Image.SCALE_SMOOTH));
			Fimg.setIcon(icon);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Event for List selection
	private void changeFilmShow() {
		Film filmShow = resultList.getSelectedValue();
		createFilmImg(filmShow.getIcon());
		Fname.setText(filmShow.getName().toUpperCase());
		Ftype.setText("Genre: " + filmShow.getGenre());
		Fdirector.setText("Directed by: "+ filmShow.getDirector());
		Fdate.setText("In theaters: "+ filmShow.getDate());
//		Ftime.setText("Runtime: "+ filmShow.getDuration() + " minutes");
		
		Fcontent.setText(filmShow.getContent());
		
	}
	
	// Event for button Search
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSearch) {
//			filmList = films.readFilmByName(filename, keyWord.getText());
			
			resultList.setListData(app.readFilm());
		}
//		if(resultList.getSelectedIndex() != -1) {
//			Film filmShow = resultList.getSelectedValue();
//			JLabel Img = createFilmImg(filmShow.getIcon());
//			JLabel Name = new JLabel(filmShow.getName());
//			
//			scrollPane.add(Img);
//			scrollPane.add(Name);
//		}
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if(resultList.getSelectedIndex() != -1) {
			Film filmShow = resultList.getSelectedValue();
//			JLabel Img = createFilmImg(filmShow.getIcon());
			JLabel Name = new JLabel(filmShow.getName());
			
//			scrollPane.add(Img);
			scrollPane.add(Name);
		}
		
	}
}