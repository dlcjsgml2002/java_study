package coffee_management.dto;

public class Sale {
	private int no;
	private Product product;
	private int price;
	private int saleCnt;
	private int marginRate;
	private SaleDetail detail;

	public Sale() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sale(int no, Product product, int price, int saleCnt, int marginRate, SaleDetail detail) {
		super();
		this.no = no;
		this.product = product;
		this.price = price;
		this.saleCnt = saleCnt;
		this.marginRate = marginRate;
		this.detail = detail;
	}

	public Sale(int no, Product product, int price, int saleCnt, int marginRate) {
		super();
		this.no = no;
		this.product = product;
		this.price = price;
		this.saleCnt = saleCnt;
		this.marginRate = marginRate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSaleCnt() {
		return saleCnt;
	}

	public void setSaleCnt(int saleCnt) {
		this.saleCnt = saleCnt;
	}

	public int getMarginRate() {
		return marginRate;
	}

	public void setMarginRate(int marginRate) {
		this.marginRate = marginRate;
	}

	public SaleDetail getDetail() {
		return detail;
	}

	public void setDetail(SaleDetail detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return String.format("Sale [no=%s, product=%s, price=%s, saleCnt=%s, marginRate=%s, detail=%s]", no, product,
				price, saleCnt, marginRate, detail);
	}

}
