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
	
	@PostMapping // INSERT
	public ResponseEntity<Integer> replyPOST(HttpServletRequest request, @RequestBody ReplyVO replyVO) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());

		return new ResponseEntity<Integer>(replyService.create(replyVO), HttpStatus.OK);
	} // end replyPOST()
	
	@GetMapping("/list/{commentNumber}") // SELECT
	public ResponseEntity<List<ReplyVO>> replyListGET(HttpServletRequest request, @PathVariable("commentNumber") int commentNumber) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		
		return new ResponseEntity<List<ReplyVO>>(replyService.readAll(commentNumber), HttpStatus.OK);
	} // end replyListGET()
	
	@GetMapping("/{commentNumber}") // SELECT
	public ResponseEntity<List<ReplyVO>> replyCountGET(HttpServletRequest request, @PathVariable("commentNumber") int commentNumber) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		
		return new ResponseEntity<List<ReplyVO>>(replyService.readAll(commentNumber), HttpStatus.OK);
	} // end replyCountGET()
	
	@PutMapping("/update/{replyNumber}") // UPDATE
	public ResponseEntity<Integer> replyPUT(HttpServletRequest request, @RequestBody String replyContent, @PathVariable("replyNumber") int replyNumber) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());

		return new ResponseEntity<Integer>(replyService.update(replyContent, replyNumber), HttpStatus.OK);
	} // end replyPUT()
	
	@DeleteMapping("/delete/{replyNumber}") // DELETE
	public ResponseEntity<Integer> replyDELETE(HttpServletRequest request, @PathVariable("replyNumber") int replyNumber) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());

		return new ResponseEntity<Integer>(replyService.delete(replyNumber), HttpStatus.OK);
	} // end replyDELETE()
	
} // end ReplyRestController