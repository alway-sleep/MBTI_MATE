package com.cafe.mbti.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe.mbti.domain.BoardVO;
import com.cafe.mbti.persistence.BoardDAO;
import com.cafe.mbti.persistence.BoardRestDAO;
import com.cafe.mbti.persistence.CommentsDAO;
import com.cafe.mbti.persistence.ReplyDAO;
import com.cafe.mbti.util.BoardPageCriteria;

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
	private BoardRestDAO boardlikeDAO;
	@Autowired
	private CommentsDAO commentsDAO;
	@Autowired
	private ReplyDAO replyDAO;

	@Override
	public int create(BoardVO boardVO) {
		logger.info("create() 호출");
		return boardDAO.insert(boardVO);
	}

	@Override
	public int readCountOnBoard() {
		logger.info("readCountOnBoard() 호출");
		return boardDAO.selectCountOnBoard();
	}

	@Override
	public int readCountOnBoard2(BoardPageCriteria boardboardPageCriteria) {
		logger.info("readCountOnBoard2() 호출");
		return boardDAO.selectCountOnBoard2(boardboardPageCriteria);
	}

	@Override
	public int readCountByBoardTitle(BoardPageCriteria boardPageCriteria) {
		logger.info("readCountByBoardTitle() 호출");
		return boardDAO.selectCountByBoardTitle(boardPageCriteria);
	}

	@Override
	public int readCountByBoardContent(BoardPageCriteria boardPageCriteria) {
		logger.info("readCountByBoardContent() 호출");
		return boardDAO.selectCountByBoardContent(boardPageCriteria);
	}

	@Override
	public int readCountByNicknameOnBoard(BoardPageCriteria boardPageCriteria) {
		logger.info("readCountByNicknameOnBoard() 호출");
		return boardDAO.selectCountByNicknameOnBoard(boardPageCriteria);
	}

	@Override
	public int readCountByCmRpContent(BoardPageCriteria boardPageCriteria) {
		logger.info("readCountByCmRpContent() 호출");
		return boardDAO.selectCountByCmRpContent(boardPageCriteria);
	}

	@Override
	public int readCountByNicknameOnCmRp(BoardPageCriteria boardPageCriteria) {
		logger.info("readCountByNicknameOnCmRp() 호출");
		return boardDAO.selectCountByNicknameOnCmRp(boardPageCriteria);
	}
	
	@Override
	public int readCountByBoardTitle2(BoardPageCriteria boardPageCriteria) {
		logger.info("readCountByBoardTitle2() 호출");
		return boardDAO.selectCountByBoardTitle2(boardPageCriteria);
	}

	@Override
	public int readCountByBoardContent2(BoardPageCriteria boardPageCriteria) {
		logger.info("readCountByBoardContent2() 호출");
		return boardDAO.selectCountByBoardContent2(boardPageCriteria);
	}

	@Override
	public int readCountByNicknameOnBoard2(BoardPageCriteria boardPageCriteria) {
		logger.info("readCountByNicknameOnBoard2() 호출");
		return boardDAO.selectCountByNicknameOnBoard2(boardPageCriteria);
	}

	@Override
	public int readCountByCmRpContent2(BoardPageCriteria boardPageCriteria) {
		logger.info("readCountByCmRpContent2() 호출");
		return boardDAO.selectCountByCmRpContent2(boardPageCriteria);
	}

	@Override
	public int readCountByNicknameOnCmRp2(BoardPageCriteria boardPageCriteria) {
		logger.info("readCountByNicknameOnCmRp2() 호출");
		return boardDAO.selectCountByNicknameOnCmRp2(boardPageCriteria);
	}

	@Override
	public List<BoardVO> readAll(BoardPageCriteria boardPageCriteria) {
		logger.info("readAll() 호출");	
		return boardDAO.selectAll(boardPageCriteria);
	}
	
	@Override
	public List<BoardVO> readBoard(BoardPageCriteria boardPageCriteria) {
		logger.info("readBoard() 호출");
		return boardDAO.selectBoard(boardPageCriteria);
	}

	@Override
	public BoardVO read(int boardNumber, int memberNumber) {
		logger.info("read() 호출");
		return boardDAO.select(boardNumber, memberNumber);
	}

	@Override
	public List<BoardVO> readByBoardTitle(BoardPageCriteria boardPageCriteria) {
		logger.info("readByBoardTitle() 호출");
		return boardDAO.selectByBoardTitle(boardPageCriteria);
	}

	@Override
	public List<BoardVO> readByBoardContent(BoardPageCriteria boardPageCriteria) {
		logger.info("readByBoardContent() 호출");
		return boardDAO.selectByBoardContent(boardPageCriteria);
	}

	@Override
	public List<BoardVO> readByNicknameOnBoard(BoardPageCriteria boardPageCriteria) {
		logger.info("readByNicknameOnBoard() 호출");
		return boardDAO.selectByNicknameOnBoard(boardPageCriteria);
	}

	@Override
	public List<BoardVO> readByCmRpContent(BoardPageCriteria boardPageCriteria) {
		logger.info("readByCmRpContent() 호출");
		return boardDAO.selectByCmRpContent(boardPageCriteria);
	}

	@Override
	public List<BoardVO> readByNicknameOnCmRp(BoardPageCriteria boardPageCriteria) {
		logger.info("readByNicknameOnCmRp() 호출");
		return boardDAO.selectByNicknameOnCmRp(boardPageCriteria);
	}
	@Override
	public List<BoardVO> readByBoardTitle2(BoardPageCriteria boardPageCriteria) {
		logger.info("readByBoardTitle2() 호출");
		return boardDAO.selectByBoardTitle2(boardPageCriteria);
	}

	@Override
	public List<BoardVO> readByBoardContent2(BoardPageCriteria boardPageCriteria) {
		logger.info("readByBoardContent2() 호출");
		return boardDAO.selectByBoardContent2(boardPageCriteria);
	}

	@Override
	public List<BoardVO> readByNicknameOnBoard2(BoardPageCriteria boardPageCriteria) {
		logger.info("readByNicknameOnBoard2() 호출");
		return boardDAO.selectByNicknameOnBoard2(boardPageCriteria);
	}

	@Override
	public List<BoardVO> readByCmRpContent2(BoardPageCriteria boardPageCriteria) {
		logger.info("readByCmRpContent2() 호출");
		return boardDAO.selectByCmRpContent2(boardPageCriteria);
	}

	@Override
	public List<BoardVO> readByNicknameOnCmRp2(BoardPageCriteria boardPageCriteria) {
		logger.info("readByNicknameOnCmRp2() 호출");
		return boardDAO.selectByNicknameOnCmRp2(boardPageCriteria);
	}

	@Override
	public int readBoardViews(int boardNumber) {
		logger.info("readBoardViews() 호출");
		return boardDAO.selectBoardViews(boardNumber);
	}

	@Override
	public int update(int boardSection, int boardList, String boardName, String boardTitle, String boardContent, String boardFiles, int boardNumber) {
		logger.info("update() 호출");
		return boardDAO.update(boardSection, boardList, boardName, boardTitle, boardContent, boardFiles, boardNumber);
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
		return boardDAO.delete(boardNumber);
	}
}
