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

.history-list,
.history-member {
	display: flex;
	flex-direction: column;
	aling-self: center;
	justify-content: center;
}

.history-wrapper .boardNumsPerPage {
	text-align: right;
	width: 80%;
	align-self: center;
	margin-bottom: 3px;
}

.history-wrapper .boardNumsPerPage select,
.history-wrapper .boardNumsPerPage select option {
	font-weight: bold;
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

.history-wrapper table a {
	color: #000;
}

.history-wrapper a:hover,
.history-options .selected {
	text-decoration: underline;
	color: #000;
}

.history-wrapper .list-options {
	width: 80%;
	display: flex;
	align-self: center;
	justify-content: flex-end;
}

.history-wrapper .list-options button {
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 3px;
	padding: 5px 10px;
	cursor: pointer;
	margin-top: 3px;
}

.history-wrapper .list-options button:hover {
	background-color: #00abff;
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
		// 게시글의 수 정렬
		$('.history #boardNumsPerPage option').each(function() {
			if ($(this).val() == "${sessionScope.target.boardNumsPerPage}") {
				$(this).prop('selected', true);
				return false;
			}
		});
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
				    	boardNumsPerPage : 5,
				    	boardPage : 1,
				    	memberNumber : "${memberVO.memberNumber}",
				    	historyOption : 0
				    	},
					success: function(includeJSP) {
						$('.history').html(includeJSP);
					}
				});
			// 작성 댓글
			} else if ($(this).hasClass('history-options-comments')) {
				$.ajax({
					url: '/mbti/member/history',
					type: 'GET',
				    data: {
				    	boardNumsPerPage : 5,
				    	boardPage : 1,
				    	memberNumber : "${memberVO.memberNumber}",
				    	historyOption : 1
				    	},
					success: function(includeJSP) {
						$('.history').html(includeJSP);
					}
				});
			// 추천 게시글
			} else if ($(this).hasClass('history-options-boardlike')) {
				$.ajax({
					url: '/mbti/member/history',
					type: 'GET',
				    data: {
				    	boardNumsPerPage : 5,
				    	boardPage : 1,
				    	memberNumber : "${memberVO.memberNumber}",
				    	historyOption : 2
				    	},
					success: function(includeJSP) {
						$('.history').html(includeJSP);
					}
				});
			}
			$('html, body').animate({scrollTop: $('.banner').offset().top}, 500);
		});
		// 게시글 보기
		$('tbody td').closest('tr').find('a').click(function(e) {
			e.preventDefault();
			var boardNumber = parseInt($(this).closest('tr').find('.boardNumber').text());
			var commentsNumber = parseInt($(this).closest('tr').find('.commentsNumber').text());
			if (boardNumber == -1) {
				alert('삭제된 게시글입니다.');
				return false;
			} else if (commentsNumber == -1) {
				alert('삭제된 댓글입니다.');
				return false;
			} else {
				$.ajax({
					url: '/mbti/board/detail',
					type: 'GET',
					   data: {
					   	boardNumber : boardNumber
					   	},
					success: function(includeJSP) {
						$('.history').css('display', 'none');
						$('.content').html(includeJSP);
						$('.content').css('display', 'block');
						$('html, body').animate({scrollTop: $('.banner').offset().top}, 500);
					}
				});				
			}
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
				    	boardNumsPerPage : "${sessionScope.target.boardNumsPerPage}",
				    	boardPage : 1,
						memberNumber : "${sessionScope.target.memberNumber}",
						historyOption : "${sessionScope.target.historyOption}"
				    	},
					success: function(includeJSP) {
						$('.history').html(includeJSP);
						$('html, body').animate({scrollTop: $('.banner').offset().top}, 500);
					}
				});
			}
			// 이전 페이지 목록
			if ($(this).hasClass('listGET-prev')) {
				$.ajax({
					url: '/mbti/member/history',
					type: 'GET',
				    data: {
				    	boardNumsPerPage : "${sessionScope.target.boardNumsPerPage}",
				    	boardPage : "${pageMaker.boardEndPage - 1}",
						memberNumber : "${sessionScope.target.memberNumber}",
						historyOption : "${sessionScope.target.historyOption}"
				    	},
					success: function(includeJSP) {
						$('.history').html(includeJSP);
						$('html, body').animate({scrollTop: $('.banner').offset().top}, 500);
					}
				});
			}
			// 현재 페이지
			if ($(this).hasClass('listGET-page')) {
				$.ajax({
					url: '/mbti/member/history',
					type: 'GET',
				    data: {
				    	boardNumsPerPage : "${sessionScope.target.boardNumsPerPage}",
				    	boardPage : parseInt($(this).text()),
						memberNumber : "${sessionScope.target.memberNumber}",
						historyOption : "${sessionScope.target.historyOption}"
				    	},
					success: function(includeJSP) {
						$('.history').html(includeJSP);
						$('html, body').animate({scrollTop: $('.banner').offset().top}, 500);
					}
				});
			}
			// 다음 페이지 목록
			if ($(this).hasClass('listGET-next')) {
				$.ajax({				
					url: '/mbti/member/history',
					type: 'GET',
				    data: {
				    	boardNumsPerPage : "${sessionScope.target.boardNumsPerPage}",
				    	boardPage : "${pageMaker.boardEndPage + 1}",
						memberNumber : "${sessionScope.target.memberNumber}",
						historyOption : "${sessionScope.target.historyOption}"
				    	},
					success: function(includeJSP) {
						$('.history').html(includeJSP);
						$('html, body').animate({scrollTop: $('.banner').offset().top}, 500);
					}
				});
			}
			// 마지막 페이지 목록
			if ($(this).hasClass('listGET-end')) {
				$.ajax({
					url: '/mbti/member/history',
					type: 'GET',
				    data: {
				    	boardNumsPerPage : "${sessionScope.target.boardNumsPerPage}",
				    	boardPage : "${pageMaker.boardTotalPage}",
				    	memberNumber : "${sessionScope.target.memberNumber}",
				    	historyOption : "${sessionScope.target.historyOption}"
				    	},
					success: function(includeJSP) {
						$('.history').html(includeJSP);
						$('html, body').animate({scrollTop: $('.banner').offset().top}, 500);
					}
				});
			}
		}); // end $('.list-page').on('click', 'ul li a', function(e) {})
		// 게시글 수 정렬
		$('.history #boardNumsPerPage').change(function(e) {
			e.preventDefault();
			$.ajax({
				url: '/mbti/member/history',
				type: 'GET',
			    data: {
			    	boardNumsPerPage : $(this).val(),
			    	boardPage : 1,
			    	memberNumber : "${sessionScope.target.memberNumber}",
			    	historyOption : "${sessionScope.target.historyOption}"
			    	},
				success: function(includeJSP) {
					$('.history').html(includeJSP);
					$('html, body').animate({scrollTop: $('.banner').offset().top}, 500);
				}
			});
		});
		// 삭제 목록 선택
		$('.history-list .list-table').on('click', 'tr td', function(e) {
			$('.history-list .list-table').off('click', 'tr td');
		    e.preventDefault();
		    if ("${sessionScope.target.historyOption eq 1}") {
			    if ($(this).css('color') === 'rgb(0, 0, 0)') {
			        $(this).css('color', 'rgb(255, 0, 0)');
			        if ($(this).hasClass('commentsNumber')) {
					    var commentsNumber = parseInt($(this).find('span.commentsNumber').text());	
					    $('.list-options .delete-btn').click(function(e) {
				        	e.preventDefault();
				        	// 댓글을 삭제할 건지 다시 한번 확인
							if (confirm('댓글을 삭제하시겠습니까?')) {
								$.ajax({
									url: '/mbti/comments/delete/'+commentsNumber,
									type: 'DELETE',
									success: function(commentsDELETEResult) {
										if (commentsDELETEResult == 1) {
											alert('댓글이 삭제되었습니다.');
											$.ajax({
												url: '/mbti/member/history',
												type: 'GET',
											    data: {
											    	boardNumsPerPage : "${sessionScope.target.boardNumsPerPage}",
											    	boardPage : "${sessionScope.target.boardPage}",
											    	memberNumber : "${sessionScope.memberVO.memberNumber}",
											    	historyOption : 1
											    	},
												success: function(includeJSP) {
													$('.history').html(includeJSP);												
												}
											});
										}
									}
								}); // end ajax()					
							}
				        });
				    } else if ($(this).hasClass('replyNumber')) {
					    var replyNumber = parseInt($(this).find('span.replyNumber').text());		    	
				        $('.list-options .delete-btn').click(function(e) {
				        	e.preventDefault();
				        	// 답글을 삭제할 건지 다시 한번 확인
							if (confirm('답글을 삭제하시겠습니까?')) {
								$.ajax({
									url: '/mbti/reply/delete/'+replyNumber,
									type: 'DELETE',
									success: function(replyDELETEResult) {
										if (replyDELETEResult == 1) {
											alert('답글이 삭제되었습니다.');
											$.ajax({
												url: '/mbti/member/history',
												type: 'GET',
											    data: {
											    	boardNumsPerPage : "${sessionScope.target.boardNumsPerPage}",
											    	boardPage : "${sessionScope.target.boardPage}",
											    	memberNumber : "${sessionScope.memberVO.memberNumber}",
											    	historyOption : 1
											    	},
												success: function(includeJSP) {
													$('.history').html(includeJSP);												
												}
											});
										}
									}
								});
							}
				        });
				    }
			    	// 나의 활동 새로고침
					$.ajax({
						url: '/mbti/memberrest/history/'+"${sessionScope.memberVO.memberNumber}",
						type: 'GET',
						success: function(historyGETResult) {
							$('.countByNumberOnBoard').text(historyGETResult[0]);
							$('.countByNumberOnCmRp').text(historyGETResult[1]);
						}
					});
			        $('html, body').animate({scrollTop: $('.banner').offset().top}, 500);
			    } else if ($(this).css('color') === 'rgb(255, 0, 0)') {
			        $(this).css('color', 'rgb(0, 0, 0)');
			    }
		    } else {
		    	return false;		    	
		    }
		});
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
		<div class="history-list">
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
			<c:if test="${empty cmRpVO}">
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
									<td class="boardNumber" style="cursor: pointer;">${boardVO.boardNumber}</td>
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
									<td class="boardNumber" style="cursor: pointer;">${boardVO.boardNumber}</td>
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
									<td class="boardNumber" style="cursor: pointer;">${boardVO.boardNumber}</td>
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
			</c:if>
			<c:if test="${not empty cmRpVO}">
				<table class="list-table">
					<thead>
						<tr>
							<th>번호</th>
							<th>내용</th>
							<th>작성일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="cmRpVO" items="${cmRpVO}">
							<c:if test="${cmRpVO.replyNumber eq 0}">
								<tr>									
									<td class="commentsNumber" style="cursor: pointer;">
									<span class="commentsNumber">${cmRpVO.commentsNumber}</span>
									<span class="boardNumber" style="display: none;">${cmRpVO.boardNumber}</span>
									</td>
									<td><a href="#">${cmRpVO.commentsContent}</a></td>
									<fmt:formatDate value="${cmRpVO.regdate}" pattern="yyyy-MM-dd" var="regdate" />
									<td>${regdate}</td>
								</tr>
							</c:if>
							<c:if test="${cmRpVO.replyNumber ne 0}">
								<tr>
									<td class="replyNumber" style="cursor: pointer;">
									<span class="replyNumber">${cmRpVO.replyNumber}</span>
									<span class="boardNumber" style="display: none;">${cmRpVO.boardNumber}</span>
									<span class="commentsNumber" style="display: none;">${cmRpVO.commentsNumber}</span>
									</td>
									<td><a href="#">${cmRpVO.replyContent}</a></td>
									<fmt:formatDate value="${cmRpVO.regdate}" pattern="yyyy-MM-dd" var="regdate" />
									<td>${regdate}</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>				
			</c:if>
			<div class="list-options">
				<c:if test="${sessionScope.memberVO.memberNumber eq sessionScope.target.memberNumber}">
					<button type="button" class="delete-btn">삭제</button>				
				</c:if>
			</div>
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
		</div>
	</div>
</body>
</html>