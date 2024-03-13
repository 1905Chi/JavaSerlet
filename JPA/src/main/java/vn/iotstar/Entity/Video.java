package vn.iotstar.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@SuppressWarnings("serial")
@Entity
@Table(name = "Video")
@NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v ")

public class Video implements Serializable{
	
	@Id
	@Column(name = "videoId")
	private String videoId;

	@Column(name = "title")
	private String title;

	@Column(name = "poster")
	private String poster;

	@Column(name = "views")
	private int views;

	@Column(name = "description")
	private String description;
	
	@Column(name = "active")
	private boolean active;
		
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="categoryId")
	private Category Category;


	public Category getCategory() {
		return Category;
	}


	public void setCategory(Category category) {
		this.Category = category;
	}

	public Video(String title, String poster, int views, String description, boolean active, Category category) {
		super();
		this.title = title;
		this.poster = poster;
		this.views = views;
		this.description = description;
		this.active = active;
		this.Category = category;
	}


	public Video() {
		super();
	}


	public String getVideoId() {
		return videoId;
	}


	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getPoster() {
		return poster;
	}


	public void setPoster(String poster) {
		this.poster = poster;
	}


	public int getViews() {
		return views;
	}


	public void setViews(int views) {
		this.views = views;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	
}
