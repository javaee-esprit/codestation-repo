package org.ug.cs.client.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.ug.cs.client.delegate.UserServiceDelegate;
import org.ug.cs.client.util.Session;
import org.ug.cs.persistence.User;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class AuthenticationPane extends JPanel {

	private static final long serialVersionUID = -4810801674114292800L;

	private JTextField loginField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthenticationPane frame = new AuthenticationPane();
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
	public AuthenticationPane() {
		setSize(300,120);
		this.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Authentication",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_this = new GroupLayout(this);
		gl_this.setHorizontalGroup(gl_this.createParallelGroup(
				Alignment.LEADING).addComponent(panel,
				GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE));
		gl_this.setVerticalGroup(gl_this.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_this.createSequentialGroup()
								.addComponent(panel,
										GroupLayout.PREFERRED_SIZE, 115,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(115, Short.MAX_VALUE)));

		JLabel lblLogin = new JLabel("login");

		loginField = new JTextField();
		loginField.setColumns(10);

		JLabel lblPassword = new JLabel("password");

		passwordField = new JPasswordField();
		passwordField.setColumns(10);

		final JLabel errorLabel = new JLabel();
		errorLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		errorLabel.setForeground(Color.RED);

		JButton btnConnect = new JButton("connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = UserServiceDelegate.authenticate(
						loginField.getText(),
						String.valueOf(passwordField.getPassword()));
				if (user == null) {
					errorLabel.setText("bad credentials");
				} else {
					Session.getInstance().store("user", user);
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								new ApplicationHome();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		});

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addComponent(
																						lblPassword)
																				.addComponent(
																						lblLogin))
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addComponent(
																						loginField,
																						GroupLayout.DEFAULT_SIZE,
																						238,
																						Short.MAX_VALUE)
																				.addComponent(
																						passwordField,
																						GroupLayout.DEFAULT_SIZE,
																						238,
																						Short.MAX_VALUE)))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		btnConnect)
																.addGap(18)
																.addComponent(
																		errorLabel)))
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblLogin)
												.addComponent(
														loginField,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblPassword)
												.addComponent(
														passwordField,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(btnConnect)
												.addComponent(errorLabel))
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		this.setLayout(gl_this);
	}
}
