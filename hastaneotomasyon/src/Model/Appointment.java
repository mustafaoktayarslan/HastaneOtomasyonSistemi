package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Appointment {
	private int id, hastaId, doctorId;
	private String hastaName, doctorName, appDate;

	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement prpStatement = null;

	public Appointment() {
	}

	public ArrayList<Appointment> getHastaList(int hasta_id) throws SQLException {
		ArrayList<Appointment> list = new ArrayList<>();
		Appointment obje;
		Connection con = conn.connDB();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM [appointment] WHERE hasta_id=" + hasta_id);
			while (rs.next()) {
				obje = new Appointment();
				obje.setId(rs.getInt("id"));
				obje.setDoctorId(rs.getInt("doctor_id"));
				obje.setDoctorName(rs.getString("doctor_name"));
				obje.setHastaId(rs.getInt("hasta_id"));
				obje.setHastaName(rs.getString("hasta_name"));
				obje.setAppDate(rs.getString("app_date"));
				list.add(obje);

			}

		} catch (SQLException e) {
			System.out.println("hasta appoint->ARRAYLÝST içerisindeki CATCH");
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}

		return list;
	}

	public ArrayList<Appointment> getDoctorList(int doctor_id) throws SQLException {
		ArrayList<Appointment> list = new ArrayList<>();
		Appointment obje;
		Connection con = conn.connDB();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM [appointment] WHERE doctor_id=" + doctor_id);
			while (rs.next()) {
				obje = new Appointment();
				obje.setId(rs.getInt("id"));
				obje.setDoctorId(rs.getInt("doctor_id"));
				obje.setDoctorName(rs.getString("doctor_name"));
				obje.setHastaId(rs.getInt("hasta_id"));
				obje.setHastaName(rs.getString("hasta_name"));
				obje.setAppDate(rs.getString("app_date"));
				list.add(obje);

			}

		} catch (SQLException e) {
			System.out.println("appoint->ARRAYLÝST içerisindeki CATCH");
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}

		return list;
	}

	public ArrayList<Appointment> getDoctorAppointList(int doctor_id) throws SQLException {
		ArrayList<Appointment> list = new ArrayList<>();
		Appointment obje;
		Connection con = conn.connDB();
		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"SELECT DISTINCT a.id, a.doctor_id, a.doctor_name, a.hasta_name, a.app_date , a.hasta_id, w.status FROM [whour] [w] LEFT JOIN [appointment] [a] ON w.doctor_id=a.doctor_id WHERE a.doctor_id="
							+ doctor_id + " AND status='p'");
			while (rs.next()) {
				obje = new Appointment();
				obje.setId(rs.getInt("id"));
				obje.setDoctorId(rs.getInt("doctor_id"));
				obje.setDoctorName(rs.getString("doctor_name"));
				obje.setHastaId(rs.getInt("hasta_id"));
				obje.setHastaName(rs.getString("hasta_name"));
				obje.setAppDate(rs.getString("app_date"));
				list.add(obje);

			}

		} catch (SQLException e) {
			System.out.println("appoint->ARRAYLÝST içerisindeki CATCH");
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}

		return list;
	}
	public boolean updateActiveWhourStatus(int doctor_id, String wDate) {
		boolean control = true;
		int key = 0;
		String query = "UPDATE [whour] SET status=? WHERE doctor_id=? AND wdate=? ";
		Connection con = conn.connDB();
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

	public Appointment(int id, int hastaId, int doctorId, String hastaName, String doctorName, String appDate) {
		super();
		this.id = id;
		this.hastaId = hastaId;
		this.doctorId = doctorId;
		this.hastaName = hastaName;
		this.doctorName = doctorName;
		this.appDate = appDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHastaId() {
		return hastaId;
	}

	public void setHastaId(int hastaId) {
		this.hastaId = hastaId;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getHastaName() {
		return hastaName;
	}

	public void setHastaName(String hastaName) {
		this.hastaName = hastaName;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

}
