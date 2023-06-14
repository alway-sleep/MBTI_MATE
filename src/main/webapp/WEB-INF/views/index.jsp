<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>음티메이트 : MBTI 카페</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
}

.index-wrapper {
	display: flex;
	flex-direction: column;
	min-height: 100vh;
    
	margin: 0 auto;
	width: 80%;
	height: 90%;
	padding: 5px 20px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0px 0px 5px 0px rgba(0, 0, 0, 0.2);
	overflow-y: auto;
}

.index-wrapper img{
	max-width: 100%
}

header {
	padding: 0;
	margin: 0;
}

nav {
	display: flex;
	justify-content: space-between;
	padding: 0;
	font-weight: bold;
}

nav .left-align {
	align-self: flex-start;	
}

nav .right-align {
	align-self: flex-end;	
}

nav ul {
	display: flex;
	align-items: center;
	list-style-type: none;
	padding: 0;
	margin: 0;
}

ul {
	list-style-type: none;
	padding: 0;
	font-weight: bold;
}

ul li {
	margin-bottom: 3px;
}

.cafe-info,
.member-info {
	display: flex;
	flex-direction: column;
}

.cafe-info .cafe-logo,
.member-info .member-profile {
	display: flex;
	justify-content: center;
	align-self: center;
	width: 65%;
	margin-bottom: 3px;
}

.member-info .member-profile .member-nickname {
	align-self: center;
	font-size: 13px;
	font-weight: bold;
}

.cafe-info table,
.member-info table {
	border-collapse: collapse;
	width: 65%;
	background-color: #f0f8ff;
	font-size: 13px;
	align-self: center;
}

.cafe-info table tbody th:nth-child(1),
.member-info table tbody th:nth-child(1) {
	width: 50%;
	text-align: left;
}

.cafe-info table tbody th:nth-child(2),
.member-info table tbody th:nth-child(2) {
	width: 50%;
	text-align: right;
}

.member-info .member-picture {
	position: relative;
}

.member-info .member-picture-set {
	position: absolute;
	bottom: 0;
	right: 0;
	z-index: 1;
	width: 16px;
	height: 16px;
	font-size: 16px;
	font-weight: bold;
	cursor: pointer;
}

a {
	text-decoration: none;
	color: #333;
}

a:hover {
	text-decoration: underline;
}

.selected {
	text-decoration: underline;
}

.container {
	flex: 1;
	display: flex;
}

.container h4 {
	margin: 0;
	color: #fff;
	text-shadow: 1px 1px 0 black, -1px -1px 0 black, 1px -1px 0 black, -1px 1px 0 black;
}

.right-container {
	flex: 1;
	display: flex;
	justify-content: center;
}

.container ul {
	margin: 5px;
}

.container ul li {
	font-size: 13px;
}

.board-category,
.summary-info {
	background-color: #f0f8ff;
	border: 1px solid #ccc;
	border-radius: 10px;
	padding: 10px;
}

.content {
	flex: 2;
	padding: 3px 10px;
}

.index-content {
	text-align: center;
}

footer {
	display: flex;
	justify-content: space-between;
	flex-shrink: 0;
	background-color: #fff;
	color: #000;
	padding-top: 5px;
	margin: 5px auto;
	width: 100%;
	border-top: 2px solid #000;
	font-weight: bold;
}
/* 모달 스타일 */
.login-modal {
	display: none; /* 기본적으로 모달은 숨김 처리 */
	position: fixed; /* 화면에 고정 */
	z-index: 1; /* 다른 요소 위에 표시 */
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto; /* 스크롤 가능하도록 설정 */
	background-color: rgba(0, 0, 0, 0.5); /* 배경은 반투명한 검정색 */
}
/* 모달 콘텐츠 스타일 */
.login-modal-wrapper {
	display: flex;
	flex-direction: column;
	background-color: #fefefe;
	margin: 15% auto; /* 화면 중앙에 위치 */
	padding: 20px;
	border: 1px solid #888;
	width: 450px;
	height: 260px;
}

.login-modal-wrapper h2 {
	text-align: center;
	margin-top: 0;
}

.login-modal-wrapper form {
	display: flex;
	flex-direction: column;
}

.login-modal-wrapper input[type="text"],
.login-modal-wrapper input[type="password"] {
	padding: 8px;
	border-radius: 3px;
	border: 1px solid #ccc;
	box-sizing: border-box;
	margin: 5px 0;
	width: 80%;
}

.login-modal-wrapper input[placeholder] {
	font-weight: bold;
}

