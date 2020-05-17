package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import AppUsed.Application;
import IOFilmFile.Film;
import IOFilmFile.Movie;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JScrollBar;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import javax.swing.JTextPane;

class test extends JFrame{

	private JPanel contentPane;
	private Application app = new Application();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
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
	public test() {
		char[] pass = new char[] {'b', 'e', 'o', 'b', 'e', 'o'};
		app.login("winterheartlove@gmail.com", pass);
		setSize(950,680);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0, 0, 0));
		setJMenuBar(menuBar);
		
		JMenu mnHome = new JMenu("HOME");
		mnHome.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		mnHome.setForeground(new Color(255, 0, 0));
		mnHome.setBackground(new Color(0, 0, 0));
		menuBar.add(mnHome);
		
		JMenu mnNewMenu_1 = new JMenu("TVshows");
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
		panelRightTop.setBackground(new Color(0, 0, 0));
		FlowLayout flowLayout = (FlowLayout) panelRightTop.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		menuBar.add(panelRightTop);
		
		JTextField txtSearch = new JTextField();
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
		
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setPreferredSize(new Dimension(935,50));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("New label");
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane);
		JPanel panelCenter = new JPanel();
		panelCenter.setBackground(Color.red);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(panelCenter);
		panelCenter.setLayout(new GridLayout(0, 3, 10, 15));
		

	}

}
