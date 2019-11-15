package org.mycontrib.api.service;

import org.mycontrib.api.entity.News;

public interface ServiceNews {
	News addNews(News n);
	News rechercherNews(long id);
	void toggleNewsUpperCase(long newsId);//bascule texte en masjuscule en base
}
