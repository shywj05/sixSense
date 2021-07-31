package package_Database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import package_VO.AdminVO;
import package_VO.CategoryVO;
import package_VO.CustomerVO;
import package_VO.EventVO;
import package_VO.OrderDetailsVO;
import package_VO.OrderInformationVO;
import package_VO.ProductVO;
import package_VO.QuestionVO;

public class Database {
	public static int snack_seq = 30;
	public static int list_seq = 0;
	public static int event_seq = 1;
	public static int question_seq = 1;

	private final AdminVO admin = new AdminVO();
	// admin 계정
	private final List<CustomerVO> customerList = new ArrayList<>();
	// 손님 목록
	private final List<ProductVO> proList = new ArrayList<>();
	// 상품리스트
	private final List<QuestionVO> questionList = new ArrayList<>();

	// 손님 목록/겸 회원리스트
	private final List<OrderInformationVO> orderinfo = new ArrayList<>();

	// 고객 주문내역 리스트!!!
	private final List<OrderDetailsVO> orderdetails = new ArrayList<>();

	private final List<CategoryVO> catelist = new ArrayList<>();

	// 이벤트 리스트
	private final List<EventVO> eventList = new ArrayList<>();

	/**
	 * <code>insertUser</code> 메서드는 새로운 사용자를 추가하기 위한 메서드입니다.
	 * 
	 * @param user
	 *            : 추가할 UserVO 객체
	 * @return 추가에 성공한다면 true, 실패한다면 false 반환
	 * @author
	 */

	public boolean insertUser(CustomerVO user) {
		return customerList.add(user);
	}

	/**
	 * <code>userIdUniqueCheck</code> 메서드는 입력한 id가 유일한 값이 맞는지 판별하는 메서드입니다.
	 * 
	 * @param userId
	 *            : 유효성을 확인하기 위한 String 객체
	 * @return 유일한 값이라면 true, 그렇지 않은 경우라면 false 반환
	 */
	public boolean userIdUniqueCheck(String userId) {
		if (admin.getId().equals(userId)) {
			return false;
		}
		return selectCustomer(userId) == null;
	}

	/**
	 * <code>adminLogin</code> 메서드는 해당 로그인 요청이 관리자 계정과 일치하는지 판별하는 메서드입니다.
	 * 
	 * @param loginInfo
	 *            : user_id, user_pw를 담은 Map
	 * @return 로그인에 성공한다면 true, 그렇지 않다면 false.
	 * @author
	 */
	public boolean adminLogin(Map<String, String> loginInfo) {
		return admin.getId().equals(loginInfo.get("user_id")) && admin.getPw().equals(loginInfo.get("user_pw"));
	}

	/**
	 * <code>selectAllUser</code> 메서드는 모든 유저의 정보를 불러오기 위한 메서드입니다.
	 * 
	 * @return 모든 유저의 정보를 포함한 List
	 * @author
	 */
	public List<CustomerVO> selectAllUser() {
		List<CustomerVO> userList = new ArrayList<>();
		for (CustomerVO user : this.customerList) {
			userList.add(user);

		}
		return userList;
	}

	/**
	 * <code>selectUser</code> 메서드는 선택한 유저의 정보를 불러오기 위한 메서드입니다.
	 * 
	 * @param id
	 *            : 사용자의 고유 id
	 * @return 사용자의 정보를 담고 있는 UserVO 객체
	 * @author
	 */
	public CustomerVO selectCustomer(String id) {
		for (CustomerVO user : customerList) {
			if (user.getCustomerID().equals(id)) {
				return user;
			}
		}
		return null;
	}

	/**
	
	 */
	public OrderInformationVO selectAllOrderInformationVO(String id) {
		for (OrderInformationVO neworder : orderinfo) {
			if (neworder.getCustomer_id().equals(id)) {
				return neworder;
			}
		}
		return null;
	}

	/**
	
	 */
	public OrderDetailsVO selectAllOrderDetailsVO(int order_seq) {
		for (OrderDetailsVO neworderdetail : orderdetails) {
			if (neworderdetail.getOrder_seq() == order_seq) {
				return neworderdetail;
			}
		}
		return null;
	}

