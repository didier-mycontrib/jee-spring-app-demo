package org.mycontrib.backend.service;

import java.util.List;

import org.mycontrib.backend.dao.DeviseDao;
import org.mycontrib.backend.entity.Devise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeviseServiceImpl implements DeviseService {
	
	@Autowired
	private DeviseDao deviseDao;

	@Override
	public Devise saveOrUpdate(Devise d) {
		return deviseDao.save(d);
	}

	@Override
	public Devise deviseByCode(String code) {
		return deviseDao.findById(code).orElse(null);
	}

	@Override
	public List<Devise> getAllDevises() {
		return (List<Devise>) deviseDao.findAll();
	}

	@Override
	public void deleteByCode(String code) {
		deviseDao.deleteById(code);
	}

}
