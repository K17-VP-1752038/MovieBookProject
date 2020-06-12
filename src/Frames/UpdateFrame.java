package Frames;

import java.awt.BorderLayout;

import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import AppUsed.Application;
import IOFilmFile.Film;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class UpdateFrame extends JFrame implements ItemListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtImg;
	private Application app;
	private Film newF;
	private File file;
	private JCheckBox cbaction, cbadventure, cbsport, cbdrama, cbanimation,cbhorror, cbthriller, cbromance, cbfantasy,cbfiction,cbcomedy,cbdetective;
	private String txtgenre;
	private ArrayList<String> listCheckbox = new ArrayList<String>();
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
	public UpdateFrame(Application ap, AdminFrame ad, Film f) {
		this.app = ap;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Update");
		newF = f;
		setSize(500, 400);
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
		
		JLabel lblTitle = new JLabel("Update Film " + f.getName());
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelTop.add(lblTitle);
		
		JPanel panelBottom = new JPanel();
		panelBottom.setOpaque(false);
		contentPane.add(panelBottom, BorderLayout.CENTER);
		panelBottom.setLayout(new BorderLayout(0, 0));
		
		JPanel panelName_Img = new JPanel();
		panelBottom.add(panelName_Img, BorderLayout.NORTH);
		panelName_Img.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panelName_Img.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		txtgenre = f.getGenre();
		
		JPanel panelGenre = new JPanel();
		panel_2.add(panelGenre, BorderLayout.WEST);
		panelGenre.setBackground(SystemColor.activeCaption);
		panelGenre.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panellblGenre = new JPanel();
		panellblGenre.setBorder(new EmptyBorder(0, 0, 0, 3));
		panellblGenre.setBackground(SystemColor.activeCaption);
		panelGenre.add(panellblGenre);
		
		JLabel lblGenre = new JLabel("Genre");
		panellblGenre.add(lblGenre);
		lblGenre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JPanel panelCbGenre = new JPanel();
		panelCbGenre.setBackground(SystemColor.activeCaption);
		panel_2.add(panelCbGenre, BorderLayout.CENTER);
		panelCbGenre.setLayout(new BoxLayout(panelCbGenre, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBorder(new EmptyBorder(0, 0, 0, 100));
		panelCbGenre.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPaneCb = new JScrollPane();
		scrollPaneCb.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		panel.add(scrollPaneCb);
		
		JPanel panelCb = new JPanel();
		panelCb.setBackground(SystemColor.activeCaption);
		scrollPaneCb.setViewportView(panelCb);
		panelCb.setLayout(new BoxLayout(panelCb, BoxLayout.X_AXIS));
		
		cbaction = new JCheckBox("Action");
		cbaction.setBackground(Color.WHITE);
		panelCb.add(cbaction);
		
		cbfiction = new JCheckBox("Fiction");
		cbfiction.setBackground(Color.WHITE);
		panelCb.add(cbfiction);
		
		cbdetective = new JCheckBox("Detective");
		cbdetective.setBackground(Color.WHITE);
		panelCb.add(cbdetective);
		
		cbcomedy = new JCheckBox("Comedy");
		cbcomedy.setBackground(Color.WHITE);
		panelCb.add(cbcomedy);
		
		cbsport = new JCheckBox("Sport");
		cbsport.setBackground(Color.WHITE);
		panelCb.add(cbsport);
		
		cbthriller = new JCheckBox("Thriller");
		cbthriller.setBackground(Color.WHITE);
		panelCb.add(cbthriller);
		
		cbadventure = new JCheckBox("Adventure");
		cbadventure.setBackground(Color.WHITE);
		panelCb.add(cbadventure);
		
		cbdrama = new JCheckBox("Drama");
		cbdrama.setBackground(Color.WHITE);
		panelCb.add(cbdrama);
		
		cbhorror = new JCheckBox("Horror");
		cbhorror.setBackground(Color.WHITE);
		panelCb.add(cbhorror);
		
		cbanimation = new JCheckBox("Animation");
		cbanimation.setBackground(Color.WHITE);
		panelCb.add(cbanimation);
		
		cbromance = new JCheckBox("Romance");
		cbromance.setBackground(Color.WHITE);
		panelCb.add(cbromance);
		
		cbfantasy = new JCheckBox("Fantasy");
		cbfantasy.setBackground(Color.WHITE);
		panelCb.add(cbfantasy);
		
		//the loai hien tai
		String[]listF_genre = txtgenre.split(",");
		for(int i = 0; i < listF_genre.length; i++) 
			listF_genre[i] = listF_genre[i].replaceAll(" ", "");
		
		for(int i = 0; i < listF_genre.length; i++) {
			if(cbaction.getText().equals(listF_genre[i]))
				cbaction.setSelected(true);
			if(cbadventure.getText().equals(listF_genre[i]))
				cbadventure.setSelected(true);
			if(cbanimation.getText().equals(listF_genre[i]))
				cbanimation.setSelected(true);
			if(cbcomedy.getText().equals(listF_genre[i]))
				cbcomedy.setSelected(true);
			if(cbdetective.getText().equals(listF_genre[i]))
				cbdetective.setSelected(true);
			if(cbdrama.getText().equals(listF_genre[i]))
				cbdrama.setSelected(true);
			if(cbfantasy.getText().equals(listF_genre[i]))
				cbfantasy.setSelected(true);
			if(cbfiction.getText().equals(listF_genre[i]))
				cbfiction.setSelected(true);
			if(cbhorror.getText().equals(listF_genre[i]))
				cbhorror.setSelected(true);
			if(cbromance.getText().equals(listF_genre[i]))
				cbromance.setSelected(true);
			if(cbsport.getText().equals(listF_genre[i]))
				cbsport.setSelected(true);
			if(cbthriller.getText().equals(listF_genre[i]))
				cbthriller.setSelected(true);
		}
		
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
		
		JPanel panelImg = new JPanel();
		panelImg.setBackground(SystemColor.activeCaption);
		FlowLayout fl_panelImg = (FlowLayout) panelImg.getLayout();
		fl_panelImg.setAlignment(FlowLayout.LEFT);
		panelName_Img.add(panelImg);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panelImg.add(panel_1);
		
		JLabel lblImg = new JLabel("Image");
		panel_1.add(lblImg);
		lblImg.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtImg = new JTextField();
		txtImg.setText(f.getIcon());
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
		        file = jfc.getSelectedFile();
		        if (r == JFileChooser.APPROVE_OPTION)
		        	txtImg.setText(file.getAbsolutePath());
		        else
		            JOptionPane.showMessageDialog(btnAdd, "Cancelled the operation"); 
		        
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
		txtContent.setText(f.getContent());
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
		
		JButton btnUpd = new JButton("Update");
		btnUpd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*if(txtGenre.getText().isBlank() || txtContent.getText().isBlank() || txtImg.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Must fill all text content.");
					return;
				}*/
				String txtGenre = "";
				for(int i = 0; i < listCheckbox.size(); i++) {
					if(i == (listCheckbox.size() - 1))
						txtGenre = txtGenre + listCheckbox.get(i);
					else txtGenre = txtGenre + listCheckbox.get(i) + ", ";
				}
				newF.setGenre(txtGenre);
				newF.setIcon(txtImg.getText());
				newF.setContent(txtContent.getText());
				
				if(app.getLibrary().updateFilm(f, newF)) {
		        	app.getLibrary().saveImage(f.getName(), file, f.getType());
					JOptionPane.showMessageDialog(null, "Successfully updated.");		        	
				}
				else
					JOptionPane.showMessageDialog(null, "Can't update film\nThere must be some error!");
				ad.setEnabled(true);
				//setVisible(false);
				dispose();
			}
		});
		btnUpd.setForeground(Color.WHITE);
		btnUpd.setBackground(Color.BLACK);
		panelBtn.add(btnUpd);
		this.addWindowListener(
		    new WindowAdapter() {
		        @Override
		        public void windowClosing(WindowEvent e) 
		        {
		        	ad.setEnabled(true);
		        	//setVisible(false);
		        	dispose();
		        }

		    });
		}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub	
		JCheckBox cb = (JCheckBox)e.getItem();
		if(cb.isSelected()) 
			listCheckbox.add(cb.getText());
	}
}
