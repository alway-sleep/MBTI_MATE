package com.cafe.mbti.persistence;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe.mbti.domain.BoardlikeVO;

@Repository
public class BoardlikeDAOImple implements BoardlikeDAO {
	private static final Logger logger = LoggerFactory.getLogger(BoardlikeDAOImple.class);
	private static final String NAMESPACE = "com.cafe.mbti.BoardlikeMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(BoardlikeVO boardlikeVO) {
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", boardlikeVO);
	}

	@Override
	public int selectBoardlikeCount(int boardNumber) {
		logger.info("selectBoardlikeCount() 호출");
		return sqlSession.selectOne(NAMESPACE + ".selectBoardlikeCount", boardNumber);
	}
	
	@Override
	public int delete(BoardlikeVO boardlikeVO) {
		logger.info("delete() 호출");
		return sqlSession.delete(NAMESPACE + ".delete", boardlikeVO);
	}

	@Override
	public int deleteOnBoard(int boardNumber) {
		logger.info("deleteOnBoard() 호출");
		return sqlSession.delete(NAMESPACE + ".deleteOnBoard", boardNumber);
	}

	@Override
	public int deleteOnMember(int memberNumber) {
		logger.info("deleteOnMember() 호출");
		return sqlSession.delete(NAMESPACE + ".deleteOnMember", memberNumber);
	}
}
