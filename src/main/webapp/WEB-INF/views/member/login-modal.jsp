<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="login-modal">
	<div class="login-modal-wrapper">
		<div class="close"><button type="button" onclick="$('.login-modal').css('display', 'none');$('#memberId').val('');$('#memberPw').val('');">&times;</button></div>
		<h2>로그인</h2>
		<form class="login-form" action="/mbti/member/login" method="POST">
			<input type="hidden" id="targetURL" name="targetURL" value="index">
			<input type="hidden" id="targetNumber" name="targetNumber" value="">
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
</div>