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
import com.cafe.mbti.util.CommentsPageCriteria;
import com.cafe.mbti.util.CommentsPageMaker;

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
	
	@GetMapping("/list/{boardNumber}/{page}") // SELECT
	public ResponseEntity<List<CommentsVO>> commentsListGET(HttpServletRequest request, @PathVariable("boardNumber") int boardNumber, @PathVariable("page") Integer page) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		
		CommentsPageCriteria commentsPageCriteria = commentsPageCriteria(page, boardNumber);
		return new ResponseEntity<List<CommentsVO>>(commentsService.readAll(commentsPageCriteria), HttpStatus.OK);
	} // end commentsListGET()
	
	@GetMapping("/count/{boardNumber}") // SELECT
	public ResponseEntity<Integer> commentsCountGET(HttpServletRequest request, @PathVariable("boardNumber") int boardNumber) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());

		return new ResponseEntity<Integer>(commentsService.readCountOnBoard(boardNumber), HttpStatus.OK);
	} // end commentsCountGET()
	
	@GetMapping("/pagemaker/{boardNumber}") // SELECT
	public ResponseEntity<CommentsPageMaker> commentsPageMakerGET(HttpServletRequest request, @PathVariable("boardNumber") int boardNumber, Integer page) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		
		CommentsPageCriteria commentsPageCriteria = commentsPageCriteria(page, boardNumber);
		return new ResponseEntity<CommentsPageMaker>(commentsPageMaker(commentsPageCriteria, commentsService.readCountOnBoard(boardNumber)), HttpStatus.OK);
	} // end commentsPageMakerGET()
	
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
	
	private CommentsPageCriteria commentsPageCriteria(Integer page, int boardNumber) {
		CommentsPageCriteria commentsPageCriteria = new CommentsPageCriteria();
		commentsPageCriteria.setPage(page != null ? page : commentsPageCriteria.getPage());
		commentsPageCriteria.setBoardNumber(boardNumber);
		return commentsPageCriteria;
	} // end commentsPageCriteria()
	
	private CommentsPageMaker commentsPageMaker(CommentsPageCriteria commentsPageCriteria, int totalCount) {
		CommentsPageMaker commentsPageMaker = new CommentsPageMaker();
		commentsPageMaker.setCriteria(commentsPageCriteria);
		commentsPageMaker.setTotalCount(totalCount);
		commentsPageMaker.setPageData();
		return commentsPageMaker;
	} // end commentsPageMaker()
} // end CommentRestController
