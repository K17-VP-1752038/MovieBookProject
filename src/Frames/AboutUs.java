package Frames;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
//import AppUsed.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//import AppUsed.*;
public class AboutUs extends JFrame {

	private static final long serialVersionUID = 1L;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AboutUs frame = new AboutUs();
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
	public AboutUs() {
		setTitle("Movie Book");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 717, 483);
		setSize(550, 500);
		setLocationRelativeTo(null);

		try {
			BufferedImage myImage = (BufferedImage) ImageIO.read(new File("Img/bgLogin2.jpg"));
			ImagePanel imagePanel = new ImagePanel(myImage);
			setContentPane(imagePanel);
			FlowLayout fl_imagePanel = new FlowLayout(FlowLayout.CENTER, 5, 45);
			imagePanel.setLayout(fl_imagePanel);

		} catch (IOException e) {
			e.printStackTrace();
		}


		initialize();
	}

	void initialize() {
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 100, 100));
		JPanel loginForm = new JPanel();
		getContentPane().add(loginForm);
		loginForm.setLayout(new BoxLayout(loginForm, BoxLayout.Y_AXIS));

		JPanel title = new JPanel();
		title.setBackground(Color.WHITE);
		title.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel AboutUs = new JLabel("Terms & Conditions");
		AboutUs.setForeground(Color.BLACK);
		title.add(AboutUs);
		loginForm.add(title);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scr = new JScrollPane(textArea);
		textArea.setBorder(new EmptyBorder(10, 10, 5, 5));
		//scr.setBounds(15, 15, 400, 300);
		//textfield.setBounds(5, 5, 1300, 1200);

		textArea.setText("Movie Book contains information, links, images and content of movie. This application \r\n" 
				+ "helps you find and view movie information easily and quickly.\r\n"
				+ "\r\n" + "Please read the conditions before continuing:\r\n" + "\r\n"
				+ "This app contains documents which aren't subject to age restrictions. If you are under 14 \r\n"
				+ "or if you are still a minor where you are to access this app,you need parental  permission"
				+ "\r\n" + "to enter this app or access its content.\r\n" + "\r\n"
				+ "Please do not copy, re-upload movie content.\r\n" + "\r\n"
				+ "Application had been created and designed by Meme team since 24 May 2020." + "\r\n\n"
				+ "By signing up on this app, you accept all of the clauses." +"\r\n\n"
				+ "Thanks for your cooperation.\n");
		loginForm.add(textArea);
		loginForm.add(scr);

	}
}