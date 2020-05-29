package Panels;
import java.awt.CardLayout;

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
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class filmManage extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private Application app;
	private String type = "";
	private JTextField tfSearch;
	private JButton btnSearch, btnNew;
	private JPopupMenu jpu;
	private JMenuItem jmiUpd;
	private JMenuItem jmiDel;
	private Film f[];
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public filmManage(Application app, String typ) {

		setLayout(new BorderLayout());
		
		this.app = app;
		this.type = typ;
		
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
		btnSearch.addActionListener(this);
		paneSearch.add(btnSearch);		
		
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
		JTable table = new JTable(tableModel);
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
				
		JPanel paneBtn = new JPanel();
		paneTable.add(paneBtn);
				
		JButton btnReload = new JButton("Reload");
		btnReload.addActionListener(this);
		paneBtn.add(btnReload);
			
		btnNew = new JButton("Add new");
		btnNew.addActionListener(this);
		paneBtn.add(btnNew);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btnSearch) {
			tableModel.setRowCount(0);
			setModelList(app.searchByKeyWord(tfSearch.getText(), type));
		}
		
		String str = e.getActionCommand();
		if(str.equals("Update")) {
			UpdateFrame up = new UpdateFrame(app,f[0]);
			up.setVisible(true);
		}
		if(str.equals("Reload")) {
			
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
