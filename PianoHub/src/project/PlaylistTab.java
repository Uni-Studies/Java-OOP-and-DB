package project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PlaylistTab extends JPanel{
	JPanel tracksPanel = new JPanel();
	JTable tracks = new JTable();
	JScrollPane tracksScroll = new JScrollPane(tracks);
	
	JPanel playlistPanel = new JPanel();
	JTable playlist = new JTable();
	JScrollPane playlistScroll = new JScrollPane(playlist);
	
	public PlaylistTab() {
		this.setLayout(new GridLayout(1, 2));
		
		tracksScroll.setPreferredSize(new Dimension(350, 150));
		tracksPanel.setLayout(new GridLayout(1, 1));
		tracksPanel.add(tracksScroll);
		this.add(tracksPanel);
		
		playlistScroll.setPreferredSize(new Dimension(350, 150));
		playlistPanel.setLayout(new GridLayout(1, 1));
		playlistPanel.add(playlistScroll);
		this.add(playlistPanel);	
	}
}
