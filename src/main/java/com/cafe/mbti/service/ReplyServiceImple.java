package com.cafe.mbti.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.mbti.domain.ReplyVO;
import com.cafe.mbti.persistence.ReplyDAO;
import com.cafe.mbti.util.ReplyPageCriteria;

@Service
public class ReplyServiceImple implements ReplyService {
	private static final Logger logger = LoggerFactory.getLogger(ReplyServiceImple.class);
	
	@Autowired
	private ReplyDAO replyDAO;

	@Override
	public int create(ReplyVO replyVO) {
		logger.info("create() 호출");
		return replyDAO.insert(replyVO);
	}

	@Override
	public List<ReplyVO> readAll(ReplyPageCriteria replyPageCriteria) {
		logger.info("readAll() 호출");
		return replyDAO.selectAll(replyPageCriteria);
	}
	
	@Override
	public int readCountOnComments(int commentsNumber) {
		logger.info("readCountOnComments() 호출");
		return replyDAO.selectCountOnComments(commentsNumber);
	}

	@Override
	public int update(String replyContent, int replyNumber) {
		logger.info("update() 호출");
		return replyDAO.update(replyContent, replyNumber);
	}

	@Override
	public int delete(int replyNumber) {
		logger.info("delete() 호출");
		return replyDAO.delete(replyNumber);
	}
}