.login-modal-wrapper input[type="submit"] {
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 3px;
	padding: 10px 10px;
	cursor: pointer;
	margin-top: 10px;
	width: 100%;
}

.login-modal-wrapper input[type="submit"]:hover {
	background-color: #00abff;
}
/* close 버튼 스타일 */
.close {
  	text-align: right;
}

.close button {
	background-color: #fff;
	color: #aaa;
	font-size: 20px;
	font-weight: bold;
	padding: 0;
	width: 30px;
	height: 30px;
	border: 2px solid #aaa;
	border-radius: 50%;
	cursor: pointer;
}

.close button:hover {
	color: #000;
	border-color: #000;
}
/* display-flex 스타일 */
.display-flex {
	display: flex;
	font-weight: bold;
}

.display-flex label {
	display: flex;
	align-items: center;
	justify-content: flex-start;
	width: 80px;
	font-size: 15px;
}
/* login-options button 스타일 */
.login-options {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	margin-top: 5px;
	border: 1px solid #fff;
}
.login-options button {
	background-color: #fff;
	color: #005bff;
	border: none;
	cursor: pointer;
	font-size: 14px;
	padding: 0;
	margin: 0;
}
.login-options button:hover {
	color: #00abff;
}
</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(document).ready(function() {
		// redirectAttributes.addFlashAttribute 확인
		<c:if test="${not empty message}">
			alert("${message}");
		</c:if>
		<c:if test="${not empty targetURL}">
			<c:choose>
				<c:when test="${targetURL eq 'write'}">
					$.ajax({
						url: '/mbti/board/write',
						type: 'GET',
						success: function(includeJSP) {
							$('.content').html(includeJSP);
							}
					});
				</c:when>
				<c:when test="${targetURL eq 'detail'}">
				$.ajax({
					url: '/mbti/board/detail',
					type: 'GET',
				    data: {
				    	boardNumber : "${sessionScope.target.boardNumber}"
				    	},
					success: function(includeJSP) {
						$('.content').html(includeJSP);
					}
				});
				</c:when>
				<c:when test="${targetURL eq 'list'}">
				$.ajax({
					url: '/mbti/board/list',
					type: 'GET',
				    data: {
				    	boardSection : "${sessionScope.target.boardSection}",
				    	boardList : "${sessionScope.target.boardList}",
				    	boardName : "${sessionScope.target.boardName}"
				    	},
					success: function(includeJSP) {
						$('.content').html(includeJSP);
					}
				});
				</c:when>
			</c:choose>
		</c:if>
		// 왼쪽 게시판 목록에서 게시판 클릭 시 해당 게시판으로 target설정
		$('.left-container').on('click', 'ul li a', function(e) {
			$('.board-category').find('.selected').removeClass('selected');
			$(this).addClass('selected');
			var boardSection = $(this).closest('section').index();
			var boardList = $(this).closest('ul li').index();
			var boardName = $(this).closest('ul li').find('span').text();
			$.ajax({
				url: '/mbti/board/list',
				type: 'GET',
			    data: {
			    	boardSection : boardSection,
			    	boardList : boardList,
			    	boardName : boardName
			    	},
				success: function(includeJSP) {
					$('.content').html(includeJSP);
				}
			});
		}); // end $('.left-container').on('click', 'ul li a', function(e) {})
		// 프로필 사진 변경
		$('.member-picture-set').click(function(e) {
			var popup = window.open("/mbti/member/picture", "음티메이트 : 프로필 사진", "width=450, height=540");
			
			$(popup).on('beforeunload', function(e) {
				location.reload();
			}); // end $(popup).on('beforeunload', function(e) {})
		}); // end $('.member-picture-set').click(function(e) {})
		// 내가 쓴 게시글 보기
		$('.countByNumberOnBoard').click(function(e) {
			e.preventDefault();
		}); // end $('.countByNumberOnBoard').click(function(e) {})
		// 내가 쓴 댓글 보기
		$('.countByNumberOnCmRp').click(function(e) {
			e.preventDefault();
		}); // end $('.countByNumberOnCmRp').click(function(e) {})
		// 로그인 모달
		$('.loginGET').click(function(e) {
			e.preventDefault();
			$('.login-modal').css('display', 'block');
			$('#memberId').attr('autofocus', true);
		}); // end $('.loginGET').click(function(e) {})
	}); // end $(document).ready(function() {})
