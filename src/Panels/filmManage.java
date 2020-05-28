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

public class filmManage extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private Application app;
	private String type = "";
	private JTextField tfSearch;
	private JButton btnSearch;
	
	/**
	 * Create the panel.
	 */
	public filmManage(Application ap, String typ) {

		setLayout(new CardLayout());
		
		this.app = ap;
		this.type = typ;
		
		//--------Panel show all---------------------
		JPanel paneTable = new JPanel();
		paneTable.setLayout(new BoxLayout(paneTable, BoxLayout.Y_AXIS));
		add("Show movies", paneTable);
				
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
			tableModel = new DefaultTableModel(header, 0);
			setModelList(app.readMovie());
		}
		else {
			String[] header = {"Name", "Image", "Genre", "Director", "Released date", "Episodes", "Content"};
			tableModel = new DefaultTableModel(header, 0);
			setModelList(app.readSeries());
		}

		JTable table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
		paneTable.add(new JScrollPane(table));
				
		JPanel paneBtn = new JPanel();
		paneTable.add(paneBtn);
				
		JButton btnReload = new JButton("Reload");
		paneBtn.add(btnReload);
			
		JButton btnNew = new JButton("Add new");
		paneBtn.add(btnNew);
				
		//---------Panel edit-------------------------
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btnSearch) {
			tableModel.setRowCount(0);
			setModelList(app.searchByKeyWord(tfSearch.getText(), type));
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
