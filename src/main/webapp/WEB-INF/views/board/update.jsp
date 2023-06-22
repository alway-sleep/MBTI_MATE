<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>음티메이트 : 게시글 수정</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
}

.update-wrapper {
	margin: auto;
	width: 97%;
	padding: 20px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0px 0px 5px 0px rgba(0, 0, 0, 0.2);
}

.update-wrapper h2 {
	text-align: left;
}

.update-wrapper form {
	display: flex;
	flex-direction: column;
}

.update-wrapper form .board-files {
	display: flex;
	flex-direction: column;
	align-items: flex-start;
	font-size: 12px;
	font-weight: bold;
	border: 1px solid #ccc;
	border-radius: 3px;
	padding: 3px;
	margin: 3px 0;
}

.update-wrapper form .board-files span {
	cursor: pointer;
	font-size: 15px;
	font-weight: bold;
}

.update-wrapper input[type=text],
.update-wrapper input[type=file],
.update-wrapper textarea {
	width: 100%;
	padding: 3px;
	border-radius: 3px;
	border: 1px solid #ccc;
	box-sizing: border-box;
	margin-bottom: 5px;
	font-weight: bold;
}

.update-wrapper input[type=submit],
.update-wrapper button[type=button] {
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 3px;
	padding: 5px 10px;
	cursor: pointer;
	margin-left: 5px;
	margin-top: 3px;
}

.update-wrapper input[type=submit]:hover,
.update-wrapper button[type=button]:hover {
	background-color: #00abff;
}

.update-options {
	display: flex;
	justify-content: flex-end;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function() {
		// redirectAttributes.addFlashAttribute 확인
		<c:if test="${not empty message}">
			alert("${message}");
		</c:if>
		// 게시글 수정 취소
		$('.update-options button[type="button"]').click(function(e) {
			e.preventDefault();
			var targetURL = '/mbti/board/detail';
			$.ajax({
				url: '/mbti/board/detail',
				type: 'GET',
			    data: {
			    	boardNumber : "${sessionScope.target.boardNumber}"
			    	},
				success: function(includeJSP) {
					$('.content').html(includeJSP);
				}
			});
		}); // end $('.update-options button[type="button"]').click(function(e) {})
		// 첨부파일 삭제
		$('.update-wrapper form .board-files span').click(function(e) {
			  e.preventDefault();
			  var boardFiles = '*';
			  var boardFilesArray = "${boardVO.boardFilesArray}";
			  $(this).closest('div').remove();

			  $('.board-files a').each(function() {
			      boardFiles += ', ' + $(this).text();
			  });
			  $('#boardFiles').val(boardFiles);
			});
	}); // end $(document).ready()
</script>
</head>
<body>
	<div class="update-wrapper">
		<h2>게시글 수정</h2>
		<form class="update-form" action="/mbti/board/update" method="POST" enctype="multipart/form-data">
			<input type="hidden" id="boardSection" name="boardSection" value="${boardVO.boardSection}">
			<input type="hidden" id="boardList" name="boardList" value="${boardVO.boardList}">
			<input type="text" id="boardName" name="boardName" value="${boardVO.boardName}" style="border: none;" onfocus="blur()" readonly>
			<input type="text" id="boardTitle" name="boardTitle" minlength="1" maxlength="40" placeholder="제목 입력" value="${boardVO.boardTitle}" required>
			<c:if test="${boardVO.boardFiles ne '*'}">
				<input type="text" value="첨부파일" style="border: none;" onfocus="blur()" readonly>
				<div class="board-files">
						<c:forEach var="fileName" items="${boardVO.boardFilesArray}">
							<div class="board-file"><a href="/mbti/resources/board?boardNumber=${boardVO.boardNumber}&fileName=${fileName}" download="${fileName}">${fileName}</a>&nbsp;&nbsp;<span>&times;</span></div>
						</c:forEach>
						<input type="hidden" id="boardFiles" name="boardFiles" value="${boardVO.boardFiles}">
				</div>
			</c:if>
			<input type="file" id="files" name="files" multiple="multiple">
			<textarea rows="20" cols="120" minlength="2" maxlength="2000" id="boardContent" name="boardContent" placeholder="내용 입력" style="resize: none;" required>${boardVO.boardContent}</textarea>
			<div class="update-options">
				<input type="submit" value="수정">
				<button type="button">취소</button>
			</div>
		</form>
	</div>
</body>
</html>