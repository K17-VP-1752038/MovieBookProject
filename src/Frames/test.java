package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Vector;

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
import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import AppUsed.Application;
import IOFilmFile.Film;


class JLabelCellenderer extends JLabel implements TableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    	TableColumn tc = table.getColumn("Image");
    	if(column == 1) {
    		
	    	//tc.setMinWidth(200);
	    	//tc.setMaxWidth(200);
	    	table.setRowHeight(200);
	       
    	}
    	else tc.setPreferredWidth(50);
    	return (Component)value;
    }
}

public class test extends JFrame {
	private Application app = new Application();
	private JPanel contentPane;
	private JTable table;
	
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

	
	public test() {
		char[] pass = new char[] {'b', 'e', 'o', 'b', 'e', 'o'};
		app.login("winterheartlove@gmail.com", pass);
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		table = new JTable() {
			public boolean isCellEditable(int row, int column) {
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
		//JLabel lblimg = new JLabel(new ImageIcon("movies\\Img\\BirdsOfPrey.jpg"));
		Object[][]rows = new Object[list.length][2];
		for(int i = 0; i < list.length; i++) {
			rows[i][0] = list[i].getName();
			JLabel lblimg = null;
			if(list[i].getIcon()!=null) {
				ImageIcon imgI = null;
				//System.out.println(list[i].getIcon());
				lblimg = new JLabel();
				if(list[i].getType().equals("movie"))
					lblimg.setIcon(new ImageIcon("movies\\Img\\" + list[i].getIcon()));
				else 
					lblimg.setIcon(new ImageIcon("series/Img/" + list[i].getIcon()));
				rows[i][1] = lblimg;
			}
			else
				rows[i][1] = null;
			
		}
		
		table.setModel(new DefaultTableModel(rows, cols));
		table.getColumn("Image").setCellRenderer(new JLabelCellenderer());
		table.setRowHeight(20);
	}
}
/*Film[]flist = app.readFilm();
getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
for(int i = 0; i < 12; i++) {
	JPanel panel = new JPanel();
	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	getContentPane().add(panel);
	JLabel lblImg = new JLabel("");
	BufferedImage img;
	try {
		if(flist[i].getType().equals("movie"))
			img = (BufferedImage) ImageIO.read(new File("movies\\Img\\"+flist[i].getIcon()));
		else img = (BufferedImage) ImageIO.read(new File("series\\Img\\"+flist[i].getIcon()));
		ImageIcon icon = new ImageIcon(img.getScaledInstance(280, 200, Image.SCALE_SMOOTH));
		lblImg.setIcon(icon);
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	panel.add(lblImg);
	
	JLabel lblName = new JLabel(flist[i].getName());
	lblName.setForeground(Color.RED);
	lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
	panel.add(lblName);

}*/
