package org.mycontrib.api.dao.jdbc;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.mycontrib.api.dao.NewsDao;
import org.mycontrib.api.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class NewsDaoJdbc extends JdbcDaoSupport implements NewsDao {
	
	@Autowired 
	private DataSource appDataSource;

	@PostConstruct
	private void initialize() {
	    setDataSource(appDataSource);
	}

	@Override 
	public News findNewsById(Long id) {
		News news=null;
		JdbcTemplate jt = this.getJdbcTemplate();
		// JdbcTemplate est créé à partir de dataSource
		// Un objet de type JdbcTemplate obtient et libère la connection JDBC
		// automatiquement depuis le dataSource
		// ET en plus , JDBCTemplate collabore bien avec la logique 
		//@Transactional au niveau des @Service
		Map map= jt.queryForMap("SELECT id_news,text FROM News WHERE id_news=?", id);
		if(map != null && map.size() > 0)
		{ news=new News((Long)map.get("id_news"),
				        (String)map.get("text"));
		}
		return news;
	}

	@Override
	public News insertNews(News n) {
		JdbcTemplate jt = this.getJdbcTemplate();
		jt.update("INSERT INTO News(id_news,text) VALUES (? , ?)",
				  n.getId_news(), n.getText());
		return n;
	}

	@Override
	public News updateNews(News n) {
		JdbcTemplate jt = this.getJdbcTemplate();
		jt.update("UPDATE News SET text=? WHERE id_news=?",
				   n.getText(),n.getId_news());
		return n;
	}

}
