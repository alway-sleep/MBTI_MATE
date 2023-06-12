<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>음티메이트 : 마이페이지</title>
<style type="text/css">
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
}

.mypage-wrapper {
	position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    
	margin: auto;
	width: 400px;
	padding: 20px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0px 0px 5px 0px rgba(0, 0, 0, 0.2);
}

.mypage-wrapper h2 {
	text-align: center;
	margin-top: 0;
}

.mypage-wrapper form {
	display: flex;
	flex-direction: column;
}

.mypage-wrapper input[type="text"] {
	padding: 8px;
	border-radius: 3px;
	border: 1px solid #ccc;
	box-sizing: border-box;
	margin-bottom: 5px;
	width: 80%;	
}

.mypage-wrapper .memberRRN input[type=text]:nth-child(1) {
	text-align: center;
	width: 150px;
}

.mypage-wrapper .memberRRN input[type=text]:nth-child(3) {
	text-align: center;
	width: 50px;
}

.mypage-wrapper .memberPhone input[type="text"] {
	text-align: center;
	width: 94px;
}
/* sumit 버튼 스타일 */
.mypage-wrapper input[type="submit"] {
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 3px;
	padding: 10px 10px;
	cursor: pointer;
	margin-top: 10px;
	width: 100%;
}

.mypage-wrapper input[type="submit"]:hover {
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
	font-size: 12px;
}

.display-flex input[value] {
	font-weight: bold;
}

</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(document).ready(function() {
		<c:if test="${not empty message}">
			alert("${message}");
		</c:if>
		
		// MBTI selected
		$('#selectMemberMBTI option').each(function() {
			if ($(this).text().split(':')[0] == "${sessionScope.memberVO.memberMBTI}") {
			    $('#memberMBTI').val($(this).text());
			    return false; // 순회 중단
			 }
		});
	}); // end document.ready()	
</script>
</head>
<body>
	<div class="mypage-wrapper">
	<div class="close"><button type="button" onclick="window.location.href='/mbti'">&times;</button></div>
		<h2>마이페이지</h2>
		<form class="mypage-form" action="/mbti/member/update" method="GET">
			<div class="display-flex">
				<label for="memberId">아이디</label>
				<input type="text" id="memberId" value="${sessionScope.memberVO.memberId}" readonly>
			</div>
			<div class="display-flex">			
				<label for="memberNickname">닉네임</label>
				<input type="text" id="memberNickname" value="${sessionScope.memberVO.memberNickname}" readonly>
			</div>
			<div class="display-flex">
				<label for="memberName">이름</label>
				<input type="text" id="memberName" value="${sessionScope.memberVO.memberName}" readonly>
			</div>
			<div class="display-flex">
			<label for="memberRRN">주민등록번호</label>
			<div class="memberRRN">
				<input type="text" id="memberRRN1" value="${sessionScope.memberVO.memberRRN.split('-')[0]}" readonly>&nbsp;<b>-</b>&nbsp;
				<input type="text" id="memberRRN2" value="${sessionScope.memberVO.memberRRN.split('-')[1]}" readonly><b>＊＊＊＊＊＊</b>
			</div>
			</div>
			<div class="display-flex">
				<label for="memberAge">나이</label>
				<input type="text" id="memberAge" value="${sessionScope.memberVO.memberAge}" readonly>
			</div>
			<div class="display-flex">
				<label for="memberGender">성별</label>
				<input type="text" id="memberGender" value="${sessionScope.memberVO.memberGender == 'M' ? '남성' : '여성'}" readonly>
			</div>
			<div class="display-flex">
				<label for="memberPhone">휴대폰 번호</label>
				<div class="memberPhone">
					<input type="text" id="memberPhone1" value="${sessionScope.memberVO.memberPhone.split('-')[0]}" readonly>&nbsp;<b>-</b>&nbsp;
					<input type="text" id="memberPhone2" value="${sessionScope.memberVO.memberPhone.split('-')[1]}" readonly>&nbsp;<b>-</b>&nbsp;
					<input type="text" id="memberPhone3" value="${sessionScope.memberVO.memberPhone.split('-')[2]}" readonly>
				</div>
			</div>
			<div class="display-flex">
				<label for="memberEmail">이메일</label>
				<input type="text" id="memberEmail" value="${sessionScope.memberVO.memberEmail}" readonly>
			</div>
			<div class="display-flex">
				<label for="memberRegion">거주 지역</label>
				<input type="text" id="memberRegion" value="${sessionScope.memberVO.memberRegion}" readonly>
			</div>
			<div class="display-flex">
				<label for="memberMBTI">MBTI</label>
				<input type="text" id="memberMBTI" value=""	readonly>
				<select id="selectMemberMBTI" required hidden>
					<option>ISTJ: 논리주의자, 집중력이 강한 관리자</option>
					<option>ISFJ: 수호자, 세실한 도우미</option>
					<option>INFJ: 예언자, 비전을 가진 치유사</option>
					<option>INTJ: 건축가, 전략적인 비전을 가진 전문가</option>
					<option>ISTP: 모험가, 냉철한 분석가</option>
					<option>ISFP: 예술가, 자유로운 영혼의 예술가</option>
					<option>INFP: 중재자, 이상주의적인 장인</option>
					<option>INTP: 아이디어 뱅크, 논리적인 사색가</option>
					<option>ESTP: 사업가, 탐험을 즐기는 엔터테이너</option>
					<option>ESFP: 연예인, 생동감 넘치는 연예인</option>
					<option>ENFP: 발명가, 자유로운 영혼의 발명가</option>
					<option>ENTP: 변론가, 비평적인 천재</option>
					<option>ESTJ: 관리자, 체계적인 조직가</option>
					<option>ESFJ: 친선도모자, 사교적인 주최자</option>
					<option>ENFJ: 언변능숙자, 카리스마 있는 선도자</option>
					<option>ENTJ: 지도자, 비전을 가진 지도자</option>
				</select>
			</div>
			<div class="display-flex">
				<label for="memberGrade">등급</label>
				<c:choose>
					<c:when test="${sessionScope.memberVO.memberGrade eq 0}"><input type="text" id="memberGrade" value="씨앗" readonly></c:when>
					<c:when test="${sessionScope.memberVO.memberGrade eq 1}"><input type="text" id="memberGrade" value="새싹" readonly></c:when>
					<c:when test="${sessionScope.memberVO.memberGrade eq 2}"><input type="text" id="memberGrade" value="꽃" readonly></c:when>
					<c:when test="${sessionScope.memberVO.memberGrade eq 3}"><input type="text" id="memberGrade" value="열매" readonly></c:when>
					<c:when test="${sessionScope.memberVO.memberGrade eq 4}"><input type="text" id="memberGrade" value="스탭" readonly></c:when>
					<c:when test="${sessionScope.memberVO.memberGrade eq 5}"><input type="text" id="memberGrade" value="매니저" readonly></c:when>
				</c:choose>
			</div>
			<div class="display-flex">
				<label for="memberPremium">프리미엄</label>
				<input type="text" id="memberPremium" value="${sessionScope.memberVO.memberPremium == 1 ? '프리미엄 회원' : '일반 회원'}" readonly>
			</div>
			<div class="display-flex">
				<label for="memberRegdate">가입일</label>
				<fmt:formatDate value="${sessionScope.memberVO.memberRegdate }" pattern="yyyy년 MM월 dd일 E요일 HH시mm분" var="memberRegdate" />
				<input type="text" id="memberRegdate" value="${memberRegdate }" readonly>
			</div>
			<input type="submit" value="회원정보 수정">
		</form>
	</div>
</body>
</html>