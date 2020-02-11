package org.mycontrib.ext.devises.service;

import java.util.List;

import org.mycontrib.ext.devises.entity.Devise;

public interface DeviseService {
	Devise saveOrUpdate(Devise d);
	Devise deviseByCode(String code);
	List<Devise> getAllDevises();
	void deleteByCode(String code);
}
