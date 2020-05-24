package Frames;

import java.awt.*;

import javax.swing.*;
import java.awt.Image;

public class LoadingFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadingFrame frame = new LoadingFrame();
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
	public LoadingFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Movie Book");
		setBackground(Color.WHITE);
		//this.setLocationRelativeTo(null);
		setBounds(500, 200, 340,300);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setBackground(Color.WHITE);
		
		JLabel lblgif = new JLabel();
//		lblgif.setPreferredSize(new Dimension(628,445));
		lblgif.setIcon(new ImageIcon(new ImageIcon("Img/BeanEater.gif").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));

		JPanel paneIcon = new JPanel();
		paneIcon.setOpaque(false);
		paneIcon.add(lblgif);
		contentPane.add(paneIcon);
		
		JPanel paneLbl = new JPanel();
		paneLbl.add(new JLabel("Please wait for loading..."));
		paneLbl.setOpaque(false);
		contentPane.add(paneLbl);
	}

}
