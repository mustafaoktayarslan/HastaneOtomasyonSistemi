package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Yardimci;
import Model.Hasta;
import Model.User;

import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel w_pane;
	private JLabel lblTC_doktor_1;
	private JLabel lblTC_doktor_2;
	private JButton btn_back;
	private JTextField fld_tc;
	private JTextField fld_name;
	private JPasswordField fld_pw;
	private Hasta hasta = new Hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setResizable(false);
		setType(Type.UTILITY);
		setTitle("Hastane Y\u00F6netim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 400);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblTC_doktor = new JLabel("Ad Soyad");
		lblTC_doktor.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 24));
		lblTC_doktor.setBounds(10, 11, 79, 35);
		w_pane.add(lblTC_doktor);

		lblTC_doktor_1 = new JLabel("T.C Numaras\u0131");
		lblTC_doktor_1.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 24));
		lblTC_doktor_1.setBounds(10, 91, 121, 35);
		w_pane.add(lblTC_doktor_1);

		lblTC_doktor_2 = new JLabel("\u015Eifre");
		lblTC_doktor_2.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 24));
		lblTC_doktor_2.setBounds(10, 171, 79, 35);
		w_pane.add(lblTC_doktor_2);

		JButton btn_register = new JButton("Kay\u0131t Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean key = true;

				if (fld_tc.getText().length() == 0 || fld_pw.getText().length() == 0
						|| fld_name.getText().length() == 0) {
					Yardimci.showMsg("!dolu");

				} else {
					char[] sayi = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
					char[] harf = { 'a', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'ð', 'h', 'ý', 'i', 'j', 'k', 'l', 'm', 'n',
							'o', 'ö', 'p', 'r', 's', 'þ', 't', 'u', 'ü', 'v', 'y', 'z', 'w', 'x' };
					for (int i = 0; i < fld_tc.getText().length(); i++) {
						for (int j = 0; j < harf.length; j++) {
							if (fld_tc.getText().charAt(i) == harf[j]) {
							
								key = false;
								break;
							}
						}
					}
					for (int i = 0; i < fld_name.getText().length(); i++) {
						for (int j = 0; j < sayi.length; j++) {
							if (fld_name.getText().charAt(i) == sayi[j]) {

								key = false;
								break;
							}
						}
					}
				}
				if (key) {
					boolean control = hasta.register(fld_tc.getText(), fld_pw.getText(), fld_name.getText());
					if (control) {
						Yardimci.showMsg("success");
						LoginGUI lgnGUI = new LoginGUI();
						lgnGUI.setVisible(true);
						dispose();

					} else {
						Yardimci.showMsg("error");

					}

				} else {
					Yardimci.showMsg("DOÐRU TC VEYA ÞÝFRE GÝRÝN");
				}

			}
		});
		btn_register.setIcon(new ImageIcon(RegisterGUI.class.getResource("/View/doctoradd.png")));
		btn_register.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 22));
		btn_register.setBackground(SystemColor.inactiveCaption);
		btn_register.setBounds(10, 260, 264, 40);
		w_pane.add(btn_register);

		btn_back = new JButton("Geri D\u00F6n");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI lgnGUI = new LoginGUI();
				lgnGUI.setVisible(true);
				dispose();
			}
		});
		btn_back.setIcon(new ImageIcon(RegisterGUI.class.getResource("/View/back.png")));
		btn_back.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 22));
		btn_back.setBackground(SystemColor.menu);
		btn_back.setBounds(10, 311, 264, 40);
		w_pane.add(btn_back);

		fld_tc = new JTextField();
		fld_tc.setForeground(Color.DARK_GRAY);
		fld_tc.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		fld_tc.setColumns(10);
		fld_tc.setBounds(10, 122, 264, 35);
		w_pane.add(fld_tc);

		fld_name = new JTextField();
		fld_name.setForeground(Color.DARK_GRAY);
		fld_name.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		fld_name.setColumns(10);
		fld_name.setBounds(10, 43, 264, 35);
		w_pane.add(fld_name);

		fld_pw = new JPasswordField();
		fld_pw.setBounds(10, 203, 264, 35);
		w_pane.add(fld_pw);
	}
}
