package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Yardimci;
import Model.Clinic;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class UpdateClinicGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_clinicName;
	private static Clinic clinic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClinicGUI frame = new UpdateClinicGUI(clinic);
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
	public UpdateClinicGUI(Clinic clinic) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 230, 170);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPoliklinikAd = new JLabel("Poliklinik Ad\u0131");
		lblPoliklinikAd.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
		lblPoliklinikAd.setBounds(10, 11, 116, 35);
		contentPane.add(lblPoliklinikAd);

		fld_clinicName = new JTextField();
		fld_clinicName.setForeground(Color.DARK_GRAY);
		fld_clinicName.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		fld_clinicName.setColumns(10);
		fld_clinicName.setBackground(SystemColor.menu);
		fld_clinicName.setBounds(10, 47, 184, 27);
		fld_clinicName.setText(clinic.getName());

		contentPane.add(fld_clinicName);

		JButton btn_update = new JButton("D\u00FCzenle");
		btn_update.setIcon(new ImageIcon(UpdateClinicGUI.class.getResource("/View/clinic_doctor.png")));
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Yardimci.showConfirm("sure")) {
					try {
						clinic.updateClinic(clinic.getId(), fld_clinicName.getText());
						Yardimci.showMsg("success");
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btn_update.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 22));
		btn_update.setBackground(Color.WHITE);
		btn_update.setBounds(10, 85, 184, 40);
		contentPane.add(btn_update);
	}

}
