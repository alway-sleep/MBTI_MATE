<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>음티메이트 : 글쓰기</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
}

.write-wrapper {
	margin: auto;
	width: 97%;
	padding: 20px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0px 0px 5px 0px rgba(0, 0, 0, 0.2);
}

.write-wrapper h2 {
	text-align: left;
}

.write-wrapper form {
	display: flex;
	flex-direction: column;
}

.write-wrapper input[type=text],
.write-wrapper textarea {
	width: 100%;
	padding: 8px;
	border-radius: 3px;
	border: 1px solid #ccc;
	box-sizing: border-box;
	margin-bottom: 5px;
	font-weight: bold;
}

.write-wrapper input[type=submit],
.write-wrapper button[type=button] {
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 3px;
	padding: 5px 10px;
	cursor: pointer;
	margin-left: 5px;
	margin-top: 3px;
}

.write-wrapper input[type=submit]:hover,
.write-wrapper button[type=button]:hover {
	background-color: #00abff;
}

.write-options {
	display: flex;
	justify-content: flex-end;
}
</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(document).ready(function() {
		// redirectAttributes.addFlashAttribute 확인
		<c:if test="${not empty message}">
			alert("${message}");
		</c:if>
		// 글쓰기 취소
		$('.write-options button[type="button"]').click(function(e) {
			$.ajax({
				url: '/mbti/board/list',
				type: 'GET',
				success: function(includeJSP) {
					$('.content').html(includeJSP);
				}
			});
		}); // end $('.write-options button[type="button"]').click()
		// 선택한 파일의 용량이 100MB를 초과할 때
		$('.files').on('change', function(e) {
			for(var i = 0; i < e.target.files.length; i++) {
				if (e.target.files[i].size > 104857600) {
					alert('최대 100MB의 파일만 업로드 가능합니다.');
					$('#files').val('');
				}
			}
		}); // end $('.files').on('change', function(e) {})
 		// 파일 선택 후 업로드
		$('.write-options input[value="등록"]').click(function(e) {
			if ($('#files').val() === '') {
				$('#files').removeAttr('name');
			}
		}); // end $('.write-options input[type="submit"][value="등록"]').click(function(e) {})
	}); // end $(document).ready()
</script>
</head>
<body>
	<div class="write-wrapper">
		<form class="write-form" action="/mbti/board/write" method="POST" enctype="multipart/form-data">
			<h2>게시글 작성</h2>
			<input type="text" id="boardName" name="boardName" value="${sessionScope.target.boardName}" style="border: none;" onfocus="blur()" readonly>
			<input type="text" id="boardTitle" name="boardTitle" minlength="1" maxlength="40" placeholder="제목 입력" required>
			<input type="file" id="files" name="files" multiple="multiple">
			<textarea rows="20" cols="120" minlength="2" maxlength="2000" id="boardContent" name="boardContent" placeholder="내용 입력" style="resize: none;" required></textarea>
			<div class="write-options">
				<input type="submit" value="등록">
				<button type="button">취소</button>
			</div>
		</form>
	</div>
</body>
</html>