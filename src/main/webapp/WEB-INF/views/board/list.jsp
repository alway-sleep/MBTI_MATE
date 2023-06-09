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
	display: flex;
	flex-direction: column;
}

.list-wrapper h2 {
	text-align: left;
	width: 80%;
	align-self: center;
}

.list-wrapper .boardNumsPerPage {
	text-align: right;
	width: 80%;
	align-self: center;
	margin-bottom: 3px;
}

.list-wrapper .boardNumsPerPage select,
.list-wrapper .boardNumsPerPage select option {
	font-weight: bold;
}

.list-wrapper table {
	border-collapse: collapse;
	width: 80%;
	background-color: #fff;
	font-size: 13px;
	font-weight: bold;
	margin-bottom: 5px;
	align-self: center;
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
	width: 50px;
}

.list-wrapper th:nth-child(2) {
	width: 300px;
}

.list-wrapper th:nth-child(3) {
	width: 100px;
}

.list-wrapper th:nth-child(4) {
	width: 100px;
}

.list-wrapper th:nth-child(5) {
	width: 50px;
}

.list-wrapper th:nth-child(6) {
	width: 50px;
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
	width: 80%;
	align-self: center;
}

.list-wrapper button[type=button],
.list-wrapper input[type=submit] {
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 3px;
	padding: 5px 10px;
	cursor: pointer;
}

.list-wrapper button[type=button]:hover,
.list-wrapper input[type=submit]:hover {
	background-color: #00abff;
}
/* 검색창 스타일 */
.list-wrapper .search-form {
	display: flex;
	justify-content: center;
}

