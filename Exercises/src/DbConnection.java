import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
public class DbConnection {
	static Connection conn = null;
	static Connection getConnection() {
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/D:\\Aneliya\\Uni\\OOP and DB Practice - JAVA/sampleDb", "sa", "1234");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
}
