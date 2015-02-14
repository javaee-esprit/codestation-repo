package org.ug.cs.services.conf;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.ug.cs.persistence.User;
import org.ug.cs.services.interfaces.UserServiceLocal;

@Singleton
@Startup
public class InitialData {

	@EJB
	private UserServiceLocal userService;

	public InitialData() {
	}

	@PostConstruct
	public void createData() {
		userService.create(new User("root", "root"));
	}

}
