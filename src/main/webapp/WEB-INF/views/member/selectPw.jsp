<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>음티메이트 : 비밀번호 찾기</title>
<style type="text/css">
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
}

.selectPw-wrapper {
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

.selectPw-wrapper h2 {
	text-align: center;
	margin-top: 0;
}

.selectPw-wrapper form {
	display: flex;
	flex-direction: column;
}

.selectPw-wrapper input[type="text"],
input[type="email"] {
	padding: 8px;
	border-radius: 3px;
	border: 1px solid #ccc;
	box-sizing: border-box;
	margin-bottom: 5px;
	width: 80%;
}

.selectPw-wrapper input[placeholder] {
	font-weight: bold;
}
/* 주민등록번호 스타일 */
.selectPw-wrapper .memberRRN input[type=text]:nth-child(1) {
	text-align: center;
	width: 150px;
}

.selectPw-wrapper .memberRRN input[type=text]:nth-child(3) {
	text-align: center;
	width: 50px;
}
/* 휴대폰 번호 스타일 */
.selectPw-wrapper .memberPhone input[type="text"] {
	text-align: center;
	width: 94px;
}

/* submit 버튼 스타일 */
.selectPw-wrapper input[type="submit"] {
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 3px;
	padding: 10px 10px;
	cursor: pointer;
	margin-top: 10px;
	width: 100%;
}

.selectPw-wrapper input[type="submit"]:hover {
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
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {		
		<c:if test="${not empty message}">
			alert("${message}");
		</c:if>
		
		$('input[type="submit"]').click(function() {
			$('#memberRRN').val($('#memberRRN1').val() + '-' + $('#memberRRN2').val());
			$('#memberPhone').val($('#memberPhone1').val() + '-' + $('#memberPhone2').val() + '-' + $('#memberPhone3').val());
		}); // end 'input[type="submit"]'.click()
	}); // end $(document).ready()
</script>
</head>
<body>
	<div class="selectPw-wrapper">
		<div class="close"><button type="button" onclick="window.close()">&times;</button></div>
		<h2>비밀번호 찾기</h2>		
		<form class="selectPw-form" action="/mbti/member/selectPw" method="post">
			<div class="display-flex">
				<label for="memberId">아이디</label>
				<input type="text" id="memberId" name="memberId" minlength="5" maxlength="20" pattern="^[a-z][a-z0-9_\-]{4,19}$" placeholder="아이디" required autofocus>
			</div>
			<div class="display-flex">
				<label for="memberName">이름</label>
				<input type="text" id="memberName" name="memberName" minlength="2" maxlength="6" placeholder="이름" pattern="^[가-힣]{2,6}$" required autofocus>
			</div>
			<div class="display-flex">
				<label for="memberRRN">주민등록번호</label>
				<div class="memberRRN">
					<input type="text" id="memberRRN1" minlength="6" maxlength="6" placeholder="생년월일 (YYMMDD)" pattern="^[0-9]{6}$" required>&nbsp;<b>-</b>&nbsp;
					<input type="text" id="memberRRN2" minlength="1" maxlength="1" placeholder="＊" pattern="^[1-4]{1}$" required><b>＊＊＊＊＊＊</b>
					<input type="hidden" id="memberRRN" name="memberRRN" value="" required>
				</div>
			</div>
			<div class="display-flex">
				<label for="memberPhone">휴대폰 번호</label>
				<div class="memberPhone">
					<input type="text" id="memberPhone1" minlength="2" maxlength="3" placeholder="010" pattern="^[0-9]{2,3}$" required>&nbsp;<b>-</b>&nbsp;
					<input type="text" id="memberPhone2" minlength="3" maxlength="4" placeholder="1234" pattern="^[0-9]{3,4}$" required>&nbsp;<b>-</b>&nbsp;
					<input type="text" id="memberPhone3" minlength="4" maxlength="4" placeholder="5678" pattern="^[0-9]{4}$" required>
					<input type="hidden" id="memberPhone" name="memberPhone" minlength="9" maxlength="11" value="" required>
				</div>
			</div>
			<div class="display-flex">
				<label for="memberEmail">이메일</label>
				<input type="email" id="memberEmail" name="memberEmail" placeholder="mbti@gmail.com" pattern="^[a-zA-Z0-9._%+\-]+@[a-zA-Z0-9.\-]+\.[a-zA-Z]{2,}$" required>
			</div>
			<input type="submit" value="비밀번호 찾기">
		</form>
	</div>
</body>
</html>