package Frames;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import AppUsed.Application;
import IOFilmFile.Film;
import IOFilmFile.Movie;
import IOFilmFile.Series;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.ScrollPaneConstants;
import javax.swing.JCheckBox;

public class InsertFrame extends JFrame implements ItemListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtImg;
	private Application app;
	private JTextField txtName;
	private JTextField txtDirector;
	private JPanel panel_1_1;
	private JTextField textField;
	private File file;
	private JCheckBox cbaction, cbadventure, cbsport, cbdrama, cbanimation,cbhorror, cbthriller, cbromance,cbfantasy,cbfiction,cbcomedy,cbdetective;
	private ArrayList<String> listCheckbox = new ArrayList<String>();
	/**
	 * Create the frame.
	 */
	public InsertFrame(Application ap, AdminFrame ad, String type) {
		this.app = ap;
		setTitle("Insert");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) 
			{
			   	ad.setEnabled(true);
			   	dispose();
			}
		});
		
		setSize(500, 500);
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
		FlowLayout flowLayout_2 = (FlowLayout) panelName.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
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
		txtName.setColumns(40);
		panelName.add(txtName);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.activeCaption);
		FlowLayout flowLayout_4 = (FlowLayout) panel_4.getLayout();
		flowLayout_4.setVgap(0);
		flowLayout_4.setHgap(0);
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panelF.add(panel_4);
		
		JPanel panelGenre = new JPanel();
		panel_4.add(panelGenre);
		panelGenre.setPreferredSize(new Dimension(476, 39));
		panelGenre.setBackground(SystemColor.activeCaption);
		panelGenre.setLayout(new BoxLayout(panelGenre, BoxLayout.X_AXIS));
		
		
		JPanel panellblGenre = new JPanel();
		panellblGenre.setBackground(SystemColor.activeCaption);
		panellblGenre.setLayout(new FlowLayout(FlowLayout.CENTER,5,10));
		panelGenre.add(panellblGenre);
		
		JLabel lblGenre = new JLabel("Genre");
		panellblGenre.add(lblGenre);
		lblGenre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JPanel panelCbGenre = new JPanel();
		panelCbGenre.setBorder(new EmptyBorder(0, 13, 0, 46));
		panelCbGenre.setBackground(SystemColor.activeCaption);
		panelGenre.add(panelCbGenre);
		panelCbGenre.setLayout(new BoxLayout(panelCbGenre, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panelCbGenre.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPaneCb = new JScrollPane();
		scrollPaneCb.setBackground(SystemColor.activeCaption);
		scrollPaneCb.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		panel.add(scrollPaneCb);
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.activeCaption);
		scrollPaneCb.setViewportView(panel_3);
	
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		cbaction = new JCheckBox("Action");
		cbaction.setBackground(Color.WHITE);
		panel_3.add(cbaction);
		
		cbsport = new JCheckBox("Sport");
		cbsport.setBackground(Color.WHITE);
		panel_3.add(cbsport);
		
		cbdetective = new JCheckBox("Detective");
		cbdetective.setBackground(Color.WHITE);
		panel_3.add(cbdetective);
		
		cbfantasy = new JCheckBox("Fantasy");
		cbfantasy.setBackground(Color.WHITE);
		panel_3.add(cbfantasy);
		
		cbanimation = new JCheckBox("Animation");
		cbanimation.setBackground(Color.WHITE);
		panel_3.add(cbanimation);
		
		cbfiction = new JCheckBox("Fiction");
		cbfiction.setBackground(Color.WHITE);
		panel_3.add(cbfiction);
		
		cbdrama = new JCheckBox("Drama");
		cbdrama.setBackground(Color.WHITE);
		panel_3.add(cbdrama);
		
		cbromance = new JCheckBox("Romance");
		cbromance.setBackground(Color.WHITE);
		panel_3.add(cbromance);
		
		cbthriller = new JCheckBox("Thriller");
		cbthriller.setBackground(Color.WHITE);
		panel_3.add(cbthriller);
		
		cbhorror = new JCheckBox("Horror");
		cbhorror.setBackground(Color.WHITE);
		panel_3.add(cbhorror);
		
		cbadventure = new JCheckBox("Adventure");
		cbadventure.setBackground(Color.WHITE);
		panel_3.add(cbadventure);
		
		cbcomedy = new JCheckBox("Comedy");
		cbcomedy.setBackground(Color.WHITE);
		panel_3.add(cbcomedy);
		
		cbaction.addItemListener(this);
		cbadventure.addItemListener(this);
		cbcomedy.addItemListener(this);
		cbdetective.addItemListener(this);
		cbdrama.addItemListener(this);
		cbfantasy.addItemListener(this);
		cbfiction.addItemListener(this);
		cbhorror.addItemListener(this);
		cbromance.addItemListener(this);
		cbsport.addItemListener(this);
		cbthriller.addItemListener(this);
		cbanimation.addItemListener(this);
		
		
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
		txtDirector.setColumns(40);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_2.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_2.setBackground(SystemColor.activeCaption);
		panelF.add(panel_2);
		
		JPanel panelFMovie_Series = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelFMovie_Series.getLayout();
		flowLayout_1.setHgap(0);
		
		panelFMovie_Series.setBackground(SystemColor.activeCaption);
		panel_2.add(panelFMovie_Series);
		
		JLabel lblF = new JLabel();
		lblF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelFMovie_Series.add(lblF);
		
		textField = new JTextField();
		textField.setColumns(40);
		if(type.equals("movie")) {
			lblF.setText("Time");
			//textField.setColumns(40);
			panelFMovie_Series.setBorder(new EmptyBorder(0, 0, 0, 19));
		}
		else {
			lblF.setText("Episode");
			//textField.setColumns(38);
		}

		panel_2.add(textField);
		
		
		JPanel panelDate = new JPanel();
		panelDate.setBackground(SystemColor.activeCaption);
		panelF.add(panelDate);
		panelDate.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1_2_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1_2_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1_2_1.setBorder(new EmptyBorder(0, 0, 0, 12));
		
		panel_1_2_1.setBackground(SystemColor.activeCaption);
		panelDate.add(panel_1_2_1, BorderLayout.WEST);
		
		JLabel lblImg_2_1 = new JLabel("Date");
		lblImg_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_1_2_1.add(lblImg_2_1);
		
		JPanel paneDateChooser = new JPanel();
		paneDateChooser.setBackground(SystemColor.activeCaption);
		paneDateChooser.setLayout(null);
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(10, 5, 273, 23);
		dateChooser.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateChooser.setDateFormatString("dd/MM/yyyy");

		paneDateChooser.add(dateChooser);
		panelDate.add(paneDateChooser, BorderLayout.CENTER);
		
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
		txtImg.setEditable(false);
		panelImg.add(txtImg);
		txtImg.setColumns(40);
		
		JButton btnAdd = new JButton("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setAcceptAllFileFilterUsed(false); 

				jfc.setDialogTitle("Select an image"); 

				FileNameExtensionFilter imgF = new FileNameExtensionFilter(".jpg", "jpg"); 
		        jfc.setFileFilter(imgF);
		        jfc.setMultiSelectionEnabled(false);
		        int r = jfc.showOpenDialog(null); 
		        file = jfc.getSelectedFile();
		        if (r == JFileChooser.APPROVE_OPTION) { 
		        	txtImg.setText(file.getAbsolutePath());
		        } 
		        else
		            JOptionPane.showMessageDialog(btnAdd, "User cancelled the operation"); 
			}
		});
		btnAdd.setPreferredSize(new Dimension(30,25));
		btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnAdd.setToolTipText("Add new image");
		btnAdd.setBackground(Color.DARK_GRAY);
		btnAdd.setIcon(new ImageIcon("icon/add.png"));
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
				ad.setEnabled(true);
				//setVisible(false);
				dispose();
			}
		});
		btnClose.setForeground(Color.WHITE);
		btnClose.setBackground(Color.BLACK);
		panelBtn.add(btnClose);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBackground(Color.BLACK);
		btnInsert.setForeground(Color.WHITE);
		btnInsert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Film f;	
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					String txtGenre = "";
					for(int i = 0; i < listCheckbox.size(); i++) {
						if(i == (listCheckbox.size() - 1))
							txtGenre = txtGenre + listCheckbox.get(i);
						else txtGenre = txtGenre + listCheckbox.get(i) + ", ";
					}
					if(txtName.getText().isBlank() || txtGenre.isBlank() || (dateChooser.getDate()==null) ||
						txtDirector.getText().isBlank() || txtImg.getText().isBlank() || 
						txtContent.getText().isBlank() || textField.getText().isBlank()){
						JOptionPane.showMessageDialog(null, "Missing text content");
						return;
					}
					if(txtContent.getText().length() < 30) {
						JOptionPane.showMessageDialog(null, "Text content must be longer than 30");
						return;
					}
					String sd = dateFormat.format(dateChooser.getDate());
					
					if(type.equals("movie"))
						f = new Movie(txtName.getText(), txtImg.getText(), txtGenre, txtDirector.getText(), Integer.parseInt(textField.getText()), sd, txtContent.getText());
					else
						f = new Series(txtName.getText(), txtImg.getText(), txtGenre, txtDirector.getText(), sd, txtContent.getText(), Integer.parseInt(textField.getText()));
					if(app.getLibrary().insertFilm(f)) {
						app.getLibrary().saveImage(txtName.getText(), file, type);
						//setVisible(false);
						dispose();
						ad.setEnabled(true);
						JOptionPane.showMessageDialog(null, "Insert successfully");
					}
					else JOptionPane.showMessageDialog(null, "Insert failed");
				}catch(Exception exc) {
					JOptionPane.showMessageDialog(null, "Error");
				}
			}
		});
		panelBtn.add(btnInsert);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		JCheckBox cb = (JCheckBox)e.getItem();
		if(cb.isSelected()) 
			listCheckbox.add(cb.getText());
	}
}
