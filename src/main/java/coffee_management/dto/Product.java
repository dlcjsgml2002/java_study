package coffee_management.dto;

public class Product {
	private String code;
	private String name;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	public Product(String code) {
		super();
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return String.format("Product [code=%s, name=%s]", code, name);
	}
}
