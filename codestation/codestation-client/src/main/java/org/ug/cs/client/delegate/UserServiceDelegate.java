package org.ug.cs.client.delegate;

import java.util.List;

import org.ug.cs.client.locator.ServiceLocator;
import org.ug.cs.persistence.User;
import org.ug.cs.services.interfaces.UserServiceRemote;

public class UserServiceDelegate {

	private static final String EAR = "codestation-ear";
	private static final String EJB_MODULE = "codestation-ejb";
	private static final String SERVICE_NAME = "UserService";

	private static UserServiceRemote getProxy() {
		return (UserServiceRemote) ServiceLocator.getInstance().getProxy(EAR,
				EJB_MODULE, SERVICE_NAME, UserServiceRemote.class);
	}

	public static Boolean isLoginInUse(String login) {
		return getProxy().isLoginInUse(login);
	}

	public static User create(User user) {
		return getProxy().create(user);
	}

	public static List<User> findAllUsers() {
		return getProxy().findAllUsers();
	}

	public static User authenticate(String login, String password) {
		return getProxy().authenticate(login, password);
	}

}
