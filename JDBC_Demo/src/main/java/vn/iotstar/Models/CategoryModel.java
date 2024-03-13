package vn.iotstar.Models;

public class CategoryModel {
	private int cateid;
	private String catename;
	private String image;
	private int status;
	
	public CategoryModel(int cateid, String catename, String image, int status) {
		super();
		this.cateid = cateid;
		this.catename = catename;
		this.image = image;
		this.status = status;
	}
	public CategoryModel() {
	}
	
	public int getCateid() {
		return cateid;
	}
	public void setCateid(int cateid) {
		this.cateid = cateid;
	}
	public String getCatename() {
		return catename;
	}
	public void setCatename(String catename) {
		this.catename = catename;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}