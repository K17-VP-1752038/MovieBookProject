package Frames;

import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.awt.FlowLayout;

public class SignUp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private JPanel contentPane;
	private Random ran = new Random();
	private JTextField tfEmail, tfFirstName, tfLastName;
	private JPasswordField tfPassword, tfConfirm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	public SignUp() {
		setTitle("Movie Book");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 628, 445);
		setSize(628, 445);
		setLocationRelativeTo(null);

		try {
			BufferedImage myImage = (BufferedImage) ImageIO.read(new File("Img/bgLogin"+ ran.nextInt(6) +".jpg"));
			ImagePanel imagePanel = new ImagePanel(myImage);
			setContentPane(imagePanel);
			FlowLayout fl_imagePanel = new FlowLayout(FlowLayout.CENTER, 5, 45);
			imagePanel.setLayout(fl_imagePanel);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
//		contentPane = new JPanel();
//		setContentPane(contentPane);
		
		initialize();
	}
	
	void initialize() {
//		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 200, 200));
		JPanel loginForm = new JPanel();
		getContentPane().add(loginForm);
		loginForm.setLayout(new BoxLayout(loginForm, BoxLayout.Y_AXIS));
		

		JPanel title = new JPanel();
		title.setBackground(Color.WHITE);
		title.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel SignUp = new JLabel("SIGN UP");
		SignUp.setForeground(Color.BLACK);
		title.add(SignUp);
		loginForm.add(title);
		
		JPanel form = new JPanel();
		form.setBackground(Color.WHITE);
		form.setBorder(new EmptyBorder(8, 5, 3, 5));
		loginForm.add(form);
		form.setLayout(new BorderLayout(5, 5));
		
		JPanel inputLabels = new JPanel(new GridLayout(0, 1, 3, 3));
		inputLabels.setBackground(Color.WHITE);
	    JPanel inputFields = new JPanel(new GridLayout(0, 1, 3, 3));
		form.add(inputLabels, BorderLayout.WEST);
		form.add(inputFields, BorderLayout.CENTER);
		
		JLabel label_2 = new JLabel("First name: ");
		label_2.setBackground(new Color(224, 255, 255));
		inputLabels.add(label_2);
		inputLabels.add(new JLabel("Last name: "));
		tfFirstName = new JTextField(15);
		tfFirstName.setFont(new Font("Arial", Font.PLAIN, 12));
		tfLastName = new JTextField(15);
		tfLastName.setFont(new Font("Arial", Font.PLAIN, 12));
		inputFields.add(tfFirstName);
		inputFields.add(tfLastName);
		
		JLabel label = new JLabel("Email: ");
		label.setForeground(new Color(0, 0, 0));
		label.setBackground(new Color(224, 255, 255));
		inputLabels.add(label);
		JLabel label_1 = new JLabel("Password: ");
		label_1.setBackground(new Color(224, 255, 255));
		inputLabels.add(label_1);
		inputLabels.add(new JLabel("Confirm Password: "));
		tfEmail = new JTextField(15);
		tfEmail.setFont(new Font("Arial", Font.PLAIN, 12));
		tfPassword = new JPasswordField(15);
		tfPassword.setFont(new Font("Arial", Font.PLAIN, 12));
		tfConfirm = new JPasswordField(15);
		tfConfirm.setFont(new Font("Arial", Font.PLAIN, 12));
		inputFields.add(tfEmail);
		inputFields.add(tfPassword);
		inputFields.add(tfConfirm);
		
		JPanel panePassFogot = new JPanel();
		panePassFogot.setBackground(Color.WHITE);
		loginForm.add(panePassFogot);
		
		
		JPanel paneEnter = new JPanel();
		paneEnter.setBackground(new Color(255, 255, 255));
		paneEnter.setBorder(new EmptyBorder(3, 0, 1, 0));
		loginForm.add(paneEnter);
		
		JButton btnReturn = new JButton("Return");
		paneEnter.add(btnReturn);
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login frame = new Login();
				setVisible(false);
				frame.setVisible(true);
			}
		});
		btnReturn.setBackground(Color.BLACK);
		btnReturn.setForeground(Color.WHITE);
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnLogin = new JButton("Sign Up");
		btnLogin.setBackground(Color.BLACK);
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		paneEnter.add(btnLogin);
		
		JPanel paneBtn = new JPanel();
		paneBtn.setBorder(new EmptyBorder(3, 0, 1, 0));
		loginForm.add(paneBtn);
		paneBtn.setLayout(new GridLayout(1, 1, 0, 0));
		
		JPanel paneReturn = new JPanel();
		FlowLayout flowLayout = (FlowLayout) paneReturn.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		paneBtn.add(paneReturn);
		
		
	}
}