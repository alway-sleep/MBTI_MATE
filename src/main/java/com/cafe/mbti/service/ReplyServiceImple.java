package com.cafe.mbti.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.mbti.domain.ReplyVO;
import com.cafe.mbti.persistence.ReplyDAO;

@Service
public class ReplyServiceImple implements ReplyService {
	private static final Logger logger = LoggerFactory.getLogger(ReplyServiceImple.class);
	
	@Autowired
	private ReplyDAO replyDAO;

	@Override
	public int create(ReplyVO vo) {
		logger.info("create() 호출");
		return replyDAO.insert(vo);
	}

	@Override
	public List<ReplyVO> readAll(int commentsNumber) {
		logger.info("readAll() 호출");
		return replyDAO.selectAll(commentsNumber);
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
