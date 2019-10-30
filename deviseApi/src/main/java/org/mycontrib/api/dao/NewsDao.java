package org.mycontrib.api.dao;

import org.mycontrib.api.entity.News;

public interface NewsDao {
	News findNewsById(Long id);
	News insertNews(News n);
	News updateNews(News n);
}
