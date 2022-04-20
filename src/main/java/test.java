import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class test {
public static void main(String[] args) {
		
		String url="jdbc:sqlserver://localhost;databasename=ENCHERES";
			String	username="pegaseUser";
			String	password="pegasePa$$w0rd";
		
		try {
			System.out.println("Try ?");
			Connection cnx = DriverManager.getConnection(url, username, password);
			
			System.out.println("Connexion OK");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		// TODO Auto-generated method stub

	}

}


