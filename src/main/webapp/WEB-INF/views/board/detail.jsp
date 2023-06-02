<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>음티메이트 : 게시글</title>
<style type="text/css">
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
}

.detail-wrapper {   
	margin: auto;
	width: 97%;
	background-color: #fff;
	padding: 20px;
	border-radius: 5px;
	box-shadow: 0px 0px 5px 0px rgba(0, 0, 0, 0.2);
	height: 100%;
	overflow-y: auto;
}

.detail-wrapper h2 {
	text-align: left;
}

.detail-wrapper form {
	display: flex;
	flex-direction: column;
}

.detail-wrapper input[type=text],
.detail-wrapper textarea {
	padding: 8px;
	border-radius: 3px;
	border: 1px solid #ccc;
	box-sizing: border-box;
	font-weight: bold;
	width: 100%;
}

.detail-wrapper input[type=submit],
.detail-wrapper button[type=submit],
.detail-wrapper button[type=button] {
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 3px;
	padding: 5px 10px;
	cursor: pointer;
	margin-top: 3px;
	margin-left: 5px;
}

.detail-wrapper input[type=submit]:hover,
.detail-wrapper button[type=submit]:hover,
.detail-wrapper button[type=button]:hover {
	background-color: #00abff;
}
/* 옵션 버튼 스타일 */
.detail-wrapper .detail-options,
.detail-wrapper .comments-options {
	display: flex;
	justify-content: flex-end;
}
/* 회원사진 스타일 */
.writer-picture img {
	width: 34px;
	height: 34px;
	max-width: 100%;
	max-height: 100%;
	border-radius: 30px;
	margin-right: 10px;
}
/* 작성자 스타일 */
.writer-wrapper {
	display: flex;
}
/* 닉네임, 추천 버튼 스타일 */
.detail-wrapper button,
.writer-wrapper button {
	border: none;
	background-color: #fff;
	font-weight: bold;
	padding: 0;
	cursor: pointer;
}
/* 추천 버튼 스타일 */
.detail-wrapper #boardLikesToggle:hover {
	transform: scale(1.2);
}
/* 글자 짤림 방지 */
.detail-wrapper textarea {
	resize: none;
	overflow-y: auto;
	overflow-wrap: break-word;
}
/* display-flex 스타일 */
.detail-wrapper .detail-info {
	display: flex;
	justify-content: flex-start;
}
/* 날짜, 조회 스타일 */
.detail-wrapper .board-info span,
.writer-wrapper .comments-info span{
	color: #979797;
	font-size: 12px;
}

