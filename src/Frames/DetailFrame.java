package Frames;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import AppUsed.Application;
import IOFilmFile.Film;
import IOFilmFile.Movie;
import IOFilmFile.Series;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

class DetailFrame extends JFrame implements MouseListener, ActionListener, ItemListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtSearch;
	private JTextField textKey;
	private JTextField txtFilm;
	private String Fname, Fdirector, Fdate, F, Fcontent, Fgenre, Ficon, Ftrailer;
	private JPanel panelRight;
	private JPanel panelLeft;
	private JPanel panelBottom;
	private JPanel panelTop;
	private JMenu mnHome;
	private JMenu mnTVseries;
	private JMenu mnMovies;
	private JMenu mnAdmin;
	private Application app;
	private JCheckBox cbaction, cbadventure, cbsport, cbdrama, cbanimation,cbhorror, cbthriller, cbroman,cbfantasy,cbfiction,cbcomedy,cbdetective;
	private ArrayList<String> listCheckbox = new ArrayList<String>();
	private JMenuItem mnChangeinfo;
	private JMenuItem mnChangepwd;
	private JMenuItem mnLogOut;
	private JMenuItem mnAbout;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Application app = new Application();
//					char[] pass = new char[] {'b', 'e', 'o', 'b', 'e', 'o'};
//					app.login("winterheartlove@gmail.com", pass);
//					Film[]f = app.readFilm();
//					new DetailFrame(f[0]);
//					//frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public DetailFrame(Film f, Application app) {	
		this.app = app;
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
		if(f.getType().equals("movie")) {
			F = "Running time: " + String.valueOf(((Movie)f).getDuration());
			Ficon = "movies\\Img\\" + f.getIcon();
		}
		else {
			F = "Episodes: " + String.valueOf(((Series)f).getEpisode());
			Ficon = "series\\Img\\" + f.getIcon();
		}
		initialize(app);
			
	}
	void initialize(Application app) {
		setSize(950,680);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0, 0, 0));
		setJMenuBar(menuBar);
		
		mnHome = new JMenu("HOME");
		mnHome.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		mnHome.setForeground(new Color(255, 0, 0));
		mnHome.setBackground(new Color(0, 0, 0));
		mnHome.addMouseListener(this);
		menuBar.add(mnHome);
		
		mnTVseries = new JMenu("TVseries");
		mnTVseries.setBackground(new Color(0, 0, 0));
		mnTVseries.setForeground(new Color(255, 0, 0));
		mnTVseries.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		mnTVseries.addMouseListener(this);
		menuBar.add(mnTVseries);
		
		mnMovies = new JMenu("Movies");
		mnMovies.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		mnMovies.setForeground(new Color(255, 0, 0));
		mnMovies.setBackground(new Color(0, 0, 0));
		mnMovies.addMouseListener(this);
		menuBar.add(mnMovies);
		
		if(app.isAdmin()) {
			mnAdmin = new JMenu("Manager");
			mnAdmin.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
			mnAdmin.setForeground(new Color(255, 255, 255));
			mnAdmin.setBackground(new Color(0, 0, 0));
			menuBar.add(mnAdmin);
		}
		
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
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainFrame main = new MainFrame(app);
				main.setVisible(true);
				main.search(txtSearch.getText(),app);
				setVisible(false);
				setEnabled(false);
			}
		});
		panelRightTop.add(btnSearch);
		
		JLabel lblUser = new JLabel(app.getUser().getName());
		//lblUser.setIcon(new ImageIcon(new ImageIcon("icon\\iconuser.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
		//lblUser.setForeground(Color.BLACK);
		lblUser.setForeground(Color.white);
		panelRightTop.add(lblUser);
		
		JMenuBar menuBarSettings = new JMenuBar();
		menuBarSettings.setBorderPainted(false);
		menuBarSettings.setBackground(Color.BLACK);
		panelRightTop.add(menuBarSettings);
		
		JMenu mnSettings = new JMenu("");
		mnSettings.setBackground(Color.BLACK);
		mnSettings.setOpaque(false);
		mnSettings.setBorderPainted(false);
		mnSettings.setIcon(new ImageIcon(new ImageIcon("icon\\iconsettings.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
		menuBarSettings.setCursor(new Cursor(Cursor.HAND_CURSOR));
		menuBarSettings.add(mnSettings);
		
		mnChangeinfo = new JMenuItem("Change Information");
		mnSettings.add(mnChangeinfo);
		mnChangeinfo.addActionListener(this);
		
		mnChangepwd = new JMenuItem("Change Password");
		mnSettings.add(mnChangepwd);
		mnChangepwd.addActionListener(this);
		
		mnAbout = new JMenuItem("About");
		mnSettings.add(mnAbout);
		mnAbout.addActionListener(this);
		mnSettings.addSeparator();
		
		mnLogOut = new JMenuItem("Log Out");
		mnSettings.add(mnLogOut);
		mnLogOut.addActionListener(this);		
		
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panelBottom = new JPanel();
		getContentPane().add(panelBottom);
		panelBottom.setLayout(new GridLayout(0, 2, 0, 0));
		panelLeft = new JPanel();
		panelBottom.add(panelLeft);
		
		panelLeft.setBackground(Color.WHITE);
		panelLeft.setOpaque(false);
		panelLeft.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
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
		BufferedImage img;
		try {
			img = (BufferedImage) ImageIO.read(new File(Ficon));
			ImageIcon icon = new ImageIcon(img.getScaledInstance(475,680, Image.SCALE_SMOOTH));
			lblimg.setIcon(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//lblimg.setIcon(new ImageIcon(Ficon));
		
		panelLeft.add(lblimg);
		
		panelRight = new JPanel();
		panelRight.setBackground(Color.WHITE);
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
		panelBottom.add(panelRight);
		
		JPanel panelName = new JPanel();
		panelName.setOpaque(false);
		panelRight.add(panelName);
		
		JTextArea txtName = new JTextArea(Fname);
		txtName.setLineWrap(true);
		txtName.setWrapStyleWord(true);
		txtName.setEditable(false);
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
		
		JPanel panelDirector = new JPanel();
		panelDirector.setOpaque(false);
		panelRight.add(panelDirector);
		panelDirector.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		
		JLabel lblDirector = new JLabel(Fdirector);
		panelDirector.add(lblDirector);
		lblDirector.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblTime = new JLabel(F);
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 14));
		
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
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JPanel panelContent = new JPanel();
		panelRight.add(panelContent);
		
		JTextArea txtContent = new JTextArea();
		JScrollPane scroll = new JScrollPane();
		scroll.setEnabled(false);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setViewportView(txtContent);
		panelContent.setOpaque(false);
		panelContent.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 10));
		panelContent.setLayout(new CardLayout(0, 0));
		panelContent.add(scroll, "name_892678557399800");
		txtContent.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtContent.setLineWrap(true);
		txtContent.setWrapStyleWord(true);
		txtContent.setText(Fcontent);
		txtContent.setEditable(false);
		 
		panelTop = new JPanel();
		panelTop.setPreferredSize(new Dimension(950,30));
		panelTop.setBackground(Color.white);
		panelTop.setOpaque(false);
		panelTop.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
		panelTop.setLayout(new GridLayout(0,13,0,0));
		
		cbaction = new JCheckBox("Action");
		cbaction.setToolTipText("Action");
		cbaction.setBorderPainted(false);
		panelTop.add(cbaction);
		
		cbsport = new JCheckBox("Sport");
		cbsport.setToolTipText("Sport");
		cbsport.setBorderPainted(false);
		panelTop.add(cbsport);
		
		cbanimation = new JCheckBox("Animation");
		cbanimation.setToolTipText("Animation");
		cbanimation.setBorderPainted(false);
		panelTop.add(cbanimation);
		
		cbroman = new JCheckBox("Roman");
		cbroman.setToolTipText("Roman");
		cbroman.setBorderPainted(false);
		panelTop.add(cbroman);
		
		cbdrama = new JCheckBox("Drama");
		cbdrama.setToolTipText("Drama");
		cbdrama.setBorderPainted(false);
		panelTop.add(cbdrama);

		cbadventure = new JCheckBox("Adventure");
		cbadventure.setToolTipText("Adventure");
		cbadventure.setBorderPainted(false);
		panelTop.add(cbadventure);

		cbfantasy = new JCheckBox("Fantasy");
		cbfantasy.setToolTipText("Fantasy");
		cbfantasy.setBorderPainted(false);
		panelTop.add(cbfantasy);
		
		cbhorror = new JCheckBox("Horror");
		cbhorror.setToolTipText("Horror");
		cbhorror.setBorderPainted(false);
		panelTop.add(cbhorror);
		
		cbdetective = new JCheckBox("Detective");
		cbdetective.setToolTipText("Detective");
		cbdetective.setBorderPainted(false);
		panelTop.add(cbdetective);
		
		cbthriller = new JCheckBox("Thriller");
		cbthriller.setToolTipText("Thriller");
		cbthriller.setBorderPainted(false);
		panelTop.add(cbthriller);
		
		cbcomedy = new JCheckBox("Comedy");
		cbcomedy.setToolTipText("Comedy");
		cbcomedy.setBorderPainted(false);
		panelTop.add(cbcomedy);
		
		cbfiction = new JCheckBox("Fiction");
		cbfiction.setToolTipText("Fiction");
		cbfiction.setBorderPainted(false);
		panelTop.add(cbfiction);
		
		cbaction.addItemListener(this);
		cbadventure.addItemListener(this);
		cbcomedy.addItemListener(this);
		cbdetective.addItemListener(this);
		cbdrama.addItemListener(this);
		cbfantasy.addItemListener(this);
		cbfiction.addItemListener(this);
		cbhorror.addItemListener(this);
		cbroman.addItemListener(this);
		cbsport.addItemListener(this);
		cbthriller.addItemListener(this);
		cbanimation.addItemListener(this);
		
		JButton btnSearchGenre = new JButton("Filter");
		btnSearchGenre.setBorderPainted(false);
		btnSearchGenre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnSearchGenre.setBackground(new Color(69, 170, 242));
		btnSearchGenre.setForeground(Color.white);
		btnSearchGenre.addActionListener(this);
		panelTop.add(btnSearchGenre);
		
		getContentPane().add(panelTop, BorderLayout.NORTH);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Film[]flist = null;
		setEnabled(false);
		MainFrame main = new MainFrame(app);
		if(e.getSource() == mnHome) 
			main.setVisible(true);
		if(e.getSource() == mnTVseries) {
			main.setVisible(true);
			flist = app.readSeries();
			main.Filter(flist);
		}
		if(e.getSource() == mnMovies){
			main.setVisible(true);
			flist = app.readMovie();
			main.Filter(flist);
		}
		setVisible(false);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		JCheckBox cb = (JCheckBox)e.getItem();
		if(cb.isSelected()) 
			listCheckbox.add(cb.getText());
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String str = e.getActionCommand();
		if(str.equals("Filter")) {
			MainFrame main = new MainFrame(app);
			if(str.equals("Filter")) {
				main.setVisible(true);
				Film[]f = app.searchByGenre(listCheckbox);
				main.Filter(f);
				listCheckbox.clear();
			}
			setVisible(false);
			setEnabled(false);
		}
		if(str.equals("Log Out")) {
			app.logout();
			Login login = new Login();
			login.setVisible(true);
			setVisible(false);
		}
		if(str.equals("Change Information")) {
			UserFrame login = new UserFrame(app, 0);
			login.setVisible(true);
			setVisible(false);
		}
		if(str.equals("Change Password")) {
			UserFrame login = new UserFrame(app, 1);
			login.setVisible(true);
			setVisible(false);
		}
		if(str.equals("About")) {
			AboutUs frame = new AboutUs();
			frame.setVisible(true);
		}
	}	
}