</script>
</head>
<body>
	<div class="index-wrapper">
		<header>
			<nav>
				<div class="left-align">
					<ul>
						<li><a href="/mbti">카페 홈</a></li>
					</ul>
				</div>
				<div class="right-align">
					<ul>
						<c:if test="${empty sessionScope.memberVO}">
							<li><a href="#" class="loginGET">로그인</a></li>
						</c:if>
						<c:if test="${not empty sessionScope.memberVO}">
							<li><a href="/mbti/member/mypage?memberNumber=${sessionScope.memberVO.memberNumber}" >마이페이지</a>&nbsp;|&nbsp;</li>
							<li><a href="/mbti/message/received" >쪽지</a>&nbsp;|&nbsp;</li>
							<li><a href="/mbti/talk/channel" >채팅</a>&nbsp;|&nbsp;</li>
							<li><a href="/mbti/member/logout" >로그아웃</a></li>
						</c:if>
					</ul>
				</div>
			</nav>
		</header>
		<div class="banner">
			<a href="/mbti" >
				<img src="/mbti/resources/index?fileName=cafebanner.png" alt="카페 배너" style="max-height: 400px; width: 100%; border-radius: 5px;">
			</a>
		</div>
			
		<div class="container">
			<div class="left-container">			
				<div class="summary-info">
					<div class="cafe-info">
						<h4>카페 정보</h4>
						<div class="cafe-logo">
							<img src="/mbti/resources/index?fileName=cafelogo.png" alt="카페 로고" style="border: 1px solid #000; border-radius: 5px;">						
						</div>
						<table>
							<tbody>
								<tr>
									<th>카페 매니저</th>
									<th>${manager}</th>
								</tr>
								<tr>
									<th>카페 회원</th>
									<th>${memberTotalCount}명</th>
								</tr>
							</tbody>
						</table>
					</div>
					<c:if test="${not empty sessionScope.memberVO}">
						<div class="member-info">
							<h4>나의 활동</h4>
							<div class="member-profile">
								<div class="member-picture">
									<img src="/mbti/resources/member?fileName=${sessionScope.memberVO.memberPicture}" style="width: 56px; height: 56px; max-height: 100%; border-radius: 30px; margin-right: 10px; align-self: flex-start;">
									<span class="member-picture-set">&#x2699;</span>
								</div>
								<fmt:formatDate value="${sessionScope.memberVO.memberRegdate}" pattern="yyyy-MM-dd" var="memberRegdate"/>
								<div class="member-nickname member-regdate">
									<span>${sessionScope.memberVO.memberNickname}</span>
									<br>
									<span style="color: #979797;">가입일 ${memberRegdate}</span>
								</div>
							</div>
							<table>
								<tbody>
									<tr>
										<th>MBTI</th>
										<th class="member-MBTI">${sessionScope.memberVO.memberMBTI}</th>
									</tr>
									<tr>
										<th>등급</th>
										<th class="member-grade">
											<c:choose>
												<c:when test="${sessionScope.memberVO.memberGrade eq 0}">씨앗</c:when>
												<c:when test="${sessionScope.memberVO.memberGrade eq 1}">새싹</c:when>
												<c:when test="${sessionScope.memberVO.memberGrade eq 2}">꽃</c:when>
												<c:when test="${sessionScope.memberVO.memberGrade eq 3}">열매</c:when>
												<c:when test="${sessionScope.memberVO.memberGrade eq 4}">스탭</c:when>
												<c:when test="${sessionScope.memberVO.memberGrade eq 5}">매니저</c:when>
											</c:choose>
										</th>
									</tr>
									<tr>
										<th>회원</th>
										<th class="member-premium">
											<c:choose>
												<c:when test="${sessionScope.memberVO.memberPremium eq 0}">일반</c:when>
												<c:when test="${sessionScope.memberVO.memberPremium eq 1}">프리미엄</c:when>
											</c:choose>
										</th>
									</tr>
									<tr>
										<th>내가 쓴 게시글</th>
										<th><a href="#" class="countByNumberOnBoard">${countByNumberOnBoard}</a>개</th>
									</tr>
									<tr>
										<th>내가 쓴 댓글</th>
										<th><a href="#" class="countByNumberOnCmRp">${countByNumberOnCmRp}</a>개</th>
									</tr>
								</tbody>
							</table>
						</div>					
					</c:if>
				</div>
				<!-- Category Boards content here -->
				<div class="board-category">
					<section>
						<ul class="boards">
							<li style="display: none;"><a href="#"><span>휴지통</span></a></li>
							<li><a href="#"><span>전체 게시글</span></a>&nbsp;&nbsp;[${countOnBoard}]</li>
						</ul>
					</section>
					<section>
						<h4>광장</h4>
						<ul class="boards">
							<li>└ <a href="#"><span>공지사항</span></a></li>
							<li>└ <a href="#"><span>건의사항</span></a></li>
							<li>└ <a href="#"><span>자유 게시판</span></a></li>
						</ul>
					</section>
					<section>
					<h4>MBTI</h4>
						<ul class="boards">
							<li>└ <a href="#"><span>ISTJ: 논리주의자, 집중력이 강한 관리자</span></a></li>
							<li>└ <a href="#"><span>ISFJ: 수호자, 세실한 도우미</span></a></li>
							<li>└ <a href="#"><span>INFJ: 예언자, 비전을 가진 치유사</span></a></li>
							<li>└ <a href="#"><span>INTJ: 건축가, 전략적인 비전을 가진 전문가</span></a></li>
							<li>└ <a href="#"><span>ISTP: 모험가, 냉철한 분석가</span></a></li>
							<li>└ <a href="#"><span>ISFP: 예술가, 자유로운 영혼의 예술가</span></a></li>
							<li>└ <a href="#"><span>INFP: 중재자, 이상주의적인 장인</span></a></li>
							<li>└ <a href="#"><span>INTP: 아이디어 뱅크, 논리적인 사색가</span></a></li>
							<li>└ <a href="#"><span>ESTP: 사업가, 탐험을 즐기는 엔터테이너</span></a></li>
							<li>└ <a href="#"><span>ESFP: 연예인, 생동감 넘치는 연예인</span></a></li>
							<li>└ <a href="#"><span>ENFP: 발명가, 자유로운 영혼의 발명가</span></a></li>
							<li>└ <a href="#"><span>ENTP: 변론가, 비평적인 천재</span></a></li>
							<li>└ <a href="#"><span>ESTJ: 관리자, 체계적인 조직가</span></a></li>
							<li>└ <a href="#"><span>ESFJ: 친선도모자, 사교적인 주최자</span></a></li>
							<li>└ <a href="#"><span>ENFJ: 언변능숙자, 카리스마 있는 선도자</span></a></li>
							<li>└ <a href="#"><span>ENTJ: 지도자, 비전을 가진 지도자</span></a></li>
						</ul>
					</section>
					<section>					
					<h4>지역</h4>
						<ul class="boards">
							<li>└ <a href="#"><span>서울특별시</span></a></li>
							<li>└ <a href="#"><span>경기도</span></a></li>
							<li>└ <a href="#"><span>인천광역시</span></a></li>
							<li>└ <a href="#"><span>강원도</span></a></li>
							<li>└ <a href="#"><span>충청북도</span></a></li>
							<li>└ <a href="#"><span>충청남도</span></a></li>
							<li>└ <a href="#"><span>대전광역시</span></a></li>
							<li>└ <a href="#"><span>경상북도</span></a></li>
							<li>└ <a href="#"><span>경상남도</span></a></li>
							<li>└ <a href="#"><span>울산광역시</span></a></li>
							<li>└ <a href="#"><span>부산광역시</span></a></li>
							<li>└ <a href="#"><span>전라북도</span></a></li>
							<li>└ <a href="#"><span>전라남도</span></a></li>
							<li>└ <a href="#"><span>광주광역시</span></a></li>
							<li>└ <a href="#"><span>제주특별자치도</span></a></li>
						</ul>
					</section>
					<section>
						<h4>기타</h4>
						<ul class="boards">
							<li>└ <a href="#"><span>가입인사</span></a></li>
							<li>└ <a href="#"><span>출석체크</span></a></li>
						</ul>
					</section>
				</div>
			</div>
			<div class="right-container">
				<div class="content">
					<div class="index-content">
						<a href="https://www.16personalities.com/ko" target="_blank" >
							<img src="/mbti/resources/index?fileName=mbtitest.png" alt="MBTI 테스트" style="width: 100%; height: 150px;">
						</a>
						<img src="/mbti/resources/index?fileName=mbticompat.png" alt="MBTI 궁합표">
					</div>
				</div>
			</div>
		</div>	
		<footer>
			<span>MBTI MATE : 음티 메이트</span>
			<span><a href="/mbti" >localhost:8080/mbti</a></span>
			<span>MBTI 카페</span>
		</footer>
		<!-- 로그인을 위한 Modal -->
		<div class="modal">
			<jsp:include page="member/login-modal.jsp"/>
		</div>
	</div>
</body>
</html>