.detail-wrapper .detail-info {
	font-weight: bold;
}
</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		// redirectAttributes.addFlashAttribute 확인
		<c:if test="${not empty message}">
			alert("${message}");
		</c:if>
		// 전역 변수 선언
		var boardNumber = "${boardVO.boardNumber}";
		var memberNumber = "${sessionScope.memberVO.memberNumber}";
		commentsGET();
		/******************************************************댓글 관련 기능*****************************************************************/
		// 댓글 불러오기
		function commentsGET() {			
			$('#commentsContent').val('');		
			$.ajax({
				url: '/mbti/comments/list/'+boardNumber,
				type: 'GET',
				success: function(commentsGETResult) {
					$('.comments-list').empty();
					if (commentsGETResult == 0) {
						$('.comments-list').append('<hr><div style="text-align: center; font-weight: bold;">댓글이 없습니다.</div>');
					} else {
						$(commentsGETResult).each(function() {
							var commentsRegdate = new Date(this.commentsRegdate);
							commentsRegdate = commentsRegdate.getFullYear()+
												'-'+(commentsRegdate.getMonth() + 1).toString().padStart(2, '0')+
												'-'+commentsRegdate.getDate().toString().padStart(2, '0')+
												' '+commentsRegdate.getHours().toString().padStart(2, '0')+
												':'+commentsRegdate.getMinutes().toString().padStart(2, '0')+
												':'+commentsRegdate.getSeconds().toString().padStart(2, '0');
							// 해당 댓글 작성자만 수정/삭제 가능
							if(memberNumber == this.memberNumber) {
								var hidden = '';
							} else {
								hidden = 'hidden';								
							};
							comments = '<div class="comments">'+
										'<div class="writer-wrapper">'+
										'<div class="writer-picture">'+
										'<img src="/mbti/member/display?memberPicture='+this.memberPicture+'">'+
										'</div>'+
										'<div class="writer-area">'+
										'<div class="writer-info">'+
										'<button class="memberNickname" name="memberNickname">'+this.memberNickname+'</button>'+
										'</div>'+
										'<div class="comments-info">'+
										'<span class="commentsRegdate">'+commentsRegdate+'</span>'+
										'</div>'+
										'</div>'+
										'</div>'+
										'<textarea rows="3" class="commentsContent" name="commentsContent" minlength="5" maxlength="2000" placeholder="댓글을 남겨보세요" readonly>'+this.commentsContent+'</textarea>'+
										'<div class="comments-options">'+
										'<button type="button" class="commentsPUT" value="'+this.commentsNumber+'" '+hidden+'>수정</button>'+
										'<button type="button" class="commentsDELETE" value="'+this.commentsNumber+'" hidden>삭제</button>'+
										'</div>'+
										'</div>';
						$('.comments-list').append(comments);
						}); // end commentsPOSTResult.each(function() {})
					}
					// 추가되는 댓글 하위 요소 스타일
					$('.writer-picture img').css('width, height, max-width, max-height, border-radius, margin-right', '34px, 34px, 100%, 100%, 30px, 10px');
					$('.writer-wrapper button').css('border, background-color, font-weight, padding, cursor', 'none, #fff, bold, 0, pointer');
					$('.writer-wrapper .comments-info span').css('color, font-size', '#979797, 12px');
				}
			}); // end ajax()
		} // end commentsGET()		
		// 댓글 등록
		$('.comments-options .commentsPOST').click(function(e) {
			e.preventDefault();
			commentsContent = $('#commentsContent').val();
			// 5글자 이상 작성해야 등록 가능
			if (commentsContent.length >= 5) {
				$.ajax({
					url: '/mbti/comments/post',
					type: 'POST',
					headers: {'Content-Type' : 'application/json'},
					data: JSON.stringify({
						boardNumber : boardNumber,
						memberNumber : memberNumber,
						commentsContent : commentsContent
						}),
					success: function(commentsPOSTResult) {
						if (commentsPOSTResult == 1) {
							alert('댓글을 등록했습니다.');
							$.ajax({
								url: '/mbti/comments/'+boardNumber,
								type: 'GET',
								success: function(boardCommentsGETResult) {
									$('#boardComments').html(boardCommentsGETResult);
									commentsGET();
								}
							}); // end ajax()
						}
					}
				}); // end ajax()					
			} else {
				alert('5글자 이상 작성해주세요.');
			}
		}); // end $('.comments-options .commentsPOST').click()		
		// 댓글 수정
		$('.comments-list').on('click', '.comments-options .commentsPUT', function(e) {
			e.preventDefault();
			var commentsTextarea = $(this).parent().parent().find('.commentsContent');
			var commentsContent = commentsTextarea.val();
			commentsTextarea.removeAttr('readonly');
			$(this).closest('.comments-options').find('.commentsDELETE').removeAttr('hidden');
			$(this).text('등록');
			// 수정된 댓글 등록
			$(this).click(function(e) {
				e.preventDefault();
				commentsContent = commentsTextarea.val();
				// 5글자 이상 작성해야 등록 가능
				if (commentsContent.length >= 5) {
					$.ajax({
						url: '/mbti/comments/update/'+$(this).val(),
						type: 'PUT',
						headers: {'Content-Type' : 'application/json'},
						data: commentsContent,
						success: function(commentsPUTResult) {
							if (commentsPUTResult == 1) {
								alert('댓글이 수정되었습니다.');
								$.ajax({
									url: '/mbti/comments/'+boardNumber,
									type: 'GET',
									success: function(boardCommentsGETResult) {
										$('#boardComments').html(boardCommentsGETResult);
										commentsGET();
									}
								}); // end ajax()
							}
						}
					}); // end ajax()
				} else {
					alert('5글자 이상 작성해주세요.');
				}
			});
			// 등록된 댓글 삭제
			$(this).closest('.comments-options').find('.commentsDELETE').click(function(e) {
				e.preventDefault();
				// 댓글을 삭제할 건지 다시 한번 확인
				if (confirm('댓글을 삭제하시겠습니까?')) {
					$.ajax({
						url: '/mbti/comments/delete/'+$(this).val(),
						type: 'DELETE',
						success: function(commentsDELETEResult) {
							if (commentsDELETEResult == 1) {
								alert('댓글이 삭제되었습니다.');
								$.ajax({
									url: '/mbti/comments/'+boardNumber,
									type: 'GET',
									success: function(boardCommentsGETResult) {
										$('#boardComments').html(boardCommentsGETResult);
										commentsGET();
									}
								}); // end ajax()
							}
						}
					}); // end ajax()					
				}
			});
		}); // end $('.comments-list').on('click', '.comments-options .commentsPUT', function(e) {})
		/**********************************************************************************************************************************/
		/*****************************************************게시글 관련 기능*****************************************************************/
		// 게시글 추천 등록/취소
		$('.detail-info #boardLikesToggle').click(function(e) {
			e.preventDefault();
			// 추천 색깔이 검정색(미추천 상태)일 때 클릭 이벤트 발생 (추천 등록)
			if ($('.detail-info #boardLikes').css('color') == 'rgb(0, 0, 0)') {
				$.ajax({
					url: '/mbti/boardlike',
					type: 'POST',
					headers: {'Content-Type' : 'application/json'},
					data: JSON.stringify({
						boardNumber : boardNumber,
						memberNumber : memberNumber
						}),
					success: function(boardlikePOSTResult) {
						if (boardlikePOSTResult == 1) {
							$.ajax({
								url: '/mbti/boardlike/'+boardNumber,
								type: 'GET',
								success: function(boardlikeGETResult) {
									$('#boardLikes').html(boardlikeGETResult);
									$('#boardLikes').css('color', 'rgb(255, 0, 0)');
								}
							}); // end ajax()
						}							
					}
				}); // end ajax()
			  // 추천 색깔이 빨간색(추천 상태)일 때 클릭 이벤트 발생 (추천 취소)
			} else if ($('.detail-info #boardLikes').css('color') == 'rgb(255, 0, 0)') { 					
				$.ajax({
					url: '/mbti/boardlike',
					type: 'DELETE',
					headers: {'Content-Type' : 'application/json'},
					data: JSON.stringify({
						boardNumber : boardNumber,
						memberNumber : memberNumber
						}),
					success: function(boardlikeDELETEResult) {
						if (boardlikeDELETEResult == 1) {
							$.ajax({
								url: '/mbti/boardlike/'+boardNumber,
								type: 'GET',
								success: function(boardlikeGETResult) {
									$('#boardLikes').html(boardlikeGETResult);
									$('#boardLikes').css('color', 'rgb(0, 0, 0)');
								}
							}); // end ajax()								
						}
					}
				}); // end ajax()
			}
		}); // end $('.detail-info #boardLikesToggle').click(function(e) {})		
		// 이전 목록으로 돌아가기
		$('.detail-options .listGET').click(function(e) {
			e.preventDefault();
			$.ajax({
				url: '/mbti/board/list',
				type: 'GET',
				success: function(includeJSP) {
					$('.content').html(includeJSP);
				}
			});
		}); // end $('.detail-options .listGET').click()				
		// 게시글 수정
		$('input[type="submit"][value="수정"]').click(function(e) {
			e.preventDefault();
			// 해당 게시글 작성자만 게시글 수정 가능
			if ("${boardVO.memberNumber}" == "${sessionScope.memberVO.memberNumber}") {
				$.ajax({
					url: '/mbti/board/update',
					type: 'GET',
					success: function(includeJSP) {
						$('.content').html(includeJSP);
					}
				});
			} else {
				alert('해당 게시글의 수정 권한이 없습니다.');
				return false;
			}
		}); // end $('input[type="submit"][value="수정"]').click(function(e) {})		
		// 게시글 삭제
		$('input[type="submit"][value="삭제"]').click(function(e) {
			e.preventDefault();
			// 해당 게시글 작성자만 게시글 삭제 가능
			if ("${boardVO.memberNumber}" == "${sessionScope.memberVO.memberNumber}") {
				// 게시글을 삭제할 건지 한번 더 확인
				return confirm('게시글을 삭제하시겠습니까?') ? true : false;			
			} else {
				alert('해당 게시글의 삭제 권한이 없습니다.');
				return false;
			}
		}); // end $('input[type="submit"][value="삭제"]').click(function(e) {})
		/**********************************************************************************************************************************/
	}); // end $(document).ready(function() {})
