package com.cafe.mbti.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe.mbti.domain.BoardVO;
import com.cafe.mbti.persistence.BoardDAO;
import com.cafe.mbti.persistence.BoardlikeDAO;
import com.cafe.mbti.persistence.CommentsDAO;
import com.cafe.mbti.persistence.FilesDAO;
import com.cafe.mbti.persistence.ReplyDAO;
import com.cafe.mbti.util.PageCriteria;

@Service // @Component
/*
 * 서비스 계층(Service/Business Layer)
 * 표현 계층(Presentation Layer)과 영속 계층(Presistence Layer)사이를 연결하여 두 계층이 직접적으로 통신하지 않도록 하는 역할
 * 트랜잭션(transaction) 관리
 * DB와 상관없이 데이터를 처리 가능
 */
public class BoardServiceImple implements BoardService {
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImple.class);
	
	@Autowired
	private BoardDAO boardDAO;
	@Autowired
	private BoardlikeDAO boardlikeDAO;
	@Autowired
	private CommentsDAO commentsDAO;
	@Autowired
	private ReplyDAO replyDAO;
	@Autowired
	private FilesDAO filesDAO;

	@Override
	public int create(BoardVO boardVO) {
		logger.info("BOARD create() 호출");
		return boardDAO.insert(boardVO);
	}

	@Override
	public int readBoardCount() {
		logger.info("BOARD readBoardTotalCount() 호출");
		return boardDAO.selectBoardCount();
	}

	@Override
	public List<BoardVO> readAll(PageCriteria pageCriteria) {
		logger.info("BOARD readAll() 호출");	
		return boardDAO.selectAll(pageCriteria);
	}
	
	@Override
	public List<BoardVO> readBoard(PageCriteria pageCriteria) {
		logger.info("BOARD readBoard() 호출");
		return boardDAO.selectBoard(pageCriteria);
	}

	@Override
	public BoardVO read(int boardNumber, int memberNumber) {
		logger.info("BOARD read() 호출");
		return boardDAO.select(boardNumber, memberNumber);
	}

	@Override
	public List<BoardVO> readByBoardTitle(PageCriteria pageCriteria) {
		logger.info("BOARD readByBoardTitle() 호출");
		return boardDAO.selectByBoardTitle(pageCriteria);
	}

	@Override
	public List<BoardVO> readByBoardContent(PageCriteria pageCriteria) {
		logger.info("BOARD readByBoardContent() 호출");
		return boardDAO.selectByBoardContent(pageCriteria);
	}

	@Override
	public List<BoardVO> readByNicknameOnBoard(PageCriteria pageCriteria) {
		logger.info("BOARD readByNicknameOnBoard() 호출");
		return boardDAO.selectByNicknameOnBoard(pageCriteria);
	}

	@Override
	public List<BoardVO> readByCmRpContent(PageCriteria pageCriteria) {
		logger.info("BOARD readByCmRpContent() 호출");
		return boardDAO.selectByCmRpContent(pageCriteria);
	}

	@Override
	public List<BoardVO> readByNicknameOnCmRp(PageCriteria pageCriteria) {
		logger.info("BOARD readByNicknameOnCmRp() 호출");
		return boardDAO.selectByNicknameOnCmRp(pageCriteria);
	}

	@Override
	public int update(int boardSection, int boardList, String boardName, String boardTitle, String boardContent, int boardNumber) {
		logger.info("BOARD update() 호출");
		return boardDAO.update(boardSection, boardList, boardName, boardTitle, boardContent, boardNumber);
	}
	
	@Override
	public int updateType(int boardType, int boardNumber) {
		logger.info("BOARD updateType() 호출");
		return boardDAO.updateType(boardType, boardNumber);
	}

	@Transactional(value = "transactionManager") // - root-context.xml에서 설정
	@Override
	public int delete(int boardNumber) throws Exception {
		logger.info("BOARD delete() 호출");
		boardlikeDAO.deleteOnBoard(boardNumber);
		commentsDAO.updateDeleteOnBoard(boardNumber);
		filesDAO.deleteOnBoard(boardNumber);
		return boardDAO.delete(boardNumber);
	}
}
