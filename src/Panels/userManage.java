package Panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import AppUsed.Application;
import UserPack.*;
import java.awt.Color;

public class userManage extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private Application app;
	private JTextField tfSearch;
	private JButton btnSearch, btnDel, btnReload;
	private JTable table;
	private int row = -1;
	
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public userManage(Application ap) {

		setLayout(new BorderLayout());
		
		this.app = ap;
		
		//--------Panel show all---------------------
		JPanel paneTable = new JPanel();
		paneTable.setLayout(new BoxLayout(paneTable, BoxLayout.Y_AXIS));
		add(paneTable);
				
		// Panel search
		JPanel paneSearch = new JPanel();
		paneTable.add(paneSearch);
				
		tfSearch = new JTextField();
		tfSearch.setColumns(16);
		paneSearch.add(tfSearch);
		
		btnSearch = new JButton("Search");
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setBackground(Color.BLACK);
		btnSearch.addActionListener(this);
		paneSearch.add(btnSearch);		
		
		
		String[] header = {"ID", "Type", "First name", "Last name", "Email"};
		tableModel = new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		ArrayList<User> users = ((Admin)app.getUser()).readUsers();
		User[] list = new User[users.size()];
		for(int i = 0; i < users.size(); i++)
			list[i] = users.get(i);
		setModelList(list);

		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        row = table.rowAtPoint(evt.getPoint());
		    }
		});
		paneTable.add(new JScrollPane(table));
				
		JPanel paneBtn = new JPanel();
		paneTable.add(paneBtn);
				
		btnReload = new JButton("Reload");
		btnReload.setForeground(Color.WHITE);
		btnReload.setBackground(Color.BLACK);
		btnReload.addActionListener(this);
		paneBtn.add(btnReload);
			
		btnDel = new JButton("Delete");
		btnDel.setForeground(Color.WHITE);
		btnDel.setBackground(Color.BLACK);
		btnDel.addActionListener(this);
		paneBtn.add(btnDel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSearch) {
			tableModel.setRowCount(0);
			
			ArrayList<User> users = ((Admin)app.getUser()).searchUsers(tfSearch.getText());
			User[] list = new User[users.size()];
			for(int i = 0; i < users.size(); i++)
				list[i] = users.get(i);
			
			setModelList(list);
		}
		
		if(e.getSource() == btnReload) {
			tableModel.setRowCount(0);
			
			ArrayList<User> users = ((Admin)app.getUser()).readUsers();
			User[] list = new User[users.size()];
			for(int i = 0; i < users.size(); i++)
				list[i] = users.get(i);
			
			setModelList(list);
		}
		
		if(e.getSource() == btnDel) {
			if(row > -1) {
				if(((String)table.getValueAt(row, 1)).equals("admin")) {
					JOptionPane.showMessageDialog(null, "Can't delete admin!");
					return;
				}
				String ID = (String)table.getValueAt(row, 0);

				String pass = JOptionPane.showInputDialog("Please input your password :)");
				if(((Admin)app.getUser()).deleteUser(ID, pass)) {
					JOptionPane.showMessageDialog(null, "Delete user successful");
					
					tableModel.setRowCount(0);
					
					ArrayList<User> users = ((Admin)app.getUser()).readUsers();
					User[] list = new User[users.size()];
					for(int i = 0; i < users.size(); i++)
						list[i] = users.get(i);
					
					setModelList(list);
				}
				else {
					JOptionPane.showMessageDialog(null, "Wrong password");
					return;
				}
			}
		}
	}

	void setModelList(User[] Users) {
		for(User M : Users) {
			Object[] ob = {M.getId(), M.getType(), M.getFirstName(), M.getName(), M.getEmail()};
			tableModel.addRow(ob);
		}
	}
	
	
}