</script>
</head>
<body>
	<div class="detail-wrapper">
		<h2>${boardVO.boardTitle }</h2>
		<form class="detail-form">
			<div class="writer-wrapper">
				<div class="writer-picture">
					<img src="/mbti/member/display?memberPicture=${boardVO.memberPicture }">
				</div>
				<div class="writer-area">
					<div class="writer-info">
						<button id="memberNickname">${boardVO.memberNickname }</button>
					</div>
					<div class="board-info">
						<fmt:formatDate value="${boardVO.boardRegdate }" pattern="yyyy-MM-dd HH:mm:ss" var="boardRegdate"/>
						<span id="boardRegdate">${boardRegdate }</span>
						<span id="boardViews">조회 ${boardVO.boardViews }</span>
					</div>
				</div>
			</div>
			<textarea rows="20" cols="80" id="boardContent" name="boardContent" readonly>${boardVO.boardContent }</textarea>
			<div class="detail-options">
				<button type="button" class="listGET">목록</button>
				<input type="submit" formaction="/mbti/board/update" formmethod="GET" value="수정">
				<input type="submit" formaction="/mbti/board/delete" formmethod="POST" value="삭제">
			</div>		
			<div class="detail-info">
				<div>
					댓글 <span id="boardComments">${boardVO.boardComments }</span>
				</div>
				&nbsp;&nbsp;
				<div>
					추천 
					<c:if test="${boardVO.boardlikeNumber == 0}">
						<span id="boardLikes" style="color: rgb(0, 0, 0)">${boardVO.boardLikes }</span>
					</c:if>
					<c:if test="${boardVO.boardlikeNumber != 0}">
						<span id="boardLikes" style="color: rgb(255, 0, 0)">${boardVO.boardLikes }</span>
					</c:if>
					<button id="boardLikesToggle">&#x1F44D;</button>
				</div>
			</div>
			<br>
			<div class="comments-wrapper">
				<div class="comments">
					<div class="writer-wrapper">
						<div class="writer-area">
							<div class="writer-info">
								<button id="memberNickname">${sessionScope.memberVO.memberNickname }</button>
							</div>
						</div>
					</div>
					<div class="comments-info">
						<textarea rows="2" id="commentsContent" name="commentsContent" minlength="5" maxlength="2000" placeholder="댓글을 남겨보세요(10글자 이상)"></textarea>
					</div>
					<div class="comments-options">
						<button type="button" class="commentsPOST">등록</button>
					</div>
				</div>
				<div class="comments-list">
				</div>
			</div>
		</form>
	</div>
</body>
</html>