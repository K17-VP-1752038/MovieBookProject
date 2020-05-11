package Frames;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;
//import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private Random ran = new Random();
	private JTextField tfEmail;
	private JPasswordField tfPassword;
	
	
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
		setBounds(100, 100, 602, 358);
		setLocationRelativeTo(null);

		try {
			BufferedImage myImage = (BufferedImage) ImageIO.read(new File("Img/bgLogin"+ ran.nextInt(3) +".jpg"));
			Frames.ImagePanel imagePanel = new ImagePanel(myImage);
			setContentPane(imagePanel);
			FlowLayout fl_imagePanel = new FlowLayout(FlowLayout.CENTER, 5, 50);
			imagePanel.setLayout(fl_imagePanel);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		initialize();
	}

	void initialize() {
		JPanel loginForm = new JPanel();
		getContentPane().add(loginForm);
//		loginForm.setSize(200, 300);
		loginForm.setLayout(new BoxLayout(loginForm, BoxLayout.Y_AXIS));
		loginForm.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "LOGIN", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
//		JLabel lblLogin = new JLabel("LOGIN");
//		lblLogin.setForeground(Color.WHITE);
//		loginForm.add(lblLogin);
		
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
		tfEmail.setFont(new Font("Arial", Font.PLAIN, 12));
		tfPassword = new JPasswordField(15);
		tfPassword.setFont(new Font("Arial", Font.PLAIN, 12));
		inputFields.add(tfEmail);
		inputFields.add(tfPassword);
		
		JPanel panePassFogot = new JPanel();
		loginForm.add(panePassFogot);
		
		JLabel lblPassForgot = new JLabel("Password forgotten?");
		lblPassForgot.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassForgot.setFont(new Font("Arial", Font.ITALIC, 11));
		panePassFogot.add(lblPassForgot);
		
		JPanel paneEnter = new JPanel();
		paneEnter.setBorder(new EmptyBorder(3, 0, 1, 0));
		loginForm.add(paneEnter);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		paneEnter.add(btnLogin);
		
		JPanel panelText = new JPanel();
		loginForm.add(panelText);
		
		JLabel label = new JLabel("Don't have an account?");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panelText.add(label);
		
		JLabel lblSignup = new JLabel("Sign up");
		lblSignup.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelText.add(lblSignup);
	}
}



class ImagePanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;
    public ImagePanel(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}