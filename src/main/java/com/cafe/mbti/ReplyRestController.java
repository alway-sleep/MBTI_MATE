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
import com.cafe.mbti.util.CmRpPageCriteria;
import com.cafe.mbti.util.PageMaker;

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
	
	@GetMapping("/list/{commentsNumber}/{replyPage}") // SELECT
	public ResponseEntity<List<ReplyVO>> replyListGET(HttpServletRequest request, @PathVariable("commentsNumber") int commentsNumber, @PathVariable("replyPage") Integer replyPage) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		
		CmRpPageCriteria cmRpPageCriteria = cmRpPageCriteria(replyPage, commentsNumber);
		return new ResponseEntity<List<ReplyVO>>(replyService.readAll(cmRpPageCriteria), HttpStatus.OK);
	} // end replyListGET()
	
	@GetMapping("/count/{commentsNumber}") // SELECT
	public ResponseEntity<Integer> replyCountGET(HttpServletRequest request, @PathVariable("commentsNumber") int commentsNumber) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		
		return new ResponseEntity<Integer>(replyService.readCountOnComments(commentsNumber), HttpStatus.OK);
	} // end replyCountGET()
	
	@GetMapping("/pagemaker/{commentsNumber}") // SELECT
	public ResponseEntity<PageMaker> replyPageMakerGET(HttpServletRequest request, @PathVariable("commentsNumber") int commentsNumber, Integer replyPage) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		
		CmRpPageCriteria cmRpPageCriteria = cmRpPageCriteria(replyPage, commentsNumber);
		return new ResponseEntity<PageMaker>(pageMaker(cmRpPageCriteria, replyService.readCountOnComments(commentsNumber)), HttpStatus.OK);
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
	
	private CmRpPageCriteria cmRpPageCriteria(Integer replyPage, int commentsNumber) {
		CmRpPageCriteria cmRpPageCriteria = new CmRpPageCriteria();
		cmRpPageCriteria.setReplyPage(replyPage != null ? replyPage : cmRpPageCriteria.getReplyPage());
		cmRpPageCriteria.setCommentsNumber(commentsNumber);
		return cmRpPageCriteria;
	} // end replyPageCriteria()
	
	private PageMaker pageMaker(CmRpPageCriteria cmRpPageCriteria, int replyTotalCount) {
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCmRpPageCriteria(cmRpPageCriteria);
		pageMaker.setReplyTotalCount(replyTotalCount);
		pageMaker.setReplyPageData();
		return pageMaker;
	} // end replyPageMaker()
} // end ReplyRestController
