package org.mycontrib.api.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@Entity
public class News {
	@Id
	private Long id_news;
	private String text;
	
	public News(Long id, String text) {
		super();
		this.id_news = id;
		this.text = text;
	}

	@Override
	public String toString() {
		return "News [id=" + id_news + ", text=" + text + "]";
	}

}
