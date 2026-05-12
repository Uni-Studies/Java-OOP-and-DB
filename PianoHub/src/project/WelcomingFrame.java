package project;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class WelcomingFrame extends JFrame{	
	JTabbedPane tab = new JTabbedPane();
		
	JPanel sessionTab = new SessionTab();
	JPanel trackTab = new TrackTab();
	JPanel playlistTab = new PlaylistTab();
	
	public WelcomingFrame() {
		this.setSize(400, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
				
		this.setLayout(new BorderLayout());
		tab.add(sessionTab, "Sessions");
		
		tab.add(trackTab, "Tracks");
		tab.add(playlistTab, "Playlists");
		
		this.add(tab, BorderLayout.NORTH);
		
		this.setVisible(true);
	}


	
}

