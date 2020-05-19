package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JLabel;

public class LoadingFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the frame.
	 */
	public LoadingFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setSize(400,400);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JLabel lblgif = new JLabel();
		lblgif.setPreferredSize(new Dimension(628,445));
		lblgif.setIcon(new ImageIcon(new ImageIcon("Img/BeanEater.gif").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
		
		contentPane.add(lblgif);
		
		JLabel load = new JLabel("Loading...");
		contentPane.add(load);
		
	}

}
