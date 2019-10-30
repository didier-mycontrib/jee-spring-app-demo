package org.mycontrib.api.service;

import org.mycontrib.api.dao.NewsDao;
import org.mycontrib.api.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServiceNewsImpl implements ServiceNews {
	
	@Autowired
	private NewsDao newsDao;

	@Override
	public News addNews(News n) {
		return newsDao.insertNews(n);
	}

	@Override
	public News rechercherNews(long id) {
		return newsDao.findNewsById(id);
	}

	@Override
	public void toggleNewsUpperCase(long newsId) {
		News n = newsDao.findNewsById(newsId);
		n.setText(n.getText().toUpperCase());
		//no jpa so no automatic save when @Transactional
		newsDao.updateNews(n);
		/*
		int a=2;int b=0;
		int c=a/b; //exception volontaire pour verifier comportement transactionnel
		*/
	}

}
