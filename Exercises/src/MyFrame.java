import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
public class MyFrame extends JFrame{
	
	Connection conn = null;
	PreparedStatement state = null;
	ResultSet result = null;
	int id = -1;
	
	JPanel upPanel = new JPanel();
	JPanel midPanel = new JPanel();
	JPanel downPanel = new JPanel();
	
	JLabel fnameL = new JLabel("Име:");
	JLabel lnameL = new JLabel("Фамилия:");
	JLabel sexL = new JLabel("Пол:");
	JLabel ageL = new JLabel("Години:");
	JLabel salaryL = new JLabel("Заплата:");
	
	JTextField fnameTF = new JTextField();
	JTextField lnameTF = new JTextField();
	JTextField ageTF = new JTextField();
	JTextField salaryTF = new JTextField();
	
	String[] item = {"Мъж", "Жена"};
	JComboBox<String> sexCombo = new JComboBox<String>(item);
	JComboBox<String> personCombo = new JComboBox<String>();
	
	
	JButton addBT = new JButton("Добави");
	JButton deleteBT = new JButton("Изтрий");
	JButton editBT = new JButton("Редактирай");
	JButton personSearchBT = new JButton("Търсене по години");
	JButton personRefreshBT = new JButton("Обнови");
	
	JTable table = new JTable();
	JScrollPane myScroll = new JScrollPane(table);
	
	public MyFrame() {
		this.setSize(400, 600);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(3, 1));
		
		//upPanel------------------------------------------------------
		upPanel.setLayout(new GridLayout(5, 2));
		upPanel.add(fnameL);
		upPanel.add(fnameTF);
		upPanel.add(lnameL);
		upPanel.add(lnameTF);
		upPanel.add(sexL);
		upPanel.add(sexCombo);
		upPanel.add(ageL);
		upPanel.add(ageTF);
		upPanel.add(salaryL);
		upPanel.add(salaryTF);
		this.add(upPanel);
		
		//midPanel-----------------------------------------------------
		midPanel.add(addBT);
		midPanel.add(deleteBT);
		midPanel.add(editBT);
		midPanel.add(personSearchBT);
		midPanel.add(personRefreshBT);
		midPanel.add(personCombo);
		
		this.add(midPanel);
		
		addBT.addActionListener(new AddAction());
		deleteBT.addActionListener(new DeleteAction());
		personSearchBT.addActionListener(new SearchActionPerson());
		personRefreshBT.addActionListener(new RefreshActionPerson());
		
		//downPanel----------------------------------------------------
		myScroll.setPreferredSize(new Dimension(350, 150));
		downPanel.add(myScroll);
		this.add(downPanel);
		table.addMouseListener(new MouseAction());
		//this.add(btn);
		
		refreshTable();
		refreshPersonCombo();
		this.setVisible(true);
	}
	
	public void refreshTable() {
		conn = DbConnection.getConnection();
		try {
			state = conn.prepareStatement("select * from person");
			result = state.executeQuery(); // executeQuery - from the db to us; otherwise execute() only
			table.setModel(new MyModel(result));
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void clearForm() {
		fnameTF.setText("");
		lnameTF.setText("");
		ageTF.setText("");
		salaryTF.setText("");
	}
	
	public void refreshPersonCombo() {
		personCombo.removeAllItems();
		
		conn = DbConnection.getConnection();
		String sql = "select id, fname, lname from person";
		String item = "";
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			while(result.next()) {
				item = result.getObject(1).toString() + "." + 
						result.getObject(2).toString() + " " +
						result.getObject(3).toString();
				personCombo.addItem(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class AddAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		//	System.out.println(fnameTF.getText() + " " + lnameTF.getText());
			conn = DbConnection.getConnection();
			String sql = "insert into person (fname, lname, sex, age, salary) values (?, ?, ?, ?, ?)";
			try {
				state = conn.prepareStatement(sql);
				state.setString(1, fnameTF.getText());
				state.setString(2, lnameTF.getText());
				state.setString(3, sexCombo.getSelectedItem().toString());
				state.setInt(4, Integer.parseInt(ageTF.getText()));
				state.setFloat(5,  Float.parseFloat(salaryTF.getText()));
				
				state.execute();
				refreshTable();
				refreshPersonCombo();
				clearForm();
				
     		} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	class MouseAction implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int row = table.getSelectedRow();
			id = Integer.parseInt(table.getValueAt(row, 0).toString());
			fnameTF.setText(table.getValueAt(row, 1).toString());
			lnameTF.setText(table.getValueAt(row, 2).toString());
			ageTF.setText(table.getValueAt(row, 4).toString());
			salaryTF.setText(table.getValueAt(row, 5).toString());
			
			if(table.getValueAt(row, 3).toString().equals("Мъж")) {
				sexCombo.setSelectedIndex(0);
			}
			else {
				sexCombo.setSelectedIndex(1);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class DeleteAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			conn = DbConnection.getConnection();
			
			String sql = "delete from person where id = ?";
			
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, id);
				state.execute();
				id = -1;
				refreshTable();
				refreshPersonCombo();
				clearForm();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	
	class SearchActionPerson implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			conn = DbConnection.getConnection();
			String sql = "select * from person where age = ?";
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, Integer.parseInt(ageTF.getText()));
				result = state.executeQuery();
				table.setModel(new MyModel(result));
				clearForm();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	class RefreshActionPerson implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			refreshTable();
		}
		
	}
}

