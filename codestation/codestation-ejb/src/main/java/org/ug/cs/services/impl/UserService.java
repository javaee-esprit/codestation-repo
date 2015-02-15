package org.ug.cs.services.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ug.cs.persistence.User;
import org.ug.cs.services.interfaces.UserServiceLocal;
import org.ug.cs.services.interfaces.UserServiceRemote;

@Stateless
@Remote(UserServiceRemote.class)
@Local(UserServiceLocal.class)
public class UserService implements UserServiceRemote, UserServiceLocal {

	@Inject
	private Logger logger;

	@PersistenceContext
	private EntityManager em;

	public UserService() {
	}

	@TransactionAttribute(TransactionAttributeType.NEVER)
	public Boolean isLoginInUse(String login) {
		return em.createNamedQuery("isLoginInUse", Boolean.class)
				.setParameter("x", login).getSingleResult();
	}

	@TransactionAttribute(TransactionAttributeType.NEVER)
	public List<User> findAllUsers() {
		return em.createQuery("from User", User.class).getResultList();
	}

	public User create(User user) {
		User persistent = null;
		if (!isLoginInUse(user.getLogin())) {
			em.persist(user);
			persistent = user;
		}
		return persistent;
	}

	@TransactionAttribute(TransactionAttributeType.NEVER)
	public User authenticate(String login, String password) {
		User found = null;
		String jpql = "select u from User u where u.login=:x and u.password=:y";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query.setParameter("x", login);
		query.setParameter("y", password);
		try {
			found = query.getSingleResult();
		} catch (NoResultException e) {
			logger.log(Level.WARNING, "Authentication failure (login='" + login
					+ "', password='" + password + "')");
		}
		return found;
	}

}
