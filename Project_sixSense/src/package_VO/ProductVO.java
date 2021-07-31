package package_VO;

public class ProductVO {// 재고
	private int seq;//pk
	private String name;
	private int price;//가격
	private int stock;//수량
	private boolean isActivate = true;
	private int category_seq;//fk
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
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
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public boolean isActivate() {
		return isActivate;
	}
	public void setActivate(boolean isActivate) {
		this.isActivate = isActivate;
	}
	public int getCategory_seq() {
		return category_seq;
	}
	public void setCategory_seq(int category_seq) {
		this.category_seq = category_seq;
	}


	@Override
	public String toString() {
		return "\r" + "[" + seq + "]" + " 이름 : " + name + " 가격 : " + price
				+ "\r";
	}


}
