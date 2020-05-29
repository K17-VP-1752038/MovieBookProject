package Frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import AppUsed.Application;
import IOFilmFile.Film;
import Panels.filmManage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.awt.event.ActionEvent;

public class InsertFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtGenre;
	private JTextField txtImg;
	private Application app;
	private JTextField txtName;
	private JTextField txtDirector;
	private JPanel panel_1_1;
	private JTextField txtDate;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateFrame frame = new UpdateFrame();
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
	public InsertFrame(Application app) {
		this.app = app;
		setTitle("Insert");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,350);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelTop = new JPanel();
		panelTop.setOpaque(false);
		panelTop.setBackground(SystemColor.activeCaption);
		contentPane.add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblTitle = new JLabel("Insert New Film");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelTop.add(lblTitle);
		
		JPanel panelBottom = new JPanel();
		panelBottom.setOpaque(false);
		contentPane.add(panelBottom, BorderLayout.CENTER);
		panelBottom.setLayout(new BorderLayout(0, 0));
		
		JPanel panelF = new JPanel();
		panelBottom.add(panelF, BorderLayout.NORTH);
		panelF.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelName = new JPanel();
		panelName.setBackground(SystemColor.activeCaption);
		panelF.add(panelName);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBorder(new EmptyBorder(0, 0, 0, 5));
		panel_1_2.setBackground(SystemColor.activeCaption);
		panelName.add(panel_1_2);
		
		JLabel lblImg_2 = new JLabel("Name");
		lblImg_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_1_2.add(lblImg_2);
		
		txtName = new JTextField();
		txtName.setText("");
		txtName.setColumns(34);
		panelName.add(txtName);
		
		JPanel panelGenre = new JPanel();
		panelGenre.setBackground(SystemColor.activeCaption);
		FlowLayout fl_panelGenre = (FlowLayout) panelGenre.getLayout();
		fl_panelGenre.setAlignment(FlowLayout.LEFT);
		panelF.add(panelGenre);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 3));
		panel.setBackground(SystemColor.activeCaption);
		panelGenre.add(panel);
		
		JLabel lblGenre = new JLabel("Genre");
		panel.add(lblGenre);
		lblGenre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtGenre = new JTextField();
		panelGenre.add(txtGenre);
		txtGenre.setColumns(34);
		
		JPanel panelDirector = new JPanel();
		panelDirector.setBackground(SystemColor.activeCaption);
		panelF.add(panelDirector);
		panelDirector.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));
		
		panel_1_1 = new JPanel();
		panel_1_1.setBackground(SystemColor.activeCaption);
		panelDirector.add(panel_1_1);
		
		JLabel lblImg_1 = new JLabel("Director");
		lblImg_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_1_1.add(lblImg_1);
		
		txtDirector = new JTextField();
		panelDirector.add(txtDirector);
		txtDirector.setColumns(34);
		
		JPanel panelDate = new JPanel();
		panelDate.setBackground(SystemColor.activeCaption);
		panelF.add(panelDate);
		
		JPanel panel_1_2_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1_2_1.getLayout();
		panel_1_2_1.setBorder(new EmptyBorder(0, 0, 0, 12));
		panel_1_2_1.setBackground(SystemColor.activeCaption);
		panelDate.add(panel_1_2_1);
		
		JLabel lblImg_2_1 = new JLabel("Date");
		lblImg_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_1_2_1.add(lblImg_2_1);
		
		txtDate = new JTextField();
		txtDate.setText((String) null);
		txtDate.setColumns(34);
		panelDate.add(txtDate);
		
		JPanel panelImg = new JPanel();
		panelImg.setBackground(SystemColor.activeCaption);
		FlowLayout fl_panelImg = (FlowLayout) panelImg.getLayout();
		fl_panelImg.setAlignment(FlowLayout.LEFT);
		panelF.add(panelImg);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panelImg.add(panel_1);
		
		JLabel lblImg = new JLabel("Image");
		panel_1.add(lblImg);
		lblImg.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtImg = new JTextField();
		panelImg.add(txtImg);
		txtImg.setColumns(30);
		
		JButton btnAdd = new JButton("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setAcceptAllFileFilterUsed(false); 

				jfc.setDialogTitle("Select a image"); 

				FileNameExtensionFilter imgF = new FileNameExtensionFilter(" .jpg", "jpg"); 
		        jfc.setFileFilter(imgF);
		        jfc.setMultiSelectionEnabled(false);
		        int r = jfc.showOpenDialog(null); 
		        File file = jfc.getSelectedFile();
		        if (r == JFileChooser.APPROVE_OPTION) { 
		        	//app.saveImage(txtName.getText(), file, f.getType());
		        } 
		        else
		            JOptionPane.showMessageDialog(btnAdd, "User cancelled the operation"); 
			}
		});
		btnAdd.setPreferredSize(new Dimension(30,25));
		btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnAdd.setToolTipText("Add new image");
		btnAdd.setBackground(Color.DARK_GRAY);
		btnAdd.setIcon(new ImageIcon("D:\\JAVA\\project\\MovieBookProject\\icon\\add.png"));
		panelImg.add(btnAdd);
		
		JPanel panelContent = new JPanel();
		panelContent.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelContent.setBackground(SystemColor.activeCaption);
		panelBottom.add(panelContent, BorderLayout.CENTER);
		panelContent.setLayout(new BorderLayout(0, 0));
		
		JPanel panelLeft = new JPanel();
		panelLeft.setBorder(new EmptyBorder(2, 5, 2, 5));
		panelLeft.setBackground(SystemColor.activeCaption);
		panelContent.add(panelLeft, BorderLayout.WEST);
		panelLeft.setLayout(new BorderLayout(0, 0));
		
		JLabel lblContent = new JLabel("Content");
		lblContent.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelLeft.add(lblContent, BorderLayout.NORTH);
		
		JPanel panelRight = new JPanel();
		panelRight.setBackground(Color.WHITE);
		panelRight.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelContent.add(panelRight, BorderLayout.CENTER);
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		panelRight.add(scrollPane);
		
		JTextArea txtContent = new JTextArea();
		txtContent.setLineWrap(true);
		txtContent.setWrapStyleWord(true);
		txtContent.setBackground(SystemColor.window);
		scrollPane.setViewportView(txtContent);
		
		JPanel panelBtn = new JPanel();
		panelBtn.setBackground(SystemColor.activeCaption);
		contentPane.add(panelBtn, BorderLayout.SOUTH);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnClose.setForeground(Color.WHITE);
		btnClose.setBackground(Color.BLACK);
		panelBtn.add(btnClose);
		
		JButton btnNewButton = new JButton("Insert");
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setForeground(Color.WHITE);
		panelBtn.add(btnNewButton);
	}

}
