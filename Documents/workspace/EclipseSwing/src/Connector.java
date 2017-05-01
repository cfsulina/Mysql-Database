import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {
  
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		getConnection();
			
	}

	public static Connection getConnection() throws Exception{
		try{
			String driver="com.mysql.jdbc.Driver";
			
			String url="jdbc:mysql://localhost:3306/info?autoReconnect=true&useSSL=false";
			String username="root";
			String password="Abcd1234";
			Class.forName(driver);
			Connection con=DriverManager.getConnection(url,username,password);
			System.out.println("Connected");
			return con;
		}
		catch(Exception e){
			System.out.println(e);
		}
		return null;
	}

}