package com.cafe.mbti.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.mbti.domain.CommentsVO;
import com.cafe.mbti.persistence.CommentsDAO;

@Service
public class CommentsServiceImple implements CommentsService {
	private static final Logger logger = LoggerFactory.getLogger(CommentsServiceImple.class);
	
	@Autowired
	private CommentsDAO commentDAO;

	@Override
	public int create(CommentsVO commentsVO) {
		logger.info("create() 호출");
		return commentDAO.insert(commentsVO);
	}

	@Override
	public List<CommentsVO> readAll(int boardNumber) {
		logger.info("readAll() 호출");
		return commentDAO.selectAll(boardNumber);
	}
	
	@Override
	public int readCommentsCount(int boardNumber) {
		logger.info("readCommentsCount() 호출");
		return commentDAO.selectCommentsCount(boardNumber);
	}

	@Override
	public int update(String commentsContent, int commentsNumber) {
		logger.info("update() 호출");
		return commentDAO.update(commentsContent, commentsNumber);
	}

	@Override
	public int delete(int commentsNumber) {
		logger.info("delete() 호출");
		return commentDAO.delete(commentsNumber);
	}
}
