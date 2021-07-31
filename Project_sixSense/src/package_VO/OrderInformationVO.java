package package_VO;

public class OrderInformationVO {
	private int seq;//pk
	private String customer_id;//fk
	private int total_price;
	private boolean isActivate;
	private boolean refund;
	
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public int getTotal_price() {
		return total_price;
	}

	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}

	public boolean isActivate() {
		return isActivate;
	}

	public void setActivate(boolean isActivate) {
		this.isActivate = isActivate;
	}

	public boolean isRefund() {
		return refund;
	}

	public void setRefund(boolean refund) {
		this.refund = refund;
	}



	@Override
	public String toString() {
		return "\r" + "[" + seq + "]" + " 총 주문금액 : " + total_price
				+ "\r";
	}
	
	
	
	

	

}
