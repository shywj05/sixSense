package package_VO;

public class OrderDetailsVO {
	private int seq;
	private int mount;
	private int order_seq;//fk
	private int pro_seq;//fk
	private boolean isActivate;
	
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getMount() {
		return mount;
	}

	public void setMount(int mount) {
		this.mount = mount;
	}

	public int getOrder_seq() {
		return order_seq;
	}

	public void setOrder_seq(int order_seq) {
		this.order_seq = order_seq;
	}

	public int getPro_seq() {
		return pro_seq;
	}

	public void setPro_seq(int pro_seq) {
		this.pro_seq = pro_seq;
	}

	public boolean isActivate() {
		return isActivate;
	}

	public void setActivate(boolean isActivate) {
		this.isActivate = isActivate;
	}

	@Override
	public String toString() {
		return "OrderDetailsVO [seq=" + seq + ", mount=" + mount
				+ ", order_seq=" + order_seq + ", pro_seq=" + pro_seq + "]";
	}
	
	
	

}
