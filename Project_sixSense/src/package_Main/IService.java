package package_Main;

import java.util.List;
import java.util.Map;

import package_VO.CategoryVO;
import package_VO.CustomerVO;
import package_VO.EventVO;
import package_VO.OrderDetailsVO;
import package_VO.OrderInformationVO;
import package_VO.ProductVO;
import package_VO.QuestionVO;

public interface IService {

	/**
	 * <상품 리스트 보기>
	 * 
	 * @return 
	 * @param 리스트 다 불러오는 거니까 파라미터 없음
	 * @author 박상빈 와따시
	 * 
	 */
	List<ProductVO> selectAllProduct();
	
	/**
	 * <상품 추가> 관리자 메서드
	 * 
	 * @since 21.01.28
	 * @author 성원제1
	 */
	boolean insertProduct(ProductVO productVO);
	
	/**
	 * <상품 삭제> 관리자 메서드
	 * 
	 * 
	 * @since 21.01.28
	 * @author 성원제2
	 */
	int removeProduct(int pro_seq);
	
	/**
	 * <고객의 소리 리스트 보기> 관리자 메서드
	 * 
	 * @since 21.01.29
	 * @author 성원제3
	 */
	List<QuestionVO> selectAllQuestion();
	
	/**
	 * 회원가입 - 회원 정보 DB에 입력
	 * @insertQuestion
	 * @param String question : 답변 등록하는 메서드 입니다.
	 * 
	 * @date 2021. 1. 29.
	 * @author 성원제5
	 */
	boolean insertAnswer(Map<String, Object> insertQuestion);
	
	/**
	 * @adminRemoveQuestion : 추가했던 답변을 삭제하는 메서드입니다.
	 * 
	 * @date 2021. 1. 31.
	 * @author 성원제8
	 * @return 
	 * 
	 */
	public boolean adminRemoveQuestion();
	
	/**
	 * @insertQuestion
	 * @param String question : 고객의 소리 항목 선택하는 메서드입니다.
	 * 
	 * @date 2021. 1. 31.
	 * @author 성원제6
	 */
	QuestionVO selectQuestion(int question_seq);
	
	
	/**
	 * @checkQuestion : 답변을 확인하는 메서드
	 * 
	 * @date 2021. 1. 31.
	 * @author 성원제7
	 * 
	 */
	public boolean checkQuestion(int seq);
	
	/**
	 * @adminEventView - 관리자 메서드 : 이벤트 리스트 보는 메서드
	 * 
	 * @date 2021. 2. 1.
	 * @author 성원제8
	 * 
	 */
	List<EventVO> adminEventView();

//	/**
//	 * @checkEvent - 관리자 메서드 : 이벤트 항목 선택 메서
//	 * 
//	 * @date 2021. 2. 1.
//	 * @author 성원제9
//	 */
//	String checkEvent();
	
	/**
	 * @insertEvent - 관리자 메서드 : 이벤트를 추가하는 메서드
	 * 
	 * @date 2021. 2. 1.
	 * @author 성원제10
	 * 
	 */
	boolean insertEvent(EventVO insertEvent);
	
	/**
	 * @removeEvent - 관리자 메서드 : 이벤트 삭제 메서드
	 * 
	 * @date 2021. 2. 1.
	 * @author 성원제11
	 */
	int removeEvent(int selectEvent);
	
	/**
	 * @insertQuestion
	 * 
	 * @date 2021. 2. 2.
	 * @author 성원제12
	 * 
	 */
	boolean insertQuestion(QuestionVO insertQuestion);
	
	/**
	 * @customerRemoveQuestion
	 * 
	 * @date 2021. 2. 2.
	 * @author 성원제
	 * 
	 */
	public boolean customerRemoveQuestion();
	
	/**
	 * 회원가입 - 유저 정보 DB에 입력
	 * 
	 * @param userVO
	 * @return 성공 시 true, 실패 시 false 반환
	 * @author 박상빈
	 */
	boolean insertUser(CustomerVO user);

	/**
	 * id 중복 여부와 조건을 충분히 만족하는지 확인
	 * 
	 * @param id
	 * @return 만족하면 true, 불만족하면 false 반환
	 * @author 박상빈
	 */
	boolean checkId(String id);

	/**
	 * 관리자 계정 로그인
	 * 
	 * @param loginInfo
	 * @return 로그인 성공 시 true, 실패 시 false 반환
	 * @author 길민선
	 */
	boolean adminLogin(Map<String, String> loginInfo);

	/**
	 * 회원 계정 로그인
	 * 
	 * @param loginInfo
	 *            <"user_id", user_id>, <"user_pw", user_pw> 키/값을 전송하여 로그인 성공여부
	 *            반환받음
	 * @return 로그인 성공 시 true, 실패 시 false 반환
	 * @author 길민선
	 */
	boolean userLogin(Map<String, String> loginInfo);

	/**
	 * 선택한 회원 정보 가져오기 - 관리자 메서드
	 * 
	 * @param user_id
	 * @return UserVO
	 * @author 길민선
	 */
	CustomerVO selectCustomer(String user_id);
	
	
	
	/**
	 * 주문 정보 추가 - 고객 메서드
	 * 
	 * @param orderInformation
	 * @return 성공 시 true, 실패 시 false 반환
	 * @author 길민선
	 */
	boolean insertOrderInformation(OrderInformationVO orderInformation);
	
	/**
	 * 주문 상세 정보 추가 - 고객 메서드
	 * 
	 * @param orderdetail
	 * @return 성공 시 true, 실패 시 false 반환
	 * @author 길민선
	 */
	boolean insertOrderDetails(OrderDetailsVO orderdetail);
	
	
	List<OrderInformationVO> selectAllOrderInformation(String user_id);
	
	


	
	public ProductVO selectProductInfo(int pro_seq);
	
	public List<CategoryVO> selectAllCategory();
	
	public List<ProductVO> selectCategory(int cate_seq);
	
	List<OrderInformationVO> selectAllOrderInformationTrue(String user_id);
	
	List<OrderInformationVO> selectAllOrderInformationFalse(String user_id);
	
	OrderDetailsVO selectSeq(int order_seq);
	
	List<OrderDetailsVO> selectAllOrderdetailsSeq(List<OrderInformationVO> orderList);
	
	OrderInformationVO selectOrderInfoSeq(int orderinfo_seq);
	
	OrderInformationVO selectAllOrderInformationVO(String id);
	
	OrderDetailsVO selectAllOrderDetailsVO(int order_seq);
	List<OrderDetailsVO> selectOrderDetails(int order_seq);
	public int refundOrder(int orderInfo_seq);
	public int updateUser(Map<String, Object> userInfo);
	
	List<OrderInformationVO> selectAllOrderInformationall();
}
