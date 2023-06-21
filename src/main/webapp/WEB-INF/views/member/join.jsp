<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>음티메이트 : 회원가입</title>
<style type="text/css">
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
}

.join-wrapper {
	position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    
	margin: 0 auto;
	width: 400px;
	background-color: #fff;
	padding: 20px;
	border-radius: 5px;
	box-shadow: 0px 0px 5px 0px rgba(0, 0, 0, 0.2);
}

.join-wrapper h2 {
	text-align: center;
	margin-top: 0;
}

.join-wrapper form {
	display: flex;
	flex-direction: column;
}

.join-wrapper input[type="text"],
.join-wrapper input[type="password"],
.join-wrapper input[type="email"],
.join-wrapper select {
	padding: 8px;
	border-radius: 3px;
	border: 1px solid #ccc;
	box-sizing: border-box;
	margin-bottom: 5px;
	width: 80%;
}
/* select, placeholder 스타일 */
.join-wrapper select,
.join-wrapper select option,
.join-wrapper input[placeholder] {
	font-weight: bold;
}
/* 주민등록번호 스타일 */
.join-wrapper .memberRRN input[type=text]:nth-child(1) {
	text-align: center;
	width: 150px;
}

.join-wrapper .memberRRN input[type=text]:nth-child(3) {
	text-align: center;
	width: 50px;
}
/* 휴대폰 번호 스타일 */
.join-wrapper .memberPhone input[type="text"] {
	text-align: center;
	width: 94px;
}
/* submit 버튼 스타일 */
.join-wrapper input[type=submit] {
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 3px;
	padding: 10px 10px;
	cursor: pointer;
	margin-top: 10px;
	width: 100%;
}

.join-wrapper input[type=submit]:hover {
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
/* 유효성 검사 스타일 */
.join-wrapper #memberIdIsDuplicated,
.join-wrapper #memberNicknameIsDuplicated,
.join-wrapper #memberPwIsDuplicated,
.join-wrapper #memberPw2IsDuplicated,
.join-wrapper #memberRRNIsDuplicated,
.join-wrapper #memberPhoneIsDuplicated,
.join-wrapper #memberEmailIsDuplicated {
	display: flex;
	color: #003bff;
	margin: 0 0 5px 80px;
}

.join-wrapper #memberIdIsDuplicated0,
.join-wrapper #memberNicknameIsDuplicated0,
.join-wrapper #memberPwIsDuplicated0,
.join-wrapper #memberPw2IsDuplicated0,
.join-wrapper #memberRRNIsDuplicated0,
.join-wrapper #memberPhoneIsDuplicated0,
.join-wrapper #memberEmailIsDuplicated0 {
	color: green;
	margin: 0 0 5px 80px;
}

.join-wrapper #memberIdIsDuplicated1,
.join-wrapper #memberNicknameIsDuplicated1,
.join-wrapper #memberPwIsDuplicated1,
.join-wrapper #memberPw2IsDuplicated1,
.join-wrapper #memberRRNIsDuplicated1,
.join-wrapper #memberPhoneIsDuplicated1,
.join-wrapper #memberEmailIsDuplicated1 {
	color: red;
}

.join-wrapper .memberIdIsDuplicated label,
.join-wrapper .memberNicknameIsDuplicated label,
.join-wrapper .memberPwIsDuplicated label,
.join-wrapper .memberPw2IsDuplicated label,
.join-wrapper .memberRRNIsDuplicated label,
.join-wrapper .memberPhoneIsDuplicated label,
.join-wrapper .memberEmailIsDuplicated label {
	font-weight: bold;
	font-size: 12px;
}

