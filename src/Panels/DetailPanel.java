package Panels;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.*;

import AppUsed.Application;
import IOFilmFile.Film;
import IOFilmFile.Movie;
import IOFilmFile.Series;

public class DetailPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel panelRight;
	private JPanel panelLeft;
	private String Ftrailer;
	private JLabel lblType,lblDirector, lblF, lblimg, lblDate;
	private JTextArea txtName, txtContent;
	private ArrayList<Film> films;
	private JPanel paneBtn, panePrevious, paneNext;
	private JLabel lblPrevious, lblNext;
	private int index;
	private JScrollPane scrollPane;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JScrollPane scrollPane_1;
	
	public DetailPanel(Application app) {
		this.films = app.getLibrary().getFilms();
		
		setLayout(new GridLayout(0, 2, 0, 0));
		panelLeft = new JPanel();
		this.add(panelLeft);
		
		panelLeft.setBackground(Color.WHITE);
		panelLeft.setOpaque(false);
		panelLeft.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		lblimg = new JLabel("");
		lblimg.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblimg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Desktop d = Desktop.getDesktop();
				try {
					d.browse(new URI(Ftrailer));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		lblimg.setBackground(Color.WHITE);
		
		
		panelLeft.add(lblimg);
		
		panelRight = new JPanel();
		panelRight.setBackground(Color.WHITE);
		//panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
		panelRight.setLayout(new BorderLayout(0,0));
		this.add(panelRight);
		JPanel panelTop = new JPanel();		
		panelRight.add(panelTop, BorderLayout.CENTER);
		panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelTop.add(scrollPane_1);
		
		JPanel panel_subTop = new JPanel();
		panel_subTop.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(panel_subTop);
		panel_subTop.setLayout(new BoxLayout(panel_subTop, BoxLayout.Y_AXIS));

		
		JPanel panelName = new JPanel();
		panelName.setBackground(Color.WHITE);
		panelName.setOpaque(false);
		panel_subTop.add(panelName);
		
		txtName = new JTextArea();
		txtName.setLineWrap(true);
		txtName.setWrapStyleWord(true);
		txtName.setEditable(false);
		panelName.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
		panelName.setLayout(new GridLayout(0, 1, 0, 0));
		panelName.add(txtName);
		txtName.setFont(new Font("Segoe UI Black", Font.PLAIN, 35));
		txtName.setForeground(Color.RED);
		
		JPanel panelType = new JPanel();
		panelType.setBackground(Color.WHITE);
		panelType.setOpaque(false);
		panel_subTop.add(panelType);
		panelType.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		
		lblType = new JLabel();
		panelType.add(lblType);
		lblType.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblF = new JLabel();
		lblF.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JPanel panelF = new JPanel();
		panelF.setBackground(Color.WHITE);
		panelF.setOpaque(false);
		panel_subTop.add(panelF);
		panelF.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		panelF.add(lblF);
		
		JPanel panelDate = new JPanel();
		panelDate.setBackground(Color.WHITE);
		FlowLayout flowLayout_1 = (FlowLayout) panelDate.getLayout();
		flowLayout_1.setHgap(20);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panelDate.setOpaque(false);
		panel_subTop.add(panelDate);
		
		lblDate = new JLabel();
		panelDate.add(lblDate);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		
		JPanel panelContent = new JPanel();
		panelContent.setBackground(Color.WHITE);
		panel_subTop.add(panelContent);
		panelContent.setOpaque(false);
		panelContent.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 10));
		panelContent.setLayout(new BorderLayout(0, 0));
		
		//scrollPane = new JScrollPane();
		//scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		txtContent = new JTextArea();
		//scrollPane.setViewportView(txtContent);
		txtContent.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtContent.setLineWrap(true);
		txtContent.setWrapStyleWord(true);
		txtContent.setEditable(false);
		panelContent.add(txtContent);
		JPanel panelDirector = new JPanel();
		panelDirector.setOpaque(false);
		panelDirector.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		
		lblDirector = new JLabel();
		panelDirector.add(lblDirector);
		lblDirector.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		
		JPanel panelBottom = new JPanel();
		panelRight.add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setLayout(new BoxLayout(panelBottom, BoxLayout.X_AXIS));
		paneBtn = new JPanel();
		panelBottom.add(paneBtn);
		paneBtn.setLayout(new GridLayout(0, 2, 0, 0));
		
		panePrevious = new JPanel();
		panePrevious.setBackground(Color.WHITE);
		panePrevious.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		FlowLayout flowLayout = (FlowLayout) panePrevious.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		panePrevious.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				index--;
				if(index < 0)
					index = films.size() - 1;
				setContent(getFilmInPos(index));
			}
		});
		paneBtn.add(panePrevious);
		
		lblPrevious = new JLabel("< Previous");
		lblPrevious.setForeground(SystemColor.textHighlight);
		panePrevious.add(lblPrevious);
		
		paneNext = new JPanel();
		paneNext.setBackground(Color.WHITE);
		paneNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		FlowLayout flowLayout_2 = (FlowLayout) paneNext.getLayout();
		flowLayout_2.setAlignment(FlowLayout.TRAILING);
		paneNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				index++;
				if(index > films.size()-1)
					index = 0;
				setContent(getFilmInPos(index));
			}
		});
		paneBtn.add(paneNext);
		
		lblNext = new JLabel("Next >");
		lblNext.setForeground(SystemColor.textHighlight);
		paneNext.add(lblNext);
	}
	
	public void setContent(Film f) {
		txtName.setText(f.getName());
		lblDirector.setText("Directed by: " + f.getDirector());
		lblDate.setText("Released date: "+ f.getDate());
		lblType.setText("Type: " + f.getGenre());
		txtContent.setText(f.getContent());
		
		String[]name = f.getName().split(" ");
		String tmp = "";
		
		for(int i = 0; i < name.length; i++)
			tmp = tmp + name[i] + "+";
		Ftrailer = "https://www.youtube.com/results?search_query=" + tmp + "trailer";
		if(f.getType().equals("movie")) {
			lblF.setText("Running time: " + String.valueOf(((Movie)f).getDuration()) + " minutes");
			String str = "movies\\Img\\" + f.getIcon();
			lblimg.setIcon(new ImageIcon(str));
			//lblimg.setIcon(new ImageIcon(new ImageIcon(str).getImage().getScaledInstance(390, 520, Image.SCALE_DEFAULT)));
		}
		else {
			lblF.setText("Episodes: " + String.valueOf(((Series)f).getEpisode()));
			String str = "series\\Img\\" + f.getIcon();
			lblimg.setIcon(new ImageIcon(str));
			//lblimg.setIcon(new ImageIcon(new ImageIcon(str).getImage().getScaledInstance(390, 520, Image.SCALE_DEFAULT)));

		}
		
		this.index = getPosOfFilm(f);
	}
	
	// Get the film in position and the position of film
	public int getPosOfFilm(Film film) {
		return films.indexOf(film);		
	}
	
	public Film getFilmInPos(int index) {
		return films.get(index);		
	}
	
	public int getIndex() {
		return index;
	}
//
//	public void setIndex(int index) {
//		this.index = index;
//	}
}
