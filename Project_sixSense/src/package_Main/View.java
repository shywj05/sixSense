package package_Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import package_Database.Database;
import package_VO.CategoryVO;
import package_VO.CustomerVO;
import package_VO.EventVO;
import package_VO.OrderDetailsVO;
import package_VO.OrderInformationVO;
import package_VO.ProductVO;
import package_VO.QuestionVO;

public class View {
	private CustomerVO customer = null;
	private final IService iServiceImpl = new IServiceImpl();

	/**
	 * @author 박상빈
	 * @return int 입력받은 숫자1
	 */
	// 숫자입력받기
	int iInput() {
		int input;
		while (true) {
			try {
				Scanner sc = new Scanner(System.in);
				input = sc.nextInt();
				break;
			} catch (Exception e) {
				System.out.println();
				System.out.println("다시 입력해주세요");
			}
		}
		return input;
	}

	/**
	 * @author 박상빈
	 * @return long 입력받은 숫자
	 */
	// 숫자입력받기
	long iLnput() {
		long input;
		while (true) {
			try {
				Scanner sc = new Scanner(System.in);
				input = sc.nextInt();
				break;
			} catch (Exception e) {
				System.out.println();
				System.out.println("재고가 부족합니다");
				System.out.println("수량을 다시 입력해주세요");
				continue;
			}
		}
		return input;
	}

	// 문자입력받기
	/**
	 * @author 박상빈
	 * @return String 입력박은 문자
	 */
	String sInput() {
		String sInput;
		while (true) {
			try {
				Scanner sc = new Scanner(System.in);
				sInput = sc.next().trim();
				break;
			} catch (Exception e) {
				System.out.println();
				System.out.println("다시 입력해주세요");
			}
		}
		return sInput;
	}

	String ssInput() {
		String ssInput;
		while (true) {
			try {
				Scanner sc = new Scanner(System.in);
				ssInput = sc.nextLine();
				break;
			} catch (Exception e) {
				System.out.println();
				System.out.println("다시 입력해주세요");
			}
		}
		return ssInput;
	}

	/**
	 * 처음 시작하면 보이는 화면
	 * 
	 * @author 박상빈
	 * @param
	 */

