package project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SessionTab extends JPanel{
	Connection conn = null;
	PreparedStatement state = null;
	ResultSet result = null;
	int id = -1;
	
	
	JPanel upSessionPanel = new JPanel();
	JPanel midSessionPanel = new JPanel();
	JPanel downSessionPanel = new JPanel();
	
	JLabel sessionLabel = new JLabel("Session", SwingConstants.CENTER);
	
	JLabel sessionDescriptionLabel = new JLabel("Description: ");
	JLabel sessionDurationLabel = new JLabel("Duration: ");
	JLabel sessionDateLabel = new JLabel("Date: ");
	JLabel selectPlayLabel = new JLabel("Select play: ");
	
	JTextField sessionDescriptionTF = new JTextField("Description");
	JTextField sessionDurationTF = new JTextField("Duration");
	JTextField sessionDateTF = new JTextField("Date");
	
	String[] item = {"Moonlight Sonata", "Symphony №9"};
	JComboBox<String> playsCombo = new JComboBox<String>(item);
	
	JButton sessionAddBtn = new JButton("Add");
	JButton sessionEditBtn = new JButton("Edit");
	JButton sessionDeleteBtn = new JButton("Delete");
	JButton sessionDetailsBtn = new JButton("Details");
	
	JTable sessions = new JTable();
	JScrollPane sessionsScroll = new JScrollPane(sessions);
	
	public SessionTab() {
		this.setLayout(new BorderLayout());
		
		upSessionPanel.setLayout(new GridLayout(5, 2, 10, 10));
		upSessionPanel.add(sessionLabel);
		
		sessionDescriptionTF.setColumns(5);
		sessionDateTF.setColumns(5);
		sessionDurationTF.setColumns(5);
		
		upSessionPanel.add(sessionDescriptionLabel);
		upSessionPanel.add(sessionDescriptionTF);
				
		upSessionPanel.add(sessionDateLabel);
		upSessionPanel.add(sessionDateTF);
				
		upSessionPanel.add(sessionDurationLabel);
		upSessionPanel.add(sessionDurationTF);
				
		upSessionPanel.add(selectPlayLabel);
		upSessionPanel.add(playsCombo);
		this.add(upSessionPanel, BorderLayout.NORTH);
		
		midSessionPanel.setLayout(new GridLayout(5, 2, 10, 10));
		midSessionPanel.add(sessionAddBtn);
		midSessionPanel.add(sessionEditBtn);
		midSessionPanel.add(sessionDeleteBtn);
		midSessionPanel.add(sessionDetailsBtn);
		
		this.add(midSessionPanel, BorderLayout.CENTER);
		
		downSessionPanel.setLayout(new GridLayout(1, 1));
		downSessionPanel.add(sessionsScroll);
		
		this.add(downSessionPanel, BorderLayout.SOUTH);
		
		refreshTable();
		refreshPlaysCombo();
	}
	
	public void refreshTable() {
		conn = DbConnection.getConnection();
		try {
			state = conn.prepareStatement(
					"SELECT * FROM SESSIONS");
			result = state.executeQuery();
			sessions.setModel(new MyModel(result));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clearForm() {
		sessionDescriptionTF.setText("");
		sessionDurationTF.setText("");
		sessionDateTF.setText("");
	}
	
	public void refreshPlaysCombo() {
		playsCombo.removeAllItems();
		
		conn = DbConnection.getConnection();
		String sql = "SELECT NAME, AUTHOR, STATUS FROM TRACKS";
		String item = "";
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			while(result.next()) {
				item = result.getObject(1).toString() + " by " + result.getObject(2) + " - " + result.getObject(3).toString();
				
				playsCombo.addItem(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	class AddAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			conn = DbConnection.getConnection();
			String sql = 
					"INSERT INTO SESSION (DESCRIPTION, DURATION, DATE) VALUES (?, ?, ?)"; 
			try {
				state = conn.prepareStatement(sql);
				state.setString(1,  sessionDescriptionTF.getText());
				state.setString(2,  sessionDurationTF.getText());
				
				String dateString = sessionDateTF.getText();
				java.sql.Date sqlDate = java.sql.Date.valueOf(dateString);
				state.setString(3,  sessionDateTF.getText());
				
				state.execute();
				refreshTable();
				refreshPlaysCombo();
				clearForm();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
}
