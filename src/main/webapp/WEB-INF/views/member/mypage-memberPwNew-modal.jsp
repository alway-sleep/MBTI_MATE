<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="mypage-memberPwNew-modal">
	<div class="memberPwNew-modal-wrapper">
		<div class="close"><button type="button" onclick="$('.mypage-memberPwNew-modal').css('display', 'none');$('#memberPwOri').val('');$('#memberPwOri2').val('');$('#memberPwNew').val('');$('#memberPwNew2').val('');">&times;</button></div>
		<h2>비밀번호 변경</h2>
		<div class="display-flex">
			<label for="memberPwOri">현재 비밀번호</label>
			<input type="password" id="memberPwOri" name="memberPwOri" minlength="8" maxlength="16" placeholder="비밀번호" pattern="^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*\(\)\-=_+\{\},.\/?\\<>:;])[a-zA-Z0-9!@#$%^&*\(\)\-=_+\{\},.\/?\\<>:;]{8,16}$">
		</div>
			<div class="memberPwIsDuplicated">
			<label id='memberPwIsDuplicated'>본인확인을 위해 비밀번호를 입력해 주세요.</label>
		</div>
		<div class="display-flex">
			<label for="memberPwOri2">현재 비밀번호 확인</label>
			<input type="password" id="memberPwOri2" name="memberPwOri2" placeholder="비밀번호 확인" pattern="^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*\(\)\-=_+\{\},.\/?\\<>:;])[a-zA-Z0-9!@#$%^&*\(\)\-=_+\{\},.\/?\\<>:;]{8,16}$">
		</div>
		<div class="memberPw2IsDuplicated">
			<label id='memberPw2IsDuplicated'>본인확인을 위해 비밀번호를 다시 입력해 주세요.</label>
		</div>
		<div class="display-flex">
			<label for="memberPwNew">변경 비밀번호</label>
			<input type="password" id="memberPwNew" name="memberPwNew" minlength="8" maxlength="16" placeholder="비밀번호" pattern="^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*\(\)\-=_+\{\},.\/?\\<>:;])[a-zA-Z0-9!@#$%^&*\(\)\-=_+\{\},.\/?\\<>:;]{8,16}$">
		</div>
			<div class="memberPwNewIsDuplicated">
			<label id='memberPwNewIsDuplicated'>사용하실  비밀번호를 입력해 주세요.</label>
		</div>
		<div class="display-flex">
			<label for="memberPwNew2">변경 비밀번호 확인</label>
			<input type="password" id="memberPwNew2" name="memberPwNew2" placeholder="비밀번호 확인" pattern="^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*\(\)\-=_+\{\},.\/?\\<>:;])[a-zA-Z0-9!@#$%^&*\(\)\-=_+\{\},.\/?\\<>:;]{8,16}$">
		</div>
		<div class="memberPwNew2IsDuplicated">
			<label id='memberPwNew2IsDuplicated'>비밀번호를 한번 더 입력해 주세요.</label>
		</div>
		<input type="submit" formaction="/mbti/member/updatepw" formmethod="POST" value="비밀번호 변경">
	</div>
</div>