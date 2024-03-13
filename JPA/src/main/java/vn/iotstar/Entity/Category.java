package vn.iotstar.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Category")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")

public class Category implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoryId")
	private int categoryId;

	@Column(name = "categoryCode")
	private String categoryCode;

	@Column(name = "categoryName")
	private String categoryName;

	@Column(name = "images")
	private String images;

	@Column(name = "status")
	private boolean status;

	// bi-directional many-to-one association to Video
	@OneToMany(mappedBy = "Category")
	private List<Video> videos =new ArrayList<>();

	public Category(int categoryId, String categoryCode, String categoryName, String images, boolean status) {
		super();
		this.categoryId = categoryId;
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.images = images;
		this.status = status;
	}

	// bi-directional many-to-one association to Video
	/*
	 * @OneToMany(mappedBy = "Category")
	 * 
	 * private List<Video> videos;
	 */

	// Bắt buộc
	public Category() {
		super();
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	
	  public List<Video> getVideos() {
	  
	  return this.videos;
	  
	  }
	  
	  public void setVideos(List<Video> videos) {
	  
	  this.videos = videos;
	  
	  }
	  
	  public Video addVideo(Video video) {
	  
	  getVideos().add(video);
	  
	  video.setCategory(this);
	  
	  return video;
	  
	  }
	  
	  public Video removeVideo(Video video) {
	  
	  getVideos().remove(video);
	  
	  video.setCategory(null);
	  
	  return video;
	  
	  }
	 
}