	/**
	 * <code>userLogin</code> 메서드는 해당 로그인 요청이 사용자 계정과 일치하는지 판별하는 메서드입니다.
	 * 
	 * @param loginInfo
	 *            : user_id, user_pw를 담은 Map
	 * @return 로그인에 성공한다면 true, 그렇지 않다면 false.
	 * @author
	 */
	public boolean userLogin(Map<String, String> loginInfo) {
		if (selectCustomer(loginInfo.get("user_id")) == null) {
			return false;
		}
		return selectCustomer(loginInfo.get("user_id")).getPassword().equals(loginInfo.get("user_pw"));
		// && selectUser(loginInfo.get("user_id")).isActivate();
	}

	// ////////////////////////////////////

	// CRUD 중 R!!!
	public List<ProductVO> selectAllProduct() {

		List<ProductVO> productList = new ArrayList<>();
		for (ProductVO productList1 : this.proList) {
			if (productList1.isActivate()) {
				productList.add(productList1);
			}
		}
		return productList;
		// return proList;
	}

	/**
	 * selectProduct 메서드는 상품을 선택하는 메서드입니다.
	 * 
	 * @param user
	 * @author 성원제3
	 * @return
	 */
	public ProductVO selectProduct(int pro_seq) {
		for (ProductVO select : proList) {
			if (select.getSeq() == pro_seq) {
				return select;
			}
		}
		return null;
	}

	/**
	 * insertProduct 메서드는 새로운 상품을 추가하기 위한 메서드입니다.
	 * 
	 * @param product
	 *            : 추가할 ProductVO 객체
	 * @return 추가에 성공하면 true, 실패하면 false 반환
	 * @author 성원제1
	 */
	public boolean insertProduct(ProductVO productVO) {
		return proList.add(productVO);
	}

	/**
	 * <code>removeProduct</code> 메서드는 상품을 삭제하가 위한 메서드입니다.
	 * 
	 * @param seq
	 *            :
	 * @return 추가에 성공한다면 true, 실패한다면 false 반환
	 * @author 성원제2
	 */
	public int removeProduct(int seq) {
		// 타입을 맞춰서 변수에 시퀀스를 넣어
		// 그 시퀀스를 엑티베이트에서 트루풜스로 바꿔 트루면 삭제, 폴스면 아니고
		ProductVO product = selectProduct(seq);
		if (product == null) {
			product.setActivate(true);
			return 0;
		}

		product.setActivate(false);
		return 1;
	}

	/**
	 * @selectAllQuestion : 고객의 소리 항목을 모두 보는 메서드입니다.
	 * 
	 * @date 2021. 1. 29.
	 * @author 성원제4
	 * 
	 */
	public List<QuestionVO> selectAllQuestion() {
		List<QuestionVO> question = new ArrayList<>();
		for (QuestionVO question1 : this.questionList) {
			if (question1.isActivate()) {
				question.add(question1);
				if (question1.getAnswer() == null) {
					question1.setActivate(false);
				}
			}
		}
		return questionList;
	}

	/**
	 * @insertQuestion - 관리자 메서드 : 답글을 입력하는 메서드입니다.
	 * 
	 * @date 2021. 1. 29.
	 * @author 성원제5
	 */
	public boolean adminInsertAnswer(Map<String, Object> insertQuestion) {
		Integer seq = (Integer) insertQuestion.get("answer_seq");
		String answer = (String) insertQuestion.get("answer");

//		QuestionVO selectQuestion = selectQuestion(seq);
//		selectQuestion.setAswer(answer);
//		
		questionList.get(seq).setAnswer(answer);
		questionList.get(seq).setActivate(true);
		if(questionList.get(seq).isActivate() == true){
			return true;
		}
//		//
//		selectQuestion.setActivate(true);
//		return true;
		return false;
	}
	
	
	/**
	 * @adminRemoveQuestion : 추가했던 답변을 삭제하는 메서드입니다.
	 * 
	 * @date 2021. 1. 31.
	 * @author 성원제8
	 * @param answer 
	 * 
	 */
	public boolean adminRemoveQuestion() {
		QuestionVO check = new QuestionVO();
		//for문을 사용해서 돌려야 되겠다.
		for(int i = 0; i < questionList.size(); i++){
			check.setAnswer(null);
		}
		for (QuestionVO checkAnswer : this.questionList) {
			if (checkAnswer.getAnswer() == null) {
				return false;
			}
			checkAnswer.setAnswer(null);
			return true;
		}
		return check.isActivate();
	}

