package Panels;

import java.awt.CardLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import AppUsed.Application;
import IOFilmFile.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.ScrollPaneConstants;
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
	
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public filmManage(Application ap, String typ) {

		setLayout(new BorderLayout());
		
		this.app = ap;
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

		JTable table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		paneTable.add(new JScrollPane(table));
				
		JPanel paneBtn = new JPanel();
		paneTable.add(paneBtn);
				
		JButton btnReload = new JButton("Reload");
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
		if(e.getSource() == btnNew) {
			CardLayout card = (CardLayout)getLayout();
			card.show(this, "Insert");
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
