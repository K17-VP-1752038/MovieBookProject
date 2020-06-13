package Frames;

import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import AppUsed.*;

import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.awt.FlowLayout;

public class SignUp extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private JPanel contentPane;
	private JTextField tfEmail, tfFirstName, tfLastName;
	private JPasswordField tfPassword, tfConfirm;
	private Application app;

	/**
	 * Create the frame.
	 */
	public SignUp(Application ap) {
		setTitle("Movie Book");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 628, 445);
//		setSize(628, 445);
		setLocationRelativeTo(null);

		try {
			BufferedImage myImage = (BufferedImage) ImageIO.read(new File("Img/bgLogin0.jpg"));
			ImagePanel imagePanel = new ImagePanel(myImage);
			setContentPane(imagePanel);
			FlowLayout fl_imagePanel = new FlowLayout(FlowLayout.CENTER, 5, 50);
			imagePanel.setLayout(fl_imagePanel);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		app = ap;
		initialize();
	}
	
	void initialize() {
		JPanel resgForm = new JPanel();
		getContentPane().add(resgForm);
		resgForm.setLayout(new BoxLayout(resgForm, BoxLayout.Y_AXIS));
		resgForm.setPreferredSize(new Dimension(360, 210));

		JPanel title = new JPanel();
		title.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel SignUp = new JLabel("SIGN UP");
//		SignUp.setForeground(Color.BLACK);
		title.add(SignUp);
		resgForm.add(title);
		
		JPanel form = new JPanel();
		form.setBorder(new EmptyBorder(8, 8, 5, 8));
		resgForm.add(form);
		form.setLayout(new BorderLayout(5, 5));
		
		JPanel inputLabels = new JPanel(new GridLayout(0, 1, 3, 5));
	    JPanel inputFields = new JPanel(new GridLayout(0, 1, 3, 5));
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
		tfConfirm.addActionListener(this);
		
		inputFields.add(tfEmail);
		inputFields.add(tfPassword);
		inputFields.add(tfConfirm);
		
		JPanel panePassFogot = new JPanel();
		resgForm.add(panePassFogot);
		
		JPanel paneBtn = new JPanel();
		paneBtn.setBorder(new EmptyBorder(3, 0, 1, 0));
		resgForm.add(paneBtn);
		paneBtn.setLayout(new GridLayout(1, 1, 0, 0));
		
		JPanel paneReturn = new JPanel();
		FlowLayout flowLayout = (FlowLayout) paneReturn.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		paneBtn.add(paneReturn);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login frame = new Login();
				frame.setSize(getSize());
				frame.setLocation(getLocationOnScreen());
				frame.setVisible(true);
				dispose();
			}
		});
		paneReturn.add(btnReturn);
		btnReturn.setBackground(Color.BLACK);
		btnReturn.setForeground(Color.WHITE);
		
		JPanel paneNext = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) paneNext.getLayout();
		flowLayout_1.setAlignment(FlowLayout.TRAILING);
		paneBtn.add(paneNext);
		
		JButton btnNext = new JButton("Register");
		btnNext.setBackground(new Color(139, 0, 0));
		btnNext.setForeground(Color.WHITE);
		btnNext.addActionListener(this);
		paneNext.add(btnNext);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(tfLastName.getText().isBlank() || tfEmail.getText().isBlank() || tfFirstName.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Please fill all your information.");
			return;
		}
		if(app.getUser().isExist(tfEmail.getText())) {
			JOptionPane.showMessageDialog(null, "Email account's already existed.");
			return;
		}
		if(tfPassword.getPassword().length < 6) {
			JOptionPane.showMessageDialog(null, "Password must have at least 6 characters.");
			return;
		}				
		if(Arrays.equals(tfPassword.getPassword(), tfConfirm.getPassword())) {
			if(!MailConfig.emailValidate(tfEmail.getText())) {
				JOptionPane.showMessageDialog(new JFrame(), "Email is invalid.");
				return;
			}
			if(app.signUp(tfLastName.getText(), tfFirstName.getText(), tfEmail.getText(), tfPassword.getPassword())) {
				Login frame = new Login(tfEmail.getText());
				frame.setSize(getSize());
				frame.setLocation(getLocationOnScreen());
				frame.setVisible(true);
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "Your infomation is invalid.");
			}
		}
		else
			JOptionPane.showMessageDialog(null, "Password not matching.");
	
	}
}