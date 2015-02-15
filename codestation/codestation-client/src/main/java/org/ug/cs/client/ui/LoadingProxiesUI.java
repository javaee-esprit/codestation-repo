package org.ug.cs.client.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

public class LoadingProxiesUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadingProxiesUI frame = new LoadingProxiesUI();
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
	public LoadingProxiesUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 447, 105);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnNewButton = new JButton("load proxies");
		final JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(0);
		
		final JLabel progressLabel = new JLabel("loading...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ClassPath classPath = ClassPath.from(getClass().getClassLoader());
					
					ImmutableSet<ClassInfo> topLevelClasses = classPath.getTopLevelClasses("org.ug.cs.client.delegate");
					int size = topLevelClasses.size();
					int i = 0;
					for (ClassInfo classInfo : topLevelClasses) {
							progressLabel.setText("loading "+ classInfo.getSimpleName()+ " ...");
							Class<?> clazz = Class.forName(classInfo.getName());
							clazz.getMethod("load").invoke(null);
							int percent = (int) (((float)++i/size)*100);
							progressBar.setValue(percent);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane
				.createParallelGroup(Alignment.LEADING)
				.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 431,
						Short.MAX_VALUE)
				.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 431,
						Short.MAX_VALUE)
				.addComponent(progressLabel, GroupLayout.DEFAULT_SIZE, 429,
						Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPane
						.createSequentialGroup()
						.addComponent(btnNewButton)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(progressBar, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 9,
								Short.MAX_VALUE).addComponent(progressLabel)));
		contentPane.setLayout(gl_contentPane);
	}
}
