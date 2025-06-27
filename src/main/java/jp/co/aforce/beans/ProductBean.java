package jp.co.aforce.beans;

import java.sql.Timestamp;

public class ProductBean {
	
	private int productId;
	private String memberId;
    private String name;
    private String description;
    private int price;
    private int categoryId;
    private int productCondition;
    private Timestamp createdAt;
    private String imagePath;
    private boolean isSoldOut;
    private String categoryName;
    private String productConditionName;

    
    
    public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
    
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getProductCondition() {
		return productCondition;
	}
	public void setProductCondition(int productCondition) {
		this.productCondition = productCondition;
	}
	public Timestamp getCreatedAt() {
	    return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
	    this.createdAt = createdAt;
	}

	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public boolean isSoldOut() {
		return isSoldOut;
	}
	public void setSoldOut(boolean isSoldOut) {
		this.isSoldOut = isSoldOut;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getProductConditionName() {
		return productConditionName;
	}
	public void setProductConditionName(String productConditionName) {
		this.productConditionName = productConditionName;
	}
    
}