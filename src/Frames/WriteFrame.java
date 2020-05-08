package Frames;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;

public class WriteFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WriteFrame window = new WriteFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WriteFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 737, 470);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("");
		tabbedPane.setBounds(10, 11, 701, 409);
		frame.getContentPane().add(tabbedPane);
		
		JLabel lblNewLabel = new JLabel("");
		tabbedPane.addTab("New tab", null, lblNewLabel, null);
		tabbedPane.setBackgroundAt(0, Color.WHITE);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		tabbedPane.addTab("New tab", null, lblNewLabel_1, null);
	}
}
