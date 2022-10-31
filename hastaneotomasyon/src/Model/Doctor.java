package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Doctor extends User {
	Statement st = null;
	ResultSet rs = null;
	Connection con = conn.connDB();
	PreparedStatement preparedStatement = null;

	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Doctor(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);
		// TODO Auto-generated constructor stub
	}

	public boolean addWhour(int doctor_id, String doctor_name, String wdate) {
		boolean control = true;
		int key = 0;
		String query = "INSERT INTO whour (doctor_id,doctor_name,wdate,status) VALUES (?,?,?,?)";

		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"SELECT * FROM whour WHERE status='a' AND doctor_id=" + doctor_id + "AND wdate='" + wdate + "'");
			while (rs.next()) {
				control = false;
				break;
			}
			if (control) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, doctor_id);
				preparedStatement.setString(2, doctor_name);
				preparedStatement.setString(3, wdate);
				preparedStatement.setString(4, "a");
				preparedStatement.executeUpdate();

			}
			key = 1;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (key == 1)
			return true;
		else
			return false;
	}

	public ArrayList<Whour> getWhourList(int doctor_id) throws SQLException {
		ArrayList<Whour> list = new ArrayList<>();
		Whour obje;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM whour WHERE status='a' AND doctor_id=" + doctor_id);
			while (rs.next()) {

				obje = new Whour();
				obje.setId(rs.getInt("id"));
				obje.setDoctor_id(rs.getInt("doctor_id"));
				obje.setDoctor_name(rs.getString("doctor_name"));
				obje.setWdate(rs.getString("wdate"));
				obje.setStatus(rs.getString("status"));
				list.add(obje);
			}

		} catch (SQLException e) {
			System.out.println("Whour ->ARRAYLÝST içerisindeki CATCH");
			e.printStackTrace();
		}

		return list;
	}

	public boolean deleteWhour(int id) throws SQLException {
		String query = "DELETE FROM [whour] WHERE id = ?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

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

	public boolean deleteAppoint(int id) throws SQLException {
		String query = "DELETE FROM [appointment] WHERE id = ? ";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
	
			preparedStatement.executeUpdate();

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
