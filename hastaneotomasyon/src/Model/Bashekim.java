package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Bashekim extends User {

	Connection con = conn.connDB();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement prpStatement = null;

	public Bashekim(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);

	}

	public Bashekim() {
	}

	public ArrayList<User> getDoctorList() throws SQLException {
		ArrayList<User> list = new ArrayList<User>();
		User obje;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM [user] WHERE type='doktor' OR type='baþhekim'");
			while (rs.next()) {
				obje = new User(rs.getInt("id"), rs.getString("tcno"), rs.getString("name"), rs.getString("password"),
						rs.getString("type"));
				list.add(obje);

			}

		} catch (SQLException e) {
			System.out.println("USER->ARRAYLÝST içerisindeki CATCH");
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<User> getClinicDoctorList(int clinic_id) throws SQLException {
		ArrayList<User> list = new ArrayList<User>();
		User obje;
		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"SELECT u.id, u.tcno, u.name, u.password,u.type FROM [worker] [w] LEFT JOIN [user] [u] ON w.user_id=u.id WHERE clinic_id="
							+ clinic_id);
			while (rs.next()) {

				// System.out.println(rs.getString("name"));

				obje = new User(rs.getInt("id"), rs.getString("tcno"), rs.getString("name"), rs.getString("password"),
						rs.getString("type"));
				list.add(obje);
			}

		} catch (SQLException e) {
			System.out.println("USER clinic->ARRAYLÝST içerisindeki CATCH");
			e.printStackTrace();
		}

		return list;
	}

	public boolean addDoctor(String tcno, String password, String name) throws SQLException {
		String query = "INSERT INTO [user]" + "(tcno,password,name,type) VALUES " + "(?,?,?,?)";
		boolean key = false;
		try {
			st = con.createStatement();
			prpStatement = con.prepareStatement(query);
			prpStatement.setString(1, tcno);
			prpStatement.setString(2, password);
			prpStatement.setString(3, name);
			prpStatement.setString(4, "doktor");
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

	public boolean deleteDoctor(int id) throws SQLException {
		String query = "DELETE FROM [user] WHERE id = ?";
		boolean key = false;
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

	public boolean updateDoctor(int id, String tcno, String password, String name) throws SQLException {
		String query = "UPDATE [user] SET name=?, tcno=?, password=? WHERE id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			prpStatement = con.prepareStatement(query);
			prpStatement.setString(1, name);
			prpStatement.setString(2, tcno);
			prpStatement.setString(3, password);
			prpStatement.setInt(4, id);

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

	public boolean addWorker(int user_id, int clinic_id) throws SQLException {
		String query = "INSERT INTO [worker]" + "(user_id,clinic_id) VALUES " + "(?,?)";
		boolean key = false;
		boolean control = true;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM [worker] WHERE clinic_id=" + clinic_id + " AND user_id=" + user_id);
			while (rs.next()) {
				control = false;
			}
			if (control) {
				prpStatement = con.prepareStatement(query);
				prpStatement.setInt(1, user_id);
				prpStatement.setInt(2, clinic_id);

				prpStatement.executeUpdate();
			}
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
}
