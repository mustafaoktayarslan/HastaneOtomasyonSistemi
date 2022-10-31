package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Whour {
	private int id, doctor_id;
	private String doctor_name, wdate, status;
	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Whour(int id, int doctor_id, String doctor_name, String wdate, String status) {
		super();
		this.id = id;
		this.doctor_id = doctor_id;
		this.doctor_name = doctor_name;
		this.wdate = wdate;
		this.status = status;
	}

	public Whour() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public ArrayList<Whour> getWhourList(int doctor_id) throws SQLException {
		ArrayList<Whour> list = new ArrayList<>();
		Whour obje;
		try {
			Connection con=conn.connDB();
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

}