.list-wrapper .search-form input[type=text],
.list-wrapper .search-form select,
.list-wrapper .search-form select option {
	padding: 8px;
	border-radius: 3px;
	border: 1px solid #ccc;
	box-sizing: border-box;
	font-weight: bold;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function() {
		// redirectAttributes.addFlashAttribute 확인
		<c:if test="${not empty message}">
			alert("${message}");
		</c:if>
		<c:if test="${not empty pageMaker.boardPageCriteria.keyword}">
			$('#searchOption').val("${sessionScope.target.searchOption}");
			$('#keyword').val("${pageMaker.boardPageCriteria.keyword}");
		</c:if>
		// 게시글의 수 정렬
		$('.content #boardNumsPerPage option').each(function() {
			if ($(this).val() == "${sessionScope.target.boardNumsPerPage}") {
				$(this).prop('selected', true);
				return false;
			}
		});
		// 게시글 보기
		$('tbody td').closest('tr').find('a').click(function(e) {
			e.preventDefault();
			var boardNumber = parseInt($(this).closest('tr').find('.boardNumber').text());
			<c:if test="${empty sessionScope.memberVO}">
				$('.login-modal').css('display', 'block');
				$('#memberId').attr('autofocus', true);
				$('#targetURL').val('detail');
				$('#boardNumber').val(boardNumber);
			</c:if>
			<c:if test="${not empty sessionScope.memberVO}">
				$.ajax({
					url: '/mbti/board/detail',
					type: 'GET',
				    data: {
				    	boardNumber : boardNumber
				    	},
					success: function(includeJSP) {
						$('.content').html(includeJSP);
						$('html, body').animate({scrollTop: $('.banner').offset().top}, 500);
					}
				});
			</c:if>
		}); // end $('tbody td').closest('tr').find('a').click()
		// 게시글 작성
		$('.list-options button[type="button"]').click(function(e) {
			e.preventDefault();
			<c:if test="${empty sessionScope.memberVO}">
				$('.login-modal').css('display', 'block');
				$('#memberId').attr('autofocus', true);
				$('#targetURL').val('write');
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
					alert('권한이 없습니다.');
					return false;
				}
			</c:if>
		}); // end $('.list-options button[type="button"]').click(function(e) {})		
		// 페이지 목록
		$('.list-page').on('click', 'ul li a', function(e) {
			e.preventDefault();
			// 처음 페이지 목록
			if ($(this).hasClass('listGET-start')) {
				$.ajax({
					url: '/mbti/board/list',
					type: 'GET',
				    data: {
				    	boardNumsPerPage : "${sessionScope.target.boardNumsPerPage}",
				    	boardPage : 1,
				    	boardSection : "${sessionScope.target.boardSection}",
				    	boardList : "${sessionScope.target.boardList}",
				    	boardName : "${sessionScope.target.boardName}",
				    	searchOption : "${sessionScope.target.searchOption}",
				    	keyword : "${sessionScope.target.keyword}"
				    	},
					success: function(includeJSP) {
						$('.content').html(includeJSP);
						$('html, body').animate({scrollTop: $('.banner').offset().top}, 500);
					}
				});
			}
			// 이전 페이지 목록
			if ($(this).hasClass('listGET-prev')) {
				$.ajax({
					url: '/mbti/board/list',
					type: 'GET',
				    data: {
				    	boardNumsPerPage : "${sessionScope.target.boardNumsPerPage}",
				    	boardPage : "${pageMaker.boardEndPage - 1}",
				    	boardSection : "${sessionScope.target.boardSection}",
				    	boardList : "${sessionScope.target.boardList}",
				    	boardName : "${sessionScope.target.boardName}",
				    	searchOption : "${sessionScope.target.searchOption}",
				    	keyword : "${sessionScope.target.keyword}"
				    	},
					success: function(includeJSP) {
						$('.content').html(includeJSP);
						$('html, body').animate({scrollTop: $('.banner').offset().top}, 500);
					}
				});
			}
			// 현재 페이지
			if ($(this).hasClass('listGET-page')) {
				$.ajax({
					url: '/mbti/board/list',
					type: 'GET',
				    data: {
				    	boardNumsPerPage : "${sessionScope.target.boardNumsPerPage}",
				    	boardPage : parseInt($(this).text()),
				    	boardSection : "${sessionScope.target.boardSection}",
				    	boardList : "${sessionScope.target.boardList}",
				    	boardName : "${sessionScope.target.boardName}",
				    	searchOption : "${sessionScope.target.searchOption}",
				    	keyword : "${sessionScope.target.keyword}"
				    	},
					success: function(includeJSP) {
						$('.content').html(includeJSP);
						$('html, body').animate({scrollTop: $('.banner').offset().top}, 500);
					}
				});
			}
			// 다음 페이지 목록
			if ($(this).hasClass('listGET-next')) {
				$.ajax({				
					url: '/mbti/board/list',
					type: 'GET',
				    data: {
				    	boardNumsPerPage : "${sessionScope.target.boardNumsPerPage}",
				    	boardPage : "${pageMaker.boardEndPage + 1}",
				    	boardSection : "${sessionScope.target.boardSection}",
				    	boardList : "${sessionScope.target.boardList}",
				    	boardName : "${sessionScope.target.boardName}",
				    	searchOption : "${sessionScope.target.searchOption}",
				    	keyword : "${sessionScope.target.keyword}"
				    	},
					success: function(includeJSP) {
						$('.content').html(includeJSP);
						$('html, body').animate({scrollTop: $('.banner').offset().top}, 500);
					}
				});
			}
			// 마지막 페이지 목록
			if ($(this).hasClass('listGET-end')) {
				$.ajax({
					url: '/mbti/board/list',
					type: 'GET',
				    data: {
				    	boardNumsPerPage : "${sessionScope.target.boardNumsPerPage}",
				    	boardPage : "${pageMaker.boardTotalPage}",
				    	boardSection : "${sessionScope.target.boardSection}",
				    	boardList : "${sessionScope.target.boardList}",
				    	boardName : "${sessionScope.target.boardName}",
				    	searchOption :"${sessionScope.target.searchOption}",
				    	keyword : "${sessionScope.target.keyword}"
				    	},
					success: function(includeJSP) {
						$('.content').html(includeJSP);
						$('html, body').animate({scrollTop: $('.banner').offset().top}, 500);
					}
				});
			}
		}); // end $('.list-page').on('click', 'ul li a', function(e) {})
		// 검색
		$('.search-form input[type="submit"]').click(function(e) {
			e.preventDefault();
			<c:if test="${empty sessionScope.memberVO}">
				alert('로그인 후 이용하실 수 있습니다.');
				return false;
			</c:if>
			<c:if test="${not empty sessionScope.memberVO}">
				if ($('#keyword').val().length >= 1) {
					$.ajax({
						url: '/mbti/board/list',
						type: 'GET',
					    data: {
					    	boardNumsPerPage : "${sessionScope.target.boardNumsPerPage}",
					    	boardPage : 1,
					    	boardSection : "${sessionScope.target.boardSection}",
					    	boardList : "${sessionScope.target.boardList}",
					    	boardName : "${sessionScope.target.boardName}",
					    	searchOption : parseInt($('#searchOption').val()),
					    	keyword : $('#keyword').val()
					    	},
						success: function(includeJSP) {
							$('.content').html(includeJSP);
							$('html, body').animate({scrollTop: $('.banner').offset().top}, 500);
						}
					});
				} else {
					return false;					
				}
			</c:if>
		}) // end $('.search-form input[type="submit"]').click(function() {})
		// 게시글 수 정렬
		$('.content #boardNumsPerPage').change(function(e) {
			e.preventDefault();
			$.ajax({
				url: '/mbti/board/list',
				type: 'GET',
			    data: {
			    	boardNumsPerPage : $(this).val(),
			    	boardPage : 1,
			    	boardSection : "${sessionScope.target.boardSection}",
			    	boardList : "${sessionScope.target.boardList}",
			    	boardName : "${sessionScope.target.boardName}",
			    	searchOption :"${sessionScope.target.searchOption}",
			    	keyword : "${sessionScope.target.keyword}"
			    	},
				success: function(includeJSP) {
					$('.content').html(includeJSP);
					$('html, body').animate({scrollTop: $('.banner').offset().top}, 500);
				}
			});
		});
	}); // end $(document).ready()
