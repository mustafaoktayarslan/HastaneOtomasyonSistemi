package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Clinic {

	private int id;
	private String name;

	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement prpStatement = null;

	public Clinic() {
	}

	public Clinic(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public ArrayList<Clinic> getClinicList() throws SQLException {
		ArrayList<Clinic> list = new ArrayList<>();
		Clinic obje;
		Connection con = conn.connDB();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM [clinic]");
			while (rs.next()) {
				obje = new Clinic(rs.getInt("id"), rs.getString("name"));
				list.add(obje);

			}

		} catch (SQLException e) {
			System.out.println("CLÝNÝC->ARRAYLÝST içerisindeki CATCH");
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}

		return list;
	}

	public boolean addClinic(String name) throws SQLException {
		Connection con = conn.connDB();
		String query = "INSERT INTO [clinic]" + "(name) VALUES " + "(?)";
		boolean key = false;
		try {
			st = con.createStatement();
			prpStatement = con.prepareStatement(query);

			prpStatement.setString(1, name);

			prpStatement.executeUpdate();
			key = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (key)
			return true;

		else
			return false;

	}

	public boolean deleteClinic(int id) throws SQLException {
		String query = "DELETE FROM [clinic] WHERE id = ?";
		boolean key = false;
		Connection con = conn.connDB();
		try {
			st = con.createStatement();
			prpStatement = con.prepareStatement(query);
			prpStatement.setInt(1, id);
			prpStatement.executeUpdate();

			key = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (key)
			return true;

		else
			return false;

	}

	public boolean updateClinic(int id, String name) throws SQLException {
		String query = "UPDATE [clinic] SET name=? WHERE id = ?";
		boolean key = false;
		Connection con = conn.connDB();
		try {
			st = con.createStatement();
			prpStatement = con.prepareStatement(query);
			prpStatement.setString(1, name);
			prpStatement.setInt(2, id);
			prpStatement.executeUpdate();

			key = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (key)
			return true;

		else
			return false;

	}

	public Clinic getSelect(int id) throws SQLException {
		String query = "SELECT * FROM [clinic]  WHERE id = " + id;

		Connection con = conn.connDB();
		Clinic c = new Clinic();
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				break;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c;

	}
	
	public ArrayList<User> getClinicDoctorList(int clinic_id)  throws SQLException { 
		ArrayList<User> list=new ArrayList<User>();
		User obje;
		try {  
			Connection con=conn.connDB();
			st=con.createStatement();
			rs=st.executeQuery("SELECT u.id, u.tcno, u.name, u.password,u.type FROM [worker] [w] LEFT JOIN [user] [u] ON w.user_id=u.id WHERE clinic_id="+clinic_id);
			while (rs.next()) {
				 
				//System.out.println(rs.getString("name"));
			
				obje=new User(rs.getInt("id"),rs.getString("tcno"),rs.getString("name"),rs.getString("password"),rs.getString("type"));
				list.add(obje);
			}
			
		} catch (SQLException e) {
			System.out.println("USER clinic->ARRAYLÝST içerisindeki CATCH");
			e.printStackTrace();
		}
		
		
		
		return list;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
