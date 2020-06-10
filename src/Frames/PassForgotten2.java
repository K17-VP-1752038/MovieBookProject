package Frames;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import AppUsed.Application;
import AppUsed.MailConfig;

import java.awt.event.*;

public class PassForgotten2 extends JFrame implements ActionListener {

private static final long serialVersionUID = 1L;
	
	private JPasswordField tfPassword, tfPassConf;
	private JButton btnReturn, btnFinish;
	private Application app;
	private String email;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Application app = new Application();
//					PassForgotten2 frame = new PassForgotten2("miknguyet99@gmail.com", app);
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
	public PassForgotten2(String mail, Application ap) {
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
		
		app = ap;
		email = mail;
		initialize();
	}

	void initialize() {
		JPanel loginForm = new JPanel();
		getContentPane().add(loginForm);
		loginForm.setLayout(new BoxLayout(loginForm, BoxLayout.Y_AXIS));
		loginForm.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JPanel panelText = new JPanel();
		panelText.setBorder(new EmptyBorder(5, 0, 5, 0));
		loginForm.add(panelText);
		JLabel lblForgotPassword = new JLabel("CHANGE PASSWORD");
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
		
		inputLabels.add(new JLabel("Password:"));
		inputLabels.add(new JLabel("Confirm password:"));
		
		tfPassword = new JPasswordField(15);
		inputFields.add(tfPassword);
		
		tfPassConf = new JPasswordField(15);
		inputFields.add(tfPassConf);
		
		JPanel panePassFogot = new JPanel();
		loginForm.add(panePassFogot);
		
		JLabel lblPassForgot = new JLabel("Password must be at least 6 characters.");
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
		
		btnReturn = new JButton("Return");
		btnReturn.addActionListener(this);
		paneReturn.add(btnReturn);
		btnReturn.setBackground(Color.BLACK);
		btnReturn.setForeground(Color.WHITE);
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JPanel paneFinish = new JPanel();
		FlowLayout fl_paneFinish = (FlowLayout) paneFinish.getLayout();
		fl_paneFinish.setAlignment(FlowLayout.TRAILING);
		paneBtn.add(paneFinish);
		
		btnFinish = new JButton("Finish");
		btnFinish.addActionListener(this);
		btnFinish.setBackground(new Color(139, 0, 0));
		btnFinish.setForeground(Color.WHITE);
		paneFinish.add(btnFinish);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnReturn) {
			PassForgotten frame = new PassForgotten("", app);
			frame.setSize(getSize());
			frame.setLocation(getLocation());
			frame.setVisible(true);
			dispose();
		}
		
		if(e.getSource() == btnFinish) {
			if(Arrays.equals(tfPassword.getPassword(), tfPassConf.getPassword())) {
				if(app.updatePassword(email, tfPassword.getPassword())) {
					try {
						MailConfig.sendEmail(app.getUser().getEmail(), "Password Changed", "You have changed your password in Movie Book since "+ new java.util.Date());
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					setOpacity(1f);
					if(app.login(email, tfPassword.getPassword())) {
//						System.out.println("Login success!");
						dispose();
						MyThread thrd = new MyThread("Movie Book", app);
						thrd.start();
					}
				}
			}
			else
				JOptionPane.showMessageDialog(new JFrame(), "Password does not match.");
		}
	}
	
}