<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>음티메이트 : 활동 내역</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
}

.history-wrapper {
	margin: auto;
	width: 97%;
	padding: 20px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0px 0px 5px 0px rgba(0, 0, 0, 0.2);
	display: flex;
	max-height: 97%;
	flex-direction: column;
}

.history-wrapper .history-member .member-profile {
	display: flex;
	align-self: center;
	width: 80%;
	margin-bottom: 10px;
	font-size: 13px;
	font-weight: bold;
}

.history-option {
	display: flex;
	align-self: center;
	width: 80%;
}

.history-options {
	display: flex;
	flex-direction: flex-start;
	font-size: 15px;
	font-weight: bold;
	margin-bottom: 5px;
}

.history-board,
.history-member {
	display: flex;
	flex-direction: column;
	aling-self: center;
	justify-content: center;
}

.history-wrapper table {
	border-collapse: collapse;
	width: 80%;
	background-color: #fff;
	font-size: 13px;
	font-weight: bold;
	margin-bottom: 5px;
	align-self: center;
}

.history-wrapper th {
	background-color: #007bff;
	color: #fff;
}

.history-wrapper th,
.history-wrapper td {
	border: 1px solid #000;
	text-align: center;
	padding: 5px;
}

.history-wrapper th:nth-child(1) {
	width: 50px;
}

.history-wrapper th:nth-child(2) {
	width: 300px;
}

.history-wrapper th:nth-child(3) {
	width: 100px;
}

.history-wrapper th:nth-child(4) {
	width: 100px;
}

.history-wrapper th:nth-child(5) {
	width: 50px;
}

.history-wrapper th:nth-child(6) {
	width: 50px;
}

.history-wrapper ul {
	list-style-type: none;
	display: flex;
	justify-content: center;
	align-items: center;
	margin-top: 20px;
}

.history-wrapper li {
	margin: 5px;
}


.history-wrapper a {
	text-decoration: none;
	color: #555;
}

.history-wrapper a:hover,
.history-options .selected {
	text-decoration: underline;
	color: #000;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function() {
		<c:if test="${not empty message}">
			alert("${message}");
		</c:if>
		if ("${sessionScope.target.historyOption}" == 0) {
			$('.history-options-board').addClass('selected');
		} else if ("${sessionScope.target.historyOption}" == 1) {
			$('.history-options-comments').addClass('selected');
		} else if ("${sessionScope.target.historyOption}" == 2) {
			$('.history-options-boardlike').addClass('selected');
		}
		// 옵션 선택
		$('.history-options').on('click', 'a', function(e) {
			e.preventDefault();
			$('.history-options').find('.selected').removeClass('selected');
			$(this).addClass('selected');
			// 작성 게시글
			if ($(this).hasClass('history-options-board')) {
				$.ajax({
					url: '/mbti/member/history',
					type: 'GET',
				    data: {
				    	boardPage : 1,
				    	memberNumber : "${memberVO.memberNumber}",
				    	historyOption : 0
				    	},
					success: function(includeJSP) {
						$('.content').html(includeJSP);
					}
				});
			// 작성 댓글
			} else if ($(this).hasClass('history-options-comments')) {
				$.ajax({
					url: '/mbti/member/history',
					type: 'GET',
				    data: {
				    	boardPage : 1,
				    	memberNumber : "${memberVO.memberNumber}",
				    	historyOption : 1
				    	},
					success: function(includeJSP) {
						$('.content').html(includeJSP);
					}
				});
			// 추천 게시글
			} else if ($(this).hasClass('history-options-boardlike')) {
				$.ajax({
					url: '/mbti/member/history',
					type: 'GET',
				    data: {
				    	boardPage : 1,
				    	memberNumber : "${memberVO.memberNumber}",
				    	historyOption : 2
				    	},
					success: function(includeJSP) {
						$('.content').html(includeJSP);
					}
				});
			}
		});
		// 게시글 보기
		$('tbody td').closest('tr').find('a').click(function(e) {
			e.preventDefault();
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
		}); // end $('tbody td').closest('tr').find('a').click()
		// 페이지 목록
		$('.list-page').on('click', 'ul li a', function(e) {
			e.preventDefault();
			// 처음 페이지 목록
			if ($(this).hasClass('listGET-start')) {
				$.ajax({
					url: '/mbti/member/history',
					type: 'GET',
				    data: {
				    	boardPage : 1,
						memberNumber : "${sessionScope.target.memberNumber}",
						historyOption : "${sessionScope.target.historyOption}"
				    	},
					success: function(includeJSP) {
						$('.content').html(includeJSP);
					}
				});
			}
			// 이전 페이지 목록
			if ($(this).hasClass('listGET-prev')) {
				$.ajax({
					url: '/mbti/member/history',
					type: 'GET',
				    data: {
				    	boardPage : "${pageMaker.boardEndPage - 1}",
						memberNumber : "${sessionScope.target.memberNumber}",
						historyOption : "${sessionScope.target.historyOption}"
				    	},
					success: function(includeJSP) {
						$('.content').html(includeJSP);
					}
				});
			}
			// 현재 페이지
			if ($(this).hasClass('listGET-page')) {
				$.ajax({
					url: '/mbti/member/history',
					type: 'GET',
				    data: {
				    	boardPage : parseInt($(this).text()),
						memberNumber : "${sessionScope.target.memberNumber}",
						historyOption : "${sessionScope.target.historyOption}"
				    	},
					success: function(includeJSP) {
						$('.content').html(includeJSP);
					}
				});
			}
			// 다음 페이지 목록
			if ($(this).hasClass('listGET-next')) {
				$.ajax({				
					url: '/mbti/member/history',
					type: 'GET',
				    data: {
				    	boardPage : "${pageMaker.boardEndPage + 1}",
						memberNumber : "${sessionScope.target.memberNumber}",
						historyOption : "${sessionScope.target.historyOption}"
				    	},
					success: function(includeJSP) {
						$('.content').html(includeJSP);
					}
				});
			}
			// 마지막 페이지 목록
			if ($(this).hasClass('listGET-end')) {
				$.ajax({
					url: '/mbti/member/history',
					type: 'GET',
				    data: {
				    	boardPage : "${pageMaker.boardTotalPage}",
				    	memberNumber : "${sessionScope.target.memberNumber}",
				    	historyOption : "${sessionScope.target.historyOption}"
				    	},
					success: function(includeJSP) {
						$('.content').html(includeJSP);
					}
				});
			}
		}); // end $('.list-page').on('click', 'ul li a', function(e) {})
	}); // end document.ready()	