	/**
	 * @selectQuestion 고객의 소리 항목 선택하는 메서드입니다.
	 * 
	 * @param question_seq
	 * @date 2021. 1. 31.
	 * @author 성원제6
	 */
	public QuestionVO selectQuestion(int question_seq) {
		for (QuestionVO select : questionList) {
			if (select.getSeq_num() == question_seq) {
				return select;
			}
		}
		return null;
	}

	/**
	 * @checkQuestion : 고객의 소리의 내용을 확인하는 메서드입니다.
	 * 
	 * @date 2021. 1. 31.
	 * @author 성원제7
	 * 
	 */
	public boolean checkQuestion(int seq) {
		for(int i=0; i<questionList.size(); i++){
			if(questionList.get(i).getSeq_num() == seq){
				System.out.println(questionList.get(i).getContent());
			}
		}
		return true;
		
//		for(int i=0; i<questionList.size(); i++){
//			if(check.getSeq_num() == seq){
//				System.out.println(check.getContent());
//				System.out.println();
//			}
////		}
//		System.out.println(check.getAswer());
//		return false;
//		if(check.getAswer() == null){
//			return "<답변 중>";
//		}
//		else{
//			return "<답변>\r" + check.getAswer();
//		}
//		for (QuestionVO check : questionList) {
//			if (check.getSeq_num() == seq ) {
//				System.out.println(check.getContent());
//				System.out.println();
//			}
//			if (check.getAswer() == null) {
//				return "<답변 중>";
//			}
//		}
//		return "<답변>\r" + check.getAswer();
		
	}

	/**
	 * @adminEventView - 관리자 메서드 : 이벤트 리스트 보는 메서드
	 * 
	 * @date 2021. 2. 1.
	 * @author 성원제8
	 * 
	 */
	public List<EventVO> adminEventView() {
		List<EventVO> allEvent = new ArrayList<>();
		for (EventVO allEvent1 : this.eventList) {
			if (allEvent1.isActivate()) {
				allEvent.add(allEvent1);
			}
		}
		return allEvent;
	}

	/**
	 * @insertEvent - 관리자 메서드 : 이벤트 추가 메서드
	 * 
	 * @date 2021. 2. 1.
	 * @author 성원제9
	 * @param EventVO
	 * 
	 */
	public boolean insertEvent(EventVO insertEvent) {
		return eventList.add(insertEvent);
	}

	/**
	 * @selectEvent
	 * 
	 * @date 2021. 2. 1.
	 * @author 성원제12
	 * 
	 */
	public EventVO selectEvent(int seq) {
		for (EventVO selectEvent : eventList) {
			if (selectEvent.getSeq_num() == seq) {
				return selectEvent;
			}
		}
		return null;
	}

	/**
	 * @checkEvent - 관리자 메서드 : 이벤트 내용 보여주는 메서드
	 * 
	 * @date 2021. 2. 1.
	 * @author 성원제10
	 * 
	 */
	// public String checkEvent(){
	// EventVO check = new EventVO();
	// for(EventVO check1 : this.eventList){
	// check = check1;
	// }
	// return check.getContent();
	// }

	/**
	 * @removeEvent - 관리자 메서드 : 이벤트 삭제 메서드
	 * 
	 * @date 2021. 2. 1.
	 * @author 성원제11
	 * 
	 */
	public int removeEvent(int seq) {
		EventVO remove = selectEvent(seq);
		if (remove == null) {
			remove.setActivate(true);
			return 0;
		}
		remove.setActivate(false);
		return 1;
	}

