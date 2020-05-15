package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import AppUsed.Application;
import IOFilmFile.Film;

/*class Renderer extends DefaultTableCellRenderer{
	 
    public void fillColor(JTable t,JLabel l,boolean isSelected ){
        //setting the background and foreground when JLabel is selected
        if(isSelected){
            l.setBackground(t.getSelectionBackground());
            l.setForeground(t.getSelectionForeground());
        }
 
        else{
            l.setBackground(t.getBackground());
            l.setForeground(t.getForeground());
        }
 
    }
 
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
         boolean hasFocus, int row, int column)
     {
 
        if(value instanceof JLabel){
            JLabel label = (JLabel)value;
            //to make label foreground n background visible you need to
            // setOpaque -> true
            label.setIcon(new ImageIcon("icon\\add.png"));
            label.setOpaque(true);
            fillColor(table,label,isSelected);
            return label;
 
        }
        else
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
     }
 
}*/

class MyModel extends AbstractTableModel{
	private String[]cols;
	private Object[][] rows;
	public MyModel() {
		// TODO Auto-generated constructor stub
	}
	MyModel(Object[][] data, String[]cols){
		this.cols = cols;
		this.rows = data;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rows.length;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.cols.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return this.rows[rowIndex][columnIndex];
	}
	public String getColsName(int idx) {
		return this.cols[idx].toString();
	}
	public Class getColumnClass(int cols) {
		return null;
		
	}
}

public class test extends JFrame {
	private Application app = new Application();
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
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
	public test() {
		char[] pass = new char[] {'b', 'e', 'o', 'b', 'e', 'o'};
		app.login("winterheartlove@gmail.com", pass);
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		table = new JTable() {
			public boolean isCellEditable(int data, int cols) {
				return false;
			}
			@Override
		    public Dimension getPreferredScrollableViewportSize() {
		        return new Dimension(1024, 512);
		    }
		};
		
		Table();
		getContentPane().add(new JScrollPane(table));
	}
	void Table() {
		Film[] list = app.readFilm();
		String[]cols = {"Name","Image"};
		Object[][]rows = new Object[list.length][2];
		for(int i = 0; i < list.length; i++) {
			rows[i][0] = list[i].getName();
			if(list[i].getIcon()!=null) {
				ImageIcon imgI = null;
				//System.out.println(list[i].getIcon());
				if(list[0].getType().equals("movie"))
					imgI = new ImageIcon(new ImageIcon("movies\\Img\\" + list[i].getIcon()).getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH));
				else
					imgI = new ImageIcon(new ImageIcon("movies\\Img\\" + list[i].getIcon()).getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH));
				rows[i][1] = imgI;
			}
			else
				rows[i][1] = null;
			
		}
		//System.out.println(rows.length);
		
		table.setModel(new MyModel(rows, cols));
		table.setRowHeight(20);
	}
}