.join-wrapper a {
	text-decoration: none;
	margin: 0 0 5px 80px;
	color: #003bff;
	font-weight: bold;
	font-size: 12px;
}
.join-wrapper a:hover {
	text-decoration: underline;
}
</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		<c:if test="${not empty message}">
			alert("${message}");
		</c:if>
		
		var memberIdIsValid = 0;
		var memberNicknameIsValid = 0;
		var memberPwIsValid = 0;
		var memberPw2IsValid = 0;
		//var memberRRNIsValid = 0;
		var memberPhoneIsValid = 0;
		var memberEmailIsValid = 0;
		
		$('#memberId').keyup(function() {
			var memberId = $('#memberId').val();
			const memberIdPattern = new RegExp(/^[a-z][a-z0-9_\-]{4,19}$/);
			if (memberId == "") {
				$('.memberIdIsDuplicated').html("<label id='memberIdIsDuplicated'>사용하실 아이디를 입력해 주세요.</label>");
			} else if (memberId.length <= 4 || !memberIdPattern.test(memberId)) {
				$('.memberIdIsDuplicated').html("<label id='memberIdIsDuplicated1'>아이디는 5~20자리이며 영문 소문자로 시작되어야 하고<br>이후 영문 소문자, 숫자, 하이픈(-), 언더스코어(-)만 사용 가능합니다.</label>");
			} else {
				$.ajax({
					url: '../memberrest/memberId',
					type: 'GET',
					headers: {'Content-Type' : 'application/json'},
					data: {'memberId' : memberId},
					success: function(memberIdGETResult) {
						if (memberIdGETResult == 1) {
							$('.memberIdIsDuplicated').html("<label id='memberIdIsDuplicated1'>이미 사용 중인 아이디입니다.</label>");
							memberIdIsValid = 0;
						} else {
							$('.memberIdIsDuplicated').html("<label id='memberIdIsDuplicated0'>사용 가능한 아이디입니다.</label>");
							memberIdIsValid = 1;
						}
					}						
				}); // end ajax()				
			}
		}); // end #memberId.keyup()
		
		$('#memberNickname').keyup(function() {
			var memberNickname = $('#memberNickname').val();
			const memberNicknamePattern = new RegExp(/^[a-zA-Z0-9가-힣][a-zA-Z0-9가-힣_\-]{1,19}$/);
			if (memberNickname == "") {
				$('.memberNicknameIsDuplicated').html("<label id='memberNicknameIsDuplicated'>사용하실 닉네임을 입력해 주세요.</label>");
			} else if (memberNickname.length <= 1 || !memberNicknamePattern.test(memberNickname)) {
				$('.memberNicknameIsDuplicated').html("<label id='memberNicknameIsDuplicated1'>닉네임은 2~20자리이며 한글 또는 영문 또는 숫자로 시작되어야 하고<br>이후 한글, 영문, 숫자, 하이픈(-), 언더스코어(-)만 사용 가능합니다.</label>");
			} else {
				$.ajax({
					url: '../memberrest/memberNickname',
					type: 'GET',
					headers: {'Content-Type' : 'application/json'},
					data: {'memberNickname' : memberNickname},
					success: function(memberNicknameGETResult) {
						if (memberNicknameGETResult == 1) {
							$('.memberNicknameIsDuplicated').html("<label id='memberNicknameIsDuplicated1'>이미 사용 중인 닉네임입니다.</label>");
							memberNicknameIsValid = 0;
						} else {
							$('.memberNicknameIsDuplicated').html("<label id='memberNicknameIsDuplicated0'>사용 가능한 닉네임입니다.</label>");
							memberNicknameIsValid = 1;
						}
					}						
				}); // end ajax()				
			}
		}); // end #memberNickname.keyup()
		
		$('#memberPw').keyup(function() {
			var memberPw = $('#memberPw').val();
			const memberPwPattern = new RegExp(/^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*\(\)\-=_+\{\},.\/?\\<>:;])[a-zA-Z0-9!@#$%^&*\(\)\-=_+\{\},.\/?\\<>:;]{8,16}$/);
			if (memberPw == "") {
				$('.memberPwIsDuplicated').html("<label id='memberPwIsDuplicated'>사용하실 비밀번호를 입력해 주세요.</label>");
			} else if (memberPw.length <= 7 || !memberPwPattern.test(memberPw)) {
				$('.memberPwIsDuplicated').html("<label id='memberPwIsDuplicated1'>비밀번호는 8~16자리이며<br>영문, 숫자, 특수문자(!@#$%^&*()-=_+{},./?\<>:;)가<br>각각 1개 이상씩 포함되어있어야 합니다.</label>");
				memberPwIsValid = 0;
			} else {
				$('.memberPwIsDuplicated').html("<label id='memberPwIsDuplicated0'>사용 가능한 비밀번호입니다.</label>");
				memberPwIsValid = 1;
			} 
		}); // end #memberPw.keyup()
		
		$('#memberPw2').keyup(function() {
			var memberPw = $('#memberPw').val();
			var memberPw2 = $('#memberPw2').val();
			if (memberPw2 == "") {
				$('.memberPw2IsDuplicated').html("<label id='memberPw2IsDuplicated'>비밀번호를 한번 더 입력해 주세요.</label>");
			} else if (memberPw !== memberPw2) {
				$('.memberPw2IsDuplicated').html("<label id='memberPw2IsDuplicated1'>비밀번호가 일치하지 않습니다.</label>");
				memberPw2IsValid = 0;
			} else {
				$('.memberPw2IsDuplicated').html("<label id='memberPw2IsDuplicated0'>비밀번호가 일치합니다.</label>");
				memberPw2IsValid = 1;
			} 
		}); // end #memberPw2.keyup()
		
		$('.memberRRN input').keyup(function() {
			var fullYear = new Date().getFullYear().toString();
			var currentYear = parseInt(fullYear.substring(fullYear.length - 2)); 
			var memberRRN1 = $('#memberRRN1').val();
			var memberRRN2 = $('#memberRRN2').val();
			var memberRRN = memberRRN1 + '-' + memberRRN2;
			const memberRRN1Pattern = new RegExp(/^\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[01])$/);
			const memberRRN2Pattern = new RegExp(/^[1-4]{1}$/);
			if (parseInt(memberRRN1.substring(0, 2)) > currentYear) {
				$('#memberRRN2').attr("pattern", "^[1-2]{1}$");
			} else {
				$('#memberRRN2').attr("pattern", "^[3-4]{1}$");
			}
			/* if (memberRRN1 == "" || memberRRN2 == "") {
				$('.memberRRNIsDuplicated').html("<label id='memberRRNIsDuplicated'>본인의 주민등록번호를 입력해 주세요.</label>");
			} else if (!memberRRN1Pattern.test(memberRRN1) || !memberRRN2Pattern.test(memberRRN2)) {
				$('.memberRRNIsDuplicated').html("<label id='memberRRNIsDuplicated1'>잘못된 주민등록번호입니다. 다시 입력해 주세요.</label>");
			} else {
				$.ajax({
					url: '../memberrest/memberRRN',
					type: 'GET',
					headers: {'Content-Type' : 'application/json'},
					data: {'memberRRN' : memberRRN},
					success: function(memberRRNGETResult) {
						if (memberRRNGETResult == 1) {
							$('.memberRRNIsDuplicated').html("<label id='memberRRNIsDuplicated1'>이미 가입된 주민등록번호입니다.<br>아이디가 기억나지 않으신다면 아이디 찾기를 이용해주세요.</label>");
							memberRRNIsValid = 0;
						} else {
							$('.memberRRNIsDuplicated').html("<label id='memberRRNIsDuplicated0'>가입 가능한 주민등록번호입니다.</label>");
							memberRRNIsValid = 1;
						}
					}						
				}); // end ajax()
			} */
		}); // end #memberRRN.keyup()
		
		$('.memberPhone input').keyup(function() {
			var memberPhone1 = $('#memberPhone1').val();
			var memberPhone2 = $('#memberPhone2').val();
			var memberPhone3 = $('#memberPhone3').val();
			var memberPhone = memberPhone1 + '-' + memberPhone2 + '-' + memberPhone3;
			const memberPhone1Pattern = new RegExp(/^[0-9]{2,3}$/);
			const memberPhone2Pattern = new RegExp(/^[0-9]{3,4}$/);
			const memberPhone3Pattern = new RegExp(/^[0-9]{4}$/);
			if (memberPhone1 == "" || memberPhone2 == "" || memberPhone3 == "") {
				$('.memberPhoneIsDuplicated').html("<label id='memberPhoneIsDuplicated'>본인의 휴대폰 번호를 양식에 맞게 입력해 주세요.</label>");
			} else if (!memberPhone1Pattern.test(memberPhone1) || !memberPhone2Pattern.test(memberPhone2) || !memberPhone3Pattern.test(memberPhone3)) {
				$('.memberPhoneIsDuplicated').html("<label id='memberPhoneIsDuplicated1'>양식에 맞게 다시 입력해 주세요.</label>");
			} else {
				$.ajax({
					url: '../memberrest/memberPhone',
					type: 'GET',
					headers: {'Content-Type' : 'application/json'},
					data: {'memberPhone' : memberPhone},
					success: function(memberPhoneGETResult) {
						if (memberPhoneGETResult == 1) {
							$('.memberPhoneIsDuplicated').html("<label id='memberPhoneIsDuplicated1'>이미 가입된 휴대폰 번호입니다.<br>아이디가 기억나지 않으신다면 아이디 찾기를 이용해주세요.</label>");
							memberPhoneIsValid = 0;
						} else {
							$('.memberPhoneIsDuplicated').html("<label id='memberPhoneIsDuplicated0'>가입 가능한 휴대폰 번호입니다.</label>");
							memberPhoneIsValid = 1;
						}
					}						
				}); // end ajax()				
			}
		}); // end #memberPhone.keyup()
		
		$('#memberEmail').keyup(function() {
			var memberEmail = $('#memberEmail').val();
			const memberEmailPattern = new RegExp(/^[a-zA-Z0-9._%+\-]+@[a-zA-Z0-9.\-]+\.[a-zA-Z]{2,}$/);
			if (memberEmail == "") {
				$('.memberEmailIsDuplicated').html("<label id='memberEmailIsDuplicated'>사용하고 계신 이메일을 양식에 맞게 입력해 주세요.</label>");
			} else if (!memberEmailPattern.test(memberEmail)) {
				$('.memberEmailIsDuplicated').html("<label id='memberEmailIsDuplicated1'>양식에 맞게 다시 입력해 주세요.</label>");
			} else {
				$.ajax({
					url: '../memberrest/memberEmail',
					type: 'GET',
					headers: {'Content-Type' : 'application/json'},
					data: {'memberEmail' : memberEmail},
					success: function(memberEmailGETResult) {
						if (memberEmailGETResult == 1) {
							$('.memberEmailIsDuplicated').html("<label id='memberEmailIsDuplicated1'>이미 가입된 이메일입니다.<br>아이디가 기억나지 않으신다면 아이디 찾기를 이용해주세요.</label>");
							memberEmailIsValid = 0;
						} else {
							$('.memberEmailIsDuplicated').html("<label id='memberEmailIsDuplicated0'>사용 가능한 이메일입니다.</label>");
							memberEmailIsValid = 1;
						}
					}						
				}); // end ajax()
			} 
		}); // end #memberEmail.keyup()
		
		$('input[type="submit"]').click(function() {
			$('#memberRRN').val($('#memberRRN1').val() + '-' + $('#memberRRN2').val());
			$('#memberPhone').val($('#memberPhone1').val() + '-' + $('#memberPhone2').val() + '-' + $('#memberPhone3').val());
			
			var isValidResult = memberIdIsValid + memberNicknameIsValid + memberPwIsValid + memberPw2IsValid + memberPhoneIsValid + memberEmailIsValid;
			if (isValidResult == 6) {
				return true;
			} else {
				return false;
			}
		}); // end 'input[type="submit"]'.click()
	}); // end document
