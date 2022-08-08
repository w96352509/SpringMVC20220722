package spring.mvc.session12.entity;

public class MyStock {

	private String  symbol; // 股票代號: 股票代號必須於存在市場中
	private Double  price;  // 買進價格: 必須為昨日收盤價±10%之間
	private Integer amount; // 買進股數: 必須為 1000 的倍數(1000股 = 1張)
	
	
	public MyStock() {
		
	}

	public MyStock(String symbol, Double price, Integer amount) {
		this.symbol = symbol;
		this.price = price;
		this.amount = amount;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "MyStock [symbol=" + symbol + ", price=" + price + ", amount=" + amount + "]";
	}
	
}
