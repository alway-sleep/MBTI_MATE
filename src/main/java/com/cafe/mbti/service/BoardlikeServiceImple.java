package com.cafe.mbti.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.mbti.domain.BoardlikeVO;
import com.cafe.mbti.persistence.BoardlikeDAO;

@Service
public class BoardlikeServiceImple implements BoardlikeService {
	private static final Logger logger = LoggerFactory.getLogger(BoardlikeServiceImple.class);
	
	@Autowired
	private BoardlikeDAO boardlikeDAO;
	
	@Override
	public int create(BoardlikeVO boardlikeVO) {
		logger.info("create() 호출");
		return boardlikeDAO.insert(boardlikeVO);
	}
	
	@Override
	public int readBoardlikeCount(int boardNumber) {
		logger.info("readBoardlikeCount() 호출");
		return boardlikeDAO.selectBoardlikeCount(boardNumber);
	}

	@Override
	public int delete(BoardlikeVO boardlikeVO) {
		logger.info("delete() 호출");
		return boardlikeDAO.delete(boardlikeVO);
	}

	@Override
	public int deleteOnBoard(int boardNumber) {
		logger.info("deleteOnBoard() 호출");
		return boardlikeDAO.deleteOnBoard(boardNumber);
	}
}
