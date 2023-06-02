<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>음티메이트 : 로그인</title>
<style type="text/css">
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
}

.login-wrapper {
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

.login-wrapper h2 {
	text-align: center;
	margin-top: 0;
}

.login-wrapper form {
	display: flex;
	flex-direction: column;
}

.login-wrapper input[type="text"],
.login-wrapper input[type="password"] {
	padding: 8px;
	border-radius: 3px;
	border: 1px solid #ccc;
	box-sizing: border-box;
	margin: 5px 0;
	width: 80%;
}

.login-wrapper input[placeholder] {
	font-weight: bold;
}

.login-wrapper input[type="submit"] {
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 3px;
	padding: 10px 10px;
	cursor: pointer;
	margin-top: 10px;
	width: 100%;
}

.login-wrapper input[type="submit"]:hover {
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
<script type="text/javascript">
	$(document).ready(function() {
		// redirectAttributes.addFlashAttribute 확인
		<c:if test="${not empty message}">
			alert("${message}");
		</c:if>
		<c:if test="${not empty sessionScope.memberVO}">
			window.close();
		</c:if>
	}); // end $(document).ready(function() {})
</script>
</head>
<body>
	<div class="login-wrapper">
		<h2>로그인</h2>
		<form class="login-form" action="/mbti/member/login" method="POST">
			<div class="display-flex">
			<label for="memberId">아이디</label>
			<input type="text" id="memberId" name="memberId" minlength="5" maxlength="20" pattern="^[a-z][a-z0-9_\-]{4,19}$" placeholder="아이디" required autofocus>
			</div>
			<div class="display-flex">
			<label for="memberPw">비밀번호</label>
			<input type="password" id="memberPw" name="memberPw" minlength="8" maxlength="16" placeholder="비밀번호" pattern="^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*\(\)\-=_+\{\},.\/?\\<>:;])[a-zA-Z0-9!@#$%^&*\(\)\-=_+\{\},.\/?\\<>:;]{8,16}$" required>
			</div>
			<input type="submit" value="로그인">
		</form>
	    <div class="login-options">
	    	<button type="button" onclick="window.open('/mbti/member/selectId', '_blank')" class="selectIdGET">아이디 찾기</button>
	    	<button type="button" onclick="window.open('/mbti/member/selectPw', '_blank')" class="selectPwGET">비밀번호 찾기</button>
	    	<button type="button" onclick="window.open('/mbti/member/join', '_blank')" class="joinGET">회원가입</button>
	    </div>
	</div>
</body>
</html>