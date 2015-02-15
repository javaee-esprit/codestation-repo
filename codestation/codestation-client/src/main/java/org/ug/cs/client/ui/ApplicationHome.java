package org.ug.cs.client.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.ug.cs.client.delegate.MediaServiceDelegate;
import org.ug.cs.client.util.Session;
import org.ug.cs.persistence.Media;
import org.ug.cs.persistence.User;

public class ApplicationHome {

	private JFrame frmCodestationAdmininstration;
	private JTextField titleField;

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

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		GroupLayout groupLayout = new GroupLayout(
				frmCodestationAdmininstration.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.TRAILING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addComponent(tabbedPane,
												GroupLayout.DEFAULT_SIZE, 638,
												Short.MAX_VALUE)
										.addComponent(lblLoggedInAs,
												Alignment.TRAILING))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblLoggedInAs)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE,
								231, Short.MAX_VALUE).addContainerGap()));

		JPanel usersPanel = new JPanel();
		tabbedPane.addTab("users", null, usersPanel, null);

		JPanel mediaPanel = new JPanel();
		tabbedPane.addTab("media", null, mediaPanel, null);

		JLabel lblTitle = new JLabel("title");

		titleField = new JTextField();
		titleField.setColumns(10);

		JButton btnSave = new JButton("save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Media media = new Media();
				media.setTitle(titleField.getText());
				MediaServiceDelegate.create(media);
			}
		});
		GroupLayout gl_mediaPanel = new GroupLayout(mediaPanel);
		gl_mediaPanel.setHorizontalGroup(gl_mediaPanel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_mediaPanel
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblTitle)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(titleField, GroupLayout.PREFERRED_SIZE,
								142, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnSave)
						.addContainerGap(320, Short.MAX_VALUE)));
		gl_mediaPanel
				.setVerticalGroup(gl_mediaPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_mediaPanel
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_mediaPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblTitle)
														.addComponent(
																titleField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(btnSave))
										.addContainerGap(201, Short.MAX_VALUE)));
		mediaPanel.setLayout(gl_mediaPanel);
		frmCodestationAdmininstration.getContentPane().setLayout(groupLayout);
	}
}
