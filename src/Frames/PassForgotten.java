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

import AppUsed.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PassForgotten extends JFrame implements ActionListener {

private static final long serialVersionUID = 1L;
	
	private Random ran = new Random();
	private Application app;
	private int randomCode;
	private JTextField tfEmail;
	private JTextField tfCode;
	private JButton btnReturn, btnNext;
	private String email = "";
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PassForgotten frame = new PassForgotten("miknguyet99@gmail.com");
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	
	/**
	 * Create the frame.
	 */
	public PassForgotten(String email, Application application) {
		setTitle("Movie Book");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(628, 445);
		setLocationRelativeTo(null);

		try {
			BufferedImage myImage = (BufferedImage) ImageIO.read(new File("Img/bgLogin2.jpg"));
			Frames.ImagePanel imagePanel = new ImagePanel(myImage);
			setContentPane(imagePanel);
			FlowLayout fl_imagePanel = new FlowLayout(FlowLayout.CENTER, 5, 40);
			imagePanel.setLayout(fl_imagePanel);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		app = application;
		setEmail(email);
		initialize();
	}

	void initialize() {
		JPanel loginForm = new JPanel();
		getContentPane().add(loginForm);
//		loginForm.setSize(200, 300);
		loginForm.setLayout(new BoxLayout(loginForm, BoxLayout.Y_AXIS));
		loginForm.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JPanel panelText = new JPanel();
		panelText.setBorder(new EmptyBorder(5, 0, 5, 0));
		loginForm.add(panelText);
		JLabel lblForgotPassword = new JLabel("PASSWORD FORGOT");
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
		
		inputLabels.add(new JLabel("Email:"));
		inputLabels.add(new JLabel("Verified code:"));
		
		JPanel emailSent = new JPanel();
		tfEmail = new JTextField(15);
		tfEmail.setText(email);
		tfEmail.setFont(new Font("Arial", Font.PLAIN, 12));
		JButton btnSent = new JButton("Sent");
		btnSent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(email.equals(null))
					JOptionPane.showMessageDialog(new JFrame(), "Please write your email.");
				else {
					if(app.getUser().isExist(tfEmail.getText())) {
						randomCode = ran.nextInt(999999);
						try {
							MailConfig.sendEmail(email, "Forgot Password", "Your verify code is "+ randomCode);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(new JFrame(), "The verify code has been sent to your email.");
					}
					else
						JOptionPane.showMessageDialog(new JFrame(), "This account does not exist.");
				}
			}
		});
		
		btnSent.setBackground(new Color(139, 0, 0));
		btnSent.setForeground(Color.WHITE);
		emailSent.setLayout(new BoxLayout(emailSent, BoxLayout.X_AXIS));
		emailSent.add(tfEmail);
		emailSent.add(btnSent);
		
		tfCode = new JTextField(15);
		tfCode.setFont(new Font("Arial", Font.PLAIN, 12));
		inputFields.add(emailSent);
		inputFields.add(tfCode);
		
		JPanel panePassFogot = new JPanel();
		loginForm.add(panePassFogot);
		
		JLabel lblPassForgot = new JLabel("The verified code will be sent to your email.");
		lblPassForgot.setForeground(new Color(0, 0, 255));
		lblPassForgot.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassForgot.setFont(new Font("Arial", Font.ITALIC, 12));
		panePassFogot.add(lblPassForgot);
		
		JPanel paneBtn = new JPanel();
		paneBtn.setBorder(new EmptyBorder(3, 0, 1, 0));
		loginForm.add(paneBtn);
		paneBtn.setLayout(new GridLayout(1, 1, 0, 0));
		
		JPanel paneReturn = new JPanel();
		FlowLayout flowLayout = (FlowLayout) paneReturn.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		paneBtn.add(paneReturn);
		
		btnReturn = new JButton("Return");
		btnReturn.addActionListener(this);
		paneReturn.add(btnReturn);
		btnReturn.setBackground(Color.BLACK);
		btnReturn.setForeground(Color.WHITE);
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JPanel paneNext = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) paneNext.getLayout();
		flowLayout_1.setAlignment(FlowLayout.TRAILING);
		paneBtn.add(paneNext);
		
		btnNext = new JButton("Next");
		btnNext.addActionListener(this);
		btnNext.setBackground(Color.BLACK);
		btnNext.setForeground(Color.WHITE);
		paneNext.add(btnNext);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnReturn) {
			Login frame = new Login();
			frame.setSize(getSize());
			frame.setLocation(getLocation());
			frame.setVisible(true);
			setVisible(false);
		}
		
		if(e.getSource() == btnNext) {
			if(randomCode == Integer.parseInt(tfCode.getText())) {
				PassForgotten2 frame = new PassForgotten2(email, app);
				frame.setSize(getSize());
				frame.setLocation(getLocation());
				frame.setVisible(true);
				setVisible(false);
			}
		}
	}
	
	private void setEmail(String mail) {
		email = mail;
	}
}