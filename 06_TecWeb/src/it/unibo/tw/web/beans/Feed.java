package it.unibo.tw.web.beans;

import java.io.Serializable;

public class Feed implements Serializable {

	private static final long serialVersionUID = 1L;

	private String title;
	private String description;
	private String author;
	private String category;
	private String pubDate;
	private String link;
	private String imageUrl;
	
	public Feed(String title, String description, String author,
			String category, String pubDate, String link, String imageUrl) {
		super();
		this.title = title;
		this.description = description;
		this.author = author;
		this.category = category;
		this.pubDate = pubDate;
		this.link = link;
		this.imageUrl = imageUrl;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}
