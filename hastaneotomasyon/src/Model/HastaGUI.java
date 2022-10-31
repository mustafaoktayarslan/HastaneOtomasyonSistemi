package Model;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import Helper.Item;
import Helper.Yardimci;
import View.BashekimGUI;
import View.LoginGUI;

import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class HastaGUI extends JFrame {

	private JPanel w_pane;
	private static Hasta hasta = new Hasta();
	private Clinic clinic = new Clinic();
	private JTable table_doctor;
	private DefaultTableModel doctorModel;
	private Object[] doctorData = null;
	private JTable table_whour;
	private Whour whour =new Whour();
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	private int selectDoctorID=0;
	private String selectDoctorName=null;
	private JTable table_myappoint;
	private DefaultTableModel appointModel;
	private Object[] appointData = null;
	private Appointment appoint=new Appointment();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaGUI frame = new HastaGUI(hasta);
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
	 * @throws SQLException
	 */
	public HastaGUI(Hasta hasta) throws SQLException {
		//doctor MODEL
		doctorModel = new DefaultTableModel();
		Object[] colDoctor = new Object[2];
		colDoctor[0] = "ID";
		colDoctor[1] = "Ad Soyad";
		doctorModel.setColumnIdentifiers(colDoctor);
		doctorData=new Object[2];
		
		//whour model
		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData=new Object[2];
		
		//appoint model
		appointModel = new DefaultTableModel();
		Object[] colAppoint = new Object[4];
		colAppoint[0] = "Randevu ID";
		colAppoint[1] = "Doktar Adý";
		colAppoint[2] = "Tarih";
		colAppoint[3] = "Doktor ID";
		appointModel.setColumnIdentifiers(colAppoint);
		appointData=new Object[4];
		for(int i=0; i<appoint.getHastaList(hasta.getId()).size();i++) {
			appointData[0]=appoint.getHastaList(hasta.getId()).get(i).getId();
			appointData[1]=appoint.getHastaList(hasta.getId()).get(i).getDoctorName();
			appointData[2]=appoint.getHastaList(hasta.getId()).get(i).getAppDate();
			appointData[3]=appoint.getHastaList(hasta.getId()).get(i).getDoctorId();
			appointModel.addRow(appointData);
		}
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 580);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoþ geldiniz Sayýn " + hasta.getName());
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 24));
		lblNewLabel.setBounds(10, 11, 350, 33);
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Çýkýþ Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI lgnGui=new LoginGUI();
				lgnGui.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(635, 11, 89, 23);
		w_pane.add(btnNewButton);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBackground(SystemColor.inactiveCaption);
		w_tab.setBounds(10, 74, 714, 456);
		w_pane.add(w_tab);

		JPanel w_appointment = new JPanel();
		w_tab.addTab("Randevu Sistemi", new ImageIcon(BashekimGUI.class.getResource("/View/appointment.png")), w_appointment, null);
		w_appointment.setLayout(null);

		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 58, 255, 352);
		w_appointment.add(w_scrollDoctor);

		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);

		JLabel lblTC_doktor = new JLabel("Doktor Listesi");
		lblTC_doktor.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 24));
		lblTC_doktor.setBounds(10, 21, 111, 35);
		w_appointment.add(lblTC_doktor);

		JLabel lblPoli = new JLabel("Poliklnik Ad\u0131");
		lblPoli.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
		lblPoli.setBounds(275, 59, 116, 35);
		w_appointment.add(lblPoli);

		JComboBox select_clinic = new JComboBox();
		select_clinic.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 18));
		select_clinic.setBounds(275, 94, 155, 35);
		w_appointment.add(select_clinic);
		select_clinic.addItem("-Poliklinik Seç-");
		for (int i = 0; i < clinic.getClinicList().size(); i++) {
			select_clinic
					.addItem(new Item(clinic.getClinicList().get(i).getId(), clinic.getClinicList().get(i).getName()));

		}
		select_clinic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (select_clinic.getSelectedIndex() != 0) {
					JComboBox c = (JComboBox) e.getSource();
					Item item = (Item) c.getSelectedItem();
					System.out.println(item.getKey() + "-" + item.getValue());
					DefaultTableModel clearModel=(DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
					try {
						for(int i=0;i<clinic.getClinicDoctorList(item.getKey()).size();i++) {
							doctorData[0]= clinic.getClinicDoctorList(item.getKey()).get(i).getId();
							doctorData[1]= clinic.getClinicDoctorList(item.getKey()).get(i).getName();
							doctorModel.addRow(doctorData);
						}
					} catch (SQLException e1) {
				
						e1.printStackTrace();
					}
					
				}
				else {
					DefaultTableModel clearModel=(DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
				
				}
			}
		});
		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(444, 58, 255, 352);
		w_appointment.add(w_scrollWhour);
		
		table_whour = new JTable(whourModel);
		w_scrollWhour.setViewportView(table_whour);
		table_whour.getColumnModel().getColumn(0).setPreferredWidth(5);
		table_whour.setEnabled(true);
		
		
		JButton btn_slctDoctor = new JButton("Se\u00E7");
		btn_slctDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int slctRow =table_doctor.getSelectedRow();
				if(slctRow>=0) {
					int slctID = Integer.parseInt(table_doctor.getModel().getValueAt(slctRow, 0).toString());
					boolean control;
					DefaultTableModel clearModel=(DefaultTableModel) table_whour.getModel();
					clearModel.setRowCount(0);
					try { 
						for(int i=0; i<whour.getWhourList(slctID).size();i++) {
							whourData[0]=whour.getWhourList(slctID).get(i).getId();
							whourData[1]=whour.getWhourList(slctID).get(i).getWdate();
							whourModel.addRow(whourData);
							
							
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					selectDoctorID=slctID;
					selectDoctorName=table_doctor.getModel().getValueAt(slctRow,1).toString();
					System.out.println(selectDoctorID+"-"+selectDoctorName);
				
				
				}
				else {
					Yardimci.showMsg("Lütfen bir doktor seçiniz");
				}
			}
		});
		btn_slctDoctor.setIcon(new ImageIcon(HastaGUI.class.getResource("/View/selectdoctor.png")));
		btn_slctDoctor.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 22));
		btn_slctDoctor.setBackground(Color.WHITE);
		btn_slctDoctor.setBounds(275, 192, 155, 40);
		w_appointment.add(btn_slctDoctor);
		
		JLabel lblDoktorSe = new JLabel("Doktor Se\u00E7");
		lblDoktorSe.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
		lblDoktorSe.setBounds(275, 152, 116, 35);
		w_appointment.add(lblDoktorSe);
		
		JLabel lblTC_doktor_1 = new JLabel("Randevu Tarihleri");
		lblTC_doktor_1.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 24));
		lblTC_doktor_1.setBounds(444, 21, 149, 35);
		w_appointment.add(lblTC_doktor_1);
		
		JLabel lblRandevu = new JLabel("Randevu");
		lblRandevu.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
		lblRandevu.setBounds(275, 268, 116, 35);
		w_appointment.add(lblRandevu);
		
		JButton btn_addAppoint = new JButton("Randevu Al");
		btn_addAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int slctRow =table_whour.getSelectedRow();
				if(slctRow>=0) {
					String date=table_whour.getModel().getValueAt(slctRow, 1).toString();
					boolean control=hasta.addAppointment(selectDoctorID, hasta.getId(), selectDoctorName, hasta.getName(),date);
						if (control) {
						Yardimci.showMsg("success");
						hasta.updatePassiveWhourStatus(selectDoctorID, date);
						try {
							updateWhourModel(selectDoctorID);
							updateAppointModel(hasta.getId());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						}
					 }
				else {
					Yardimci.showMsg("Lütfen geçerli bir tarih giriniz");
				}
				
			}
		});
		btn_addAppoint.setIcon(new ImageIcon(HastaGUI.class.getResource("/View/clinic_doctor.png")));
		btn_addAppoint.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 22));
		btn_addAppoint.setBackground(Color.WHITE);
		btn_addAppoint.setBounds(275, 308, 155, 40);
		w_appointment.add(btn_addAppoint);
		
		JPanel w_myappoint = new JPanel();
		w_tab.addTab("Randevularým",new ImageIcon(HastaGUI.class.getResource("/View/personal.png")), w_myappoint, null);
		w_myappoint.setLayout(null);
		
		JScrollPane scroll_Myappoint = new JScrollPane();
		scroll_Myappoint.setBounds(10, 55, 689, 344);
		w_myappoint.add(scroll_Myappoint);
		
		table_myappoint = new JTable(appointModel);
		scroll_Myappoint.setViewportView(table_myappoint);
		
		JButton btnRandevuSil = new JButton("Randevu Sil");
		btnRandevuSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int slctRow=table_myappoint.getSelectedRow();
				if(slctRow>=0) {
					int slctappointID=Integer.parseInt(table_myappoint.getValueAt(slctRow, 0).toString());
					String date=table_myappoint.getModel().getValueAt(slctRow, 2).toString();
					System.out.println("------"+date);
					try {
						if(hasta.deleteAppoint(slctappointID)) {
							int slctdoctorID=Integer.parseInt(table_myappoint.getValueAt(slctRow, 3).toString());
							hasta.updateActiveWhourStatus(slctdoctorID, date);
							
							Yardimci.showMsg("success");
							
							
							updateAppointModel(hasta.getId());
							
						}
						else {
							Yardimci.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
				
				
				
			}
		});
		btnRandevuSil.setIcon(new ImageIcon(HastaGUI.class.getResource("/View/delete.png")));
		btnRandevuSil.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 22));
		btnRandevuSil.setBackground(Color.WHITE);
		btnRandevuSil.setBounds(515, 4, 184, 40);
		w_myappoint.add(btnRandevuSil);
	}
	public void updateWhourModel(int doctor_id) throws SQLException{
		DefaultTableModel clearModel=(DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		
		for(int i=0; i<whour.getWhourList(doctor_id).size();i++) {
			whourData[0]=whour.getWhourList(doctor_id).get(i).getId();
			whourData[1]=whour.getWhourList(doctor_id).get(i).getWdate();
			whourModel.addRow(whourData);
			
			
		}
		
	}
	public void updateAppointModel(int hasta_id) throws SQLException{
		DefaultTableModel clearModel=(DefaultTableModel) table_myappoint.getModel();
		clearModel.setRowCount(0);
		
		for(int i=0; i<appoint.getHastaList(hasta_id).size();i++) {
			appointData[0]=appoint.getHastaList(hasta_id).get(i).getId();
			appointData[1]=appoint.getHastaList(hasta_id).get(i).getDoctorName();
			appointData[2]=appoint.getHastaList(hasta_id).get(i).getAppDate();
			appointData[3]=appoint.getHastaList(hasta_id).get(i).getDoctorId();
		
			appointModel.addRow(appointData);
			
			
		}
		
	}
	
	
}
