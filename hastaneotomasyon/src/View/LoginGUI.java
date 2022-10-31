package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;
import Model.Bashekim;
import Model.Doctor;
import Model.Hasta;
import Model.HastaGUI;
import Model.User;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_hastaTc;
	private JTextField text_doktorTc;
	private JPasswordField fld_doktorPw;
	private JPasswordField fld_hastaPw;
	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	 */
	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Y\u00F6netim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 498, 400);

		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lbl_icon = new JLabel(new ImageIcon(getClass().getResource("hastaneicon.png")));
		lbl_icon.setBounds(218, 11, 58, 50);
		w_pane.add(lbl_icon);

		JLabel lblNewLabel = new JLabel("Hastane Y\u00F6netim Sistemine Ho\u015F Geldiniz");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 24));
		lblNewLabel.setBounds(100, 72, 295, 35);
		w_pane.add(lblNewLabel);

		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 127, 462, 223);
		w_pane.add(w_tabpane);

		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBorder(UIManager.getBorder("EditorPane.border"));
		w_hastaLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Hasta Giriþi", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);

		JLabel lblTC = new JLabel("T.C. Numaras\u0131:");
		lblTC.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 24));
		lblTC.setBounds(25, 23, 116, 35);
		w_hastaLogin.add(lblTC);

		JLabel lblsifre = new JLabel("\u015Eifre:");
		lblsifre.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 24));
		lblsifre.setBounds(25, 69, 116, 35);
		w_hastaLogin.add(lblsifre);

		fld_hastaTc = new JTextField();
		fld_hastaTc.setForeground(Color.DARK_GRAY);
		fld_hastaTc.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		fld_hastaTc.setBounds(151, 22, 274, 35);
		w_hastaLogin.add(fld_hastaTc);
		fld_hastaTc.setColumns(10);

		JButton btnKayit = new JButton("Kay\u0131t Ol");
		btnKayit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rgstrGUI = new RegisterGUI();
				rgstrGUI.setVisible(true);
				dispose();
			}
		});
		btnKayit.setIcon(new ImageIcon(LoginGUI.class.getResource("/View/signup.png")));
		btnKayit.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 24));
		btnKayit.setBounds(25, 129, 191, 43);
		w_hastaLogin.add(btnKayit);

		JButton btnGiris_hasta = new JButton("Giri\u015F Yap");
		btnGiris_hasta.setIcon(new ImageIcon(LoginGUI.class.getResource("/View/login.png")));
		btnGiris_hasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_hastaTc.getText().length() == 0 || fld_hastaPw.getText().length() == 0) {
					Yardimci.showMsg("!dolu");
				} else {
					boolean key = true;
					try {
						Connection con = conn.connDB();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM [user]");

						while (rs.next()) {

							if (fld_hastaTc.getText().equals(rs.getString("tcno"))
									&& fld_hastaPw.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("hasta")) {
									
									System.out.println("BÝLGÝ EÞLEÞTÝ");
									Hasta hasta = new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setPassword("password");
									hasta.setTcno(rs.getString("tcno"));
									hasta.setName(rs.getString("name"));
									hasta.setType(rs.getString("type"));
									HastaGUI hastaGUI = new HastaGUI(hasta);
									hastaGUI.setVisible(true);
									dispose();
									key = false;
								

								}
							}
						}

					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
						System.out.println("hasta login catch");
					}

					if (key) {
						Yardimci.showMsg("Böyle bir kullanýcý bulunamadý!");
					}
				}

			}
		});
		btnGiris_hasta.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 24));
		btnGiris_hasta.setBounds(234, 129, 191, 43);
		w_hastaLogin.add(btnGiris_hasta);

		fld_hastaPw = new JPasswordField();
		fld_hastaPw.setBounds(151, 69, 274, 35);
		w_hastaLogin.add(fld_hastaPw);

		JPanel w_doktorLogin = new JPanel();
		w_doktorLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Doktor Giriþi", null, w_doktorLogin, null);
		w_doktorLogin.setLayout(null);

		JLabel lblTC_doktor = new JLabel("T.C. Numaras\u0131:");
		lblTC_doktor.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 24));
		lblTC_doktor.setBounds(25, 23, 116, 35);
		w_doktorLogin.add(lblTC_doktor);

		text_doktorTc = new JTextField();
		text_doktorTc.setForeground(Color.DARK_GRAY);
		text_doktorTc.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		text_doktorTc.setColumns(10);
		text_doktorTc.setBounds(151, 24, 274, 35);
		w_doktorLogin.add(text_doktorTc);

		JLabel lblsifre_doktor = new JLabel("\u015Eifre:");
		lblsifre_doktor.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 24));
		lblsifre_doktor.setBounds(25, 69, 116, 35);
		w_doktorLogin.add(lblsifre_doktor);

		JButton btnGiris_doktor = new JButton("Giri\u015F Yap");
		btnGiris_doktor.setIcon(new ImageIcon(LoginGUI.class.getResource("/View/login.png")));
		btnGiris_doktor.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				boolean cntrl = true;
				if (text_doktorTc.getText().length() == 0 || fld_doktorPw.getText().length() == 0) {
					Yardimci.showMsg("!dolu");
				} else {

					try {
						Connection con = conn.connDB();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM [user]");

						while (rs.next()) {

							if (text_doktorTc.getText().equals(rs.getString("tcno"))
									&& fld_doktorPw.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("baþhekim")) {
									System.out.println("BÝLGÝ EÞLEÞTÝ");
									Bashekim bhekim = new Bashekim();
									bhekim.setId(rs.getInt("id"));
									bhekim.setPassword("password");
									bhekim.setTcno(rs.getString("tcno"));
									bhekim.setName(rs.getString("name"));
									bhekim.setType(rs.getString("type"));
									BashekimGUI bshkmGUI = new BashekimGUI(bhekim);
									bshkmGUI.setVisible(true);
									dispose();
									cntrl = false;

								}

								if (rs.getString("type").equals("doktor")) {
									Doctor doctor = new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setPassword("password");
									doctor.setTcno(rs.getString("tcno"));
									doctor.setName(rs.getString("name"));
									doctor.setType(rs.getString("type"));
									DoctorGUI doctorGUI = new DoctorGUI(doctor);
									doctorGUI.setVisible(true);
									dispose();
									cntrl = false;

								}
							} else {
								System.out.println("Bilgi uyuþmadý if içine girilmedi");

							}

						}
						if (cntrl) {
							Yardimci.showMsg("YANLIÞ KULLANICI ADI VEYA ÞÝFRE GÝRDÝNÝZ!");
						}

					} catch (SQLException | IOException e1) {
						System.out.println("CATCHE DÜÞTÜ");
						e1.printStackTrace();
					}

				}

			}
		});
		btnGiris_doktor.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 24));
		btnGiris_doktor.setBounds(25, 129, 400, 43);
		w_doktorLogin.add(btnGiris_doktor);

		fld_doktorPw = new JPasswordField();
		fld_doktorPw.setBounds(151, 72, 274, 35);
		w_doktorLogin.add(fld_doktorPw);
	}
}
