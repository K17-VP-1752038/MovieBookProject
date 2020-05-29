package Frames;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import AppUsed.Application;
import Panels.*;

import java.awt.Color;
import java.awt.EventQueue;

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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application app = new Application();
					char[] pass = new char[] {'t', 'h', 'u', 'y', 't', 'r','a','n','g'};
					app.login("nguyentthuytrang1110@gmail.com", pass);
					AdminFrame frame = new AdminFrame(app);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminFrame(Application app) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(950, 680);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.BLACK);
		setJMenuBar(menuBar);
		
		JMenu mnReturn = new JMenu("< Return");
		mnReturn.setForeground(Color.WHITE);
		menuBar.add(mnReturn);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		this.app = app;
		
		initialize();
	}

	void initialize() {
		JTabbedPane tabbedPane = new JTabbedPane();
		getContentPane().add(tabbedPane);
		
		//------Panel Movies created--------------------------
		tabbedPane.addTab("Movies management", new filmManage(app, "movie"));

		//------Panel Series created--------------------------
		tabbedPane.addTab("Series management", new filmManage(app, "series"));
		
		//JPanel paneUser = new JPanel();
		tabbedPane.addTab("Users management", new userManage(app));
	}
}
