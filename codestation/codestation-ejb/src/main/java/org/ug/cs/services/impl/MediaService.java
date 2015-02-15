package org.ug.cs.services.impl;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ug.cs.persistence.Media;
import org.ug.cs.services.AbstractManagementService;
import org.ug.cs.services.interfaces.MediaServiceLocal;
import org.ug.cs.services.interfaces.MediaServiceRemote;

@Stateless
@Remote(MediaServiceRemote.class)
@Local(MediaServiceLocal.class)
public class MediaService extends AbstractManagementService<Media, Integer> implements MediaServiceRemote, MediaServiceLocal {

	@PersistenceContext
	private EntityManager em;
	
    public MediaService() {
    	super(Media.class);
    }

	public EntityManager getEntityManager() {
		return em;
	}

}
