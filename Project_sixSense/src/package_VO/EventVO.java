package package_VO;

public class EventVO {
	private int seq_num;// pk
	private String title;
	private boolean isActivate = true;

	public int getSeq_num() {
		return seq_num;
	}

	public void setSeq_num(int seq_num) {
		this.seq_num = seq_num+1;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isActivate() {
		return isActivate;
	}

	public void setActivate(boolean isActivate) {
		this.isActivate = isActivate;
	}

//	@Override
//	public String toString() {
//		return "\r[" + seq_num + "]" + title + "\r";
//	}

}
