package coffee_management.dto;

public class SaleDetail {
	private int supplyPrice;
	private int addTax;
	private int salePrice;
	private int marginPrice;
	private int rank;
	public SaleDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SaleDetail(int supplyPrice, int addTax, int salePrice, int marginPrice, int rank) {
		super();
		this.supplyPrice = supplyPrice;
		this.addTax = addTax;
		this.salePrice = salePrice;
		this.marginPrice = marginPrice;
		this.rank = rank;
	}
	public SaleDetail(int supplyPrice, int addTax, int salePrice, int marginPrice) {
		super();
		this.supplyPrice = supplyPrice;
		this.addTax = addTax;
		this.salePrice = salePrice;
		this.marginPrice = marginPrice;
	}
	public int getSupplyPrice() {
		return supplyPrice;
	}
	public void setSupplyPrice(int supplyPrice) {
		this.supplyPrice = supplyPrice;
	}
	public int getAddTax() {
		return addTax;
	}
	public void setAddTax(int addTax) {
		this.addTax = addTax;
	}
	public int getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
	public int getMarginPrice() {
		return marginPrice;
	}
	public void setMarginPrice(int marginPrice) {
		this.marginPrice = marginPrice;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	@Override
	public String toString() {
		return String.format("SaleDetail [supplyPrice=%s, addTax=%s, salePrice=%s, marginPrice=%s, rank=%s]",
				supplyPrice, addTax, salePrice, marginPrice, rank);
	}
	
	
}
