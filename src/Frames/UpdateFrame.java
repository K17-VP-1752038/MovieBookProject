package Frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import AppUsed.Application;
import IOFilmFile.Film;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.event.ActionEvent;

public class UpdateFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtGenre;
	private JTextField txtImg;
	private Application app;
	private Film newF;
	private File file;
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
		setSize(500, 350);
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
		
		JPanel panelGenre = new JPanel();
		panelGenre.setBackground(SystemColor.activeCaption);
		FlowLayout fl_panelGenre = (FlowLayout) panelGenre.getLayout();
		fl_panelGenre.setAlignment(FlowLayout.LEFT);
		panelName_Img.add(panelGenre);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 3));
		panel.setBackground(SystemColor.activeCaption);
		panelGenre.add(panel);
		
		JLabel lblGenre = new JLabel("Genre");
		panel.add(lblGenre);
		lblGenre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtGenre = new JTextField();
		txtGenre.setText(f.getGenre());
		panelGenre.add(txtGenre);
		txtGenre.setColumns(34);
		
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
				setVisible(false);
			}
		});
		btnClose.setForeground(Color.WHITE);
		btnClose.setBackground(Color.BLACK);
	
		panelBtn.add(btnClose);
		
		JButton btnUpd = new JButton("Update");
		btnUpd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtGenre.getText().isBlank() || txtContent.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Must fill all text content.");
					return;
				}
				newF.setGenre(txtGenre.getText());
				newF.setIcon(txtImg.getText());
				newF.setContent(txtContent.getText());
				
				if(app.getLibrary().updateFilm(f, newF)) {
		        	app.getLibrary().saveImage(f.getName(), file, f.getType());
					JOptionPane.showMessageDialog(null, "Successfully updated.");		        	
				}
				else
					JOptionPane.showMessageDialog(null, "Can't update film\nThere must be some error!");
				
				System.out.println(newF.getContent());
				ad.setEnabled(true);
				setVisible(false);
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
		        	setVisible(false);
		        }

		    });
		}
}
