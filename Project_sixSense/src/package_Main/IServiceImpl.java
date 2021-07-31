package package_Main;

import java.util.List;
import java.util.Map;

import package_Database.Database;
import package_VO.CategoryVO;
import package_VO.CustomerVO;
import package_VO.EventVO;
import package_VO.OrderDetailsVO;
import package_VO.OrderInformationVO;
import package_VO.ProductVO;
import package_VO.QuestionVO;

public class IServiceImpl implements IService {
	Database db = new Database();

	/**
	 * <상품 리스트 보기>
	 * 
	 * @return
	 * @param 리스트
	 *            다 불러오는 거니까 파라미터 없음
	 * @author
	 * 
	 */
	@Override
	public List<ProductVO> selectAllProduct() {
		return db.selectAllProduct();
	}

	/**
	 * <상품 추가> 관리자 메서드
	 * 
	 * @since 21.01.28
	 * @author 성원제1
	 */
	@Override
	public boolean insertProduct(ProductVO productVO) {
		return db.insertProduct(productVO);
	}

	/**
	 * <상품 삭제> 관리자 메서드
	 * 
	 * @since 21.01.29.
	 * @author 성원제2
	 */
	@Override
	public int removeProduct(int pro_seq) {
		db.removeProduct(pro_seq);
		return 1;
	}

	/**
	 * <회원의 소리 리스트 보기> 관리자 메서드 데이터 베이스에 메서드 추가해야돼
	 * 
	 * @since 21.01.29.
	 * @author 성원제3
	 * 
	 */
	@Override
	public List<QuestionVO> selectAllQuestion() {
		return db.selectAllQuestion();
	}

	/**
	 * @insertQuestion
	 * @param String question
	 * 
	 * @date 2021. 1. 29.
	 * @author 성원제
	 */
	@Override
	public boolean insertAnswer(Map<String, Object> insertQuestion) {
		return db.adminInsertAnswer(insertQuestion);
	}
	
	/**
	 * @selectQuestion
	 * @param String question
	 * 
	 * @date 2021. 1. 29.
	 * @author 성원제6
	 */
	@Override
	public QuestionVO selectQuestion(int question_seq) {
		return db.selectQuestion(question_seq);
	}
	
	/**
	 * @adminRemoveQuestion : 추가했던 답변을 삭제하는 메서드입니다.
	 * 
	 * @date 2021. 1. 31.
	 * @author 성원제8
	 * 
	 */
	@Override
	public boolean adminRemoveQuestion() {
		return db.adminRemoveQuestion();
	}

	/**
	 * @checkQuestion : 답변을 확인하는 메서드
	 * 
	 * @date 2021. 1. 31.
	 * @author 성원제7
	 * 
	 */
	@Override
	public boolean checkQuestion(int seq) {
		return db.checkQuestion(seq);
	}

	
	/**
	 * @adminEventView - 관리자 메서드 : 이벤트 리스트 보는 메서드
	 * 
	 * @date 2021. 2. 1.
	 * @author 성원제8
	 * 
	 */
	@Override
	public List<EventVO> adminEventView() {
		return db.adminEventView();
	}
	
	/**
	 * @checkEvent - 관리자 메서드 : 이벤트 항목 선택 메서드
	 * 
	 * @date 2021. 2. 1.
	 * @author 성원제
	 */
//	@Override
//	public String checkEvent() {
//		return db.checkEvent();
//	}

	/**
	 * @insertEvent - 관리자 메서드 : 이벤트를 추가하는 메서드
	 * 
	 * @date 2021. 2. 1.
	 * @author 성원제
	 * 
	 */
	@Override
	public boolean insertEvent(EventVO insertEvent) {
		return db.insertEvent(insertEvent);
	}
	
	/**
	 * @removeEvent - 관리자 메서드 : 이벤트 삭제 메서드
	 * 
	 * @date 2021. 2. 1.
	 * @author 성원제11
	 */
	@Override
	public int removeEvent(int seq) {
		db.removeEvent(seq);
		return 0;
	}
	
	public boolean insertQuestion(QuestionVO insertQuestion){
		return db.insertQuestion(insertQuestion);
	}
	
	

	@Override
	public boolean customerRemoveQuestion() {
		return db.customerRemoveQuestion();
	}

