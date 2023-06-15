<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>음티메이트 : 프로필 사진</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
}

.picture-wrapper {
	margin: 0 auto;
	width: 400px;
	height: 500px; 
	background-color: #fff;
	padding: 10px;
	border-radius: 5px;
	box-shadow: 0px 0px 5px 0px rgba(0, 0, 0, 0.2);
	overflow: hidden;
}

.picture-wrapper h2 {
	text-align: left;
	margin: 10px 0;
}

.picture-wrapper form {
	display: flex;
	flex-direction: column;
}

.picture-wrapper input[type=file] {
	padding: 8px;
	border-radius: 3px;
	border: 1px solid #ccc;
	box-sizing: border-box;
	font-weight: bold;
}

.picture-wrapper input[type=submit] {
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 3px;
	padding: 5px 10px;
	cursor: pointer;
	margin-top: 3px;
}

.picture-wrapper input[type=submit]:hover {
	background-color: #00abff;
}

.picture-wrapper .picture-options {
	display: flex;
	justify-content: space-between;
}

.picture-wrapper .picture-preview {
	display: flex;
	justify-content: center;
	align-items: center;
}

.picture-wrapper .picture-preview img {
	border: 1px solid #ccc;
	border-radius: 50%;
	width: 300px;
	height: 300px;
}
</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		<c:if test="${not empty message}">
			alert("${message}");
			<c:if test="${picturePOSTResult eq 1}">
				window.close();
			</c:if>
		</c:if>
		// 프로필 사진 변경
		$('.picture-options input[type="submit"][value="변경"]').click(function(e) {
			if ($('#file').val() == null || $('#file').val() == '') {
				return false;
			} else {
				$('#file').attr('name', 'file');
				return true;
			}
		})// end $('.picture-options input[type="submit"][value="저장"]').click(function(e) {})
		// 미리보기
		$('#file').on('change', function(e) {
			// 선택한 파일의 타입이 유효하다면
			if (['image/jpeg', 'image/png', 'image/gif', 'image/heic', 'image/heif'].includes(e.target.files[0].type) && e.target.files[0].size < 104857600) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$('.picture-preview').html('<img src="'+e.target.result+'">');
				};
				reader.readAsDataURL(e.target.files[0]);
			// 선택한 파일의 타입이 유효하지 않다면
			} else {
				alert('이미지 파일만 선택해주세요.');
				$('#file').val('');
			}
		}); // end $('#file').on('change', function(e) {})
	}); // end $(document).ready(function() {})
</script>
</head>
<body>
	<div class="picture-wrapper">
		<h2>프로필 사진</h2>
		<form class="picture-form" action="/mbti/member/picture" method="POST" enctype="multipart/form-data">
			<input type="file" id="file" accept="image/jpeg, image/png, image/gif">
			<div class="picture-options">
				<input type="submit" value="기본 프로필 설정">
				<input type="submit" value="변경">
			</div>
		</form>
		<h2>미리보기</h2>
		<div class="picture-preview">
		</div>
	</div>
</body>
</html>