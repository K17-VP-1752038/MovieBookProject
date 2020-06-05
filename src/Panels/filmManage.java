package Panels;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import AppUsed.Application;
import Frames.*;
import IOFilmFile.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.BorderLayout;
import java.awt.Panel;

public class filmManage extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DefaultTableModel tableModel;
	private Application app;
	private String type;
	private JTextField tfSearch;
	private JButton btnSearch, btnNew;
	private JPopupMenu jpu;
	private JMenuItem jmiUpd;
	private JMenuItem jmiDel;
	private Film f[];
	private JTable table;
	private AdminFrame admin;
	private JPanel panel, panelBottom;
	private Panel panelSave;
	private JLabel lblsave, lblCount;
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public filmManage(Application app, AdminFrame ad, String typ) {

		setLayout(new BorderLayout());
		this.admin = ad;
		this.app = app;
		this.type = typ;

		//--------Panel show all---------------------
		JPanel paneTable = new JPanel();
		paneTable.setLayout(new BoxLayout(paneTable, BoxLayout.Y_AXIS));
		add(paneTable);
		
		panel = new JPanel();
		paneTable.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				
		// Panel search
		JPanel paneSearch = new JPanel();
		panel.add(paneSearch);
				
		tfSearch = new JTextField();
		tfSearch.setColumns(16);
		paneSearch.add(tfSearch);
		
		btnSearch = new JButton("Search");
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setBackground(Color.BLACK);
		btnSearch.addActionListener(this);
		paneSearch.add(btnSearch);		
		
		lblCount = new JLabel();
		
		if(type.equals("movie")) {
			String[] header = {"Name", "Image", "Genre", "Director", "Duration (min)", "Released date", "Content"};
			tableModel = new DefaultTableModel(header, 0){
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			setModelList(app.readMovie());
		}
		else {
			String[] header = {"Name", "Image", "Genre", "Director", "Released date", "Episodes", "Content"};
			tableModel = new DefaultTableModel(header, 0) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			setModelList(app.readSeries());
		}
		
		jpu = new JPopupMenu();
        jmiUpd = new JMenuItem("Update");
        jmiDel = new JMenuItem("Delete");
        jmiUpd.addActionListener(this);
        jmiDel.addActionListener(this);
        jpu.add(jmiUpd);
        jpu.add(jmiDel);
		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if(SwingUtilities.isRightMouseButton(me) == true)
				{
					int row = table.rowAtPoint(me.getPoint());
					table.clearSelection();
					table.addRowSelectionInterval(row,row);
					f = app.searchByKeyWord((String)table.getValueAt(table.getSelectedRow(), 0));
				}
				
            }
		});
		table.setComponentPopupMenu(jpu);
		paneTable.add(new JScrollPane(table));
		
		JPanel paneCount = new JPanel();
		paneTable.add(paneCount);
		lblCount.setText("Total: "+ tableModel.getRowCount());
		paneCount.add(lblCount);
		
		panelBottom = new JPanel();
		paneTable.add(panelBottom);
		panelBottom.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel paneBtn = new JPanel();
		panelBottom.add(paneBtn);
				
		JButton btnReload = new JButton("Reload");
		btnReload.setForeground(Color.WHITE);
		btnReload.setBackground(Color.BLACK);
		btnReload.addActionListener(this);
		paneBtn.add(btnReload);
			
		btnNew = new JButton("Add new");
		btnNew.setForeground(Color.WHITE);
		btnNew.setBackground(Color.BLACK);
		btnNew.addActionListener(this);
		paneBtn.add(btnNew);
		
		panelSave = new Panel();
		panelSave.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		panelBottom.add(panelSave);
		
		lblsave = new JLabel("Do you want to save? ");
		lblsave.setForeground(Color.BLUE);
		lblsave.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblsave.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblsave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int op = JOptionPane.showConfirmDialog(null, "Do you want to save file?");
				if(op == 0) {
					if(typ.equals("movie")) {
						app.getLibrary().updateMovieStore(typ);
					}
					else {
						app.getLibrary().updateSeriesStore(typ);
					}
					JOptionPane.showMessageDialog(null, "Save file successfully");
				}
			}
		});
		panelSave.add(lblsave);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if(str.equals("Search")) {
			tableModel.setRowCount(0);
			
			if(app.searchByKeyWord(tfSearch.getText(), type)== null)
				JOptionPane.showMessageDialog(null, "Not found");
			else 
				setModelList(app.searchByKeyWord(tfSearch.getText(), type));
			return;
		}
		if(str.equals("Add new")) {
			InsertFrame insert = new InsertFrame(app, admin, type);
			insert.setVisible(true);
			admin.setEnabled(false);
		}
		
		if(str.equals("Update")) {
			UpdateFrame up = new UpdateFrame(app, admin, f[0]);
			up.setVisible(true);
			admin.setEnabled(false);
		}
		if(str.equals("Delete")) {
			int op = JOptionPane.showConfirmDialog(null, "Do you want to delete this film?");
			if(op == 0) 
				if(app.getLibrary().deleteFilm(f[0])) {
					JOptionPane.showMessageDialog(null, "Delete successfully");
					tableModel.setRowCount(0);
					if(type.equals("movie"))
						setModelList(app.readMovie());
					else
						setModelList(app.readSeries());
					lblCount.setText("Total: "+ tableModel.getRowCount());
				}
				else
					JOptionPane.showMessageDialog(null, "Film not exists");
		}
		if(str.equals("Reload")) {
			tableModel.setRowCount(0);
			if(type.equals("movie"))
				setModelList(app.readMovie());
			else
				setModelList(app.readSeries());
			lblCount.setText("Total: "+ tableModel.getRowCount());
		}
		
	}

	void setModelList(Film[] films) {
		if(type.equals("movie")) {
			for(Film M : films) {
				Object[] ob = {M.getName(), M.getIcon(), M.getGenre(), M.getDirector(), ((Movie)M).getDuration(), M.getDate(), M.getContent()};
				tableModel.addRow(ob);
			}
		}
		else {
			for(Film M : films) {
				Object[] ob = {M.getName(), M.getIcon(), M.getGenre(), M.getDirector(), M.getDate(), ((Series)M).getEpisode(), M.getContent()};
				tableModel.addRow(ob);
			}
		}
	}
	
	
}
