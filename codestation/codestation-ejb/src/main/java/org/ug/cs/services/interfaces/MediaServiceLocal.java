package org.ug.cs.services.interfaces;

import java.util.List;

import org.ug.cs.persistence.Media;

public interface MediaServiceLocal {

	void create(Media media);

	void update(Media media);

	Media findById(Integer id);

	List<Media> findAll();

	Long count();

}
