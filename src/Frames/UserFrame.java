package Frames;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import AppUsed.Application;
import AppUsed.MailConfig;
import IOFilmFile.Film;

public class UserFrame extends JFrame implements ActionListener, MouseListener, ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtSearch, tfFirstName, tfLastName;
	private JMenu mnHome, mnTVseries, mnMovies, mnAdmin;
	private Application app;
	private JList<String> list;
	private JMenuItem mnAbout, mnLogOut, mnChangeinfo, mnChangepwd;
	private JPanel pane1, pane2, paneCard, panel;
	private JButton btnChangePw, btnChangeInfo;
	private JPasswordField tfConfirm, tfOldPass, tfNewPass;
	private JLabel name;
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application ap = new Application();
					char[] pass = new char[] {'b', 'e', 'o', 'b', 'e', 'o','9','9'};
					if(ap.login("winterheartlove18@gmail.com", pass)) {
						UserFrame frame = new UserFrame(ap, 0);
						frame.setVisible(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public UserFrame(Application app, int index) {
		setSize(950,680);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0, 0, 0));
		setJMenuBar(menuBar);
		
		this.app = app;
		
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
	
		String[] option = {"Change Information", "Change Password", "Log out"};
	
		list = new JList<>();
		list.setFixedCellHeight(28);
		list.setFixedCellWidth(160);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setListData(option);
		list.addListSelectionListener(this);
		
		JScrollPane paneList = new JScrollPane(list);
		getContentPane().add(paneList, BorderLayout.WEST);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel iconPane = new JPanel();
		JLabel lblIcon = new JLabel("");
		BufferedImage img;
		try {
			img = (BufferedImage) ImageIO.read(new File("Img/usericon.png"));
			ImageIcon icon = new ImageIcon(img.getScaledInstance(120, 120, Image.SCALE_SMOOTH));
			lblIcon.setIcon(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		iconPane.add(lblIcon);
		iconPane.setBorder(new EmptyBorder(10, 5, 5, 5));
		panel.add(iconPane);
	
//		JPanel paneMail = new JPanel();
//		paneMail.add(new JLabel(app.getUser().getEmail()));
//		panel.add(paneMail);
		
		JPanel paneName = new JPanel();
		name = new JLabel(app.getUser().getFirstName() + " "+ app.getUser().getName());
		paneName.add(name);
		panel.add(paneName);
		
		initPaneInfo();
		initPanePassword();
		
		paneCard = new JPanel();
		panel.add(paneCard);
		paneCard.setLayout(new CardLayout());
		paneCard.add("Change Information", pane1);
		paneCard.add("Change Password", pane2);
		
		list.setSelectedIndex(index);
	}
	
	private void initPaneInfo() {
		pane1 = new JPanel();
		
		JPanel paneInfo = new JPanel();
		paneInfo.setLayout(new BoxLayout(paneInfo, BoxLayout.Y_AXIS));
		pane1.add(paneInfo);
		
		JPanel form = new JPanel();
		form.setBorder(new EmptyBorder(8, 5, 1, 5));
		paneInfo.add(form);
		form.setLayout(new BorderLayout(5, 5));
		
		JPanel inputLabels = new JPanel(new GridLayout(0, 1, 3, 3));
	    JPanel inputFields = new JPanel(new GridLayout(0, 1, 3, 3));
		form.add(inputLabels, BorderLayout.WEST);
		form.add(inputFields, BorderLayout.CENTER);
		
		inputLabels.add(new JLabel("First name: "));
		inputLabels.add(new JLabel("Last name: "));
		
		tfFirstName = new JTextField(15);
		tfFirstName.setFont(new Font("Arial", Font.PLAIN, 12));
		
		tfLastName = new JTextField(15);
		tfLastName.setFont(new Font("Arial", Font.PLAIN, 12));

		inputFields.add(tfFirstName);
		inputFields.add(tfLastName);
		
		JPanel paneEnter = new JPanel();
		paneEnter.setBorder(new EmptyBorder(3, 0, 1, 0));
		paneInfo.add(paneEnter);
		
		btnChangeInfo = new JButton("Change name");
		btnChangeInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfFirstName.getText().isBlank() && tfLastName.getText().isBlank())
					JOptionPane.showMessageDialog(new JFrame(), "Name can't be null.");
				else {
					// Steps to change the user name in database
					app.getUser().setName(tfLastName.getText());
					app.getUser().setFirstName(tfFirstName.getText());
					app.getUser().updateUserInfo();
					// Change the name in screen
					name.setText(tfFirstName.getText() + " "+ tfLastName.getText());
				}
			}
		});

		btnChangeInfo.setBackground(Color.BLACK);
		btnChangeInfo.setForeground(Color.WHITE);
		btnChangeInfo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		paneEnter.add(btnChangeInfo);
	}
	
	private void initPanePassword() {
		pane2 = new JPanel();
		
		JPanel panePassword = new JPanel();
		panePassword.setLayout(new BoxLayout(panePassword, BoxLayout.Y_AXIS));
		pane2.add(panePassword);
		
		JPanel form = new JPanel();
		form.setBorder(new EmptyBorder(8, 5, 1, 5));
		panePassword.add(form);
		form.setLayout(new BorderLayout(5, 5));
		
		JPanel inputLabels = new JPanel(new GridLayout(0, 1, 3, 3));
	    JPanel inputFields = new JPanel(new GridLayout(0, 1, 3, 3));
		form.add(inputLabels, BorderLayout.WEST);
		form.add(inputFields, BorderLayout.CENTER);
		
		inputLabels.add(new JLabel("Password: "));
		inputLabels.add(new JLabel("New password: "));
		inputLabels.add(new JLabel("Confirm: "));
		
		tfOldPass = new JPasswordField(15);
		tfOldPass.setFont(new Font("Arial", Font.PLAIN, 12));
		
		tfNewPass = new JPasswordField(15);
		tfOldPass.setFont(new Font("Arial", Font.PLAIN, 12));
	
		tfConfirm = new JPasswordField(15);
		tfConfirm.setFont(new Font("Arial", Font.PLAIN, 12));
		
		inputFields.add(tfOldPass);
		inputFields.add(tfNewPass);
		inputFields.add(tfConfirm);
		
		JPanel paneEnter = new JPanel();
		paneEnter.setBorder(new EmptyBorder(3, 0, 1, 0));
		panePassword.add(paneEnter);
		
		btnChangePw = new JButton("Change password");
		btnChangePw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfOldPass.getPassword().length< 6 || tfNewPass.getPassword().length < 6 || !Arrays.equals(tfConfirm.getPassword(),tfNewPass.getPassword()))
					JOptionPane.showMessageDialog(new JFrame(), "Password is invalid or not match");
				else if(app.updatePassword(tfOldPass.getPassword(), tfNewPass.getPassword()))
					JOptionPane.showMessageDialog(new JFrame(), "Password is incorrect");				
				else {
					try {
						MailConfig.sendEmail(app.getUser().getEmail(), "Password Changed", "You have changed your password in Movie Book since "+ new java.util.Date());
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		
		btnChangePw.setBackground(Color.BLACK);
		btnChangePw.setForeground(Color.WHITE);
		btnChangePw.setFont(new Font("Tahoma", Font.PLAIN, 12));
		paneEnter.add(btnChangePw);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String str = e.getActionCommand();
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Film[]flist = null;
		setEnabled(false);
		MainFrame main = new MainFrame(app);
		/*if(e.getSource() == mnHome) 
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
		}*/
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
	public void valueChanged(ListSelectionEvent e) {
		if(list.getSelectedIndex() == 0) {
			CardLayout cardLayout = (CardLayout)(paneCard.getLayout());
			cardLayout.show(paneCard, "Change Information");
		}
		if(list.getSelectedIndex() == 1) {
			CardLayout cardLayout = (CardLayout)(paneCard.getLayout());
			cardLayout.show(paneCard, "Change Password");
		}
		if(list.getSelectedIndex() == 2) {
			int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?");
			if(option == 0) {
				setVisible(false);
				app.logout();
				Login frame = new Login();
				frame.setVisible(true);
			}
		}
	}
}