</script>
</head>
<body>
	<div class="list-wrapper">
		<h2>${sessionScope.target.boardName}</h2>
		<div class="boardNumsPerPage">
			<select id="boardNumsPerPage">
				<option value="5">5개씩</option>
				<option value="10">10개씩</option>
				<option value="15">15개씩</option>
				<option value="20">20개씩</option>
				<option value="30">30개씩</option>
				<option value="40">40개씩</option>
				<option value="50">50개씩</option>
			</select>
		</div>
		<table class="list-table">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회</th>
					<th>좋아요</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="boardVO" items="${boardVO}">
					<c:if test="${boardVO.boardType eq 2}">
						<tr style="background-color: #FFF0F5;">
							<td class="boardNumber">${boardVO.boardNumber}</td>
							<td><a href="#">${boardVO.boardTitle}</a>&nbsp;[${boardVO.boardComments}]</td>
							<td>${boardVO.memberNickname}</td>
							<fmt:formatDate value="${boardVO.boardRegdate}" pattern="yyyy-MM-dd" var="boardRegdate" />
							<td>${boardRegdate}</td>
							<td>${boardVO.boardViews}</td>
							<td>${boardVO.boardLikes}</td>
						</tr>
					</c:if>
					<c:if test="${boardVO.boardType eq 1}">
						<tr style="background-color: #FFEFD5;">
							<td class="boardNumber">${boardVO.boardNumber}</td>
							<td><a href="#">${boardVO.boardTitle}</a>&nbsp;[${boardVO.boardComments}]</td>
							<td>${boardVO.memberNickname}</td>
							<fmt:formatDate value="${boardVO.boardRegdate}" pattern="yyyy-MM-dd" var="boardRegdate" />
							<td>${boardRegdate}</td>
							<td>${boardVO.boardViews}</td>
							<td>${boardVO.boardLikes}</td>
						</tr>
					</c:if>
					<c:if test="${boardVO.boardType eq 0}">
						<tr>
							<td class="boardNumber">${boardVO.boardNumber}</td>
							<td><a href="#">${boardVO.boardTitle}</a>&nbsp;[${boardVO.boardComments}]</td>
							<td>${boardVO.memberNickname}</td>
							<fmt:formatDate value="${boardVO.boardRegdate}" pattern="yyyy-MM-dd" var="boardRegdate" />
							<td>${boardRegdate}</td>
							<td>${boardVO.boardViews}</td>
							<td>${boardVO.boardLikes}</td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
		<c:if test="${sessionScope.target.boardSection != 0}">
			<div class="list-options">
				<button type="button">글쓰기</button>
			</div>
		</c:if>
		<div class="list-page">
			<ul>
				<c:if test="${pageMaker.boardPageCriteria.boardEnd < 5}">
					<li><a href="#" class="listGET-start">처음</a></li>				
				</c:if>
				<c:if test="${pageMaker.boardHasPrev}">
					<li><a href="#" class="listGET-prev">이전</a></li>
				</c:if>
				<c:forEach var="boardPage" begin="${pageMaker.boardStartPage}" end="${pageMaker.boardEndPage}">
				<c:if test="${pageMaker.boardPageCriteria.boardPage eq boardPage}">
					<li><a href="#" class="listGET-page selected">${boardPage}</a></li>
				</c:if>
				<c:if test="${pageMaker.boardPageCriteria.boardPage ne boardPage}">
					<li><a href="#" class="listGET-page">${boardPage}</a></li>
				</c:if>
				</c:forEach>
				<c:if test="${pageMaker.boardHasNext}">
					<li><a href="#"	class="listGET-next">다음</a></li>
				</c:if>
				<c:if test="${pageMaker.boardPageCriteria.boardEnd < 5}">
					<li><a href="#" class="listGET-end">끝</a></li>
				</c:if>
			</ul>
		</div>
		<form class="search-form">
			<select id="searchOption">
				<option value="0">게시글 제목</option>
				<option value="1">게시글 내용</option>
				<option value="2">게시글 작성자</option>
				<option value="3">댓글 내용</option>
				<option value="4">댓글 작성자</option>
				<!-- <option value="5">게시글 작성일</option> -->
			</select>
			&nbsp;
			<input type="text" id="keyword" minlength="1"/>
			&nbsp;
			<input type="submit" value="검색">
		</form>
	</div>
</body>
</html>