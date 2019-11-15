package org.mycontrib.backend.service;

import java.util.List;

import org.mycontrib.backend.entity.Devise;

public interface DeviseService {
	Devise saveOrUpdate(Devise d);
	Devise deviseByCode(String code);
	List<Devise> getAllDevises();
	void deleteByCode(String code);
}
