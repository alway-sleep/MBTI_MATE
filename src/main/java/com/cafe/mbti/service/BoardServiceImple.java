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

	@Transactional(value = "transactionManager")
	@Override
	public int create(BoardVO boardVO) {
		logger.info("create() 호출");
		System.out.println(boardVO.getFilesName().toString());
		boardDAO.insert(boardVO);
		return 1;
	}

	@Override
	public int readCountOnBoard() {
		logger.info("readCountOnBoard() 호출");
		return boardDAO.selectCountOnBoard();
	}

	@Override
	public int readCountOnBoard2(PageCriteria pageCriteria) {
		logger.info("readCountOnBoard2() 호출");
		return boardDAO.selectCountOnBoard2(pageCriteria);
	}

	@Override
	public int readCountByBoardTitle(PageCriteria pageCriteria) {
		logger.info("readCountByBoardTitle() 호출");
		return boardDAO.selectCountByBoardTitle(pageCriteria);
	}

	@Override
	public int readCountByBoardContent(PageCriteria pageCriteria) {
		logger.info("readCountByBoardContent() 호출");
		return boardDAO.selectCountByBoardContent(pageCriteria);
	}

	@Override
	public int readCountByNicknameOnBoard(PageCriteria pageCriteria) {
		logger.info("readCountByNicknameOnBoard() 호출");
		return boardDAO.selectCountByNicknameOnBoard(pageCriteria);
	}

	@Override
	public int readCountByCmRpContent(PageCriteria pageCriteria) {
		logger.info("readCountByCmRpContent() 호출");
		return boardDAO.selectCountByCmRpContent(pageCriteria);
	}

	@Override
	public int readCountByNicknameOnCmRp(PageCriteria pageCriteria) {
		logger.info("readCountByNicknameOnCmRp() 호출");
		return boardDAO.selectCountByNicknameOnCmRp(pageCriteria);
	}
	
	@Override
	public int readCountByBoardTitle2(PageCriteria pageCriteria) {
		logger.info("readCountByBoardTitle2() 호출");
		return boardDAO.selectCountByBoardTitle2(pageCriteria);
	}

	@Override
	public int readCountByBoardContent2(PageCriteria pageCriteria) {
		logger.info("readCountByBoardContent2() 호출");
		return boardDAO.selectCountByBoardContent2(pageCriteria);
	}

	@Override
	public int readCountByNicknameOnBoard2(PageCriteria pageCriteria) {
		logger.info("readCountByNicknameOnBoard2() 호출");
		return boardDAO.selectCountByNicknameOnBoard2(pageCriteria);
	}

	@Override
	public int readCountByCmRpContent2(PageCriteria pageCriteria) {
		logger.info("readCountByCmRpContent2() 호출");
		return boardDAO.selectCountByCmRpContent2(pageCriteria);
	}

	@Override
	public int readCountByNicknameOnCmRp2(PageCriteria pageCriteria) {
		logger.info("readCountByNicknameOnCmRp2() 호출");
		return boardDAO.selectCountByNicknameOnCmRp2(pageCriteria);
	}

	@Override
	public List<BoardVO> readAll(PageCriteria pageCriteria) {
		logger.info("readAll() 호출");	
		return boardDAO.selectAll(pageCriteria);
	}
	
	@Override
	public List<BoardVO> readBoard(PageCriteria pageCriteria) {
		logger.info("readBoard() 호출");
		return boardDAO.selectBoard(pageCriteria);
	}

	@Override
	public BoardVO read(int boardNumber, int memberNumber) {
		logger.info("read() 호출");
		return boardDAO.select(boardNumber, memberNumber);
	}

	@Override
	public List<BoardVO> readByBoardTitle(PageCriteria pageCriteria) {
		logger.info("readByBoardTitle() 호출");
		return boardDAO.selectByBoardTitle(pageCriteria);
	}

	@Override
	public List<BoardVO> readByBoardContent(PageCriteria pageCriteria) {
		logger.info("readByBoardContent() 호출");
		return boardDAO.selectByBoardContent(pageCriteria);
	}

	@Override
	public List<BoardVO> readByNicknameOnBoard(PageCriteria pageCriteria) {
		logger.info("readByNicknameOnBoard() 호출");
		return boardDAO.selectByNicknameOnBoard(pageCriteria);
	}

	@Override
	public List<BoardVO> readByCmRpContent(PageCriteria pageCriteria) {
		logger.info("readByCmRpContent() 호출");
		return boardDAO.selectByCmRpContent(pageCriteria);
	}

	@Override
	public List<BoardVO> readByNicknameOnCmRp(PageCriteria pageCriteria) {
		logger.info("readByNicknameOnCmRp() 호출");
		return boardDAO.selectByNicknameOnCmRp(pageCriteria);
	}
	@Override
	public List<BoardVO> readByBoardTitle2(PageCriteria pageCriteria) {
		logger.info("readByBoardTitle2() 호출");
		return boardDAO.selectByBoardTitle2(pageCriteria);
	}

	@Override
	public List<BoardVO> readByBoardContent2(PageCriteria pageCriteria) {
		logger.info("readByBoardContent2() 호출");
		return boardDAO.selectByBoardContent2(pageCriteria);
	}

	@Override
	public List<BoardVO> readByNicknameOnBoard2(PageCriteria pageCriteria) {
		logger.info("readByNicknameOnBoard2() 호출");
		return boardDAO.selectByNicknameOnBoard2(pageCriteria);
	}

	@Override
	public List<BoardVO> readByCmRpContent2(PageCriteria pageCriteria) {
		logger.info("readByCmRpContent2() 호출");
		return boardDAO.selectByCmRpContent2(pageCriteria);
	}

	@Override
	public List<BoardVO> readByNicknameOnCmRp2(PageCriteria pageCriteria) {
		logger.info("readByNicknameOnCmRp2() 호출");
		return boardDAO.selectByNicknameOnCmRp2(pageCriteria);
	}

	@Override
	public int update(int boardSection, int boardList, String boardName, String boardTitle, String boardContent, int boardNumber) {
		logger.info("update() 호출");
		return boardDAO.update(boardSection, boardList, boardName, boardTitle, boardContent, boardNumber);
	}
	
	@Override
	public int updateType(int boardType, int boardNumber) {
		logger.info("updateType() 호출");
		return boardDAO.updateType(boardType, boardNumber);
	}

	@Transactional(value = "transactionManager") // - root-context.xml에서 설정
	@Override
	public int delete(int boardNumber) throws Exception {
		logger.info("delete() 호출");
		boardlikeDAO.deleteOnBoard(boardNumber);
		commentsDAO.updateDeleteOnBoard(boardNumber);
		replyDAO.updateDeleteOnBoard(boardNumber);
		filesDAO.deleteOnBoard(boardNumber);
		return boardDAO.delete(boardNumber);
	}
}
