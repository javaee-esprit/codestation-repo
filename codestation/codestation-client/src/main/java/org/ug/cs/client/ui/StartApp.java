package org.ug.cs.client.ui;

import javax.swing.JFrame;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

public class StartApp extends JFrame {

	private static final long serialVersionUID = 3887568292371543647L;

	private LoadingProxiesPane loadingProxiesPane;
	private AuthenticationPane authenticationPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		StartApp frame = new StartApp();
		frame.setVisible(true);
		frame.launch();

	}

	/**
	 * Create the frame.
	 */
	public StartApp() {
		setSize(310, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loadingProxiesPane = new LoadingProxiesPane();
		loadingProxiesPane.setVisible(true);
		setContentPane(loadingProxiesPane);

	}

	public void launch() {
		try {
			ClassPath classPath = ClassPath.from(getClass().getClassLoader());

			ImmutableSet<ClassInfo> topLevelClasses = classPath
					.getTopLevelClasses("org.ug.cs.client.delegate");
			for (ClassInfo classInfo : topLevelClasses) {
				loadingProxiesPane.getProgressLabel().setText(
						classInfo.getSimpleName());
				Class<?> clazz = Class.forName(classInfo.getName());
				clazz.getMethod("load").invoke(null);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		loadingProxiesPane.setVisible(false);
		authenticationPane = new AuthenticationPane();
		authenticationPane.setVisible(true);
		setContentPane(authenticationPane);
	}

}
