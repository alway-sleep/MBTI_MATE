<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="mypage-delete-modal">
	<div class="delete-modal-wrapper">
		<div class="close"><button type="button" onclick="$('.mypage-delete-modal').css('display', 'none');$('#memberPwDel').val('');$('#memberPw2Del').val('');">&times;</button></div>
		<h2>본인확인</h2>
		<div class="display-flex">
			<label for="memberPwDel">비밀번호</label>
			<input type="password" id="memberPwDel" name="memberPwDel" minlength="8" maxlength="16" placeholder="비밀번호" pattern="^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*\(\)\-=_+\{\},.\/?\\<>:;])[a-zA-Z0-9!@#$%^&*\(\)\-=_+\{\},.\/?\\<>:;]{8,16}$">
		</div>
			<div class="memberPwIsDuplicated">
			<label id='memberPwIsDuplicated'>본인확인을 위해 비밀번호를 입력해 주세요.</label>
		</div>
		<div class="display-flex">
			<label for="memberPw2Del">비밀번호 확인</label>
			<input type="password" id="memberPw2Del" name="memberPw2Del" placeholder="비밀번호 확인" pattern="^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*\(\)\-=_+\{\},.\/?\\<>:;])[a-zA-Z0-9!@#$%^&*\(\)\-=_+\{\},.\/?\\<>:;]{8,16}$">
		</div>
		<div class="memberPw2IsDuplicated">
			<label id='memberPw2IsDuplicated'>본인확인을 위해 비밀번호를 다시 입력해 주세요.</label>
		</div>
		<input type="submit" formaction="/mbti/member/delete" formmethod="POST" value="회원탈퇴">
	</div>
</div>