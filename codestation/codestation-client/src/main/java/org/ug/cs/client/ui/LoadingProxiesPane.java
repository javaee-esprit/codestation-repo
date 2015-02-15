package org.ug.cs.client.ui;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import java.awt.Color;

public class LoadingProxiesPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel progressLabel;

	/**
	 * Create the frame.
	 */
	public LoadingProxiesPane() {

		setSize(300, 120);
		setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null,
				null));

		progressLabel = new JLabel();

		JLabel imageLabel = new JLabel();
		imageLabel.setIcon(new ImageIcon("loading.gif"));;
		imageLabel.setBackground(Color.DARK_GRAY);
		
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(100)
							.addComponent(imageLabel, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(82)
							.addComponent(progressLabel, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(84, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addComponent(imageLabel, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(progressLabel)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		imageLabel.setSize(80, 80);
		setLayout(gl_contentPane);

	}

	public JLabel getProgressLabel() {
		return progressLabel;
	}

	public void setProgressLabel(JLabel progressLabel) {
		this.progressLabel = progressLabel;
	}

}