</script>
</head>
<body>
	<div class="join-wrapper">
		<div class="close"><button type="button" onclick="window.close()">&times;</button></div>
		<h2>회원가입</h2>
		<form class="join-form" action="/mbti/member/join" method="post">
			<div class="display-flex">
				<label for="memberId">아이디</label>
				<input type="text" id="memberId" name="memberId" minlength="5" maxlength="20" pattern="^[a-z][a-z0-9_\-]{4,19}$" placeholder="아이디" required autofocus>
			</div>
			<div class="memberIdIsDuplicated">
				<label id='memberIdIsDuplicated'>사용하실 아이디를 입력해 주세요.</label>
			</div>
			<div class="display-flex">
				<label for="memberPw">비밀번호</label>
				<input type="password" id="memberPw" name="memberPw" minlength="8" maxlength="16" placeholder="비밀번호" pattern="^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*\(\)\-=_+\{\},.\/?\\<>:;])[a-zA-Z0-9!@#$%^&*\(\)\-=_+\{\},.\/?\\<>:;]{8,16}$" required>
			</div>
			<div class="memberPwIsDuplicated">
				<label id='memberPwIsDuplicated'>사용하실 비밀번호를 입력해 주세요.</label>
			</div>
			<div class="display-flex">
				<label for="memberPw2">비밀번호 확인</label>
				<input type="password" id="memberPw2" name="memberPw2" placeholder="비밀번호 확인" pattern="^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*\(\)\-=_+\{\},.\/?\\<>:;])[a-zA-Z0-9!@#$%^&*\(\)\-=_+\{\},.\/?\\<>:;]{8,16}$" required>
			</div>
			<div class="memberPw2IsDuplicated">
				<label id='memberPw2IsDuplicated'>비밀번호를 한번 더 입력해 주세요.</label>
			</div>
			<div class="display-flex">
				<label for="memberNickname">닉네임</label>
				<input type="text" id="memberNickname" name="memberNickname" minlength="2" maxlength="20" placeholder="닉네임" pattern="^[a-zA-Z0-9가-힣][a-zA-Z0-9가-힣_\-]{1,19}$" required>
			</div>
			<div class="memberNicknameIsDuplicated">
				<label id='memberNicknameIsDuplicated'>사용하실 닉네임을 입력해 주세요.</label>
			</div>
			<div class="display-flex">
				<label for="memberName">이름</label>
				<input type="text" id="memberName" name="memberName" minlength="2" maxlength="6" placeholder="이름" pattern="^[가-힣]{2,6}$" required>
			</div>
			<div class="display-flex">
				<label for="memberRRN">주민등록번호</label>
				<div class="memberRRN">
					<input type="text" id="memberRRN1" minlength="6" maxlength="6" placeholder="생년월일 (YYMMDD)" pattern="^\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[01])$" required>&nbsp;<b>-</b>&nbsp;
					<input type="text" id="memberRRN2" minlength="1" maxlength="1" placeholder="＊" required><b>＊＊＊＊＊＊</b>
					<input type="hidden" id="memberRRN" name="memberRRN" minlength="8" maxlength="8" value="" required>
				</div>
			</div>
			<!-- 
			<div class="memberRRNIsDuplicated">
				<label id='memberRRNIsDuplicated'>본인의 주민등록번호를 입력해 주세요.</label>
			</div>		
			 -->
			<div class="display-flex">
				<label for="memberPhone">휴대폰 번호</label>
				<div class="memberPhone">
					<input type="text" id="memberPhone1" minlength="2" maxlength="3" placeholder="010" pattern="^[0-9]{2,3}$" required>&nbsp;<b>-</b>&nbsp;
					<input type="text" id="memberPhone2" minlength="3" maxlength="4" placeholder="1234" pattern="^[0-9]{3,4}$" required>&nbsp;<b>-</b>&nbsp;
					<input type="text" id="memberPhone3" minlength="4" maxlength="4" placeholder="5678" pattern="^[0-9]{4}$" required>
					<input type="hidden" id="memberPhone" name="memberPhone" minlength="11" maxlength="13" value="" required>
				</div>
			</div>
			<div class="memberPhoneIsDuplicated">
				<label id='memberPhoneIsDuplicated'>본인의 휴대폰 번호를 양식에 맞게 입력해 주세요.</label>
			</div>
			<div class="display-flex">
				<label for="memberEmail">이메일</label>
				<input type="email" id="memberEmail" name="memberEmail" placeholder="mbti@gmail.com" pattern="^[a-zA-Z0-9._%+\-]+@[a-zA-Z0-9.\-]+\.[a-zA-Z]{2,}$" required>
			</div>
			<div class="memberEmailIsDuplicated">
				<label id='memberEmailIsDuplicated'>사용하고 계신 이메일을 양식에 맞게 입력해 주세요.</label>
			</div>
			<div class="display-flex">
				<label for="memberRegion">거주 지역</label>
				<select	id="memberRegion" name="memberRegion" required>
					<option value="" disabled selected>선택해주세요</option>
					<option>서울특별시</option>
					<option>경기도</option>
					<option>인천광역시</option>
					<option>강원도</option>
					<option>충청북도</option>
					<option>충청남도</option>
					<option>대전광역시</option>
					<option>경상북도</option>
					<option>경상남도</option>
					<option>울산광역시</option>
					<option>부산광역시</option>
					<option>전라북도</option>
					<option>전라남도</option>
					<option>광주광역시</option>
					<option>제주특별자치도</option>
				</select>
			</div>
			<div class="display-flex">
				<label for="memberMBTI">MBTI</label>
				<select	id="memberMBTI" name="memberMBTI" required>
					<option value="" disabled selected>선택해주세요</option>
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
				<a href="https://www.16personalities.com/ko" target="_blank">아직 MBTI를 모르고 있다면?</a>
			</div>
			<input type="submit" value="가입하기">
		</form>
	</div>
</body>
</html>