	public boolean insertQuestion(QuestionVO insertquestion) {
		return questionList.add(insertquestion);
	}

	/**
	 * @customerRemoveQuestion
	 * 
	 * @date 2021. 2. 2.
	 * @author 성원제
	 * 
	 */
	public boolean customerRemoveQuestion() {
		QuestionVO remove = new QuestionVO();
		for (QuestionVO remove1 : this.questionList) {
			remove1.setSeq_num(0);
			remove1.setActivate(false);
		}
		return true;
	}

	/**
	 * <code>insertOrderInformation</code> 메서드는 새로운 주문을 추가하기 위한 메서드입니다.
	 * 
	 * @param orderInformation
	 *            : 추가할 OrderInformationVO 객체
	 * @return 추가에 성공하면 true, 실패하면 false 반환
	 * @author 길민선
	 */
	public boolean insertOrderInformation(OrderInformationVO orderInformation) {
		return orderinfo.add(orderInformation);
	}

	/**
	 * <code>insertOrderInformation</code> 메서드는 새로운 주문을 추가하기 위한 메서드입니다.
	 * 
	 * @param orderInformation
	 *            : 추가할 OrderInformationVO 객체
	 * @return 추가에 성공하면 true, 실패하면 false 반환
	 * @author 길민선
	 */
	public boolean insertOrderDetails(OrderDetailsVO orderdetail) {
		return orderdetails.add(orderdetail);
	}

	/**
	 * 
	 * @return 모든 주문 정보를 담고 있는 List
	 * @author 길민선
	 */
	public List<OrderInformationVO> selectAllOrderInformationTrue(String user_id) {

		List<OrderInformationVO> orderInformationList = new ArrayList<>();

		for (int i = 0; i < orderinfo.size(); i++) {
			if (user_id.equals(orderinfo.get(i).getCustomer_id())) {
				if (orderinfo.get(i).isActivate() == true) {
					orderInformationList.add(orderinfo.get(i));
				}
			}
		}
		return orderInformationList;
	}

	/**
	 * 메서드입니다.--선발대
	 * 
	 * @return 모든 주문 정보를 담고 있는 List
	 * @author 길민선
	 */
	public List<OrderInformationVO> selectAllOrderInformationFalse(String user_id) {

		List<OrderInformationVO> orderInformationList = new ArrayList<>();

		for (int i = 0; i < orderinfo.size(); i++) {
			if (user_id.equals(orderinfo.get(i).getCustomer_id())) {
				if (orderinfo.get(i).isActivate() == false) {
					if (orderinfo.get(i).isRefund() == false) {
						orderInformationList.add(orderinfo.get(i));

					}
				}
			}

		}
		return orderInformationList;
	}

	/**
	 * 카테고리VO의 모든 정보를 반환할 리스트-고객 매서드
	 * 
	 * @param
	 * @return catelist
	 * @author 길민선
	 */
	public List<CategoryVO> selectAllCategory() {
		return catelist;
	}

	/**
	 * 카테고리 시퀀스를 매개 변수로 받아서 시퀀스에 맞는 상품들만 리스트에 담아서 반환하는 메서드-고객 매서드 리스트는 ProductVO
	 * 임
	 * 
	 * @param
	 * @return categorylist
	 * @author 길민선
	 */
	public List<ProductVO> selectCategory(int cate_seq) {
		List<ProductVO> categorylist = new ArrayList<>();
		for (int i = 0; i < proList.size(); i++) {
			if (cate_seq == (proList.get(i).getCategory_seq())) {
				categorylist.add(proList.get(i));
			}
		}
		return categorylist;
	}

	public OrderInformationVO selectOrderInfoSeq(int orderinfo_seq) {
		for (OrderInformationVO select : orderinfo) {
			if (select.getSeq() == orderinfo_seq) {
				return select;
			}
		}
		return null;
	}

	public OrderDetailsVO selectSeq(int order_seq) {
		for (OrderDetailsVO select : orderdetails) {
			if (select.getSeq() == order_seq) {
				return select;
			}
		}
		return null;
	}

