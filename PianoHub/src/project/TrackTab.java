package project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TrackTab extends JPanel{
	
	JPanel upTrackPanel = new JPanel();
	JPanel midTrackPanel = new JPanel();
	JPanel downTrackPanel = new JPanel();
	
	JLabel trackLabel = new JLabel("New Track", SwingConstants.CENTER);
	
	JLabel trackNameLabel = new JLabel("Name: ");
	JLabel trackAuthorLabel = new JLabel("Author: ");
	JLabel trackDurationLabel = new JLabel("Duration: ");
	JLabel trackDescriptionLabel = new JLabel("Description: ");
	JLabel trackStatusLabel = new JLabel("Status: ");
	
	JTextField trackNameTF = new JTextField("Name");
	JTextField trackAuthorTF = new JTextField("Author");
	JTextField trackDurationTF = new JTextField("Duration");
	JTextField trackDescriptionTF = new JTextField("Description");
	JTextField trackStatusTF = new JTextField("Status");
	
	JButton trackAddBtn = new JButton("Add");
	
	
	public TrackTab() {
		this.setLayout(new BorderLayout());
		
		upTrackPanel.setLayout(new GridLayout(1, 1));
		upTrackPanel.add(trackLabel);
		this.add(upTrackPanel, BorderLayout.NORTH);
		
		midTrackPanel.setLayout(new GridLayout(5, 2, 10, 10));
		
		trackNameTF.setColumns(5);
		trackAuthorTF.setColumns(5);
		trackDurationTF.setColumns(5);
		trackDescriptionTF.setColumns(5);
		trackStatusTF.setColumns(5);
		
		midTrackPanel.add(trackNameLabel);
		midTrackPanel.add(trackNameTF);
				
		midTrackPanel.add(trackAuthorLabel);
		midTrackPanel.add(trackAuthorTF);
				
		midTrackPanel.add(trackDurationLabel);
		midTrackPanel.add(trackDurationTF);
				
		midTrackPanel.add(trackDescriptionLabel);
		midTrackPanel.add(trackDescriptionTF);
		
		midTrackPanel.add(trackStatusLabel);
		midTrackPanel.add(trackStatusTF);
		
		this.add(midTrackPanel, BorderLayout.CENTER);
						
		downTrackPanel.setLayout(new GridLayout(1, 1));
		downTrackPanel.add(trackAddBtn);
		this.add(downTrackPanel, BorderLayout.SOUTH);

	}
}
