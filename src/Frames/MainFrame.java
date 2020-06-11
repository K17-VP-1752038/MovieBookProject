package Frames;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import AppUsed.Application;
import IOFilmFile.Film;
import Panels.*;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame implements MouseListener, ActionListener, ItemListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Application app;
	private JPanel panelBottom, panelCenter;
	private JScrollPane scrollPane, scrollMovies, scrollSeries, scrollSearch, scrollFilter;
	private JTextArea txtName;
	private JTextField txtSearch;
	private JMenu mnHome;
	private JMenu mnTVseries;
	private JMenu mnMovies;
	private JMenu mnAdmin;
	private ArrayList<String> listCheckbox = new ArrayList<String>();
	private JCheckBox cbaction, cbadventure, cbsport, cbdrama, cbanimation,cbhorror, cbthriller, cbroman,cbfantasy,cbfiction,cbcomedy,cbdetective;
	private JMenuItem mnChangeinfo;
	private JMenuItem mnChangepwd;
	private JMenuItem mnLogOut;
	private JMenuItem mnAbout;
	private DetailPanel detail;
	private UserPanel user;
	private JLabel lblUser;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					char[] pass = new char[] {'t', 'h', 'u', 'y', 't', 'r', 'a', 'n','g'};
					Application app = new Application();
					app.login("nguyentthuytrang1110@gmail.com", pass);
					MainFrame frame = new MainFrame(app);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public MainFrame(Application ap) {
		this.app = ap;
		
		setTitle("Movie Book");
		setSize(950, 700);
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
			mnAdmin.addMouseListener(this);
			menuBar.add(mnAdmin);
			
		}
		
		JPanel panelRightTop = new JPanel();
		panelRightTop.setBackground(new Color(0, 0, 0));
		FlowLayout flowLayout = (FlowLayout) panelRightTop.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		menuBar.add(panelRightTop);
		
		txtSearch = new JTextField();
		panelRightTop.add(txtSearch);
		txtSearch.setColumns(12);
		
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
				search(txtSearch.getText(), app);
			}
		});
		panelRightTop.add(btnSearch);
		
		lblUser = new JLabel(app.getUser().getFirstName());
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
		menuBarSettings.add(mnSettings);
		
		mnChangeinfo = new JMenuItem("Change Information");
		mnSettings.add(mnChangeinfo);
		mnChangeinfo.addActionListener(this);
		
		mnChangepwd = new JMenuItem("Change Password");
		mnSettings.add(mnChangepwd);
		mnChangepwd.addActionListener(this);
		
		mnLogOut = new JMenuItem("Log Out");
		mnSettings.add(mnLogOut);
		mnSettings.addSeparator();
		mnLogOut.addActionListener(this);
		
		mnAbout = new JMenuItem("About");
		mnAbout.addActionListener(this);
		mnSettings.add(mnAbout);
		
		panelCenter = new JPanel();
		panelCenter.setLayout(new CardLayout());
		scrollPane = new JScrollPane();
		scrollPane.setAlignmentX(2.0f);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		scrollMovies = new JScrollPane();
		scrollMovies.setAlignmentX(2.0f);
		scrollMovies.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		scrollSeries = new JScrollPane();
		scrollSeries.setAlignmentX(2.0f);
		scrollSeries.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		scrollFilter = new JScrollPane();
		scrollFilter.setAlignmentX(2.0f);
		scrollFilter.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		scrollSearch = new JScrollPane();
		scrollSearch.setAlignmentX(2.0f);
		scrollSearch.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		panelCenter.add("Show films",scrollPane);
		panelCenter.add("Show movies",scrollMovies);
		panelCenter.add("Show series",scrollSeries);
		panelCenter.add("Search",scrollSearch);
		panelCenter.add("Filter",scrollFilter);
		user = new UserPanel(app,this);
		panelCenter.add("Settings", user);
		
		panelBottom = new JPanel();
		panelBottom.setBackground(Color.WHITE);
		Film[]flist = app.readFilm();
		Filter(flist, scrollPane);
		
		flist = app.readMovie();
		Filter(flist, scrollMovies);
		
		flist = app.readSeries();
		Filter(flist, scrollSeries);
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(panelCenter);
		
		JPanel panelTop = new JPanel();
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
		detail = new DetailPanel(app);
		panelCenter.add("Show detail", detail);
	}
	
	public void setUser(String name) {
		lblUser.setText(name);
	}

	void search(String txtsearch, Application app) {
		if(txtsearch.isEmpty() == false) {
			Film[]flist = app.searchByKeyWord(txtsearch);
			if(flist.length > 0) {
				Filter(flist, scrollSearch);
				CardLayout card = (CardLayout)panelCenter.getLayout();
				card.show(panelCenter, "Search");
			}else
				JOptionPane.showMessageDialog(panelBottom, "Not Found");
			
		}
		else {
			JOptionPane.showMessageDialog(panelBottom, "Not Found");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == mnHome) {
			CardLayout card = (CardLayout)panelCenter.getLayout();
			card.show(panelCenter, "Show films");
		}
		if(e.getSource() == mnTVseries) {
			CardLayout card = (CardLayout)panelCenter.getLayout();
			card.show(panelCenter, "Show series");
		}
		if(e.getSource() == mnMovies) {
			CardLayout card = (CardLayout)panelCenter.getLayout();
			card.show(panelCenter, "Show movies");
		}
		if(e.getSource() == mnAdmin) {
			AdminFrame ad = new AdminFrame(app, this);
			ad.setSize(getSize());
			ad.setLocation(getLocationOnScreen());
			ad.setVisible(true);
			
			setVisible(false);
			//dispose();
		}
	}
	
	void Filter(Film[]flist, JScrollPane scroll) {
		panelBottom = new JPanel();
		panelBottom.setBackground(Color.WHITE);
		
		for(int i = 0; i < flist.length; i++) {
			JPanel panel = new JPanel();
			JLabel lblImg = new JLabel();
			panel.setLayout(new FlowLayout());
			panel.setPreferredSize(new Dimension(280,250));
			panel.setBackground(Color.white);
			
			
			BufferedImage img;
			try {
				if(flist[i].getType().equals("movie")) {
					img = (BufferedImage) ImageIO.read(new File("movies/Img/"+flist[i].getIcon()));
				}
				else {
					img = (BufferedImage) ImageIO.read(new File("series/Img/"+ flist[i].getIcon()));
				}
				ImageIcon icon = new ImageIcon(img.getScaledInstance(280, 200, Image.SCALE_SMOOTH));

				lblImg.setIcon(icon);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			lblImg.setCursor(new Cursor(Cursor.HAND_CURSOR));

			panel.add(lblImg);
			Film f = flist[i];

			lblImg.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					detail.setContent(f);
					CardLayout card = (CardLayout) panelCenter.getLayout();
					card.show(panelCenter, "Show detail");
				}
			});
			txtName = new JTextArea(flist[i].getName());
			txtName.setPreferredSize(new Dimension(280,40));
			txtName.setLineWrap(true);
			txtName.setWrapStyleWord(true);
			txtName.setEditable(false);
			txtName.setForeground(Color.RED);
			txtName.setFont(new Font("Tahoma", Font.BOLD, 15));
			panel.add(txtName);
			panelBottom.add(panel);
		}
		scroll.setViewportView(panelBottom);
		panelBottom.setLayout(new GridLayout(0, 3, 10, 15));
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String str = e.getActionCommand();
		if(str.equals("Filter")) {
			if(listCheckbox.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please check the box to filter");
				return;
			}
			Film[]f = app.searchByGenre(listCheckbox);
			if(f.length == 0) 
				JOptionPane.showMessageDialog(panelBottom, "Not Found");
			else
				Filter(f, scrollFilter);
			cbaction.setSelected(false);
			cbadventure.setSelected(false);
			cbanimation.setSelected(false);
			cbcomedy.setSelected(false);
			cbdetective.setSelected(false);
			cbdrama.setSelected(false);
			cbfantasy.setSelected(false);
			cbfiction.setSelected(false);
			cbhorror.setSelected(false);
			cbroman.setSelected(false);
			cbsport.setSelected(false);
			cbthriller.setSelected(false);
			listCheckbox.clear();
			CardLayout card = (CardLayout)panelCenter.getLayout();
			card.show(panelCenter, "Filter");
		}
		if(str.equals("Change Information")) {
			user.setIndex(0);
			CardLayout card = (CardLayout)panelCenter.getLayout();
			card.show(panelCenter, "Settings");
		}
		if(str.equals("Change Password")) {
			user.setIndex(1);
			CardLayout card = (CardLayout)panelCenter.getLayout();
			card.show(panelCenter, "Settings");
		}
		if(str.equals("Log Out")) {
			int op = JOptionPane.showConfirmDialog(null, "Are you sure want to log out?");
			if(op == 0) {
				app.logout();
				app = null;
				
				Login login = new Login();
				login.setVisible(true);
				dispose();
				
				System.gc();
			}
		}
		if(str.equals("About")) {
			AboutUs about = new AboutUs();
			about.setVisible(true);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		JCheckBox cb = (JCheckBox)e.getItem();
		if(cb.isSelected()) 
			listCheckbox.add(cb.getText());
				
	}
}
