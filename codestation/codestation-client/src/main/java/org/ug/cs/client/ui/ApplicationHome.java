package org.ug.cs.client.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.ug.cs.client.util.Session;
import org.ug.cs.persistence.User;

public class ApplicationHome {

	private JFrame frmCodestationAdmininstration;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationHome window = new ApplicationHome();
					window.frmCodestationAdmininstration.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ApplicationHome() {
		initialize();
		frmCodestationAdmininstration.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCodestationAdmininstration = new JFrame();
		frmCodestationAdmininstration.setTitle("CODESTATION ADMININSTRATION");
		frmCodestationAdmininstration.setBounds(100, 100, 666, 300);
		frmCodestationAdmininstration
				.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		User user = (User) Session.getInstance().retrieve("user");
		JLabel lblLoggedInAs = new JLabel("Logged in as " + user.getLogin());
		lblLoggedInAs.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLoggedInAs.setForeground(new Color(0, 0, 0));
		GroupLayout groupLayout = new GroupLayout(
				frmCodestationAdmininstration.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				groupLayout.createSequentialGroup()
						.addContainerGap(602, Short.MAX_VALUE)
						.addComponent(lblLoggedInAs).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(lblLoggedInAs)
						.addContainerGap(248, Short.MAX_VALUE)));
		frmCodestationAdmininstration.getContentPane().setLayout(groupLayout);
	}

}
