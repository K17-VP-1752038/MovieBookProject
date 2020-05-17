package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.border.EmptyBorder;

import AppUsed.Application;
import IOFilmFile.Film;
import IOFilmFile.Movie;
import IOFilmFile.Series;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetailFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtSearch;
	private JTextField textKey;
	private JTextField txtFilm;
	private String Fname, Fdirector, Fdate, F, Fcontent, Fgenre, Ficon, Ftrailer;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application app = new Application();
					char[] pass = new char[] {'b', 'e', 'o', 'b', 'e', 'o'};
					app.login("winterheartlove@gmail.com", pass);
					Film[]f = app.readFilm();
					new DetailFrame(f[0]);
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public DetailFrame(Film f) {	
		Fname = f.getName();
		Fdirector = "Directed by: " + f.getDirector();
		Fdate = "Released date: "+ f.getDate();
		Fgenre = "Type: " + f.getGenre();
		Fcontent = f.getContent();
		String[]name = f.getName().split(" ");
		String tmp = "";
		
		//lien ket youtube
		for(int i = 0; i < name.length; i++)
			tmp = tmp + name[i] + "+";
		Ftrailer = "https://www.youtube.com/results?search_query=" + tmp + "trailer";
		System.out.println(Ftrailer);
		if(f.getType().equals("movie")) {
			F = "Running time: " + String.valueOf(((Movie)f).getDuration());
			Ficon = "movies\\Img\\" + f.getIcon();
		}
		else {
			F = "Episodes: " + String.valueOf(((Series)f).getEpisode());
			Ficon = "series\\Img\\" + f.getIcon();
		}
		initialize();
			
	}
	void initialize() {
		JFrame frame = new JFrame();
		frame.setSize(1000,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0, 0, 0));
		setJMenuBar(menuBar);
		
		JMenu mnHome = new JMenu("HOME");
		mnHome.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		mnHome.setForeground(new Color(255, 0, 0));
		mnHome.setBackground(new Color(0, 0, 0));
		menuBar.add(mnHome);
		
		JMenu mnNewMenu_1 = new JMenu("TVseries");
		mnNewMenu_1.setBackground(new Color(0, 0, 0));
		mnNewMenu_1.setForeground(new Color(255, 0, 0));
		mnNewMenu_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("Movies");
		mnNewMenu_2.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		mnNewMenu_2.setForeground(new Color(255, 0, 0));
		mnNewMenu_2.setBackground(new Color(0, 0, 0));
		menuBar.add(mnNewMenu_2);
		
		JPanel panelRightTop = new JPanel();
		panelRightTop.setOpaque(false);
		panelRightTop.setBackground(new Color(0, 0, 0));
		FlowLayout flowLayout = (FlowLayout) panelRightTop.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		menuBar.add(panelRightTop);
		
		txtSearch = new JTextField();
		panelRightTop.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("");
		btnSearch.setPreferredSize(new Dimension(30,30));
		btnSearch.setFocusPainted(true);
		btnSearch.setContentAreaFilled(false);
		btnSearch.setBorderPainted(false);
		btnSearch.setIcon(new ImageIcon(new ImageIcon("icon\\iconsearch.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
		btnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panelRightTop.add(btnSearch);
		
		JLabel lblUser = new JLabel("");
		lblUser.setIcon(new ImageIcon(new ImageIcon("icon\\iconuser.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
		lblUser.setForeground(Color.BLACK);
		panelRightTop.add(lblUser);
		frame.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelLeft = new JPanel();
		panelLeft.setBackground(Color.WHITE);
		frame.getContentPane().add(panelLeft);
		
		FlowLayout fl_panelLeft = new FlowLayout(FlowLayout.CENTER, 0, 0);
		panelLeft.setOpaque(false);
		panelLeft.setLayout(fl_panelLeft);
		
		JLabel lblimg = new JLabel("");
		lblimg.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblimg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Desktop d = Desktop.getDesktop();
				try {
					d.browse(new URI(Ftrailer));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		lblimg.setBackground(Color.WHITE);
		lblimg.setIcon(new ImageIcon(Ficon));
		
		panelLeft.add(lblimg);
		
		
		JPanel panelRight = new JPanel();
		panelRight.setBackground(Color.WHITE);
		frame.getContentPane().add(panelRight);
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
		
		JPanel panelName = new JPanel();
		panelName.setOpaque(false);
		panelRight.add(panelName);
		//panelName.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		
		//JLabel lblName = new JLabel(Fname);
		JTextArea txtName = new JTextArea(Fname);
		txtName.setLineWrap(true);
		txtName.setWrapStyleWord(true);
		txtName.setEditable(false);
		//txtName.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelName.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
		panelName.setLayout(new GridLayout(0, 1, 0, 0));
		panelName.add(txtName);
		txtName.setFont(new Font("Segoe UI Black", Font.PLAIN, 35));
		txtName.setForeground(Color.RED);
		
		
		JPanel panelType = new JPanel();
		panelType.setOpaque(false);
		panelRight.add(panelType);
		panelType.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		
		JLabel lblType = new JLabel(Fgenre);
		panelType.add(lblType);
		lblType.setFont(new Font("Tahoma", Font.BOLD, 14));
		//lblType.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel panelDirector = new JPanel();
		panelDirector.setOpaque(false);
		panelRight.add(panelDirector);
		panelDirector.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		
		JLabel lblDirector = new JLabel(Fdirector);
		panelDirector.add(lblDirector);
		lblDirector.setFont(new Font("Tahoma", Font.BOLD, 14));
		//lblDirector.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel lblTime = new JLabel(F);
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 14));
		//lblTime.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel panelF = new JPanel();
		panelF.setOpaque(false);
		panelRight.add(panelF);
		panelF.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		panelF.add(lblTime);
		
		JPanel panelDate = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelDate.getLayout();
		flowLayout_1.setHgap(20);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panelDate.setOpaque(false);
		panelRight.add(panelDate);
		
		JLabel lblDate = new JLabel(Fdate);
		panelDate.add(lblDate);
		//lblDate.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		//lblDate.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel panelContent = new JPanel();
		panelRight.add(panelContent);
		panelContent.setLayout(new BoxLayout(panelContent, BoxLayout.X_AXIS));
		
		JTextArea txtContent = new JTextArea();
		panelContent.setOpaque(false);
		panelContent.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 10));
		panelContent.add(txtContent);
		txtContent.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtContent.setLineWrap(true);
		txtContent.setWrapStyleWord(true);
		txtContent.setText(Fcontent);
		txtContent.setEditable(false);
		frame.setVisible(true);
	}
}


