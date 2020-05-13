package Frames;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PassForgotten extends JFrame {

private static final long serialVersionUID = 1L;
	
	private Random ran = new Random();
	private JTextField tfEmail;
	private JTextField tfPassword;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PassForgotten frame = new PassForgotten("miknguyet99@gmail.com");
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
	public PassForgotten(String email) {
		setTitle("Movie Book");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 628, 445);
		setLocationRelativeTo(null);

		try {
			BufferedImage myImage = (BufferedImage) ImageIO.read(new File("Img/bgLogin"+ ran.nextInt(3) +".jpg"));
			Frames.ImagePanel imagePanel = new ImagePanel(myImage);
			setContentPane(imagePanel);
			FlowLayout fl_imagePanel = new FlowLayout(FlowLayout.CENTER, 5, 45);
			imagePanel.setLayout(fl_imagePanel);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		initialize(email);
	}

	void initialize(String email) {
		JPanel loginForm = new JPanel();
		getContentPane().add(loginForm);
//		loginForm.setSize(200, 300);
		loginForm.setLayout(new BoxLayout(loginForm, BoxLayout.Y_AXIS));
		loginForm.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JPanel panelText = new JPanel();
		panelText.setBorder(new EmptyBorder(5, 0, 5, 0));
		loginForm.add(panelText);
		JLabel lblForgotPassword = new JLabel("FORGOT PASSWORD");
		lblForgotPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelText.add(lblForgotPassword);
		
		JPanel form = new JPanel();
		form.setBorder(new EmptyBorder(10, 6, 4, 5));
		loginForm.add(form);
		form.setLayout(new BorderLayout(5, 5));
		
		JPanel inputLabels = new JPanel(new GridLayout(0, 1, 3, 3));
	    JPanel inputFields = new JPanel(new GridLayout(0, 1, 3, 3));
		form.add(inputLabels, BorderLayout.WEST);
		form.add(inputFields, BorderLayout.CENTER);
		
		inputLabels.add(new JLabel("Email: "));
		inputLabels.add(new JLabel("Verified code: "));
		
		JPanel emailSent = new JPanel();
		tfEmail = new JTextField(15);
		tfEmail.setText(email);
		tfEmail.setFont(new Font("Arial", Font.PLAIN, 12));
		JButton btnSent = new JButton("Sent");
		btnSent.setBackground(new Color(139, 0, 0));
		btnSent.setForeground(Color.WHITE);
		emailSent.setLayout(new BoxLayout(emailSent, BoxLayout.X_AXIS));
		emailSent.add(tfEmail);
		emailSent.add(btnSent);
		
		tfPassword = new JTextField(15);
		tfPassword.setFont(new Font("Arial", Font.PLAIN, 12));
		inputFields.add(emailSent);
		inputFields.add(tfPassword);
		
		JPanel panePassFogot = new JPanel();
		loginForm.add(panePassFogot);
		
		JLabel lblPassForgot = new JLabel("The verified code will be sent to your email.");
		lblPassForgot.setForeground(new Color(0, 0, 255));
		lblPassForgot.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassForgot.setFont(new Font("Arial", Font.ITALIC, 11));
		panePassFogot.add(lblPassForgot);
		
		JPanel paneBtn = new JPanel();
		paneBtn.setBorder(new EmptyBorder(3, 0, 1, 0));
		loginForm.add(paneBtn);
		paneBtn.setLayout(new GridLayout(1, 1, 0, 0));
		
		JPanel paneReturn = new JPanel();
		FlowLayout flowLayout = (FlowLayout) paneReturn.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		paneBtn.add(paneReturn);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login frame = new Login();
				setVisible(false);
				frame.setVisible(true);
			}
		});
		paneReturn.add(btnReturn);
		btnReturn.setBackground(Color.BLACK);
		btnReturn.setForeground(Color.WHITE);
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JPanel paneNext = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) paneNext.getLayout();
		flowLayout_1.setAlignment(FlowLayout.TRAILING);
		paneBtn.add(paneNext);
		
		JButton btnNext = new JButton("Next");
		btnNext.setBackground(Color.BLACK);
		btnNext.setForeground(Color.WHITE);
		paneNext.add(btnNext);
		
//		JPanel panelText = new JPanel();
//		loginForm.add(panelText);
//		
//		JLabel label = new JLabel("Don't have an account?");
//		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		panelText.add(label);
//		
//		JLabel lblSignup = new JLabel("Sign up");
//		lblSignup.setForeground(new Color(204, 0, 0));
//		lblSignup.setFont(new Font("Tahoma", Font.BOLD, 12));
//		panelText.add(lblSignup);
	}
}