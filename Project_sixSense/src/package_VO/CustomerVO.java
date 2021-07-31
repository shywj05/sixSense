package package_VO;

//손님
public class CustomerVO {
	private String customerID;//손님 아이디pk
	private String password;//손님 비밀번호
	private String name;//손님 이름
	private String birthday;//주민번호	
	private String address;//손님 주소
	private int point;//돈 충전
	
	
	public String getCustomerID() {
		return customerID;
	}


	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getPoint() {
		return point;
	}


	public void setPoint(int point) {
		this.point = point;
	}


	@Override
	public String toString() {//아이디, 이름 반환
		return "아이디 : " + customerID +"이름 : "+ name ;
	}	
}
