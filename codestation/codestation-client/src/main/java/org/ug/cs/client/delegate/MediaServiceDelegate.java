package org.ug.cs.client.delegate;

import java.util.List;

import org.ug.cs.client.locator.ServiceLocator;
import org.ug.cs.persistence.Media;
import org.ug.cs.services.interfaces.MediaServiceRemote;

public class MediaServiceDelegate {

	private static final String EAR = "codestation-ear";
	private static final String EJB_MODULE = "codestation-ejb";
	private static final String SERVICE_NAME = "MediaService";

	private static MediaServiceRemote proxy = null;

	public static void load() {
		proxy = (MediaServiceRemote) ServiceLocator.getInstance().getProxy(EAR,
				EJB_MODULE, SERVICE_NAME, MediaServiceRemote.class);
	}

	public static void create(Media media) {
		proxy.create(media);
	}

	public static Media findById(Integer id) {
		return proxy.findById(id);
	}

	public static List<Media> findAll() {
		return proxy.findAll();
	}

	public static Long count() {
		return proxy.count();
	}

	public static void update(Media media) {
		proxy.update(media);
	}

	public static void remove(Media media) {
		proxy.remove(media);
	}

}
