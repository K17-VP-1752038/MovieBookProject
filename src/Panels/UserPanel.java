package Panels;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import AppUsed.Application;
import AppUsed.MailConfig;
import Frames.*;

public class UserPanel extends JPanel implements ActionListener, MouseListener, ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfFirstName, tfLastName;
	private Application app;
	private JList<String> list;
	private JPanel pane1, pane2, paneCard, panel;
	private JButton btnChangePw, btnChangeInfo;
	private JPasswordField tfConfirm, tfOldPass, tfNewPass;
	private JLabel name;
	private int index = 0;
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application ap = new Application();
					char[] pass = new char[] {'b', 'e', 'o', 'b', 'e', 'o','9','9'};
					if(ap.login("winterheartlove18@gmail.com", pass)) {
						UserFrame frame = new UserFrame(ap, 0);
						frame.setVisible(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public UserPanel(Application ap, MainFrame frame) {
		setLayout(new BorderLayout(5,5));
		this.app = ap;
		String[] option = {"Change Information", "Change Password"};
	
		list = new JList<>();
		list.setFixedCellHeight(28);
		list.setFixedCellWidth(160);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setListData(option);
		list.addListSelectionListener(this);
		
		JScrollPane paneList = new JScrollPane(list);
		this.add(paneList, BorderLayout.WEST);
		
		panel = new JPanel();
		this.add(panel, BorderLayout.CENTER);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel iconPane = new JPanel();
		iconPane.setBackground(SystemColor.activeCaption);
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
		panel.add(iconPane);

		JPanel paneName = new JPanel();
		paneName.setBackground(SystemColor.activeCaption);
		name = new JLabel(app.getUser().getFirstName() + " "+ app.getUser().getName());
		name.setFont(new Font("Tahoma", Font.BOLD, 16));
		paneName.add(name);
		panel.add(paneName);
		
		initPaneInfo(frame);
		initPanePassword();
		
		paneCard = new JPanel();
		panel.add(paneCard);
		paneCard.setLayout(new CardLayout());
		paneCard.add("Change Information", pane1);
		paneCard.add("Change Password", pane2);
	}
	
	private void initPaneInfo(MainFrame frame) {
		pane1 = new JPanel();
		pane1.setBackground(SystemColor.activeCaption);
		
		JPanel paneInfo = new JPanel();
		paneInfo.setLayout(new BoxLayout(paneInfo, BoxLayout.Y_AXIS));
		paneInfo.setOpaque(false);
		pane1.add(paneInfo);
		
		JPanel form = new JPanel();
		form.setOpaque(false);
		form.setBorder(new EmptyBorder(8, 5, 1, 5));
		paneInfo.add(form);
		form.setLayout(new BorderLayout(5, 5));
		
		JPanel inputLabels = new JPanel(new GridLayout(0, 1, 3, 8));
		inputLabels.setOpaque(false);
	    JPanel inputFields = new JPanel(new GridLayout(0, 1, 3, 8));
	    inputFields.setOpaque(false);

		form.add(inputLabels, BorderLayout.WEST);
		form.add(inputFields, BorderLayout.CENTER);
		
		inputLabels.add(new JLabel("First name: "));
		inputLabels.add(new JLabel("Last name: "));
		
		tfFirstName = new JTextField(15);
		tfFirstName.setFont(new Font("Arial", Font.PLAIN, 12));
		
		tfLastName = new JTextField(15);
		tfLastName.setFont(new Font("Arial", Font.PLAIN, 12));

		inputFields.add(tfFirstName);
		inputFields.add(tfLastName);
		
		JPanel paneEnter = new JPanel();
		FlowLayout flowLayout = (FlowLayout) paneEnter.getLayout();
		flowLayout.setVgap(20);
		paneEnter.setOpaque(false);
		paneEnter.setBorder(new EmptyBorder(3, 0, 1, 0));
		paneInfo.add(paneEnter);
		
		btnChangeInfo = new JButton("Change name");
		btnChangeInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfFirstName.getText().isBlank() || tfLastName.getText().isBlank())
					JOptionPane.showMessageDialog(new JFrame(), "Name can't be null.");
				else {
					// Steps to change the user name in database
					app.getUser().setName(tfLastName.getText());
					app.getUser().setFirstName(tfFirstName.getText());
					app.getUser().updateUserInfo(app.getUser());
					// Change the name in screen
					name.setText(tfFirstName.getText() + " "+ tfLastName.getText());
					
					tfFirstName.setText(null);
					tfLastName.setText(null);
					frame.setUser(app.getUser().getFirstName());
					
				}
			}
		});

		btnChangeInfo.setBackground(Color.BLACK);
		btnChangeInfo.setForeground(Color.WHITE);
		btnChangeInfo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		paneEnter.add(btnChangeInfo);
	}
	
	private void initPanePassword() {
		pane2 = new JPanel();
		pane2.setBackground(SystemColor.activeCaption);
		
		JPanel panePassword = new JPanel();
		panePassword.setOpaque(false);
		panePassword.setLayout(new BoxLayout(panePassword, BoxLayout.Y_AXIS));
		pane2.add(panePassword);
		
		JPanel form = new JPanel();
		form.setOpaque(false);
		form.setBorder(new EmptyBorder(8, 5, 1, 5));
		panePassword.add(form);
		form.setLayout(new BorderLayout(5, 5));
		
		JPanel inputLabels = new JPanel(new GridLayout(0, 1, 3, 8));
		inputLabels.setOpaque(false);
	    JPanel inputFields = new JPanel(new GridLayout(0, 1, 3, 8));
	    inputFields.setOpaque(false);
		form.add(inputLabels, BorderLayout.WEST);
		form.add(inputFields, BorderLayout.CENTER);
		
		inputLabels.add(new JLabel("Password: "));
		inputLabels.add(new JLabel("New password: "));
		inputLabels.add(new JLabel("Confirm: "));
		
		tfOldPass = new JPasswordField(15);
		tfOldPass.setFont(new Font("Arial", Font.PLAIN, 12));
		
		tfNewPass = new JPasswordField(15);
		tfOldPass.setFont(new Font("Arial", Font.PLAIN, 12));
	
		tfConfirm = new JPasswordField(15);
		tfConfirm.setFont(new Font("Arial", Font.PLAIN, 12));
		
		inputFields.add(tfOldPass);
		inputFields.add(tfNewPass);
		inputFields.add(tfConfirm);
		
		JPanel paneEnter = new JPanel();
		FlowLayout flowLayout = (FlowLayout) paneEnter.getLayout();
		flowLayout.setVgap(20);
		paneEnter.setOpaque(false);
		paneEnter.setBorder(new EmptyBorder(3, 0, 1, 0));
		panePassword.add(paneEnter);
		
		btnChangePw = new JButton("Change password");
		btnChangePw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfOldPass.getPassword().length< 6 || tfNewPass.getPassword().length < 6 || !Arrays.equals(tfConfirm.getPassword(),tfNewPass.getPassword()))
					JOptionPane.showMessageDialog(new JFrame(), "Password is invalid or not matched");
				else if(app.updatePassword(tfOldPass.getPassword(), tfNewPass.getPassword()))
					JOptionPane.showMessageDialog(new JFrame(), "Password is incorrect");				
				else {
					try {
						MailConfig.sendEmail(app.getUser().getEmail(), "Password Changed", "You have changed your password in Movie Book since "+ new java.util.Date());
						tfOldPass.setText(null);
						tfNewPass.setText(null);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		
		btnChangePw.setBackground(Color.BLACK);
		btnChangePw.setForeground(Color.WHITE);
		btnChangePw.setFont(new Font("Tahoma", Font.PLAIN, 12));
		paneEnter.add(btnChangePw);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(list.getSelectedIndex() == 0) {
			CardLayout cardLayout = (CardLayout)(paneCard.getLayout());
			cardLayout.show(paneCard, "Change Information");
		}
		if(list.getSelectedIndex() == 1) {
			CardLayout cardLayout = (CardLayout)(paneCard.getLayout());
			cardLayout.show(paneCard, "Change Password");
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void setIndex(int d) {
		index = d;
		list.setSelectedIndex(index);

	}
}
