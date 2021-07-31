package package_VO;

public class QuestionVO {
	private int seq_num;//pk
	private String title;
	private String content;
	private String answer;
	private String customer_id;//fk
	private boolean isActivate = false;
	
	public boolean isActivate() {
		return isActivate;
	}
	public void setActivate(boolean isActivate) {
		this.isActivate = isActivate;
	}
	public int getSeq_num() {
		return seq_num;
	}
	public void setSeq_num(int seq_num) {
		this.seq_num = seq_num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	@Override
	public String toString() {
		return "[" + (seq_num) + "] " + title + "\r<답변 상태 : " + isActivate + ">";
	}
	
	
	
}
