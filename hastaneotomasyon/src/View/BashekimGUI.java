package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

import com.microsoft.sqlserver.jdbc.spatialdatatypes.Point;

import Model.Bashekim;
import Model.Clinic;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.security.PublicKey;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import Helper.*;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class BashekimGUI extends JFrame {
	static Bashekim bashekim = new Bashekim();
	static Clinic clinic = new Clinic();
	private JPanel w_pane;
	private JTextField fld_dName;
	private JTextField fld_dTc;
	private JTextField fld_dpw;
	private JTextField fld_drID;
	private JTable table_doctor;
	private DefaultTableModel doctorModel;
	private Object[] doctorData = null;
	private JTable table_clinic;
	private JTextField fld_clinicName;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JPopupMenu clinicMenu;
	private JTable table_worker;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
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
	 * @throws SQLException
	 */
	public BashekimGUI(Bashekim bashekim) throws SQLException {

		// DOCTOR MODEL
		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Ad Soyad";
		colDoctorName[2] = "TC NO";
		colDoctorName[3] = "Þifre";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			System.out.println(i);

			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
		// CLÝNÝC MODEL
		clinicModel = new DefaultTableModel();
		Object[] colClinicName = new Object[2];
		colClinicName[0] = "ID";
		colClinicName[1] = "POLÝKLÝNÝK ADI";

		clinicModel.setColumnIdentifiers(colClinicName);
		clinicData = new Object[2];
		for (int i = 0; i < clinic.getClinicList().size(); i++) {
			System.out.println(i);

			clinicData[0] = clinic.getClinicList().get(i).getId();
			clinicData[1] = clinic.getClinicList().get(i).getName();
			clinicModel.addRow(clinicData);
		}

		// WORKER MODEL
		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWolker = new Object[2];
		colWolker[0] = "ID";
		colWolker[1] = "Ad Soyad";
		workerModel.setColumnIdentifiers(colWolker);
		Object[] workerData = new Object[2];

		setTitle("Hastene Y\u00F6netim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 580);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoþ Geldiniz Sayýn " + bashekim.getName());
		lblNewLabel.setBounds(10, 11, 350, 33);
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 24));
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Çýkýþ Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI lgnGui=new LoginGUI();
				lgnGui.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(626, 11, 98, 33);
		w_pane.add(btnNewButton);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.inactiveCaption);
		tabbedPane.setBounds(10, 74, 714, 456);
		w_pane.add(tabbedPane);

		JPanel panel_doctor = new JPanel();
		panel_doctor.setBackground(Color.WHITE);
		tabbedPane.addTab("Doktor Yönetimi", new ImageIcon(getClass().getResource("doctoricon.png")), panel_doctor,
				null);
		panel_doctor.setLayout(null);

		fld_dName = new JTextField();
		fld_dName.setBackground(UIManager.getColor("TextField.disabledBackground"));
		fld_dName.setForeground(Color.DARK_GRAY);
		fld_dName.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		fld_dName.setColumns(10);
		fld_dName.setBounds(515, 47, 184, 27);
		panel_doctor.add(fld_dName);

		JLabel lblAd = new JLabel("Ad Soyad");
		lblAd.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
		lblAd.setBounds(515, 11, 116, 35);
		panel_doctor.add(lblAd);

		JLabel lblTC = new JLabel("T.C. Numaras\u0131");
		lblTC.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
		lblTC.setBounds(515, 86, 116, 35);
		panel_doctor.add(lblTC);

		fld_dTc = new JTextField();
		fld_dTc.setBackground(UIManager.getColor("TextField.disabledBackground"));
		fld_dTc.setForeground(Color.DARK_GRAY);
		fld_dTc.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		fld_dTc.setColumns(10);
		fld_dTc.setBounds(515, 122, 184, 27);
		panel_doctor.add(fld_dTc);

		fld_dpw = new JTextField();
		fld_dpw.setBackground(UIManager.getColor("TextField.disabledBackground"));
		fld_dpw.setForeground(Color.DARK_GRAY);
		fld_dpw.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		fld_dpw.setColumns(10);
		fld_dpw.setBounds(515, 196, 184, 27);
		panel_doctor.add(fld_dpw);

		JLabel lblSifre = new JLabel("\u015Eifre");
		lblSifre.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
		lblSifre.setBounds(515, 160, 116, 35);
		panel_doctor.add(lblSifre);

		JButton btnEkle = new JButton("Ekle");
		btnEkle.setIcon(new ImageIcon(BashekimGUI.class.getResource("/View/doctoradd.png")));
		btnEkle.setBackground(UIManager.getColor("Button.background"));
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_dName.getText().length() == 0 || fld_dpw.getText().length() == 0
						|| fld_dTc.getText().length() == 0) {
					Yardimci.showMsg("!dolu");
				} else {
					
					
					try {
						boolean control = bashekim.addDoctor(fld_dTc.getText(), fld_dpw.getText(), fld_dName.getText());
						if (control) {

							Yardimci.showMsg("success");
							fld_dName.setText(null);
							fld_dpw.setText(null);
							fld_dTc.setText(null);
							updateDoctorModel();
						}

					} catch (Exception e2) {
						// TODO: handle exception
					}

				}

			}
		});
		btnEkle.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 22));
		btnEkle.setBounds(515, 234, 184, 40);
		panel_doctor.add(btnEkle);

		JLabel lblkullanýcýID = new JLabel("Kullan\u0131c\u0131 ID");
		lblkullanýcýID.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
		lblkullanýcýID.setBounds(515, 280, 116, 35);
		panel_doctor.add(lblkullanýcýID);

		fld_drID = new JTextField();
		fld_drID.setBackground(UIManager.getColor("TextField.disabledBackground"));
		fld_drID.setForeground(Color.DARK_GRAY);
		fld_drID.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		fld_drID.setColumns(10);
		fld_drID.setBounds(515, 314, 184, 27);
		panel_doctor.add(fld_drID);

		JButton btnSil = new JButton("Sil");
		btnSil.setIcon(new ImageIcon(BashekimGUI.class.getResource("/View/doctordelete.png")));
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_drID.getText().length() == 0) {
					Yardimci.showMsg("Lütfen geçerli bir doktor seçiniz !");
				}

				else {
					if (Yardimci.showConfirm("sure")) {
						int selecID = Integer.parseInt(fld_drID.getText());
						try {
							boolean control = bashekim.deleteDoctor(selecID);
							if (control) {
								Yardimci.showMsg("success");
								fld_drID.setText(null);
								updateDoctorModel();

							}
						} catch (Exception e2) {
							// TODO: handle exception
						}
					}

				}
			}
		});
		btnSil.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 22));
		btnSil.setBackground(UIManager.getColor("Button.highlight"));
		btnSil.setBounds(515, 358, 184, 40);
		panel_doctor.add(btnSil);

		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 11, 496, 388);
		panel_doctor.add(w_scrollDoctor);

		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);

		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_drID.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
				} catch (Exception ex) {
					// TODO: handle exception
				}

			}
		});
		table_doctor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer
							.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
					String selectName = table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString();
					String selectTcno = table_doctor.getValueAt(table_doctor.getSelectedRow(), 2).toString();
					String selectPw = table_doctor.getValueAt(table_doctor.getSelectedRow(), 3).toString();
					try {
						if (Yardimci.showConfirm("BU ÝÞLEM GERÝ ALINAMAZ GÜNCELLEMEK ÝSTER MÝSÝNÝZ")) {
							boolean control = bashekim.updateDoctor(selectID, selectTcno, selectPw, selectName);

						} else {
							updateDoctorModel();

						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		JPanel panel_clinic = new JPanel();
		tabbedPane.addTab("Poliklinikler", new ImageIcon(getClass().getResource("clinic.png")), panel_clinic, null);
		panel_clinic.setLayout(null);

		JScrollPane p_clinicScroll_l = new JScrollPane();
		p_clinicScroll_l.setBounds(10, 11, 250, 376);
		panel_clinic.add(p_clinicScroll_l);

		clinicMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);
		updateMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int slctID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
				try {
					Clinic slctClinic = clinic.getSelect(slctID);
					UpdateClinicGUI updateGUI = new UpdateClinicGUI(slctClinic);
					updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					updateGUI.setVisible(true);
					updateGUI.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(java.awt.event.WindowEvent e) {
							try {
								updateClinicModel();
							} catch (SQLException e1) {

								e1.printStackTrace();
							}
						};
					});

				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			}
		});
		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Yardimci.showConfirm("sure")) {
					int slctID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
					try {
						if (clinic.deleteClinic(slctID)) {
							Yardimci.showMsg("success");
							updateClinicModel();
						} else
							Yardimci.showMsg("error");
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}

			}
		});

		table_clinic = new JTable(clinicModel);
		table_clinic.setComponentPopupMenu(clinicMenu);
		table_clinic.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				java.awt.Point point = e.getPoint();
				int selectedRow = table_clinic.rowAtPoint(point);
				table_clinic.setRowSelectionInterval(selectedRow, selectedRow);

			}
		});

		p_clinicScroll_l.setViewportView(table_clinic);

		JLabel lblPoli = new JLabel("Poliklnik Ad\u0131");
		lblPoli.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
		lblPoli.setBounds(275, 11, 116, 35);
		panel_clinic.add(lblPoli);

		fld_clinicName = new JTextField();
		fld_clinicName.setForeground(Color.DARK_GRAY);
		fld_clinicName.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		fld_clinicName.setColumns(10);
		fld_clinicName.setBackground(SystemColor.menu);
		fld_clinicName.setBounds(275, 47, 155, 27);
		panel_clinic.add(fld_clinicName);

		JButton btnEkle_1 = new JButton("  Ekle");
		btnEkle_1.setIcon(new ImageIcon(BashekimGUI.class.getResource("/View/clinicadd1.png")));
		btnEkle_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_clinicName.getText().length() == 0) {
					Yardimci.showMsg("!dolu");
				} else {
					try {
						if (clinic.addClinic(fld_clinicName.getText())) {

							Yardimci.showMsg("success");
							fld_clinicName.setText(null);
							updateClinicModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btnEkle_1.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 22));
		btnEkle_1.setBackground(Color.WHITE);
		btnEkle_1.setBounds(275, 85, 155, 40);
		panel_clinic.add(btnEkle_1);

		JScrollPane p_clinicScroll_r = new JScrollPane();
		p_clinicScroll_r.setBounds(449, 11, 250, 376);
		panel_clinic.add(p_clinicScroll_r);

		table_worker = new JTable();
		p_clinicScroll_r.setViewportView(table_worker);

		JComboBox select_doctor = new JComboBox();
		select_doctor.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 18));
		select_doctor.setBounds(275, 286, 155, 27);

		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			select_doctor.addItem(
					new Item(bashekim.getDoctorList().get(i).getId(), bashekim.getDoctorList().get(i).getName()));

		}
		select_doctor.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() + " : " + item.getValue());
		});
		panel_clinic.add(select_doctor);

		JButton btn_addworker = new JButton("Ekle");
		btn_addworker.setIcon(new ImageIcon(BashekimGUI.class.getResource("/View/clinic_doctor.png")));
		btn_addworker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int slctRow = table_clinic.getSelectedRow();
				if (slctRow >= 0) {
					String slctClinic = table_clinic.getModel().getValueAt(slctRow, 0).toString();
					int slctClinicID = Integer.parseInt(slctClinic);
					Item doctorItem = (Item) select_doctor.getSelectedItem();
					try {
						boolean control = bashekim.addWorker(doctorItem.getKey(), slctClinicID);
						if (control) {
							Yardimci.showMsg("success");
							DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
							clearModel.setRowCount(0);

							for (int i = 0; i < bashekim.getClinicDoctorList(slctClinicID).size(); i++) {
								workerData[0] = bashekim.getClinicDoctorList(slctClinicID).get(i).getId();
								workerData[1] = bashekim.getClinicDoctorList(slctClinicID).get(i).getName();

								workerModel.addRow(workerData);
							}

							table_worker.setModel(workerModel);
						}

						else {
							Yardimci.showMsg("error");

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					Yardimci.showMsg("LÜTFEN BÝR POLÝKLÝNÝK SEÇÝN");
				}
			}
		});
		btn_addworker.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 22));
		btn_addworker.setBackground(Color.WHITE);
		btn_addworker.setBounds(275, 324, 155, 40);
		panel_clinic.add(btn_addworker);

		JLabel lblPoli_1 = new JLabel("Poliklinik Bilgileri");
		lblPoli_1.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
		lblPoli_1.setBounds(275, 143, 149, 35);
		panel_clinic.add(lblPoli_1);

		JButton btn_workerSelect = new JButton("G\u00F6ster");
		btn_workerSelect.setIcon(new ImageIcon(BashekimGUI.class.getResource("/View/clinicshow.png")));
		btn_workerSelect.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int slctRow = table_clinic.getSelectedRow();
				if (slctRow >= 0) {
					String slctClinic = table_clinic.getModel().getValueAt(slctRow, 0).toString();
					int slctClinicID = Integer.parseInt(slctClinic);
					DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < bashekim.getClinicDoctorList(slctClinicID).size(); i++) {
							workerData[0] = bashekim.getClinicDoctorList(slctClinicID).get(i).getId();
							workerData[1] = bashekim.getClinicDoctorList(slctClinicID).get(i).getName();
							workerModel.addRow(workerData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_worker.setModel(workerModel);

				} else
					Yardimci.showMsg("Lütfen bir poliklinik seçiniz!");
			}
		});
		btn_workerSelect.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 22));
		btn_workerSelect.setBackground(Color.WHITE);
		btn_workerSelect.setBounds(275, 178, 155, 40);
		panel_clinic.add(btn_workerSelect);

		JLabel lblPoli_1_1 = new JLabel("Poliklinik ve Doktor");
		lblPoli_1_1.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
		lblPoli_1_1.setBounds(275, 251, 149, 35);
		panel_clinic.add(lblPoli_1_1);
	}

	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			System.out.println(i);

			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}

	}

	public void updateClinicModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_clinic.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < clinic.getClinicList().size(); i++) {
			System.out.println(i);

			clinicData[0] = clinic.getClinicList().get(i).getId();
			clinicData[1] = clinic.getClinicList().get(i).getName();

			clinicModel.addRow(clinicData);
		}

	}
}