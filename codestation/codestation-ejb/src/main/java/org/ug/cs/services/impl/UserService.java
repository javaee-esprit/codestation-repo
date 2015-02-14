package org.ug.cs.services.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ug.cs.persistence.User;
import org.ug.cs.services.interfaces.UserServiceLocal;
import org.ug.cs.services.interfaces.UserServiceRemote;

@Stateless
@Remote(UserServiceRemote.class)
@Local(UserServiceLocal.class)
public class UserService implements UserServiceRemote, UserServiceLocal {
	
	@PersistenceContext
	private EntityManager em;

    public UserService() {
    }

    @TransactionAttribute(TransactionAttributeType.NEVER)
    public Boolean isLoginInUse(String login) {
    	return em
    			.createNamedQuery("isLoginInUse", Boolean.class)
    			.setParameter("x", login)
    			.getSingleResult();
    }
    
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public List<User> findAllUsers() {
    	return em
    			.createQuery("from User", User.class)
    			.getResultList();
    }
    
	public User create(User user) {
		User persistent = null;
		if(!isLoginInUse(user.getLogin())){
			em.persist(user);
			persistent = user;
		}
		return persistent;
	}



}