	/**
	 * 회원가입 - 유저 정보 DB에 입력
	 * 
	 * @param user
	 * @return 성공 시 true, 실패 시 false 반환
	 * @author
	 */
	@Override
	public boolean insertUser(CustomerVO user) {
		return db.insertUser(user);
	}


	@Override
	public boolean checkId(String id) {
		return db.userIdUniqueCheck(id);
	}

	@Override
	public boolean adminLogin(Map<String, String> loginInfo) {
		return db.adminLogin(loginInfo);
	}

	@Override
	public boolean userLogin(Map<String, String> loginInfo) {
		return db.userLogin(loginInfo);
	}

	@Override
	public CustomerVO selectCustomer(String user_id) {
		return db.selectCustomer(user_id);
	}

	/**
	 * 주문 정보 추가 - 고객 메서드
	 * 회원이 구매한 정보를 추가해야함
	 * 주문 정보 추가 - 고객 메서드 고객이 구매한 정보를 추가해야함
	 * 
	 * @param orderInformation
	 * @return 성공 시 true, 실패 시 false 반환
	 * @author 길민선
	 */
	@Override
	public boolean insertOrderInformation(OrderInformationVO orderInformation) {
		return db.insertOrderInformation(orderInformation);
	}
	
	/**
	 * 주문 상세 정보 추가 - 고객 메서드
	 * 고객이 구매한 상세 정보 각각 추가
	 * @param orderdetail
	 * @return 성공 시 true, 실패 시 false 반환
	 * @author 길민선
	 */
	@Override
	public boolean insertOrderDetails(OrderDetailsVO orderdetail) {
		return db.insertOrderDetails(orderdetail);
	}


	
	/**
	 * productVO를 가져와서 나중에 필요한 속성을 get해서 쓸거임 - 고객 메서드
	 * 
	 * @param pro_seq
	 * @return ProductVO
	 * @author 길민선
	 */
	@Override
	public ProductVO selectProductInfo(int pro_seq) {
		return db.selectProduct(pro_seq);
	}

	@Override
	public List<CategoryVO> selectAllCategory() {
		return db.selectAllCategory();
	}

	@Override
	public List<ProductVO> selectCategory(int cate_seq) {
		return db.selectCategory(cate_seq);
	}


	@Override
	public List<OrderInformationVO> selectAllOrderInformationTrue(String user_id) {
		return db.selectAllOrderInformationTrue(user_id);
	}

	@Override
	public List<OrderInformationVO> selectAllOrderInformationFalse(String user_id) {
		return db.selectAllOrderInformationFalse(user_id);
	}

	@Override
	public OrderDetailsVO selectSeq(int order_seq) {
		return db.selectSeq(order_seq);
	}

	@Override
	public List<OrderDetailsVO> selectAllOrderdetailsSeq(List<OrderInformationVO> orderList) {
		return db.selectAllOrderdetailsSeq(orderList);
	}

	@Override
	public OrderInformationVO selectOrderInfoSeq(int orderinfo_seq) {
		return db.selectOrderInfoSeq(orderinfo_seq);
	}

	@Override
	public List<OrderInformationVO> selectAllOrderInformation(String user_id) {
		return db.selectAllOrderInformation(user_id);
	}

	@Override
	public OrderInformationVO selectAllOrderInformationVO(String id) {
		return db.selectAllOrderInformationVO(id);
	}

	@Override
	public OrderDetailsVO selectAllOrderDetailsVO(int order_seq) {
		return db.selectAllOrderDetailsVO(order_seq);
	}

	
	/**
	 * 주문 상세내역 조회 - 관리자 / 유저 메서드
	 
	 */
	@Override
	public List<OrderDetailsVO> selectOrderDetails(int order_seq) {
		return db.selectOrderDetailsByOrderSeq(order_seq);
	}
	
	@Override
	public int refundOrder(int orderInfo_seq) {
		return db.refundOrder(orderInfo_seq);
	}
	
	@Override
	public int updateUser(Map<String, Object> userInfo) {
		return db.updateUser(userInfo);
	}

	@Override
	public List<OrderInformationVO> selectAllOrderInformationall() {
		return db.selectAllOrderInformationall();
	}
	
	
}
