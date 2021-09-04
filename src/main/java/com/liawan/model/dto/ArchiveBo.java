package com.liawan.model.dto;

import com.liawan.model.domain.Article;

import java.io.Serializable;
import java.util.List;

public class ArchiveBo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5838966077062702007L;
	private String date;
	private List<Article> articles;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
}
