package Frames;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import AppUsed.Application;
import Panels.*;

import java.awt.Color;
//import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Application app;


	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application app = new Application();
					char[] pass = new char[] {'t', 'h', 'u', 'y', 't', 'r','a','n','g'};
					app.login("nguyentthuytrang1110@gmail.com", pass);
					MainFrame main = new MainFrame(app);
					AdminFrame frame = new AdminFrame(app,main);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public AdminFrame(Application ap, MainFrame frame) {
		setLocationRelativeTo(null);
		this.app = ap;
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) 
			{
				frame.setVisible(true);
				dispose();
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.BLACK);
		setJMenuBar(menuBar);
		
		JMenu mnReturn = new JMenu("< Return");
		mnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(true);
				dispose();
			}
		});
		mnReturn.setForeground(Color.WHITE);
		menuBar.add(mnReturn);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		menuBar.add(panel);
		
		JButton btnRestart = new JButton("Restart");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Login log = new Login(app.getUser().getEmail());
//				
//				app.logout();
				frame.dispose();
				MyThread thrd = new MyThread("Movie Book", app);
				
				app = null;
				dispose();
				
				thrd.start();
			}
		});
		
		btnRestart.setBackground(new Color(240, 248, 255));
		panel.add(btnRestart);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
				
		initialize();
	}

	void initialize() {
		JTabbedPane tabbedPane = new JTabbedPane();
		getContentPane().add(tabbedPane);
		
		//------Panel Movies created--------------------------
		tabbedPane.addTab("Movies management", new filmManage(app, this, "movie"));

		//------Panel Series created--------------------------
		tabbedPane.addTab("Series management", new filmManage(app, this, "series"));
		
		//------Panel Users created--------------------------
		tabbedPane.addTab("Users management", new userManage(app));
	}
}
