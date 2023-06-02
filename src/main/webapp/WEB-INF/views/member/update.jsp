<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>음티메이트 : 마이 페이지</title>
<meta charset="UTF-8">
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
}

.update-wrapper {
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

.update-wrapper h2,
.update-modal-wrapper h2 {
	text-align: center;
	margin-top: 0;
}

.update-wrapper form {
	display: flex;
	flex-direction: column;
}

.update-wrapper input[readonly] {
	background-color: #ccc;
	font-weight: bold;
}

.update-wrapper input[type=text],
.update-wrapper input[type=password],
.update-wrapper input[type=email],
.update-wrapper select {
	padding: 8px;
	border-radius: 3px;
	border: 1px solid #ccc;
	box-sizing: border-box;
	margin-bottom: 5px;
	width: 80%;
	font-weight: bold;
}
/* select, placeholder 스타일 */
.update-wrapper select,
.update-wrapper select option {
	font-weight: bold;
}
/* 주민등록번호 스타일 */
.update-wrapper .memberRRN input[type=text]:nth-child(1) {
	text-align: center;
	width: 150px;
}

.update-wrapper .memberRRN input[type=text]:nth-child(3) {
	text-align: center;
	width: 50px;
}
/* 휴대폰 번호 스타일 */
.update-wrapper .memberPhone input[type="text"] {
	text-align: center;
	width: 94px;
}
.update-wrapper button,
.update-wrapper input[type=submit],
.update-wrapper input[type=button],
.update-wrapper button[type=submit] {
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 3px;
	padding: 10px 10px;
	cursor: pointer;
	margin-top: 10px;
	width: 100%
}
.update-wrapper button:hover,
.update-wrapper input[type=submit]:hover,
.update-wrapper input[type=button]:hover,
.update-wrapper button[type=submit]:hover {
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
.update-wrapper #memberIdIsDuplicated,
.update-wrapper #memberNicknameIsDuplicated,
.update-wrapper #memberPwIsDuplicated,
.update-wrapper #memberPw2IsDuplicated,
.update-wrapper #memberPwNewIsDuplicated,
.update-wrapper #memberPwNew2IsDuplicated,
.update-wrapper #memberRRNIsDuplicated,
.update-wrapper #memberPhoneIsDuplicated,
.update-wrapper #memberEmailIsDuplicated {
	display: flex;
	color: #003bff;
	margin: 0 0 5px 80px;
}

.update-wrapper #memberIdIsDuplicated0,
.update-wrapper #memberNicknameIsDuplicated0,
.update-wrapper #memberPwIsDuplicated0,
.update-wrapper #memberPw2IsDuplicated0,
.update-wrapper #memberPwNewIsDuplicated0,
.update-wrapper #memberPwNew2IsDuplicated0,
.update-wrapper #memberRRNIsDuplicated0,
.update-wrapper #memberPhoneIsDuplicated0,
.update-wrapper #memberEmailIsDuplicated0 {
	color: green;
	margin: 0 0 5px 80px;
}

.update-wrapper #memberIdIsDuplicated1,
.update-wrapper #memberNicknameIsDuplicated1,
.update-wrapper #memberPwIsDuplicated1,
.update-wrapper #memberPw2IsDuplicated1,
.update-wrapper #memberPwNewIsDuplicated1,
.update-wrapper #memberPwNew2IsDuplicated1,
.update-wrapper #memberRRNIsDuplicated1,
.update-wrapper #memberPhoneIsDuplicated1,
.update-wrapper #memberEmailIsDuplicated1 {
	color: red;
}

