package Helper;
import java.sql.*;
public class DBConnection {/*	
			Statement statement=c.createStatement();
			rows=statement.executeUpdate(sql);
			if (rows>0) System.out.println("eklendi");
			
				Statement st=c.createStatement();
			ResultSet resultSet=  st.executeQuery(sql);
			while (resultSet.next()) {
				String name=resultSet.getString("name");
				int age= resultSet.getInt("age");
				System.out.println(name + " " +age);
				
			}
			*/
	
	Connection c=null;
	int rows;
	String sql="SELECT * FROM [user]";
	String url="jdbc:sqlserver://localhost:52390;databaseName=HastaneOtomasyonDB;user=sa;password=123qwe;encrypt=true;trustServerCertificate=true;";
	String username="sa";
	String password="123qwe";
	public DBConnection() {}
	public Connection connDB() {
		
		try {
			this.c=DriverManager.getConnection(url);
			System.out.println("veri tabanýna girildi");
		
			
		

			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("VERÝ TABANI BAÐLANTISI YAPILAMADI");
			e.printStackTrace();
		}
		return c;
	}
	
}