</script>
</head>
<body>
	<div class="history-wrapper">
		<div class="close"><button type="button" onclick="$('.history').css('display', 'none');$('.content').css('display', 'block');">&times;</button></div>
		<div class="history-member">
			<div class="member-profile">
				<div class="member-picture">
					<img src="/mbti/resources/member?fileName=${memberVO.memberPicture}" style="width: 56px; height: 56px; max-height: 100%; border-radius: 30px; margin-right: 10px; align-self: flex-start;">
				</div>
				<div class="member-nickname member-id member-grade member-premium">
					<span style="font-size: 17px;">${memberVO.memberNickname}(${memberVO.memberId.substring(0, memberVO.memberId.length()-3).concat("***")})</span>
					<br>
					<c:choose>
						<c:when test="${memberVO.memberGrade eq 0}">씨앗&#x1F954;</c:when>
						<c:when test="${memberVO.memberGrade eq 1}">새싹&#x1F331;</c:when>
						<c:when test="${memberVO.memberGrade eq 2}">꽃&#x1F33C;</c:when>
						<c:when test="${memberVO.memberGrade eq 3}">열매&#x1F352;</c:when>
						<c:when test="${memberVO.memberGrade eq 4}">스탭&#x1F332;</c:when>
						<c:when test="${memberVO.memberGrade eq 5}">매니저&#x1F384;</c:when>
					</c:choose>
					<br>
					<c:choose>
						<c:when test="${memberVO.memberPremium eq 0}">프리미엄&#x274C;</c:when>
						<c:when test="${memberVO.memberPremium eq 1}">프리미엄&#x1F48E;</c:when>
					</c:choose>
				</div>
			</div>
		</div>
		<div class="history-option">
			<div class="history-options">
				<a href="#" class="history-options-board">작성 게시글</a>
				&nbsp;&nbsp;
				<a href="#" class="history-options-comments">작성 댓글</a>
				&nbsp;&nbsp;
				<a href="#" class="history-options-boardlike">추천 게시글</a>
			</div>
		</div>
		<div class="history-board">
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
			<div class="list-page">
				<ul>
					<li><a href="#" class="listGET-start">처음</a></li>
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
					<li><a href="#" class="listGET-end">끝</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>