package com.cafe.mbti.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.mbti.domain.BoardRestVO;
import com.cafe.mbti.persistence.BoardRestDAO;

@Service
public class BoardRestServiceImple implements BoardRestService {
	private static final Logger logger = LoggerFactory.getLogger(BoardRestServiceImple.class);
	
	@Autowired
	private BoardRestDAO boardRestDAO;
	
	@Override
	public int createBoardlike(BoardRestVO boardRestVO) {
		logger.info("createBoardlike() 호출");
		return boardRestDAO.insertBoardlike(boardRestVO);
	}
	
	@Override
	public int readBoardlikeCount(int boardNumber) {
		logger.info("readBoardlikeCount() 호출");
		return boardRestDAO.selectBoardlikeCount(boardNumber);
	}

	@Override
	public int delete(BoardRestVO boardRestVO) {
		logger.info("delete() 호출");
		return boardRestDAO.delete(boardRestVO);
	}

	@Override
	public int deleteOnBoard(int boardNumber) {
		logger.info("deleteOnBoard() 호출");
		return boardRestDAO.deleteOnBoard(boardNumber);
	}

	@Override
	public int createBoardview(BoardRestVO boardRestVO) {
		logger.info("createBoardview() 호출");
		return boardRestDAO.insertBoardview(boardRestVO);
	}	
}
