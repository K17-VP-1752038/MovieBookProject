package Frames;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
//import javax.swing.border.EmptyBorder;
import javax.swing.border.EmptyBorder;
import AppUsed.Application;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


class MyThread extends Thread{
	private LoadingFrame load;
	private MainFrame main;
	private Application app;
	MyThread(String name, Application app) {
		super(name);
		this.app = app;
	}
	
	public void run() {
		try
        { 	
			load = new LoadingFrame();
			load.setVisible(true);
			main = new MainFrame(app);
			//Thread.sleep(1000);
			main.setVisible(true);
			//load.setVisible(false);
			load.dispose();
        } catch (Exception e) { 
            // Throwing an exception 
            System.out.println ("Exception in thread is caught"); 
        } 	
	}
}

public class Login extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Application app = new Application();
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
					Login frame = new Login("miknguyet99@gmail.com");
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
			BufferedImage myImage = (BufferedImage) ImageIO.read(new File("Img/bgLogin0.jpg"));
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
		setSize(628, 445);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		try {
			BufferedImage myImage = (BufferedImage) ImageIO.read(new File("Img/bgLogin0.jpg"));
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
		tfPassword.addActionListener(this);
		inputFields.add(tfEmail);
		inputFields.add(tfPassword);
		
		JPanel panePassFogot = new JPanel();
		loginForm.add(panePassFogot);
		
		JLabel lblPassForgot = new JLabel("Password forgotten?");
		lblPassForgot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblPassForgot.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				PassForgotten frame = new PassForgotten(tfEmail.getText(), app);
				frame.setSize(getSize());
				frame.setLocation(getLocation());
				frame.setVisible(true);
				//setVisible(false);
				dispose();
			}
		});
		lblPassForgot.setForeground(new Color(0, 0, 255));
		lblPassForgot.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassForgot.setFont(new Font("Arial", Font.ITALIC, 13));
		panePassFogot.add(lblPassForgot);
		
		JPanel paneEnter = new JPanel();
		paneEnter.setBorder(new EmptyBorder(3, 0, 1, 0));
		loginForm.add(paneEnter);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(this);
		btnLogin.setBackground(Color.BLACK);
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		paneEnter.add(btnLogin);
		
		JPanel panelText = new JPanel();
		loginForm.add(panelText);
		
		JLabel label = new JLabel("Don't have an account?");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelText.add(label);
		
		JLabel lblSignup = new JLabel("Sign up");
		lblSignup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSignup.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				SignUp frame = new SignUp(app);
				frame.setSize(getSize());
				frame.setLocation(getLocation());
				//setVisible(false);
				dispose();
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
			JOptionPane.showMessageDialog(null, "Email or password is invalid.");
			tfPassword.setText(null);
			return;
		}
		
		if(app.login(tfEmail.getText(), tfPassword.getPassword()))
		{
			//this.setVisible(false);
			dispose();
			MyThread thrd = new MyThread("Movie Book", app);
			thrd.start();
		}
		else
			JOptionPane.showMessageDialog(null, "Email or password is incorrect.");
	}
}
