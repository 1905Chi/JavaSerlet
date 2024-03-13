package iotstar.vn.Model;

public class CategoryModel {
	
	private int cateid;
	private String catename;
	private String images;
	private int status;
	
	public CategoryModel() {
		super();
	}
	

	public CategoryModel(int cateid, String catename, String images, int status) {
		super();
		this.cateid = cateid;
		this.catename = catename;
		this.images = images;
		this.status = status;
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

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