	void mainScreen() {
		String message = "";
		while (true) {
			System.out.println("메인화면");
			System.out.println();
			System.out.println("[ 1 ] 로그인");
			System.out.println("[ 2 ] 회원가입");
			System.out.println("[ 0 ] 종료");

			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
				message = "";
			}

			switch (iInput()) {
			case 0:
				System.out.println("프로그램을 종료합니다.");
				return;
			case 1:
				login();// 로그인
				break;
			case 2:
				joinmember();// 회원가입;
				break;
			default:
				message = "다시 입력해 주세요.";
			}
		}
	}

	/**
	 * 회원가입 메서드 db List<CustomerVO> customerList = CustomerVO();
	 * 
	 * @author 박상빈
	 */
	private void joinmember() {

		CustomerVO customer = new CustomerVO();
		customer.setPoint(-1);

		while (true) {
			System.out.println("==================================");
			if (customer.getCustomerID() == null) {
				System.out.println("1. 아이디 설정");
			} else if (customer.getName() == null) {
				System.out.println("2. 이름 설정");
			} else if (customer.getPassword() == null) {
				System.out.println("3. 비밀번호 설정");
			} else if (customer.getAddress() == null) {
				System.out.println("4. 주소 입력");
			} else if (customer.getPoint() == -1) {
				System.out.println("5. 포인트 입력");
			}
			System.out.println("==================================");
			if (customer.getCustomerID() == null) {
				customer.setCustomerID(checkCustomerID());
				continue;
			} else if (customer.getName() == null) {
				customer.setName(scanName());
				continue;
			} else if (customer.getPassword() == null) {
				customer.setPassword(scanPw());
				continue;
			} else if (customer.getAddress() == null) {
				customer.setAddress(scanAddr());
				continue;
			} else if (customer.getPoint() == -1) {
				customer.setPoint(scanPoint());
				continue;
			}
			break;
		}
		if (iServiceImpl.insertUser(customer)) {
			System.out.println("회원 등록 완료");
		}

	}

	/**
	 * 아이디가 유일하지 확인 - 사용자 메서드
	 * 
	 * @author 박상빈
	 * @return 아이디가 유일하지 확인후String 반환
	 */
	private String checkCustomerID() {
		String id;
		while (true) {
			id = scanId();
			boolean result = iServiceImpl.checkId(id);
			if (result) {
				break;
			} else {
				System.out.println("중복된 아이디입니다.");
			}
		}
		return id;
	}

	/**
	 * 아이디가 규칙에 맞는지 확인
	 * 
	 * @author 박상빈
	 * @return 아이디의 규칙이 맞는지 확인하고 입력받은 값 반환
	 */
	private String scanId() {
		String input;
		String message = "";
		while (true) {
			System.out.println("아이디 입력");
			System.out.println(">8 ~ 20자리의 영문 또는 숫자 조합이 가능합니다<");
			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
			}
			input = sInput();
			if (RegEx.checkUser_id(input)) {
				break;
			}
			message = "다시 입력해주세요";
			continue;
		}
		return input;
	}

	/**
	 * 이름 받아오는 메서드 - 사용자 메서드
	 * 
	 * @author 박상빈
	 * @return 이름이 규칙에 맞는지 확인 후 String 반환
	 */
	private String scanName() {
		String input;
		String message = "";
		while (true) {
			System.out.println();
			System.out.println("이름 입력 ");
			System.out.println("2 ~ 17자의 한글만 입력해주세요. (※특수기호, 공백 사용 불가※ )");
			System.out.println("￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
			}
			input = sInput();
			if (RegEx.checkUser_name(input)) {
				break;
			}
			message = "다시 입력해주세요";
			continue;
		}
		return input;
	}

	/**
	 * 비밀번호 받아오는 메서드 - 사용자 메서드
	 * 
	 * @author 박상빈
	 * @return 비밀번호 규칙확인을 위해 String 반환
	 */
	private String scanPw() {
		String message = "";
		String input;
		while (true) {
			System.out.println();
			System.out.println("비밀번호 입력");
			System.out.println("8~20자 영문 대 소문자, 숫자, 특수문자를 사용하세요");
			System.out.println("￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
			}
			input = sInput();
			if (RegEx.checkUser_pw(input)) {
				break;
			}
			message = "다시 입력해주세요";
			continue;
		}
		return input;
	}

	/**
	 * 주소 받아오는 메서드 - 사용자 메서드
	 * 
	 * @author 박상빈
	 * @return 주소 규칙확인을 위해 String 반환
	 */
	private String scanAddr() {
		String message = "";
		String input;
		while (true) {
			System.out.println();
			System.out.println("주소입력 입력");
			System.out.println("※ 주소 숫자 또는 문자를 입력해주세요 ※");
			System.out.println("￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
			}
			input = sInput();
			if (RegEx.chckAddress(input)) {
				break;
			}
			message = "다시 입력해주세요";
			continue;
		}
		return input;
	}

	/**
	 * 포인트 받아오는 메서드 - 사용자 메서드
	 * 
	 * @author 박상빈
	 * @return 충전 금액 int로 반환
	 */
	private int scanPoint() {
		String message = "";
		int point;
		while (true) {
			System.out.println();
			System.out.println("충전 금액을 입력");
			System.out.println("※ 숫자를 입력해주세요 ※");
			System.out.println("￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
			}
			point = iInput();
			if (point > 0) {
				break;
			}
			message = "다시 입력해주세요";
			continue;
		}
		return point;
	}

	/**
	 * 로그인 뷰 -관리자/사용자 메서드 -아이디 비밀번호값을 받아 database에서 비교
	 * 
	 * @author 박상빈
	 */
	private void login() {
		String userId = null;
		String userPw = null;
		String message = "";

		while (true) {
			System.out.println();
			if (userId == null) {
				System.out.println("아이디 입력");
			} else if (userPw == null) {
				System.out.println("비밀번호 입력");
			}
			System.out.println();

			if (userId == null) {
				if (!"".equals(message)) {
					System.out.println();
					System.out.println(message);
					message = "";
				}
				userId = sInput();
				continue;
			} else if (userPw == null) {
				userPw = sInput();
				continue;
			}

			Map<String, String> loginInfo = new HashMap<>();
			loginInfo.put("user_id", userId);
			loginInfo.put("user_pw", userPw);

			if (iServiceImpl.adminLogin(loginInfo)) {
				adminMainView();
				break;
			} else if (iServiceImpl.userLogin(loginInfo)) {
				customer = iServiceImpl.selectCustomer(userId);
				userMainView();
				break;
			}

			message = "아이디 또는 비밀번호를 확인하세요.";
			userId = null;
			userPw = null;
		}
	}

	/**
	 * -관리자 메인 뷰 -관리자 메서드
	 * 
	 * @author
	 * 
	 */
	private void adminMainView() {

		while (true) {
			System.out.println("관리자 페이지");
			System.out.println("[ 1 ] 상품리스트 관리");
			System.out.println("[ 2 ] 상품재고 관리");
			System.out.println("[ 3 ] 고객의 소리 관리");
			System.out.println("[ 4 ] 공지사항 관리");
			System.out.println("[ 0 ] 로그아웃");
			System.out.println();
			System.out.println("메뉴를 선택하세요.");
			switch (iInput()) {
			case 0:
				// 뒤로가기
				return;
			case 1:
				// 상품리스트 관리 메서드 호출
				adminProductView();
				break;
			case 2:
				// 상품재고 관리 메서드 호출
				adminStockView();
				break;
			case 3:
				// 고객의 소리 관리 메서드 호출
				adminQuestionView();
				break;
			case 4:
				// 이벤트 관리 메서드 호출
				adminEventView();
				break;
			case 5:
				orderInfoView();
				break;
			default:
				System.out.println("다시 입력해주세요");
				continue;
			}
		}
	}

	/**
	 * 재고관리 메서드
	 * 
	 * @author 박상빈
	 * 
	 */
	private void adminStockView() {
		List<ProductVO> productList = iServiceImpl.selectAllProduct();
		while (true) {
			System.out.println("재고 관리 화면");
			System.out.println("============================");
			System.out.println("번호\t이름\t수량");
			for (ProductVO product : productList) {
				System.out.println("[ " + product.getSeq() + " ]" + product.getName() + "\t" + product.getStock());
			}
			System.out.println("");
			System.out.println("[ 0 ] 이전화면");
			System.out.println("[ 1 ] 수정");
			System.out.println("[ 2 ] 삭제");
			switch (iInput()) {
			case 0:
				return;
			case 1:
				productUpdata();// 재고 수정
				break;
			case 2:
				productDelete();// 재고 삭제
				break;
			default:
				System.out.println("다시 입력해주세요");
				continue;
			}
		}
	}

	/**
	 * 재고 수정 메서드
	 * 
	 * @author 박상빈
	 */
	private void productUpdata() {
		List<ProductVO> productList = iServiceImpl.selectAllProduct();
		System.out.println("수정할 상품 번호를 입력하세요");
		int productNum = iInput();
		if(productNum == 0) {
			adminStockView();
		} else if(productNum > productList.size()) {
			System.out.println("잘 못 입력했습니다. 다시 입력해주세요");
			return;
		}
		System.out.println("재고를 입력하세요. 입력한 수량으로 변경됩니다.");
		int productStock = iInput();
		productList.get(productNum - 1).setStock(productStock);

	}

	/**
	 * 재고 삭제 메서드
	 * 
	 * @author 박상빈
	 */
	private void productDelete() {
		List<ProductVO> productList = iServiceImpl.selectAllProduct();
		System.out.println("삭제할 상품 번호를 입력하세요");
		int productNum = iInput();
		if(productList.size() < productNum) {
			System.out.println("잘 못 입력했습니다. 다시 입력해주세요.");
			System.out.println("");
			return;
		}
		int sizeIs = productList.size();
		System.out.println(sizeIs);
		productList.get(productNum-1).setStock(0);
	}

	/**
	 * 관리자메인-리스트
	 * 
	 * @author 박상빈
	 */

	private void adminProductView() {
		while (true) {
			System.out.println("상품관리 화면");
			System.out.println("[1]과자 리스트 보기 ");
			System.out.println("[2]젤리 리스트 보기 ");
			System.out.println("[3]초코 리스트 보기 ");
			System.out.println("[4]모든 리스트 보기 ");
			System.out.println("[0]이전");
			switch (iInput()) {
			case 0:
				return;
			case 1:
				productSnacks();// [1]과자리스트 보기
				break;
			case 2:
				productJelly();// [2]젤리리스트보기
				break;
			case 3:
				productChoco();// [3]초코리스트보기
				break;
			case 4:
				productAll();// [4]모든 리스트보기
				break;
			default:
				System.out.println("다시 입력해 주세요.");
			}
		}
	}

	// /////////////////상품관리/////////////////////////////////

	/**
	 * @insertProductMethod - 관리자 메서드 : 상품을 추가하는 메서드
	 * 
	 * @since 21.01.28.
	 * @author 성원제2
	 */
	private void insertProductMethod() {
		System.out.println("=============================");
		System.out.println("상품 추가하기");
		System.out.println("=============================");

		System.out.println("추가할 상품의 이름을 입력해주세요");
		// 상품이름을 입력받는다
		String name = sInput();

		System.out.println("추가할 상품의 가격을 입력해주세요");
		// 상품의 가격을 입력한다.
		int price = iInput();
		if (price < 1) {
			System.out.println("다시 입렵해주세요");
			return;
		}
		System.out.println("추가할 상품의 재고를 입력해주세요");
		// 상품의 재고를 입력한다.
		int stock = iInput();
		if (stock < 1) {
			System.out.println("다시 입렵해주세요");
			return;
		}
		System.out.println("추가할 상품의 카테고리를 입력해주세요");
		System.out.println("1과자, 2젤리, 3초코");
		int cateNum = iInput();
		if (cateNum < 1 || cateNum > 3) {
			System.out.println("다시 입렵해주세요");
			return;
		}
		ProductVO insertProduct = new ProductVO();

		// ProductVO 리스트에 새로운 제품 이름 추가
		insertProduct.setName(name);
		// ProductVO 리스트에 가격 추가
		insertProduct.setPrice(price);
		// ProductVO 리스트에 재고 추가
		insertProduct.setStock(stock);
		// ProductVO 리스트에 카테고리 추가
		insertProduct.setCategory_seq(cateNum);

		insertProduct.setSeq(++Database.snack_seq);

		// System.out.println(insertProduct.getSeq());

		if (iServiceImpl.insertProduct(insertProduct)) {
			System.out.println("추가되었습니다.");
		} else {
			System.out.println("추가에 실패하였습니다.");
		}
	}

	// /**
	// * @removeProductMethod - 관리자 메서드 : 상품을 삭제하는 메서드
	// *
	// * @date 2021. 1. 29.
	// * @author 성원제3
	// *
	// */
	// private void removeProductMethod() {
	//
	// List<ProductVO> productlist = iServiceImpl.selectAllProduct();
	// System.out.println(productlist);
	//
	// System.out.println("=============================");
	// System.out.println("상품 삭제하기");
	// System.out.println("=============================");
	// while (true) {
	// System.out.println("삭제할 항목을 선택해주세요");
	// int selectNum = iInput();//
	// if (selectNum == 0) {
	// System.out.println("다시 입력해주세요.");
	// continue;
	// }
	// // 아래와 같이 seq 선택에서 사용자 입력값과 범위가 달라짐 곧, 터짐
	// // if (selectNum-1 < productlist.size(){}
	//
	// // result는 1이면 삭제완료 리턴, 0이면 삭제실패 리턴
	// int result = iServiceImpl.removeProduct(selectNum);
	//
	// if (result == 1) {
	// System.out.println("삭제완료");
	// return;
	// }
	// System.out.println("삭제실패");
	// return;
	// }
	// }

	/**
	 * @adminQuestionView - 관리자 메서드 : 고객의 소리 리스트를 불러오는 메서드입니다.
	 * 
	 * @date 2021. 1. 29.
	 * @author 성원제4
	 */
	private void adminQuestionView() {
		while (true) {

			List<QuestionVO> questionList = iServiceImpl.selectAllQuestion();
			for (int i = 0; i < questionList.size(); i++) {
				System.out.println(questionList.get(i));
			}

			System.out.println();
			System.out.println("항목을 선택하세요");
			System.out.println("[0] 이전");
			int input = iInput();

			if (input == 0) {
				adminMainView();
			} else if (input > 0 && input <= iServiceImpl.selectAllQuestion().size()) {
				// 선택한 번호에 맞는 컨텐츠를 출력한다.
				System.out.println(iServiceImpl.selectQuestion(input).getContent());

				if (iServiceImpl.selectQuestion(input).getAnswer() == null) {
					System.out.println();
					System.out.println("--답변 없음--");
					checkQuestion();
				} else if (iServiceImpl.selectQuestion(input).getAnswer() != null) {
					System.out.println();
					System.out.println("답변 : " + iServiceImpl.selectQuestion(input).getAnswer());
					deleteQuestion(input);
				}
				// else if (!(input > 0 && input <=
				// iServiceImpl.selectAllQuestion().size())) {
				// System.out.println("다시입력");
				// }
			}
		}
	}

	/**
	 * @checkQuestion - 관리자 메서드 : 고객의 소리 내용확인하는 메서드
	 * 
	 * @date 2021. 1. 31.
	 * @author 성원제7
	 * @param answer
	 */
	public void checkQuestion() {
		// System.out.println(iServiceImpl.checkQuestion(seq));
		// System.out.println();

		while (true) {
			System.out.println();
			System.out.println("[1] 답변추가하기");
			System.out.println("[0] 이전");
			int result = iInput();
			switch (result) {
			case 0:
				return;
			case 1:
				adminInsertAnswer();
				break;
			default:
				System.out.println("다시 입력해주세요.");
				continue;
			}
		}

	}

	public void deleteQuestion(int seq) {
		System.out.println("");
		System.out.println("[1] 답변삭제하기");
		System.out.println("[0] 이전");
		int result = iInput();

		if (result == 0) {
			adminQuestionView();
		} else if (result == 1) {
			adminRemoveQuestion(result);
		} else {
			System.out.println("다시 입력해주세요.");
			deleteQuestion(seq);
		}
	}

	/**
	 * @adminInsertQuestion - 관리자 메서드 : 답변을 추가하는 메서드
	 * 
	 * @date 2021. 1. 29.
	 * @author 성원제5
	 */
	private void adminInsertAnswer() {

		System.out.println("=============================");
		System.out.println("답변을 입력하세요.");
		System.out.println("=============================");

		QuestionVO questionList = new QuestionVO();
		// for (QuestionVO question : iServiceImpl.selectQuestion()) {
		// questionList = question;
		// }
		String input = ssInput();

		Map<String, Object> insertQuestion = new HashMap<>();

		insertQuestion.put("answer_seq", questionList.getSeq_num());
		insertQuestion.put("answer", input);

		if (iServiceImpl.insertAnswer(insertQuestion)) {

			System.out.println("추가되었습니다.");
			System.out.println("");
			System.out.println("");
			adminQuestionView();
		} else {
			System.out.println("추가에 실패하였습니다.");

		}

	}

	/**
	 * @adminRemoveQuestion - 관리자 메서드 : 추가했던 답변을 삭제하는 메서드입니다.
	 * 
	 * @date 2021. 1. 31.
	 * @author 성원제6
	 * @param answer
	 * 
	 */
	public void adminRemoveQuestion(int seq) {
		String answer = iServiceImpl.selectQuestion(seq).getAnswer();
		iServiceImpl.adminRemoveQuestion();
		if (answer == null) {
			System.out.println("삭제완료!");
			System.out.println("[0] 이전");
			int result = iInput();
			if (result == 0) {
				adminQuestionView();
			} else {

			}
		}

		// if (!iServiceImpl.adminRemoveQuestion(seq)) {
		// System.out.println("삭제할 답변이 없습니다. 이전으로 돌아가주세요.");
		// System.out.println();
		// return;
		// }
		// System.out.println("삭제완료!");

	}

	// //////////////////공지사항 관리/////////////////////////////////
	/**
	 * @adminEventView - 관리자 메서드 : 이벤트 리스트를 불러오는 메서드입니다.
	 * 
	 * @date 2021. 1. 29.
	 * @author 성원제8
	 */
	private void adminEventView() {
		List<EventVO> eventAllList = iServiceImpl.adminEventView();
		// System.out.println(eventAllList);
		for (int i = 0; i < eventAllList.size(); i++) {
			System.out.println("[" + (i + 1) + "]" + eventAllList.get(i).getTitle());
		}

		while (true) {
			System.out.println();
			System.out.println("[1] 공지사항 수정 페이지로 이동");
			System.out.println("[0] 이전");

			switch (iInput()) {
			case 0:
				return;

			case 1:
				updateEvent();
				break;

			default:
				System.out.println("다시 입력해주세요.");
				break;
			}

		}

	}

	/**
	 * @updateEvent
	 * 
	 * @date 2021. 2. 1.
	 * @author 성원제
	 * 
	 */
	private void updateEvent() {
		List<EventVO> eventAllList = iServiceImpl.adminEventView();
		for (int i = 0; i < eventAllList.size(); i++) {
			System.out.println("[" + (i + 1) + "]" + eventAllList.get(i).getTitle());
		}

		System.out.println();
		System.out.println("[1] 공지사항 추가");
		System.out.println("[2] 공지사항 삭제");
		System.out.println("[0] 이전");
		int selectEvent = iInput();

		switch (selectEvent) {
		case 0:
			return;
		case 1:
			adminInsertEvent();
			break;
		case 2:
			adminRemoveEvent();
			break;
		default:
			System.out.println("다시 입력해주세요");
			break;
		}
	}

	/**
	 * @adminInsertEvent
	 * 
	 * @date 2021. 2. 1.
	 * @author 성원제
	 * 
	 */
	public void adminInsertEvent() {
		System.out.println("=============================");
		System.out.println("공지사항 추가하기");
		System.out.println("=============================");

		System.out.println("추가할 공지의 제목을 입력해주세요");
		// 상품이름을 입력받는다
		String title = ssInput();

		EventVO insertEvent = new EventVO();

		insertEvent.setTitle(title);
		for (int i = 0; i < iServiceImpl.adminEventView().size(); i++) {
			insertEvent.setSeq_num(i + 1);
		}
		// insertEvent.setSeq_num(++Database.event_seq);

		if (iServiceImpl.insertEvent(insertEvent)) {
			System.out.println("추가되었습니다.");
		} else {
			System.out.println("추가에 실패하였습니다.");
		}
		return;
	}

	/**
	 * @adminRemoveEvent - 관리자 메서드 : 공지사항 삭제 메서드
	 * 
	 * @date 2021. 2. 1.
	 * @author 성원제
	 * 
	 */
	public void adminRemoveEvent() {
		List<EventVO> adminEventView = iServiceImpl.adminEventView();
		for (EventVO event : adminEventView) {
			System.out.println("[ " + event.getSeq_num() + " ]" + event.getTitle());
		}
		System.out.println("=============================");
		System.out.println("공지사항 삭제하기");
		System.out.println("=============================");

		while (true) {
			System.out.println("삭제할 항목을 선택해주세요");
			int seq = iInput();
			if (seq > adminEventView.size()) {
				System.out.println("다시 입력해주세요");
				continue;
			}
			if (seq == 0) {
				System.out.println("다시 입력해주세요.");
				continue;
			}
			int result = iServiceImpl.removeEvent(seq);

			if (result == 0) {
				System.out.println("삭제완료");
				return;
			}
			System.out.println("삭제실패");
			return;
		}
	}

	// [ 5 ] 고객 주문내역 보기
	private void orderInfoView() {
		List<OrderInformationVO> orderInforView = iServiceImpl.selectAllOrderInformationall();
		for (OrderInformationVO orderInformation : orderInforView) {
			System.out.println(orderInformation.getCustomer_id() + orderInformation.getTotal_price() + orderInformation.getSeq());
		}
	}

	// /////////////////////////공지사항(고객메서드)//////////////////////////
	/**
	 * @customerShowEvent
	 * 
	 * @date 2021. 2. 1.
	 * @author 성원제
	 * 
	 */
	private void customerShowEvent() {
		List<EventVO> showEvent = iServiceImpl.adminEventView();
		int size = showEvent.size();
		for (int i = 0; i < size; i++) {
			System.out.println("[" + (i + 1) + "]" + showEvent.get(i).getTitle());
		}
		System.out.println();
		System.out.println("[0] 이전");
		while (true) {
			if (iInput() == 0) {
				return;
			}
			System.out.println("[0] 이전");
			System.out.println("이전으로 가기만 가능합니다.");

		}
	}

	// /////////////////////////고객의 소리(고객메서드)//////////////////////////
	/**
	 * @customerShowQuestion
	 * 
	 * @date 2021. 2. 1.
	 * @author 성원제
	 * 
	 */
	private void customerShowQuestion() {
		while (true) {
			List<QuestionVO> showQuestion = iServiceImpl.selectAllQuestion();
			System.out.println("[0] 이전");
			for (int i = 0; i < showQuestion.size(); i++) {
				System.out.println("[" + (i + 1) + "] " + showQuestion.get(i).getTitle());
			}
			System.out.println();
			System.out.println("<확인할 항목을 선택하세요>");
			System.out.println("<고객의 소리를 추가하려면 " + Database.question_seq + "보다 큰 정수를 입력하세요>");
			System.out.println();
			int goMethod = iInput();

			if (goMethod == Database.question_seq) {
				customerCheckQuestion();
			} else if (goMethod == 0) {
				return;
			} else if (goMethod > 0 && goMethod != Database.question_seq) {
				customerInsertQuestion();
			} else {
				System.out.println("다시 입력해주세요.");
				System.out.println();
			}
		}
	}

	/**
	 * @customerCheckQuestion
	 * 
	 * @date 2021. 2. 2.
	 * @author 성원제
	 * 
	 */
	private void customerCheckQuestion() {

		iServiceImpl.checkQuestion(Database.question_seq);
		// System.out.println(checkCont1ent);
		System.out.println();
		while (true) {

			// System.out.println("[1] 고객의 소리 삭제");
			System.out.println("[0] 이전");

			switch (iInput()) {
			case 0:
				return;
			// case 1:
			// customerRemoveQuestion();
			// break;
			default:
				System.out.println();
				System.out.println("다시 입력해주세요.");
				System.out.println();
				break;
			}

		}
	}

	/**
	 * @customerInsertQuestion
	 * 
	 * @date 2021. 2. 1.
	 * @author 성원제
	 * 
	 */
	private void customerInsertQuestion() {
		QuestionVO insertQuestion = new QuestionVO();

		System.out.println("제목을 입력하세요");
		String insertTitle = ssInput();
		insertQuestion.setTitle(insertTitle);

		System.out.println("내용을 입력해하세요");
		String insertContent = ssInput();
		insertQuestion.setContent(insertContent);

		insertQuestion.setSeq_num(++Database.question_seq);

		if (iServiceImpl.insertQuestion(insertQuestion)) {
			System.out.println();
			System.out.println("추가되었습니다.");
			System.out.println();
		} else {
			System.out.println();
			System.out.println("다시 시도해주세요");
			System.out.println();
		}
		while (true) {
			System.out.println("[0] 이전");
			if (iInput() == 0) {
				return;
			}
			System.out.println("다시 입력해주세요");

		}
	}

	// private void customerRemoveQuestion() {
	//
	// }

	/**
	 * @customerRemoveQuestion
	 * 
	 * @date 2021. 2. 2.
	 * @author 성원제
	 * 
	 */

	// /////////////////////////회원뷰//////////////////////////
	/**
	 * -회원 메인 뷰 -사용자 메서드
	 * 
	 */
	private void userMainView() {
		String message = "";
		while (true) {
			if (customer == null) {
				return;
			}
			System.out.println("고객 페이지");
			System.out.println("--------------------");
			System.out.println("[ 1 ] 상품 리스트 보기");// userProductView();
			System.out.println("[ 2 ] 주문내역 보기");// userOrderedListView();
			System.out.println("[ 3 ] 공지사항 보기");
			System.out.println("[ 4 ] 마이페이지");// myPageView();
			System.out.println("[ 5 ] 장바구니");// shoplistView();
			System.out.println("[ 6 ] 고객의 소리");
			System.out.println("[ 0 ] 로그아웃");
			System.out.println("--------------------");

			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
				message = "￣￣￣￣￣￣￣￣￣￣￣￣￣￣";
			}

			switch (iInput()) {
			case 0:
				return;
			case 1:
				userProductView();
				break;
			case 2:
				userOrderedListView();
				break;
			case 3:
				customerShowEvent();
				break;
			case 4:
				myPageView();
				break;
			case 5:
				shoplistView();
				break;
			case 6:
				customerShowQuestion();
				break;
			default:
				System.out.println("--------------------");
				System.out.println("다시 입력해주세요");
				System.out.println();
				continue;
			}
		}
	}

	/**
	 * [1]상품 리스트 보기
	 * 
	 * @author 길민선
	 */
	private void userProductView() {
		while (true) {
			List<ProductVO> productlist = iServiceImpl.selectAllProduct();
			List<CategoryVO> cateviewlist = iServiceImpl.selectAllCategory();
			System.out.println("[ 0 ] 이전");
			System.out.println("카테고리를 선택해주세요");
			for (int i = 0; i < cateviewlist.size(); i++) {
				System.out.println("[ " + (i + 1) + " ] " + cateviewlist.get(i).getKind());
			}
			while (true) {
				int cateNum = iInput();// 카테고리 선택
				if (cateviewlist.size() < cateNum) {
					System.out.println("다시 입력해주세요");
					return;
				} else if (cateNum < 0) {
					System.out.println("다시 입력해주세요");
					return;
				} else if (cateNum == 0) {
					return;
				}
				if (cateNum <= cateviewlist.size()) {
					List<ProductVO> cateview = iServiceImpl.selectCategory(cateNum);
					System.out.println("구매하고자 하는 상품 번호를 입력해주세요");
					System.out.println("[ 0 ] 이전");
					for (int i = 0; i < cateview.size(); i++) {
						System.out.println("[ " + (i + 1) + " ] " + cateview.get(i).getName() + "\t" + cateview.get(i).getPrice() + "원");
					}
					int selectNum = iInput();// 사용자 상품 번호 입력값
					if (cateview.size() < selectNum) {
						System.out.println("다시 입력해주세요");
						return;
					} else if (selectNum < 0) {
						System.out.println("다시 입력해주세요");
						return;
					} else if (selectNum == 0) {
						return;
					}
					if (selectNum == 0) {// 0 입력시 이전화면
						return;
					} else if (selectNum - 1 < cateview.size()) {
						System.out.println("수량을 입력해주세요");
						// ////
						long selectCount = iLnput();// 사용자 수량입력
						if (selectCount > cateview.get(selectNum - 1).getStock()) {
							return;
						}

						// ///
						System.out.println("[ 0 ] 취소");
						System.out.println("[ 1 ] 장바구니 담기");
						System.out.println("[ 2 ] 바로 결제하기");

						int selectOrder = iInput();// 장바구니 담을지 바로 결제할지 결정
						if (selectOrder < 0 || selectOrder > 2) {
							System.out.println("다시 입력해주세요");
							return;
						}
						if (selectOrder == 0) {// [ 0 ] 취소
							return;
						}
						if (selectOrder == 1) {// [ 1 ] 장바구니 담기
							System.out.println(cateview.get(selectNum - 1).getName() + (int) selectCount + "개" + "를 장바구니에 담았습니다.");
							int list_seq = ++Database.list_seq;
							OrderInformationVO orderinfo = new OrderInformationVO();
							orderinfo.setCustomer_id(customer.getCustomerID());
							orderinfo.setSeq(list_seq);
							orderinfo.setTotal_price(productlist.get(selectNum - 1).getPrice() * (int) selectCount);
							orderinfo.setActivate(true);
							orderinfo.setRefund(false);
							iServiceImpl.insertOrderInformation(orderinfo);

							OrderDetailsVO orderdetail = new OrderDetailsVO();
							orderdetail.setSeq(list_seq);
							orderdetail.setOrder_seq(list_seq);
							orderdetail.setPro_seq(cateview.get(selectNum - 1).getSeq());
							orderdetail.setMount((int) selectCount);
							orderdetail.setActivate(true);
							iServiceImpl.insertOrderDetails(orderdetail);
							return;

						} else if (selectOrder == 2) {// [ 2 ] 바로 결제하기
							for (int j = 0; j < selectCount; j++) {
								if (selectNum != 0) {
									// 관리자 재고 차감, 손님 돈 차감
									customer.setPoint(customer.getPoint() - productlist.get(selectNum - 1).getPrice());
								} else {
									System.out.println("다시 입력 해주세요");
								}
							}
							System.out.println(cateview.get(selectNum - 1).getName() + " " + selectCount + "개" + "를 구매하셨습니다" + "\r" + "남은돈은 " + customer.getPoint() + "원 입니다");
							int list_seq = ++Database.list_seq;
							OrderInformationVO orderinfo = new OrderInformationVO();
							orderinfo.setCustomer_id(customer.getCustomerID());
							orderinfo.setSeq(list_seq);
							orderinfo.setTotal_price(productlist.get(selectNum - 1).getPrice() * (int) selectCount);
							orderinfo.setActivate(false);
							orderinfo.setRefund(false);
							iServiceImpl.insertOrderInformation(orderinfo);

							OrderDetailsVO orderdetail = new OrderDetailsVO();
							orderdetail.setSeq(list_seq);
							orderdetail.setOrder_seq(list_seq);
							orderdetail.setPro_seq(cateview.get(selectNum - 1).getSeq());
							orderdetail.setMount((int) selectCount);
							orderdetail.setActivate(false);
							iServiceImpl.insertOrderDetails(orderdetail);
							return;
						} else {
							System.out.println("상품 번호를 다시 입력해주세요");
							return;// 해결해야함
						}
					} else {
						System.out.println("번호를 잘못 입력하셨습니다. 다시 입력해주세요");
						return;
					}
				}
				return;
			}
		}
	}

	/**
	 * [5]장바구니
	 * 
	 * @author 길민선
	 */
	private void shoplistView() {
		while (true) {
			List<OrderInformationVO> orderList = iServiceImpl.selectAllOrderInformationTrue(customer.getCustomerID());
			List<OrderDetailsVO> orderdetails = iServiceImpl.selectAllOrderdetailsSeq(orderList);

			if (orderList.size() == 0) {
				System.out.println("장바구니가 비었습니다");
				break;
			}
			System.out.println("[ 0 ] 이전");
			System.out.println("[ 00 ] 전체구매");
			System.out.println("====장바구니 리스트====");
			System.out.println("구매하시고자 하는 상품의 번호를 선택해주세요");
			System.out.println("번호 선택 즉시 결제가 이뤄집니다");

			for (int i = 0; i < orderdetails.size(); i++) {
				ProductVO proseq = iServiceImpl.selectProductInfo(orderdetails.get(i).getPro_seq());
				System.out.println("[ " + (i + 1) + " ] " + proseq.getName() + "\t" + orderdetails.get(i).getMount() + "개");

			}
			int selectNum = iInput();
			if (selectNum == 00) {
				shoplistAll();
				break;
			}
			if (selectNum == 0) {
				return;
			}
			if (selectNum < 0 || selectNum > orderdetails.size()) {
				System.out.println("다시 입력해주세요");
				return;
			}
			if (selectNum - 1 < orderdetails.size()) {
				orderList.get(selectNum - 1).setActivate(false);
				customer.setPoint(customer.getPoint() - orderList.get(selectNum - 1).getTotal_price());
				orderList.clear();
				orderdetails.clear();
				System.out.println("주문 성공! 주문내역에서 확인해주세요");
			}

		}
	}

	/**
	 * 장바구니 전부 구입
	 * 
	 * @author 길민선씨
	 */
	private void shoplistAll() {
		List<OrderInformationVO> orderList = iServiceImpl.selectAllOrderInformationTrue(customer.getCustomerID());
		List<OrderDetailsVO> orderdetails = iServiceImpl.selectAllOrderdetailsSeq(orderList);
		for (int i = 0; i < orderList.size(); i++) {
			orderList.get(i).setActivate(false);
			customer.setPoint(customer.getPoint() - orderList.get(i).getTotal_price());
		}
		orderList.clear();
		orderdetails.clear();
		System.out.println("주문 성공! 주문내역에서 확인해주세요");
		System.out.println("전부 주문했습니다.");
	}

	// 모든 주문내역 보기 <주문내역 조회>
	private void userOrderedListView() {

		List<OrderInformationVO> orderList = iServiceImpl.selectAllOrderInformationFalse(customer.getCustomerID());
		if (orderList.size() == 0) {
			System.out.println("주문 내역이 없습니다.");
		}
		for (int i = 0; i < orderList.size(); i++) {
			if (orderList.get(i).isRefund())
				System.out.print(" - 환불됨");
		}

		for (int i = 0; i < orderList.size(); i++) {
			List<OrderDetailsVO> orderdetails = iServiceImpl.selectAllOrderdetailsSeq(orderList);
			ProductVO product = iServiceImpl.selectProductInfo(orderdetails.get(i).getPro_seq());
			System.out.println("[ " + (i + 1) + " ]  총결제금액 " + orderList.get(i).getTotal_price() + "원");

		}

		System.out.println("원하는 목록 선택해서 상세내역보기");
		System.out.println("[ 0 ] 뒤로가기");
		int input = iInput();
		if (input == 0) {
			return;
		} else if (input > 0 && input < orderList.size() + 1) {
			userOrderedListDetailView(input);
			// 윗라인
			orderList.clear();
		} else {
			System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");
		}
		orderList.clear();
	}

	// 주문 상세 내역
	private void userOrderedListDetailView(int order_seq) {
		while (true) {

			List<OrderInformationVO> orderList = iServiceImpl.selectAllOrderInformationFalse(customer.getCustomerID());
			List<OrderDetailsVO> orderdetails = iServiceImpl.selectAllOrderdetailsSeq(orderList);
			ProductVO product = iServiceImpl.selectProductInfo(orderdetails.get(order_seq - 1).getPro_seq());
			// 여기
			System.out.println("상품이름 : " + product.getName());
			System.out.println("상품가격 : " + product.getPrice());
			System.out.println("구매개수 : " + orderdetails.get(order_seq - 1).getMount());

			System.out.println("--------------------------");
			if (!orderList.get(order_seq - 1).isRefund()) {
				System.out.println("[ 1 ] 환불하기");

			}
			System.out.println("[ 0 ] 뒤로가기");
			int input = iInput();

			if (!orderList.get(order_seq - 1).isRefund()) {
				if (input == 1) {
					if (refund(order_seq) == 1) {
						return;
					} else {
						System.out.println("환불에 실패하였습니다.");
					}
				}
			}
			if (input == 0) {

				return;
			} else {
				System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");
			}
			orderList.clear();

		}

	}

	/**
	 * -환불하기 -사용자 메서드
	 */
	private int refund(int orderInfo_seq) {
		// 환불
		List<OrderInformationVO> orderList = iServiceImpl.selectAllOrderInformationFalse(customer.getCustomerID());
		List<OrderDetailsVO> orderdetails = iServiceImpl.selectAllOrderdetailsSeq(orderList);

		int orderInfo = iServiceImpl.refundOrder(orderInfo_seq);
		// 유저에게 돈 돌려주기
		OrderInformationVO order = iServiceImpl.selectOrderInfoSeq(orderInfo_seq);
		ProductVO product = iServiceImpl.selectProductInfo(orderInfo_seq);
		order.setRefund(true);
		Map<String, Object> params = new HashMap<>();
		Integer changePoint = customer.getPoint() + order.getTotal_price();
		if (orderInfo > -50) {
			params.put("user_id", customer.getCustomerID());
			params.put("user_point", changePoint);
			iServiceImpl.updateUser(params);
			System.out.println("환불이 정상적으로 처리되었습니다.");

			return 1;

		} else {
			System.out.println("정상적으로 환불되지 않았습니다.");
		}
		return 0;
	}

	/**
	 * 회원정보 모두 모두 보여주기(이름, 아이디, 주소, 남은돈) point충전 화면이동 가능
	 * 
	 * @author 박상빈
	 * @param
	 */

	// 마이페이지
	// 이름, 아이디, 주소, 남은돈
	private void myPageView() {
		// containsAll 두개가 같은지 비교하는 메서드
		System.out.println("아이디" + customer.getCustomerID());
		System.out.println("이름" + customer.getName());
		System.out.println("주소" + customer.getAddress());
		System.out.println("현재 돈" + customer.getPoint());

		while (true) {
			System.out.println("정보수정 화면");
			System.out.println();
			System.out.println("[ 1 ] 포인트 충전");
			System.out.println("[ 2 ] 포인트 인출");
			System.out.println("[ 3 ] 비밀번호 수정");
			System.out.println("[ 4 ] 주소 수정");
			System.out.println("[ 0 ] 이전");
			switch (iInput()) {
			case 0:
				return;
			case 1:
				updataPointIn();// 포인트 충전
				break;
			case 2:
				updataPointOut();// 포인트 인출
				break;
			case 3:
				updataPassword();// 비밀번호 변경
				break;
			case 4:
				updataAddress();// 주소 변경
				break;
			default:
				System.out.println("다시 입력해 주세요");
				continue;
			}

		}
	}

	/**
	 * 포인트 충전 메서드
	 * 
	 * @author 박상빈
	 * @return point를 수정한다.
	 */
	private void updataPointIn() {
		System.out.println("포인트 충전 화면");
		System.out.println("현재 돈" + customer.getPoint());
		System.out.println("충전할 포인트를 적어주세요");
		System.out.println("================================");
		int pointCharging = iInput();// 충전할 금액
		if (pointCharging == 0) {
			return;
		}
		int pointIn = customer.getPoint();
		customer.setPoint(pointIn + pointCharging);
	}

	/**
	 * 포인트 인출 메서드
	 * 
	 * @author 박상빈
	 * @return point를 수정한다.
	 */
	private void updataPointOut() {
		System.out.println("포인트 인출 화면");
		System.out.println("현재 돈" + customer.getPoint());
		System.out.println("인출할 포인트를 적어주세요");
		System.out.println("================================");
		int pointCharging = iInput();// 인출할 금액
		if (pointCharging == 0) {
			return;
		}
		if (pointCharging > customer.getPoint()) {
			System.out.println("10다시 입력해주세요");
		}
		int pointOut = customer.getPoint();
		customer.setPoint(pointCharging - pointOut);
	}

	/**
	 * 고객 비밀번호 변경 메서드
	 * 
	 * @author 박상빈
	 * 
	 */
	private void updataPassword() {
		System.out.println("비밀번호 변경 화면");
		System.out.println("변경할 비밀번호를 적어주세요");
		System.out.println("================================");
		customer.setPassword(sInput());
	}

	/**
	 * 고객 주소 변경 메서드
	 * 
	 * @author 박상빈
	 * 
	 */
	private void updataAddress() {
		System.out.println("주소 변경 화면");
		System.out.println("변경할 주소를 적어주세요");
		System.out.println("================================");
		customer.setAddress(sInput());
	}

	// 물건 관리 한번 만들어봄
	List<ProductVO> productlist = iServiceImpl.selectAllProduct();// 물건리스트
	List<CategoryVO> cateviewlist = iServiceImpl.selectAllCategory();// 카테리스트

	// List<ProductVO> cateview = iServiceImpl.selectCategory(cateNum);//작은
	// 카테고리

	/**
	 * 관리자 - 과자 상품 보기
	 * 
	 * @author 박상빈
	 */
	private void productSnacks() {
		List<ProductVO> productlist = iServiceImpl.selectAllProduct();
		System.out.println("과자 리스트");
		System.out.println("번호\t이름\t수량\t가격");
		for (ProductVO snacks : productlist) {
			if (snacks.getCategory_seq() == 1) {
				System.out.println("[ " + snacks.getSeq() + " ]" + snacks.getName() + "\t" + snacks.getStock() + "\t" + snacks.getPrice());
			}
		}

		while (true) {
			System.out.println();
			System.out.println("메뉴를 선택해주세요");
			System.out.println("[0] 이전화면");
			System.out.println("[1] 재고관리");
			System.out.println("[2] 상품추가");
			int customNum = iInput();
			switch (customNum) {
			case 0:
				return;
			case 1:
				adminStockView();// 상품재고 관리 메서드 호출
				break;
			case 2:
				insertProductMethod();
				break;
			default:
				System.out.println("다시 입력해주세요");
				productSnacks();
			}
		}
	}

	/**
	 * 관리자 - 젤리 상품 보기
	 * 
	 * @author 박상빈
	 */
	private void productJelly() {
		List<ProductVO> productlist = iServiceImpl.selectAllProduct();
		System.out.println("젤리 리스트");
		System.out.println("번호\t이름\t수량\t가격");
		for (ProductVO snacks : productlist) {
			if (snacks.getCategory_seq() == 2) {
				System.out.println("[ " + snacks.getSeq() + " ]" + snacks.getName() + "\t" + snacks.getStock() + "\t" + snacks.getPrice());
			}
		}
		while (true) {
			System.out.println();
			System.out.println("메뉴를 선택해주세요");
			System.out.println("[0] 이전화면");
			System.out.println("[1] 재고관리");
			System.out.println("[2] 상품추가");
			int customNum = iInput();
			switch (customNum) {
			case 0:
				return;
			case 1:
				adminStockView();// 상품재고 관리 메서드 호출
				break;
			case 2:
				insertProductMethod();
				break;
			default:
				System.out.println("다시 입력해주세요");
				continue;
			}
		}
	}

	/**
	 * 관리자 - 초코 상품 보기
	 * 
	 * @author 박상빈
	 */
	private void productChoco() {
		System.out.println("초코 리스트");
		System.out.println("번호\t이름\t수량\t가격");
		for (ProductVO snacks : productlist) {
			if (snacks.getCategory_seq() == 3) {
				System.out.println("[ " + snacks.getSeq() + " ]" + snacks.getName() + "\t" + snacks.getStock() + "\t" + snacks.getPrice());
			}
		}
		while (true) {
			System.out.println();
			System.out.println("메뉴를 선택해주세요");
			System.out.println("[0] 이전화면");
			System.out.println("[1] 재고관리");
			System.out.println("[2] 상품추가");
			int customNum = iInput();
			switch (customNum) {
			case 0:
				return;
			case 1:
				adminStockView();// 상품재고 관리 메서드 호출
				break;
			case 2:
				insertProductMethod();
				break;
			default:
				System.out.println("다시 입력해주세요");
				continue;
			}
		}
	}

	/**
	 * 관리자 - 모든 리스트 보기
	 * 
	 * @author 박상빈
	 */
	private void productAll() {
		System.out.println("모든 리스트");
		System.out.println("번호\t이름\t수량\t가격");
		for (ProductVO snacks : productlist) {
			System.out.println("[ " + snacks.getSeq() + " ]" + snacks.getName() + "\t" + snacks.getStock() + "\t" + snacks.getPrice());
		}
		while (true) {
			System.out.println();
			System.out.println("메뉴를 선택해주세요");
			System.out.println("[0] 이전화면");
			System.out.println("[1] 재고관리");
			int customNum = iInput();
			switch (customNum) {
			case 0:
				return;
			case 1:
				adminStockView();// 상품재고 관리 메서드 호출
				break;
			default:
				System.out.println("다시 입력해주세요");
				continue;
			}
		}
	}

}
