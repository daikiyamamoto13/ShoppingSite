package jp.co.aforce.beans;

public class CartItem {
	
	private int productId;
	private String name;
	private int price;
	private String imagePath;
	
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int product_id) {
		this.productId = product_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String image_path) {
		this.imagePath = image_path;
	}

}