	// 민선 수정
	public List<OrderDetailsVO> selectAllOrderdetailsSeq(List<OrderInformationVO> orderList) {
		List<OrderDetailsVO> orderseq = new ArrayList<>();
		if (orderList.size() == 0) {
			return null;
		} else if (orderdetails.size() == 0) {
			return null;
		} else {

			for (int i = 0; i < orderList.size(); i++) {
				for (int j = 0; j < orderdetails.size(); j++) {
					if (orderList.get(i).getSeq() == orderdetails.get(j).getOrder_seq()) {
						orderseq.add(orderdetails.get(j));
					}
				}
			}
			return orderseq;
		}
	}

	public List<OrderInformationVO> selectAllOrderInformation(String user_id) {
		List<OrderInformationVO> orderInformationList = new ArrayList<>();
		for (int i = 0; i < orderinfo.size(); i++) {
			if (user_id.equals(orderinfo.get(i).getCustomer_id())) {
				orderInformationList.add(orderinfo.get(i));
			}
		}
		return orderInformationList;
	}

	public List<OrderInformationVO> selectAllOrderInformationall() {
		List<OrderInformationVO> orderInformationList = new ArrayList<>();
		for (int i = 0; i < orderinfo.size(); i++) {
			orderInformationList.add(orderinfo.get(i));
		}

		return orderInformationList;
	}

	/**
	 * 
	 * @param order_seq
	 * @return order_seq가 일치하는 주문 상세 정보를 포함한 List
	 * @author 길민선
	 */
	public List<OrderDetailsVO> selectOrderDetailsByOrderSeq(int order_seq) {
		List<OrderDetailsVO> OrderDetails = new ArrayList<>();
		for (OrderDetailsVO orderDetailsVO : orderdetails) {
			if (orderDetailsVO.getOrder_seq() == order_seq) {
				OrderDetails.add(orderDetailsVO);
			}
		}
		return OrderDetails;
	}

	/**
	 * @param orderInfo_seq
	 * @return 길민선
	 */
	public int refundOrder(int orderInfo_seq) {
		OrderInformationVO order = selectOrderInfoSeq(orderInfo_seq);
		if (order == null) {
			return 0;
		}

		if (order.isRefund()) {
			return 0;
		}

		order.setRefund(true);
		return 1;
	}

	/**
	 * @param userInfo
	 * @return 길민선
	 */
	public int updateUser(Map<String, Object> userInfo) {
		CustomerVO user = selectCustomer((String) userInfo.get("user_id"));
		if (user == null) {
			return 0;
		}
		if (userInfo.get("user_pw") != null) {
			user.setPassword((String) userInfo.get("user_pw"));
			return 1;
		} else if (userInfo.get("user_name") != null) {
			user.setName((String) userInfo.get("user_name"));
			return 1;
		} else if (userInfo.get("user_point") != null) {
			user.setPoint((Integer) userInfo.get("user_point"));
			return 1;
		}
		return 0;
	}

	{
		CategoryVO cate1 = new CategoryVO();
		cate1.setKind("스낵리스트");
		cate1.setSeq(1);
		catelist.add(cate1);

		CategoryVO cate2 = new CategoryVO();
		cate2.setKind("젤리리스트");
		cate2.setSeq(2);
		catelist.add(cate2);

		CategoryVO cate3 = new CategoryVO();
		cate3.setKind("초콜릿리스트");
		cate3.setSeq(3);
		catelist.add(cate3);

	}

