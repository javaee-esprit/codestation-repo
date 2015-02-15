package org.ug.cs.services.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ug.cs.persistence.Media;
import org.ug.cs.services.AbstractManagementService;
import org.ug.cs.services.interfaces.MediaServiceLocal;
import org.ug.cs.services.interfaces.MediaServiceRemote;

@Stateless
@Remote(MediaServiceRemote.class)
@Local(MediaServiceLocal.class)
public class MediaService extends AbstractManagementService<Media, Integer>
		implements MediaServiceRemote, MediaServiceLocal {

	@PersistenceContext
	private EntityManager em;

	public MediaService() {
		super(Media.class);
	}

	public EntityManager getEntityManager() {
		return em;
	}

	@TransactionAttribute(TransactionAttributeType.NEVER)
	public List<Media> findAll() {
		return super.findAll();
	}

	@TransactionAttribute(TransactionAttributeType.NEVER)
	public Media findById(Integer id) {
		return super.findById(id);
	}

	@TransactionAttribute(TransactionAttributeType.NEVER)
	public Long count() {
		return super.count();
	}

}
