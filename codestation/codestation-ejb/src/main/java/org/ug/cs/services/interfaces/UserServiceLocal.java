package org.ug.cs.services.interfaces;

import java.util.List;

import org.ug.cs.persistence.User;

public interface UserServiceLocal {

	Boolean isLoginInUse(String login);

	User create(User user);

	List<User> findAllUsers();

}
