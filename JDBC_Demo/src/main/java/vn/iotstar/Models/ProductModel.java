package vn.iotstar.Models;

public class ProductModel {
	private  int productId;
	private String productName;
	private int productCode;
	private String  description;
	private double price;
	private String image;
	private String status;
	private int seller;
	public ProductModel(int productId, String productName, int productCode, String description, double price,
			String image, String status, int seller) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productCode = productCode;
		this.description = description;
		this.price = price;
		this.image = image;
		this.status = status;
		this.seller = seller;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductCode() {
		return productCode;
	}
	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getSeller() {
		return seller;
	}
	public void setSeller(int seller) {
		this.seller = seller;
	}
	public ProductModel() {
		super();
	}
	
			

}
