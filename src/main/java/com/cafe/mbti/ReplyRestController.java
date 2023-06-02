package com.cafe.mbti;

import java.util.List;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.mbti.domain.ReplyVO;
import com.cafe.mbti.service.ReplyService;

@RestController
@RequestMapping(value="/reply")
public class ReplyRestController {
	private static final Logger logger = LoggerFactory.getLogger(ReplyRestController.class);
	
	@Autowired
	private ReplyService replyService;
	
	@PostMapping // POST : 대댓글 입력
	// @RequestBody : 클라이언트에서 전송받은 JSON타입의 데이터를 자바 객체로 변환시켜주는 annotation
	public ResponseEntity<Integer> createReply(HttpServletRequest request, @RequestBody ReplyVO vo) {
		logger.info("Request URL (POST)  : {}", request.getRequestURI());
		logger.info("vo = " + vo.toString());
		
		// ResponseEntity<T> : REST 방식에서 데이터를 리턴할 때 쓰이는 객체
		// 데이터 HttpStatus를 전송
		// <T> : 보내고자 하는 데이터 타입
		int result = replyService.create(vo);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	} // end createReply()
	
	@GetMapping("/all/{commentNumber}") // GET : 대댓글 선택(all)
	// @PathVariable("commentNumber") : /all/{commentNumber} 값을 설정된 변수에 저장
	public ResponseEntity<List<ReplyVO>> readAllReply(HttpServletRequest request, @PathVariable("commentNumber") int commentNumber) {
		logger.info("Request URL (GET)  : {}", request.getRequestURI());
		List<ReplyVO> list = replyService.readAll(commentNumber);
		for(ReplyVO vo : list) {
			logger.info(vo.toString());
		}
		return new ResponseEntity<List<ReplyVO>>(list, HttpStatus.OK);
	} // end readAllReply()
	
	@PutMapping("/{replyNumber}") // PUT : 대댓글 수정
	public ResponseEntity<Integer> updateReply(HttpServletRequest request, @RequestBody String replyContent, @PathVariable("replyNumber") int replyNumber) {
		logger.info("Request URL (PUT)  : {}", request.getRequestURI());
		logger.info("replyNumber = " + replyNumber + ", replyContent = " + replyContent);
		int result = replyService.update(replyContent, replyNumber);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	} // end updateReply()
	
	@DeleteMapping("/{replyNumber}") // DELETE : 대댓글 삭제
	public ResponseEntity<Integer> deleteReply(HttpServletRequest request, @PathVariable("replyNumber") int replyNumber) {
		logger.info("Request URL (DELETE)  : {}", request.getRequestURI());
		logger.info("replyNumber = " + replyNumber);
		int result = replyService.delete(replyNumber);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	} // end deleteReply()
	
} // end ReplyRestController