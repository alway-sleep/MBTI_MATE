package com.cafe.mbti;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.mbti.domain.BoardlikeVO;
import com.cafe.mbti.service.BoardlikeService;

/*
 RESTful URL과 의미
 /comment (POST) : 댓글 추가(insert)
 /comment/all/숫자 (GET) : 해당 글 번호(boardNumber)의 모든 댓글 검색(select)
 /comment/숫자 (PUT) : 해당 댓글 번호(commentNumber)의 내용을 수정(update)
 /comment/숫자 (DELETE) : 해당 댓글 번호(commentNumber)의 댓글을 삭제(delete)
 
 TODO : @RestController
 스프링 프레임워크에서 제공하는 어노테이션 중 하나로,
 해당 클래스에서 반환하는 값들이 모두 HTTP Response Body에 직접 작성되도록 해줍니다.
 일반적으로 JSON 형태로 데이터를 반환하는 RESTful API를 구현할 때 많이 사용됩니다.
 따라서 일반적인 컨트롤러와는 달리 View를 반환하지 않으며,
 HTTP Response Body에 직접 데이터를 작성하므로 @ResponseBody 어노테이션을 사용할 필요가 없습니다.
 */
@RestController
@RequestMapping(value="/boardlike")
public class BoardlikeRestController {
	private static final Logger logger = LoggerFactory.getLogger(BoardlikeRestController.class);
	
	@Autowired
	private BoardlikeService boardlikeService;
	
	@PostMapping // POST : 게시글 추천수 + 1
	// @RequestBody : 클라이언트에서 전송받은 JSON타입의 데이터를 자바 객체로 변환시켜주는 annotation
	public ResponseEntity<Integer> boardlikePOST(HttpServletRequest request, @RequestBody BoardlikeVO boardlikeVO) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		
		return new ResponseEntity<Integer>(boardlikeService.create(boardlikeVO), HttpStatus.OK);
	} // end createBoardlike()
	
	@GetMapping("/{boardNumber}") // GET : 게시글 추천수 카운팅
	public ResponseEntity<Integer> boardLikesGET(HttpServletRequest request, @PathVariable("boardNumber") Integer boardNumber) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());

		return new ResponseEntity<Integer>(boardlikeService.readBoardlikeCount(boardNumber), HttpStatus.OK);
	} // end selectBoardLikes()
	
	@DeleteMapping // DELETE : 게시글 추천수 - 1
	public ResponseEntity<Integer> boardlikeDELETE(HttpServletRequest request, @RequestBody BoardlikeVO boardlikeVO) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());

		return new ResponseEntity<Integer>(boardlikeService.delete(boardlikeVO), HttpStatus.OK);
	} // end deleteBoardlike()	
} // end CommentRestController