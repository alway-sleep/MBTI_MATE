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

import com.cafe.mbti.domain.CommentsVO;
import com.cafe.mbti.service.CommentsService;
import com.cafe.mbti.util.CmRpPageCriteria;
import com.cafe.mbti.util.PageMaker;

@RestController
@RequestMapping(value="/comments")
public class CommentsRestController {
	private static final Logger logger = LoggerFactory.getLogger(CommentsRestController.class);
	
	@Autowired
	private CommentsService commentsService;
	
	@PostMapping("/post") // INSERT
	public ResponseEntity<Integer> commentsPOST(HttpServletRequest request, @RequestBody CommentsVO commentsVO) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		
		return new ResponseEntity<Integer>(commentsService.create(commentsVO), HttpStatus.OK);
	} // end commentsPOST()
	
	@GetMapping("/list/{boardNumber}/{commentsPage}") // SELECT
	public ResponseEntity<List<CommentsVO>> commentsListGET(HttpServletRequest request, @PathVariable("boardNumber") int boardNumber, @PathVariable("commentsPage") Integer commentsPage) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		
		CmRpPageCriteria cmRpPageCriteria = cmRpPageCriteria(commentsPage, boardNumber);
		return new ResponseEntity<List<CommentsVO>>(commentsService.readAll(cmRpPageCriteria), HttpStatus.OK);
	} // end commentsListGET()
	
	@GetMapping("/count/{boardNumber}") // SELECT
	public ResponseEntity<Integer> commentsCountGET(HttpServletRequest request, @PathVariable("boardNumber") int boardNumber) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());

		return new ResponseEntity<Integer>(commentsService.readCountOnBoard(boardNumber), HttpStatus.OK);
	} // end commentsCountGET()
	
	@GetMapping("/pagemaker/{boardNumber}") // SELECT
	public ResponseEntity<PageMaker> PageMakerGET(HttpServletRequest request, @PathVariable("boardNumber") int boardNumber, Integer commentsPage) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		
		CmRpPageCriteria cmRpPageCriteria = cmRpPageCriteria(commentsPage, boardNumber);
		return new ResponseEntity<PageMaker>(pageMaker(cmRpPageCriteria, commentsService.readCountOnBoard(boardNumber)), HttpStatus.OK);
	} // end commentscommentsPageMakerGET()
	
	@GetMapping("/{boardNumber}") // SELECT
	public ResponseEntity<Integer> boardCommentsGET(HttpServletRequest request, @PathVariable("boardNumber") int boardNumber) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		
		return new ResponseEntity<Integer>(commentsService.readBoardComments(boardNumber), HttpStatus.OK);
	} // end commentsGET()
	
	@PutMapping("/update/{commentsNumber}") // UPDATE
	public ResponseEntity<Integer> commentsPUT(HttpServletRequest request, @RequestBody String commentsContent, @PathVariable("commentsNumber") int commentsNumber) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		
		return new ResponseEntity<Integer>(commentsService.update(commentsContent, commentsNumber), HttpStatus.OK);
	} // end commentsPUT()
	
	@DeleteMapping("/delete/{commentsNumber}") // DELETE
	public ResponseEntity<Integer> commentsDELETE(HttpServletRequest request, @PathVariable("commentsNumber") int commentsNumber) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());

		return new ResponseEntity<Integer>(commentsService.delete(commentsNumber), HttpStatus.OK);
	} // end commentsDELETE()
	
	private CmRpPageCriteria cmRpPageCriteria(Integer commentsPage, int boardNumber) {
		CmRpPageCriteria commentsPageCriteria = new CmRpPageCriteria();
		commentsPageCriteria.setCommentsPage(commentsPage != null ? commentsPage : commentsPageCriteria.getCommentsPage());
		commentsPageCriteria.setBoardNumber(boardNumber);
		return commentsPageCriteria;
	} // end commentscommentsPageCriteria()
	
	private PageMaker pageMaker(CmRpPageCriteria cmRpPageCriteria, int commentsTotalCount) {
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCmRpPageCriteria(cmRpPageCriteria);
		pageMaker.setCommentsTotalCount(commentsTotalCount);
		pageMaker.setCommentsPageData();
		return pageMaker;
	} // end commentscommentsPageMaker()
} // end CommentRestController