	{
		ProductVO prod1 = new ProductVO();
		prod1.setSeq(1);
		prod1.setName("로아커");
		prod1.setPrice(1000);
		prod1.setStock(100);
		prod1.setCategory_seq(1);
		proList.add(prod1);

		ProductVO prod2 = new ProductVO();
		prod2.setSeq(2);
		prod2.setName("돌김");
		prod2.setPrice(1000);
		prod2.setStock(100);
		prod2.setCategory_seq(1);
		proList.add(prod2);

		ProductVO prod3 = new ProductVO();
		prod3.setSeq(3);
		prod3.setName("스윙칩");
		prod3.setPrice(1000);
		prod3.setStock(100);
		prod3.setCategory_seq(1);
		proList.add(prod3);

		ProductVO prod4 = new ProductVO();
		prod4.setSeq(4);
		prod4.setName("프링글스");
		prod4.setPrice(1000);
		prod4.setStock(500);
		prod4.setCategory_seq(1);
		proList.add(prod4);

		ProductVO prod5 = new ProductVO();
		prod5.setSeq(5);
		prod5.setName("옥수수깡");
		prod5.setPrice(1000);
		prod5.setStock(100);
		prod5.setCategory_seq(1);
		proList.add(prod5);

		ProductVO prod6 = new ProductVO();
		prod6.setSeq(6);
		prod6.setName("감자깡");
		prod6.setPrice(1000);
		prod6.setStock(500);
		prod6.setCategory_seq(1);
		proList.add(prod6);

		ProductVO prod7 = new ProductVO();
		prod7.setSeq(7);
		prod7.setName("호두깡");
		prod7.setPrice(1000);
		prod7.setStock(100);
		prod7.setCategory_seq(1);
		proList.add(prod7);

		ProductVO prod8 = new ProductVO();
		prod8.setSeq(8);
		prod8.setName("초코파이");
		prod8.setPrice(1000);
		prod8.setStock(500);
		prod8.setCategory_seq(1);
		proList.add(prod8);

		ProductVO prod9 = new ProductVO();
		prod9.setSeq(9);
		prod9.setName("쟈크");
		prod9.setPrice(1000);
		prod9.setStock(100);
		prod9.setCategory_seq(1);
		proList.add(prod9);

		ProductVO prod10 = new ProductVO();
		prod10.setSeq(10);
		prod10.setName("피자깡");
		prod10.setPrice(1000);
		prod10.setStock(500);
		prod10.setCategory_seq(1);
		proList.add(prod10);
		// 젤리
		ProductVO prod11 = new ProductVO();
		prod11.setSeq(11);
		prod11.setName("하리보");
		prod11.setPrice(1000);
		prod11.setStock(100);
		prod11.setCategory_seq(2);
		proList.add(prod11);

		ProductVO prod12 = new ProductVO();
		prod12.setSeq(12);
		prod12.setName("마이구미");
		prod12.setPrice(1000);
		prod12.setStock(500);
		prod12.setCategory_seq(2);
		proList.add(prod12);

		ProductVO prod13 = new ProductVO();
		prod13.setSeq(13);
		prod13.setName("방울포도");
		prod13.setPrice(1000);
		prod13.setStock(100);
		prod13.setCategory_seq(2);
		proList.add(prod13);

		ProductVO prod14 = new ProductVO();
		prod14.setSeq(14);
		prod14.setName("왕꿈틀");
		prod14.setPrice(1000);
		prod14.setStock(500);
		prod14.setCategory_seq(2);
		proList.add(prod14);

		ProductVO prod15 = new ProductVO();
		prod15.setSeq(15);
		prod15.setName("피치젤리");
		prod15.setPrice(1000);
		prod15.setStock(100);
		prod15.setCategory_seq(2);
		proList.add(prod15);

		ProductVO prod16 = new ProductVO();
		prod16.setSeq(16);
		prod16.setName("포도젤리");
		prod16.setPrice(1000);
		prod16.setStock(500);
		prod16.setCategory_seq(2);
		proList.add(prod16);

		ProductVO prod17 = new ProductVO();
		prod17.setSeq(17);
		prod17.setName("딸기젤리");
		prod17.setPrice(1000);
		prod17.setStock(100);
		prod17.setCategory_seq(2);
		proList.add(prod17);

		ProductVO prod18 = new ProductVO();
		prod18.setSeq(18);
		prod18.setName("지구젤리");
		prod18.setPrice(1000);
		prod18.setStock(500);
		prod18.setCategory_seq(2);
		proList.add(prod18);

		ProductVO prod19 = new ProductVO();
		prod19.setSeq(19);
		prod19.setName("더탱글");
		prod19.setPrice(1000);
		prod19.setStock(100);
		prod19.setCategory_seq(2);
		proList.add(prod19);

		ProductVO prod20 = new ProductVO();
		prod20.setSeq(20);
		prod20.setName("프루팁스");
		prod20.setPrice(1000);
		prod20.setStock(500);
		prod20.setCategory_seq(2);
		proList.add(prod20);
		// 초코
		ProductVO prod21 = new ProductVO();
		prod21.setSeq(21);
		prod21.setName("허쉬");
		prod21.setPrice(1000);
		prod21.setStock(100);
		prod21.setCategory_seq(3);
		proList.add(prod21);

		ProductVO prod22 = new ProductVO();
		prod22.setSeq(22);
		prod22.setName("페레로쉐");
		prod22.setPrice(1000);
		prod22.setStock(500);
		prod22.setCategory_seq(3);
		proList.add(prod22);

		ProductVO prod23 = new ProductVO();
		prod23.setSeq(23);
		prod23.setName("가나");
		prod23.setPrice(1000);
		prod23.setStock(100);
		prod23.setCategory_seq(3);
		proList.add(prod23);

		ProductVO prod24 = new ProductVO();
		prod24.setSeq(24);
		prod24.setName("로이스");
		prod24.setPrice(1000);
		prod24.setStock(500);
		prod24.setCategory_seq(3);
		proList.add(prod24);

		ProductVO prod25 = new ProductVO();
		prod25.setSeq(25);
		prod25.setName("리세스");
		prod25.setPrice(1000);
		prod25.setStock(100);
		prod25.setCategory_seq(3);
		proList.add(prod25);

		ProductVO prod26 = new ProductVO();
		prod26.setSeq(26);
		prod26.setName("하티스푼");
		prod26.setPrice(1000);
		prod26.setStock(500);
		prod26.setCategory_seq(3);
		proList.add(prod26);

		ProductVO prod27 = new ProductVO();
		prod27.setSeq(27);
		prod27.setName("노브랜드");
		prod27.setPrice(1000);
		prod27.setStock(100);
		prod27.setCategory_seq(3);
		proList.add(prod27);

		ProductVO prod28 = new ProductVO();
		prod28.setSeq(28);
		prod28.setName("고디바");
		prod28.setPrice(1000);
		prod28.setStock(500);
		prod28.setCategory_seq(3);
		proList.add(prod28);

		ProductVO prod29 = new ProductVO();
		prod29.setSeq(29);
		prod29.setName("허쉬");
		prod29.setPrice(1000);
		prod29.setStock(100);
		prod29.setCategory_seq(3);
		proList.add(prod29);

		ProductVO prod30 = new ProductVO();
		prod30.setSeq(30);
		prod30.setName("크런키");
		prod30.setPrice(1000);
		prod30.setStock(500);
		prod30.setCategory_seq(3);
		proList.add(prod30);
	}

	{
		CustomerVO user1 = new CustomerVO();
		user1.setCustomerID("1");
		user1.setPassword("1");
		user1.setName("길민선");
		user1.setAddress("대전");
		user1.setPoint(100000);
		customerList.add(user1);

	}
	{
		CustomerVO user2 = new CustomerVO();
		user2.setCustomerID("2");
		user2.setPassword("2");
		user2.setName("박상빈");
		user2.setAddress("대전");
		user2.setPoint(10000);
		customerList.add(user2);

	}
	{
		QuestionVO question1 = new QuestionVO();
		question1.setSeq_num(1);
		question1.setTitle("저기요...이건 아니잖아요");
		question1.setContent("아니..제품이 왜케 없어요 두개라뇨..매장은 20평인데 말이 되나요??\r 두개 사러 여기까지 오게했다고?");
		question1.setCustomer_id("1");
		questionList.add(question1);
	}

	{
		EventVO event0 = new EventVO();
		event0.setSeq_num(0);
		event0.setTitle("최신유행 지구젤리 입성! 1000원에 팝니다!");
		eventList.add(event0);
	}

}
