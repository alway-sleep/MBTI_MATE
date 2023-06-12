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
import com.cafe.mbti.util.ReplyPageCriteria;
import com.cafe.mbti.util.ReplyPageMaker;

@RestController
@RequestMapping(value="/reply")
public class ReplyRestController {
	private static final Logger logger = LoggerFactory.getLogger(ReplyRestController.class);
	
	@Autowired
	private ReplyService replyService;
	
	@PostMapping("/post") // INSERT
	public ResponseEntity<Integer> replyPOST(HttpServletRequest request, @RequestBody ReplyVO replyVO) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());

		return new ResponseEntity<Integer>(replyService.create(replyVO), HttpStatus.OK);
	} // end replyPOST()
	
	@GetMapping("/list/{commentsNumber}/{page}") // SELECT
	public ResponseEntity<List<ReplyVO>> replyListGET(HttpServletRequest request, @PathVariable("commentsNumber") int commentsNumber, @PathVariable("page") Integer page) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		
		ReplyPageCriteria replyPageCriteria = replyPageCriteria(page, commentsNumber);
		return new ResponseEntity<List<ReplyVO>>(replyService.readAll(replyPageCriteria), HttpStatus.OK);
	} // end replyListGET()
	
	@GetMapping("/count/{commentsNumber}") // SELECT
	public ResponseEntity<Integer> replyCountGET(HttpServletRequest request, @PathVariable("commentsNumber") int commentsNumber) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		
		return new ResponseEntity<Integer>(replyService.readCountOnComments(commentsNumber), HttpStatus.OK);
	} // end replyCountGET()
	
	@GetMapping("/pagemaker/{commentsNumber}") // SELECT
	public ResponseEntity<ReplyPageMaker> replyPageMakerGET(HttpServletRequest request, @PathVariable("commentsNumber") int commentsNumber, Integer page) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		
		ReplyPageCriteria replyPageCriteria = replyPageCriteria(page, commentsNumber);
		return new ResponseEntity<ReplyPageMaker>(replyPageMaker(replyPageCriteria, replyService.readCountOnComments(commentsNumber)), HttpStatus.OK);
	} // end replyPageMakerGET()
	
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
	
	private ReplyPageCriteria replyPageCriteria(Integer page, int commentsNumber) {
		ReplyPageCriteria replyPageCriteria = new ReplyPageCriteria();
		replyPageCriteria.setPage(page != null ? page : replyPageCriteria.getPage());
		replyPageCriteria.setCommentsNumber(commentsNumber);
		return replyPageCriteria;
	} // end replyPageCriteria()
	
	private ReplyPageMaker replyPageMaker(ReplyPageCriteria replyPageCriteria, int totalCount) {
		ReplyPageMaker replyPageMaker = new ReplyPageMaker();
		replyPageMaker.setCriteria(replyPageCriteria);
		replyPageMaker.setTotalCount(totalCount);
		replyPageMaker.setPageData();
		return replyPageMaker;
	} // end replyPageMaker()
} // end ReplyRestController
