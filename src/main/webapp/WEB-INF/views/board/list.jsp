<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>음티메이트 : 게시판</title>
<style type="text/css">
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
}

.list-wrapper {
	margin: auto;
	width: 97%;
	padding: 20px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0px 0px 5px 0px rgba(0, 0, 0, 0.2);
}

.list-wrapper h2 {
	text-align: left;
}

.list-wrapper table {
	border-collapse: collapse;
	width: 100%;
	background-color: #fff;
	font-size: 15px;
	font-weight: bold;
}

.list-wrapper th {
	background-color: #007bff;
	color: #fff;
}

.list-wrapper th,
.list-wrapper td {
	border: 1px solid #000;
	text-align: center;
	padding: 5px;
}

.list-wrapper th:nth-child(1) {
	width: 60px;
}

.list-wrapper th:nth-child(2) {
	width: 700px;
}

.list-wrapper th:nth-child(3) {
	width: 120px;
}

.list-wrapper th:nth-child(4) {
	width: 100px;
}

.list-wrapper th:nth-child(5) {
	width: 60px;
}

.list-wrapper th:nth-child(6) {
	width: 60px;
}

.list-wrapper ul {
	list-style-type: none;
	display: flex;
	justify-content: center;
	align-items: center;
	margin-top: 20px;
}

.list-wrapper li {
	margin: 5px;
}

.list-wrapper .list-options {
	display: flex;
	justify-content: flex-end;
}

.list-wrapper button[type=button] {
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 3px;
	padding: 5px 10px;
	cursor: pointer;
	margin-left: 5px;
	margin-top: 3px;
}

.list-wrapper button[type="button"]:hover {
	background-color: #00abff;
}

.list-wrapper table a {
	font-size: 17px;
}

.list-wrapper a {
	text-decoration: none;
	color: #000;
	font-weight: bold;
	padding: 2px;
}

.list-wrapper a:hover {
	text-decoration: underline;
}

.list-wrapper .link_startPage,
.list-wrapper .link_endPage,
.list-wrapper .link_listPage,
.list-wrapper .link_prevPage,
.list-wrapper .link_nextPage {
	background-color: #fff;
	color: #000;
	border: none;
	border-radius: 3px;
	padding: 2px 0;
	margin: 0 4px;
	font-weight: 700;
}

