package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Helper.Yardimci;

public class Hasta extends User {
	Connection con = conn.connDB();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement prpStatement = null;

	public Hasta() {

	}

	public Hasta(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);

	}

	public boolean register(String tcno, String password, String name) {
		boolean control = true;
		int key = 0;
		String query = "INSERT INTO [user] (tcno,password,name,type) VALUES (?,?,?,?)";

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM [user] WHERE tcno='" + tcno + "'");
			while (rs.next()) {
				control = false;
				Yardimci.showMsg("Bu T.C numarasýna ait bir kayýt bulunmaktadýr.");
				break;
			}
			if (control) {
				prpStatement = con.prepareStatement(query);
				prpStatement.setString(1, tcno);
				prpStatement.setString(2, password);
				prpStatement.setString(3, name);
				prpStatement.setString(4, "hasta");
				prpStatement.executeUpdate();
				key = 1;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (key == 1)
			return true;
		else
			return false;
	}

	public boolean addAppointment(int doctor_id, int hasta_id, String doctor_name, String hasta_name, String app_date) {
		boolean control = true;
		int key = 0;
		String query = "INSERT INTO [appointment] (doctor_id,doctor_name,hasta_id,hasta_name,app_date) VALUES (?,?,?,?,?)";

		try {

			prpStatement = con.prepareStatement(query);
			prpStatement.setInt(1, doctor_id);
			prpStatement.setString(2, doctor_name);
			prpStatement.setInt(3, hasta_id);
			prpStatement.setString(4, hasta_name);
			prpStatement.setString(5, app_date);
			prpStatement.executeUpdate();
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

	public boolean updatePassiveWhourStatus(int doctor_id, String wDate) {
		boolean control = true;
		int key = 0;
		String query = "UPDATE [whour] SET status=? WHERE doctor_id=? AND wdate=? ";

		try {

			prpStatement = con.prepareStatement(query);
			prpStatement.setString(1, "p");
			prpStatement.setInt(2, doctor_id);
			prpStatement.setString(3, wDate);
			prpStatement.executeUpdate();
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
	public boolean updateActiveWhourStatus(int doctor_id, String wDate) {
		boolean control = true;
		int key = 0;
		String query = "UPDATE [whour] SET status=? WHERE doctor_id=? AND wdate=? ";

		try {

			prpStatement = con.prepareStatement(query);
			prpStatement.setString(1, "a");
			prpStatement.setInt(2, doctor_id);
			prpStatement.setString(3, wDate);
			prpStatement.executeUpdate();
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
	public boolean deleteAppoint(int id) throws SQLException {
		String query = "DELETE FROM [appointment] WHERE id = ?";
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
}
