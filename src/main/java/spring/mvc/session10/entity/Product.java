package spring.mvc.session10.entity;

public class Product {

	private String productName; // 名稱
	private Integer quantity; // 數量
	private Double price; // 價格

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [productName=" + productName + ", quantity=" + quantity + ", price=" + price + "]";
	}

	public Product(String productName, Integer quantity, Double price) {

		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
	}

	public Product() {

	}

}