.list-wrapper .link_startPage:hover, 
.list-wrapper .link_endPage:hover,
.list-wrapper .link_listPage:hover,
.list-wrapper .link_prevPage:hover,
.list-wrapper .link_nextPage:hover {
	background-color: lightgrey;
}
</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(document).ready(function() {
		// redirectAttributes.addFlashAttribute 확인
		<c:if test="${not empty message}">
			alert("${message}");
		</c:if>
		// 게시글 보기
		$('tbody td').closest('tr').find('a').click(function(e) {
			e.preventDefault();
			<c:if test="${empty sessionScope.memberVO}">
				window.open('/mbti/member/login','음티메이트 : 로그인', 'width=450px, height=260px').onbeforeunload =
					function() {
					location.reload();
					}				
			</c:if>
			<c:if test="${not empty sessionScope.memberVO}">
				var boardNumber = parseInt($(this).closest('tr').find('.boardNumber').text());
				$.ajax({
					url: '/mbti/board/detail',
					type: 'GET',
				    data: {
				    	boardNumber : boardNumber
				    	},
					success: function(includeJSP) {
						$('.content').html(includeJSP);
					}
				});
			</c:if>
		}); // end $('tbody td').closest('tr').find('a').click()		
		// 게시글 작성
		$('.list-options button[type="button"]').click(function(e) {
			e.preventDefault();
			<c:if test="${empty sessionScope.memberVO}">
				window.open('/mbti/member/login','음티메이트 : 로그인', 'width=450px, height=260px').onbeforeunload = function(){location.reload();}				
			</c:if>
			<c:if test="${not empty sessionScope.memberVO}">
				// '공지사항' 이외의 모든 게시판
				if ("${sessionScope.target.boardName}" != '공지사항') {
					$.ajax({
						url: '/mbti/board/write',
						type: 'GET',
						success: function(includeJSP) {
							$('.content').html(includeJSP);
						}
					});
				  // '공지사항'게시판이라면 '카페 매니저'만 글 작성 가능
				} else if ("${sessionScope.memberVO.memberGrade}" == 5) {
					$.ajax({
						url: '/mbti/board/write',
						type: 'GET',
						success: function(includeJSP) {
							$('.content').html(includeJSP);
						}
					});
				} else {
					alert('공지사항 작성 권한이 없습니다.');
					return false;
				}
			</c:if>
		}); // end $('.list-options button[type="button"]').click(function(e) {})		
		// 페이지 목록
		$('.list-page').on('click', 'li', function(e) {
			e.preventDefault();
			// 처음 페이지 목록
			if ($(this).hasClass("listGET-start")) {
				$('.content').load(window.location.href + "board/list?page=1&boardSection=${sessionScope.target.boardSection}&boardList=${sessionScope.target.boardList}&boardName=${sessionScope.target.boardName}");
			}
			// 이전 페이지 목록
			if ($(this).hasClass("listGET-prev")) {
				$('.content').load(window.location.href + "board/list?page=${pageMaker.endPageNo-1}&boardSection=${sessionScope.target.boardSection}&boardList=${sessionScope.target.boardList}&boardName=${sessionScope.target.boardName}");
			}
			// 현재 페이지
			if ($(this).hasClass("listGET-page")) {
				$('.content').load(window.location.href + "board/list?page="+parseInt($(this).text())+"&boardSection=${sessionScope.target.boardSection}&boardList=${sessionScope.target.boardList}&boardName=${sessionScope.target.boardName}");
			}
			// 다음 페이지 목록
			if ($(this).hasClass("listGET-next")) {
				$('.content').load(window.location.href + "board/list?page=${pageMaker.endPageNo+1}&boardSection=${sessionScope.target.boardSection}&boardList=${sessionScope.target.boardList}&boardName=${sessionScope.target.boardName}");
			}
			// 마지막 페이지 목록
			if ($(this).hasClass("listGET-end")) {
				$('.content').load(window.location.href + "board/list?page=${pageMaker.totalLinkNo}&boardSection=${sessionScope.target.boardSection}&boardList=${sessionScope.target.boardList}&boardName=${sessionScope.target.boardName}");
			}
		}); // end $('.list-page').on('click', 'li', function(e) {})
	}); // end $(document).ready()
</script>
</head>
<body>
	<div class="list-wrapper">
		<h2>${sessionScope.target.boardName}</h2>
		<table class="list-table">
			<thead>
				<tr>
					<th style="width: 60px">번호</th>
					<th style="width: 700px">제목</th>
					<th style="width: 120px">작성자</th>
					<th style="width: 100px">작성일</th>
					<th style="width: 60px">조회</th>
					<th style="width: 60px">좋아요</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="boardVO" items="${boardVO}">
					<tr>
						<td class="boardNumber">${boardVO.boardNumber}</td>
						<td><a href="#">${boardVO.boardTitle}(${boardVO.boardComments})</a></td>
						<td>${boardVO.memberNickname}</td>
						<fmt:formatDate value="${boardVO.boardRegdate}" pattern="yyyy-MM-dd" var="boardRegdate" />
						<td>${boardRegdate}</td>
						<td>${boardVO.boardViews}</td>
						<td>${boardVO.boardLikes}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:if test="${sessionScope.target.boardSection != 0}">
			<div class="list-options">
				<button type="button">글쓰기</button>
			</div>
		</c:if>
		<ul class="list-page">
				<li><a href="#" class="listGET-start">처음</a></li>
			<c:if test="${pageMaker.hasPrev}">
				<li><a href="#" class="listGET-prev">이전</a></li>
			</c:if>
			<c:forEach var="num" begin="${pageMaker.startPageNo}" end="${pageMaker.endPageNo}">
				<li><a href="#" class="listGET-page">${num}</a></li>
			</c:forEach>
			<c:if test="${pageMaker.hasNext}">
				<li><a href="#"	class="listGET-next">다음</a></li>
			</c:if>
				<li><a href="#" class="listGET-end">끝</a></li>
		</ul>
	</div>
</body>
</html>