package Frames;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;
//import javax.swing.border.EmptyBorder;
import javax.swing.border.EmptyBorder;
import AppUsed.Application;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Application app = new Application();
	private Random ran = new Random();
	private JTextField tfEmail;
	private JPasswordField tfPassword;
	private String mail = "";
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("Movie Book");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 628, 445);
		setSize(628, 445);
		setLocationRelativeTo(null);
		
		try {
			BufferedImage myImage = (BufferedImage) ImageIO.read(new File("Img/bgLogin"+ ran.nextInt(2) +".jpg"));
			Frames.ImagePanel imagePanel = new ImagePanel(myImage);
			setContentPane(imagePanel);
			FlowLayout fl_imagePanel = new FlowLayout(FlowLayout.CENTER, 5, 40);
			imagePanel.setLayout(fl_imagePanel);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		initialize();
	}
	
	public Login(String email) {
		setTitle("Movie Book");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(628, 445);
		setLocationRelativeTo(null);
		
		try {
			BufferedImage myImage = (BufferedImage) ImageIO.read(new File("Img/bgLogin"+ ran.nextInt(5) +".jpg"));
			Frames.ImagePanel imagePanel = new ImagePanel(myImage);
			setContentPane(imagePanel);
			FlowLayout fl_imagePanel = new FlowLayout(FlowLayout.CENTER, 5, 40);
			imagePanel.setLayout(fl_imagePanel);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mail = email;
		initialize();
	}

	void initialize() {
		JPanel loginForm = new JPanel();
		getContentPane().add(loginForm);
//		loginForm.setSize(200, 300);
		loginForm.setLayout(new BoxLayout(loginForm, BoxLayout.Y_AXIS));
		loginForm.setBorder(new EmptyBorder(3, 3, 3, 3));
		
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
		loginForm.add(iconPane);
		
		JPanel form = new JPanel();
		form.setBorder(new EmptyBorder(8, 5, 1, 5));
		loginForm.add(form);
		form.setLayout(new BorderLayout(5, 5));
		
		JPanel inputLabels = new JPanel(new GridLayout(0, 1, 3, 3));
	    JPanel inputFields = new JPanel(new GridLayout(0, 1, 3, 3));
		form.add(inputLabels, BorderLayout.WEST);
		form.add(inputFields, BorderLayout.CENTER);
		
		inputLabels.add(new JLabel("Email: "));
		inputLabels.add(new JLabel("Password: "));
		tfEmail = new JTextField(15);
		tfEmail.setText(mail);
		tfEmail.setFont(new Font("Arial", Font.PLAIN, 12));
		tfPassword = new JPasswordField(15);
		tfPassword.setFont(new Font("Arial", Font.PLAIN, 12));
		tfPassword.setToolTipText("Password must have at least 6 characters.");
		inputFields.add(tfEmail);
		inputFields.add(tfPassword);
		
		JPanel panePassFogot = new JPanel();
		loginForm.add(panePassFogot);
		
		JLabel lblPassForgot = new JLabel("Password forgotten?");
		lblPassForgot.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				PassForgotten frame = new PassForgotten(tfEmail.getText(), app);
				frame.setSize(getSize());
				frame.setLocation(getLocation());
				frame.setVisible(true);
				setVisible(false);
			}
		});
		lblPassForgot.setForeground(new Color(0, 0, 255));
		lblPassForgot.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassForgot.setFont(new Font("Arial", Font.ITALIC, 11));
		panePassFogot.add(lblPassForgot);
		
		JPanel paneEnter = new JPanel();
		paneEnter.setBorder(new EmptyBorder(3, 0, 1, 0));
		loginForm.add(paneEnter);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(this);
		btnLogin.setBackground(Color.BLACK);
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		paneEnter.add(btnLogin);
		
		JPanel panelText = new JPanel();
		loginForm.add(panelText);
		
		JLabel label = new JLabel("Don't have an account?");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panelText.add(label);
		
		JLabel lblSignup = new JLabel("Sign up");
		lblSignup.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				SignUp frame = new SignUp();
				frame.setSize(getSize());
				frame.setLocation(getLocation());
				setVisible(false);
				frame.setVisible(true);
			}
		});
		lblSignup.setForeground(new Color(204, 0, 0));
		lblSignup.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelText.add(lblSignup);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Check if user is valid
		if(!tfEmail.getText().contains("@") || !tfEmail.getText().contains(".") || tfPassword.getPassword().length < 6) {
			JOptionPane.showMessageDialog(new JFrame(), "Email or password is incorrect.");
			tfPassword.setText(null);
			return;
		}
		
		if(app.login(tfEmail.getText(), tfPassword.getPassword()))
		{
			MainFrame main = new MainFrame(app);
			main.setVisible(true);
			setVisible(false);
		}
		else
			JOptionPane.showMessageDialog(new JFrame(), "Email or password is incorrect.");
	}
}
