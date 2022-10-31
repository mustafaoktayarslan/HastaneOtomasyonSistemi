package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Appointment;
import Model.Doctor;
import Model.HastaGUI;

import javax.swing.JLabel;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.SystemColor;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.toedter.calendar.JDateChooser;

import Helper.Yardimci;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DoctorGUI extends JFrame {

	private JPanel contentPane;
	private static Doctor doctor = new Doctor();

	private JTable table_whour;
	private DefaultTableModel whourModel;
	private Object[] whourData;

	private DefaultTableModel appointModel;
	private Object[] appointData;
	private JTable table_appoint;
	private Appointment appoint = new Appointment();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorGUI frame = new DoctorGUI(doctor);
					frame.setLocationRelativeTo(frame);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 * @throws SQLException
	 */
	public DoctorGUI(Doctor doctor) throws IOException, SQLException {
		// WHOUR MODEL
		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];
		for (int i = 0; i < doctor.getWhourList(doctor.getId()).size(); i++) {
			whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();
			whourModel.addRow(whourData);
		}
		// Appoint model
		appointModel = new DefaultTableModel();
		Object[] colAppoint = new Object[4];
		colAppoint[0] = "Randevu ID";
		colAppoint[1] = "Hasta Adý";
		colAppoint[2] = "Tarih";
		colAppoint[3] = "Hasta ID";
		appointModel.setColumnIdentifiers(colAppoint);
		appointData = new Object[4];
		for (int i = 0; i < appoint.getDoctorAppointList(doctor.getId()).size(); i++) {
			appointData[0] = appoint.getDoctorAppointList(doctor.getId()).get(i).getId();
			appointData[1] = appoint.getDoctorAppointList(doctor.getId()).get(i).getHastaName();
			appointData[2] = appoint.getDoctorAppointList(doctor.getId()).get(i).getAppDate();
			appointData[3] = appoint.getDoctorAppointList(doctor.getId()).get(i).getHastaId();
			appointModel.addRow(appointData);
		}

		setResizable(false);
		setTitle("Hastane Y\u00F6netim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoþ geldiniz Sayýn " + doctor.getName());
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 24));
		lblNewLabel.setBounds(10, 11, 350, 33);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI lgnGui = new LoginGUI();
				lgnGui.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(625, 11, 99, 38);
		contentPane.add(btnNewButton);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBackground(SystemColor.inactiveCaption);
		w_tab.setBounds(10, 84, 714, 416);
		contentPane.add(w_tab);

		JPanel w_workhour = new JPanel();
		w_tab.addTab("Çalýþma Saatleri", new ImageIcon(getClass().getResource("worktime.png")), w_workhour, null);
		w_workhour.setLayout(null);

		JDateChooser dateSelect = new JDateChooser();
		dateSelect.setBounds(25, 11, 184, 30);
		w_workhour.add(dateSelect);

		JComboBox cb_selectTime = new JComboBox();
		cb_selectTime.setBounds(219, 11, 70, 30);
		w_workhour.add(cb_selectTime);
		cb_selectTime.setFont(new Font("Tw Cen MT", Font.PLAIN, 16));
		cb_selectTime.setModel(new DefaultComboBoxModel(new String[] { "10.00", "10.30", "11.00", "11.30", "12.00",
				"12.30", "13.00", "13.30", "14.00", "14.30", "15.00", "15.30", "16.00" }));

		JButton btn_addWhour = new JButton("Ekle");
		btn_addWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = "";
				try {
					date = sdf.format(dateSelect.getDate());

				} catch (Exception e2) {
					// TODO: handle exception
				}
				if (date.length() == 0) {
					Yardimci.showMsg("Lütfen geçerli bir tarih giriniz!");
				} else {

					String time = " " + cb_selectTime.getSelectedItem().toString();
					String selectDate = date + time;
					boolean control = doctor.addWhour(doctor.getId(), doctor.getName(), selectDate);
					if (control) {
						Yardimci.showMsg("success");
						try {
							updateWhourModel(doctor);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						Yardimci.showMsg("error");
					}

				}
			}
		});
		btn_addWhour.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 22));
		btn_addWhour.setBackground(Color.WHITE);
		btn_addWhour.setBounds(298, 11, 108, 30);
		w_workhour.add(btn_addWhour);

		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(0, 52, 709, 318);
		w_workhour.add(w_scrollWhour);

		table_whour = new JTable(whourModel);
		w_scrollWhour.setViewportView(table_whour);

		JButton btn_deleteWhour = new JButton("Sil");
		btn_deleteWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int slctRow = table_whour.getSelectedRow();
				if (slctRow >= 0) {
					int slctID = Integer.parseInt(table_whour.getModel().getValueAt(slctRow, 0).toString());
					boolean control;
					try {
						control = doctor.deleteWhour(slctID);
						if (control) {
							Yardimci.showMsg("success");
							updateWhourModel(doctor);

						} else {
							Yardimci.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					Yardimci.showMsg("Lütfen bir tarih seçiniz");
				}
			}
		});
		btn_deleteWhour.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 22));
		btn_deleteWhour.setBackground(Color.WHITE);
		btn_deleteWhour.setBounds(591, 11, 108, 30);
		w_workhour.add(btn_deleteWhour);

		JPanel w_appointment = new JPanel();
		w_tab.addTab("Randevular", new ImageIcon(HastaGUI.class.getResource("/View/personal.png")), w_appointment,
				null);
		w_appointment.setLayout(null);

		JScrollPane w_scrollAppoint = new JScrollPane();
		w_scrollAppoint.setBounds(0, 52, 709, 318);
		w_appointment.add(w_scrollAppoint);

		table_appoint = new JTable(appointModel);
		w_scrollAppoint.setViewportView(table_appoint);

		JButton btn_deleteAppoint = new JButton("Sil");
		btn_deleteAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int slctRow = table_appoint.getSelectedRow();
				if (slctRow >= 0) {
					int slctID = Integer.parseInt(table_appoint.getModel().getValueAt(slctRow, 0).toString());
					String date = table_appoint.getModel().getValueAt(slctRow, 2).toString();
					System.out.println(slctID + "-");
					boolean control;
					try {
						control = doctor.deleteAppoint(slctID);
						if (control) {
							Yardimci.showMsg("success");
							updateAppointModel(doctor.getId());
							appoint.updateActiveWhourStatus(doctor.getId(), date);

						} else {
							Yardimci.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					Yardimci.showMsg("Lütfen bir tarih seçiniz");
				}

			}
		});
		btn_deleteAppoint.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 22));
		btn_deleteAppoint.setBackground(Color.WHITE);
		btn_deleteAppoint.setBounds(591, 11, 108, 30);
		w_appointment.add(btn_deleteAppoint);

	}

	public void updateWhourModel(Doctor doctor) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < doctor.getWhourList(doctor.getId()).size(); i++) {
			whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();
			whourModel.addRow(whourData);
		}
	}

	public void updateAppointModel(int doctor_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_appoint.getModel();
		clearModel.setRowCount(0);

		for (int i = 0; i < appoint.getDoctorAppointList(doctor_id).size(); i++) {
			appointData[0] = appoint.getDoctorAppointList(doctor_id).get(i).getId();
			appointData[1] = appoint.getDoctorAppointList(doctor_id).get(i).getHastaName();
			appointData[2] = appoint.getDoctorAppointList(doctor_id).get(i).getAppDate();
			appointData[3] = appoint.getDoctorAppointList(doctor_id).get(i).getHastaId();

			appointModel.addRow(appointData);

		}

	}
}