.update-wrapper .memberIdIsDuplicated label,
.update-wrapper .memberNicknameIsDuplicated label,
.update-wrapper .memberPwIsDuplicated label,
.update-wrapper .memberPw2IsDuplicated label,
.update-wrapper .memberPwNewIsDuplicated label,
.update-wrapper .memberPwNew2IsDuplicated label,
.update-wrapper .memberRRNIsDuplicated label,
.update-wrapper .memberPhoneIsDuplicated label,
.update-wrapper .memberEmailIsDuplicated label {
	font-weight: bold;
	font-size: 12px;
}
/* 모달 스타일 */
.mypage-update-modal,
.mypage-delete-modal,
.mypage-memberPwNew-modal {
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
.update-modal-wrapper,
.delete-modal-wrapper,
.memberPwNew-modal-wrapper {
	display: flex;
	flex-direction: column;
	background-color: #fefefe;
	margin: 15% auto; /* 화면 중앙에 위치 */
	padding: 20px;
	border: 1px solid #888;
	width: 80%;
}
/* login-options button 스타일 */
.update-options {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	margin-top: 5px;
	border: 1px solid #fff;
}
.update-options button[type="button"] {
	background-color: #fff;
	color: #005bff;
	border: none;
	cursor: pointer;
	font-size: 14px;
	padding: 0;
	margin: 0;
}
.update-options button[type="button"]:hover {
	color: #00abff;
}
</style>
</head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(document).ready(function() {
		<c:if test="${not empty message}">
			alert("${message}");
		</c:if>
		// 거주 지역 selected
		$('#memberRegion option').each(function() {
			  if ($(this).text() == "${sessionScope.memberVO.memberRegion}") {
			    $(this).prop('selected', true);
			    return false; // 순회 중단
			  }
		});		
		// MBTI selected
		$('#memberMBTI option').each(function() {
			if ($(this).text().split(':')[0] == "${sessionScope.memberVO.memberMBTI}") {
			    $(this).prop('selected', true);
			    return false; // 순회 중단
			 }
		});
		// 전역변수
		var memberNumber = $('#memberNumber').val();
		var memberNicknameIsValid = 1;
		var memberPwIsValid = 0;
		var memberPw2IsValid = 0;
		var memberPhoneIsValid = 1;
		var memberEmailIsValid = 1;
		// 닉네임 유효성검사
		$('#memberNickname').keyup(function() {
			var memberNickname = $('#memberNickname').val();
			const memberNicknamePattern = new RegExp(/^[a-zA-Z0-9가-힣][a-zA-Z0-9가-힣_\-]{1,19}$/);
			if (memberNickname == "") {
				$('.memberNicknameIsDuplicated').html("<label id='memberNicknameIsDuplicated'>변경하실 닉네임을 입력해 주세요.</label>");
			} else if (memberNickname.length <= 1 || !memberNicknamePattern.test(memberNickname)) {
				$('.memberNicknameIsDuplicated').html("<label id='memberNicknameIsDuplicated1'>닉네임은 2~20자리이며 한글 또는 영문 또는 숫자로 시작되어야 하고 이후<br>한글, 영문, 숫자, 하이픈(-), 언더스코어(-)만 사용 가능합니다.</label>");
			} else {
				$.ajax({
					url: '../memberrest/memberNicknameString',
					type: 'GET',
					headers: {'Content-Type' : 'application/json'},
					data: {'memberNickname' : memberNickname, 'memberNumber' : memberNumber},
					success: function(memberNicknameStringResult) {
						if (memberNicknameStringResult == 'same') {
							$('.memberNicknameIsDuplicated').html("<label id='memberNicknameIsDuplicated'>변경사항이 없습니다.</label>");
							memberNicknameIsValid = 1;
						} else if (memberNicknameStringResult == 'unusable') {
							$('.memberNicknameIsDuplicated').html("<label id='memberNicknameIsDuplicated1'>이미 사용 중인 닉네임입니다.</label>");
							memberNicknameIsValid = 0;							
						} else {
							$('.memberNicknameIsDuplicated').html("<label id='memberNicknameIsDuplicated0'>사용 가능한 닉네임입니다.</label>");
							memberNicknameIsValid = 1;
						}
					}						
				}); // end ajax()				
			}			
		}); // $('#memberNickname').keyup()
		// 회원정보 수정 시 비밀번호 유효성검사
		$('#memberPw').keyup(function() {
			var memberPw = $('#memberPw').val();
			if (memberPw == "") {
			    $('.memberPwIsDuplicated').html("<label id='memberPwIsDuplicated'>본인확인을 위해 비밀번호를 입력해 주세요.</label>");
			} else {
			    $.ajax({
			        url: '../memberrest/memberPwString',
			        type: 'GET',
			        headers: {'Content-Type': 'application/json'},
			        data: {'memberNumber' : memberNumber},
			        success: function (memberPwStrResult) {
			            if (memberPwStrResult === memberPw) {
			                $('.memberPwIsDuplicated').html("<label id='memberPwIsDuplicated0'>비밀번호가 일치합니다.</label>");
			                memberPwIsValid = 1;
			            } else {
			                $('.memberPwIsDuplicated').html("<label id='memberPwIsDuplicated1'>비밀번호가 일치하지 않습니다.</label>");
			                memberPwIsValid = 0;
			            }
			        }
			    }); // end ajax()
			}				
		}); // $('#memberPw').keyup()
		$('#memberPw2').keyup(function() {
			var memberPw = $('#memberPw').val();
			var memberPw2 = $('#memberPw2').val();
			if (memberPw2 == "") {
				$('.memberPw2IsDuplicated').html("<label id='memberPw2IsDuplicated'>본인확인을 위해 비밀번호를 다시 입력해 주세요.</label>");
			} else if (memberPwIsValid == 0 || memberPw !== memberPw2) {
				$('.memberPw2IsDuplicated').html("<label id='memberPw2IsDuplicated1'>비밀번호가 일치하지 않습니다.</label>");
				memberPw2IsValid = 0;
			} else {
				$('.memberPw2IsDuplicated').html("<label id='memberPw2IsDuplicated0'>비밀번호가 일치합니다.</label>");
				memberPw2IsValid = 1;
			}
		}); // $('#memberPw2').keyup()
		// 비밀번호 변경 시 비밀번호 유효성검사
		$('#memberPwOri').keyup(function() {
			var memberPw = $('#memberPwOri').val();
			if (memberPw == "") {
			    $('.memberPwIsDuplicated').html("<label id='memberPwIsDuplicated'>본인확인을 위해 비밀번호를 입력해 주세요.</label>");
			} else {
			    $.ajax({
			        url: '../memberrest/memberPwString',
			        type: 'GET',
			        headers: {'Content-Type': 'application/json'},
			        data: {'memberNumber' : memberNumber},
			        success: function (memberPwStrResult) {
			            if (memberPwStrResult === memberPw) {
			                $('.memberPwIsDuplicated').html("<label id='memberPwIsDuplicated0'>비밀번호가 일치합니다.</label>");
			                memberPwIsValid = 1;
			            } else {
			                $('.memberPwIsDuplicated').html("<label id='memberPwIsDuplicated1'>비밀번호가 일치하지 않습니다.</label>");
			                memberPwIsValid = 0;
			            }
			        }
			    }); // end ajax()
			}				
		}); // $('#memberPwOri').keyup()
		$('#memberPwOri2').keyup(function() {
			var memberPw = $('#memberPwOri').val();
			var memberPw2 = $('#memberPwOri2').val();
			if (memberPw2 == "") {
				$('.memberPw2IsDuplicated').html("<label id='memberPw2IsDuplicated'>본인확인을 위해 비밀번호를 다시 입력해 주세요.</label>");
			} else if (memberPwIsValid == 0 || memberPw !== memberPw2) {
				$('.memberPw2IsDuplicated').html("<label id='memberPw2IsDuplicated1'>비밀번호가 일치하지 않습니다.</label>");
				memberPw2IsValid = 0;
			} else {
				$('.memberPw2IsDuplicated').html("<label id='memberPw2IsDuplicated0'>비밀번호가 일치합니다.</label>");
				memberPw2IsValid = 1;
			}
		}); // $('#memberPwOri2').keyup()
		$('#memberPwNew').keyup(function() {
			var memberPw = $('#memberPwNew').val();
			const memberPwPattern = new RegExp(/^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*\(\)\-=_+\{\},.\/?\\<>:;])[a-zA-Z0-9!@#$%^&*\(\)\-=_+\{\},.\/?\\<>:;]{8,16}$/);
			if (memberPw == "") {
				$('.memberPwNewIsDuplicated').html("<label id='memberPwNewIsDuplicated'>사용하실 비밀번호를 입력해 주세요.</label>");
			} else if (memberPw.length <= 7 || !memberPwPattern.test(memberPw)) {
				$('.memberPwNewIsDuplicated').html("<label id='memberPwNewIsDuplicated1'>비밀번호는 8~16자리이며<br>영문, 숫자, 특수문자(!@#$%^&*()-=_+{},./?\<>:;)가<br>각각 1개 이상씩 포함되어있어야 합니다.</label>");
				memberPwIsValid = 0;
			} else {
				$('.memberPwNewIsDuplicated').html("<label id='memberPwNewIsDuplicated0'>사용 가능한 비밀번호입니다.</label>");
				memberPwIsValid = 1;
			} 
		}); // end $('#memberPwNew').keyup()		
		$('#memberPwNew2').keyup(function() {
			var memberPw = $('#memberPwNew').val();
			var memberPw2 = $('#memberPwNew2').val();
			if (memberPw2 == "") {
				$('.memberPwNew2IsDuplicated').html("<label id='memberPwNew2IsDuplicated'>비밀번호를 한번 더 입력해 주세요.</label>");
			} else if (memberPw !== memberPw2) {
				$('.memberPwNew2IsDuplicated').html("<label id='memberPwNew2IsDuplicated1'>비밀번호가 일치하지 않습니다.</label>");
				memberPw2IsValid = 0;
			} else {
				$('.memberPwNew2IsDuplicated').html("<label id='memberPwNew2IsDuplicated0'>비밀번호가 일치합니다.</label>");
				memberPw2IsValid = 1;
			} 
		}); // end $('#memberPwNew2').keyup()
		// 회원탈퇴 시 비밀번호 유효성검사
		$('#memberPwDel').keyup(function() {
			var memberPw = $('#memberPwDel').val();
			if (memberPw == "") {
			    $('.memberPwIsDuplicated').html("<label id='memberPwIsDuplicated'>본인확인을 위해 비밀번호를 입력해 주세요.</label>");
			} else {
			    $.ajax({
			        url: '../memberrest/memberPwString',
			        type: 'GET',
			        headers: {'Content-Type': 'application/json'},
			        data: {'memberNumber' : memberNumber},
			        success: function (memberPwStrResult) {
			            if (memberPwStrResult === memberPw) {
			                $('.memberPwIsDuplicated').html("<label id='memberPwIsDuplicated0'>비밀번호가 일치합니다.</label>");
			                memberPwIsValid = 1;
			            } else {
			                $('.memberPwIsDuplicated').html("<label id='memberPwIsDuplicated1'>비밀번호가 일치하지 않습니다.</label>");
			                memberPwIsValid = 0;
			            }
			        }
			    }); // end ajax()
			}				
		}); // $('#memberPwDel').keyup()
		$('#memberPw2Del').keyup(function() {
			var memberPw = $('#memberPwDel').val();
			var memberPw2 = $('#memberPw2Del').val();
			if (memberPw2 == "") {
				$('.memberPw2IsDuplicated').html("<label id='memberPw2IsDuplicated'>본인확인을 위해 비밀번호를 다시 입력해 주세요.</label>");
			} else if (memberPwIsValid == 0 || memberPw !== memberPw2) {
				$('.memberPw2IsDuplicated').html("<label id='memberPw2IsDuplicated1'>비밀번호가 일치하지 않습니다.</label>");
				memberPw2IsValid = 0;
			} else {
				$('.memberPw2IsDuplicated').html("<label id='memberPw2IsDuplicated0'>비밀번호가 일치합니다.</label>");
				memberPw2IsValid = 1;
			}
		}); // $('#memberPw2Del').keyup()
		// 휴대폰 번호 유효성검사
		$('#memberPhone').keyup(function() {
			var memberPhone1 = $('#memberPhone1').val();
			var memberPhone2 = $('#memberPhone2').val();
			var memberPhone3 = $('#memberPhone3').val();
			var memberPhone = memberPhone1 + '-' + memberPhone2 + '-' + memberPhone3;
			const memberPhone1Pattern = new RegExp(/^[0-9]{2,3}$/);
			const memberPhone2Pattern = new RegExp(/^[0-9]{3,4}$/);
			const memberPhone3Pattern = new RegExp(/^[0-9]{4}$/);
			if (memberPhone1 == "" || memberPhone2 == "" || memberPhone3 == "") {
			    $('.memberPhoneIsDuplicated').html("<label id='memberPhoneIsDuplicated'>변경된 휴대폰 번호를 양식에 맞게 입력해 주세요.</label>");
			} else if (!memberPhone1Pattern.test(memberPhone1) || !memberPhone2Pattern.test(memberPhone2) || !memberPhone3Pattern.test(memberPhone3)) {
			    $('.memberPhoneIsDuplicated').html("<label id='memberPhoneIsDuplicated1'>양식에 맞게 다시 입력해 주세요.</label>");
			} else {
			    $.ajax({
			        url: '../memberrest/memberPhoneString',
			        type: 'GET',
			        headers: {'Content-Type': 'application/json'},
			        data: {'memberPhone': memberPhone, 'memberNumber' : memberNumber},
			        success: function (memberPhoneStringResult) {
			            if (memberPhoneStringResult == 'same') {
			                $('.memberPhoneIsDuplicated').html("<label id='memberPhoneIsDuplicated'>변경사항이 없습니다.</label>");
			                memberPhoneIsValid = 1;
			            } else if (memberPhoneStringResult == 'unusable') {
			                $('.memberPhoneIsDuplicated').html("<label id='memberPhoneIsDuplicated1'>이미 사용 중인 휴대폰 번호입니다.</label>");
			                memberPhoneIsValid = 0;
			            } else {
			                $('.memberPhoneIsDuplicated').html("<label id='memberPhoneIsDuplicated0'>변경 가능한 휴대폰 번호입니다.</label>");
			                memberPhoneIsValid = 1;
			            }
			        }
			    }); // end ajax()
			}			
		}); // $('#memberPhone').keyup()
		// 이메일 유효성검사
		$('#memberEmail').keyup(function() {
			var memberEmail = $('#memberEmail').val();
			const memberEmailPattern = new RegExp(/^[a-zA-Z0-9._%+\-]+@[a-zA-Z0-9.\-]+\.[a-zA-Z]{2,}$/);
			if (memberEmail == "") {
				$('.memberEmailIsDuplicated').html("<label id='memberEmailIsDuplicated'>변경된 이메일을 양식에 맞게 입력해 주세요.</label>");
			} else if (!memberEmailPattern.test(memberEmail)) {
				$('.memberEmailIsDuplicated').html("<label id='memberEmailIsDuplicated1'>양식에 맞게 다시 입력해 주세요.</label>");
			} else {
				$.ajax({
					url: '../memberrest/memberEmailString',
					type: 'GET',
					headers: {'Content-Type' : 'application/json'},
					data: {'memberEmail' : memberEmail, 'memberNumber' : memberNumber},
					success: function(memberEmailStringResult) {
						if (memberEmailStringResult == 'same') {
							$('.memberEmailIsDuplicated').html("<label id='memberEmailIsDuplicated'>변경사항이 없습니다.</label>");
							memberEmailIsValid = 1;
						} else if (memberEmailStringResult == 'unusable') {
							$('.memberEmailIsDuplicated').html("<label id='memberEmailIsDuplicated1'>이미 사용 중인 이메일입니다.</label>");
							memberEmailIsValid = 0;							
						} else {
							$('.memberEmailIsDuplicated').html("<label id='memberEmailIsDuplicated0'>변경 가능한 이메일입니다.</label>");
							memberEmailIsValid = 1;
						}
					}						
				}); // end ajax()
			}			
		}); // $('#memberEmail').keyup()
		// 회원정보 수정 유효성검사
		$('input[type="submit"][value="회원정보 수정"]').click(function() {
			$('#memberRRN').val($('#memberRRN1').val() + '-' + $('#memberRRN2').val());
			$('#memberPhone').val($('#memberPhone1').val() + '-' + $('#memberPhone2').val() + '-' + $('#memberPhone3').val());			
			var isValidResult = memberNicknameIsValid + memberPwIsValid + memberPw2IsValid + memberPhoneIsValid + memberEmailIsValid;
			if (isValidResult == 5) {
				return true;
			} else {
				return false;
			}
		}); // end $('input[type="submit"][value="회원정보 수정"]').click()
		// 회원정보 수정 유효성검사
		$('input[type="submit"][value="비밀번호 변경"]').click(function() {			
			var isValidResult = memberPwOriIsValid + memberPwOri2IsValid + memberPwNewIsValid + memberPwNew2IsValid;
			if (isValidResult == 4) {
				return true;
			} else {
				return false;
			}
		}); // end $('input[type="submit"][value="회원정보 수정"]').click()
		// 회원탈퇴 유효성검사
		$('input[type="submit"][value="회원탈퇴"]').click(function() {
			var isValidResult = memberPwIsValid + memberPw2IsValid;
			if (isValidResult == 2) {
				return true;
			} else {
				return false;
			}
		}); // end $('input[type="submit"][value="회원탈퇴"]').click()
		// modal 엔터키 입력 이벤트
		$('.button1').click(function() { // 회원정보 수정
			$('.mypage-update-modal').css('display', 'block');
			$('input[value="회원탈퇴"]').attr('type', 'button');
			$('input[value="비밀번호 변경"]').attr('type', 'button');
			$('input[value="회원정보 수정"]').attr('type', 'submit');
		});
		$('.button2').click(function() { // 비밀번호 변경
			$('.mypage-memberPwNew-modal').css('display', 'block');
			$('input[value="회원정보 수정"]').attr('type', 'button');
			$('input[value="회원탈퇴"]').attr('type', 'button');
			$('input[value="비밀번호 변경"]').attr('type', 'submit');
		});
		$('.button3').click(function() { // 회원탈퇴
			$('.mypage-delete-modal').css('display', 'block');
			$('input[value="회원정보 수정"]').attr('type', 'button');
			$('input[value="비밀번호 변경"]').attr('type', 'button');
			$('input[value="회원탈퇴"]').attr('type', 'submit');
		});
	}); // end document
</script>
<body>
	<div class="update-wrapper">
		<div class="close"><button type="button" onclick="window.location.href='/mbti/member/mypage'">&times;</button></div>
		<h2>회원정보 수정</h2>
		<form class="update-form">
			<input type="hidden" id="memberNumber" name="memberNumber" value="${sessionScope.memberVO.memberNumber }">
			<div class="display-flex">
				<label for="memberId">아이디</label>
				<input type="text" id="memberId" value="${sessionScope.memberVO.memberId}" readonly tabindex="-1">
			</div>
			<div class="display-flex">
				<label for="memberNickname">닉네임</label>
				<input type="text" id="memberNickname" name="memberNickname" minlength="2" maxlength="20" placeholder="닉네임" pattern="^[a-zA-Z0-9가-힣][a-zA-Z0-9가-힣_\-]{1,19}$" value="${sessionScope.memberVO.memberNickname}" required>
			</div>
			<div class="memberNicknameIsDuplicated">
				<label id='memberNicknameIsDuplicated'>변경하실 닉네임을 입력해 주세요.</label>
			</div>
			<div class="display-flex">
				<label for="memberName">이름</label>
				<input type="text" id="memberName" value="${sessionScope.memberVO.memberName}" readonly tabindex="-1">
			</div>
			<div class="display-flex">
				<label for="memberRRN">주민등록번호</label>
				<div class="memberRRN">
					<input type="text" id="memberRRN1" value="${sessionScope.memberVO.memberRRN.split('-')[0]}" readonly tabindex="-1">&nbsp;<b>-</b>&nbsp;
					<input type="text" id="memberRRN2" value="${sessionScope.memberVO.memberRRN.split('-')[1]}" readonly tabindex="-1"><b>＊＊＊＊＊＊</b>
				</div>
			</div>
			<div class="display-flex">
				<label for="memberPhone">휴대폰 번호</label>
				<div class="memberPhone">
					<input type="text" id="memberPhone1" value="${sessionScope.memberVO.memberPhone.split('-')[0]}">&nbsp;<b>-</b>&nbsp;
					<input type="text" id="memberPhone2" value="${sessionScope.memberVO.memberPhone.split('-')[1]}">&nbsp;<b>-</b>&nbsp;
					<input type="text" id="memberPhone3" value="${sessionScope.memberVO.memberPhone.split('-')[2]}">
					<input type="hidden" id="memberPhone" name="memberPhone" minlength="11" maxlength="13" value="" required>
				</div>
			</div>
			<div class="memberPhoneIsDuplicated">
				<label id='memberPhoneIsDuplicated'>변경된 휴대폰 번호를 양식에 맞게 입력해 주세요.</label>
			</div>
			<div class="display-flex">
				<label for="memberEmail">이메일</label>
				<input type="email" id="memberEmail" name="memberEmail" placeholder="mbti@gmail.com" pattern="^[a-zA-Z0-9._%+\-]+@[a-zA-Z0-9.\-]+\.[a-zA-Z]{2,}$" value="${sessionScope.memberVO.memberEmail}" required>
			</div>
			<div class="memberEmailIsDuplicated">
				<label id='memberEmailIsDuplicated'>변경된 이메일을 양식에 맞게 입력해 주세요.</label>
			</div>
			<div class="display-flex">
				<label for="memberRegion">거주 지역</label>
				<select	id="memberRegion" name="memberRegion" required>
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
			<button type="button" class="button1">회원정보 수정</button>
			<div class="update-options">
		    	<button type="button" class="button2">비밀번호 변경</button>
		    	<button type="button" class="button3">회원탈퇴</button>
	  	 	 </div>
			<!-- 본인확인을 위한 updateModal -->
			<div class="modal">
				<jsp:include page="mypage-update-modal.jsp"/>
				<jsp:include page="mypage-memberPwNew-modal.jsp"/>
				<jsp:include page="mypage-delete-modal.jsp"/>				
			</div>
		</form>
	</div>
</body>
</html>