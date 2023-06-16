package com.cafe.mbti.persistence;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe.mbti.domain.BoardRestVO;

@Repository
public class BoardRestDAOImple implements BoardRestDAO {
	private static final Logger logger = LoggerFactory.getLogger(BoardRestDAOImple.class);
	private static final String NAMESPACE = "com.cafe.mbti.BoardrestMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertBoardlike(BoardRestVO boardRestVO) {
		logger.info("insertBoardlike() 호출");
		return sqlSession.insert(NAMESPACE + ".insertBoardlike", boardRestVO);
	}

	@Override
	public int selectBoardlikeCount(int boardNumber) {
		logger.info("selectBoardlikeCount() 호출");
		return sqlSession.selectOne(NAMESPACE + ".selectBoardlikeCount", boardNumber);
	}
	
	@Override
	public int delete(BoardRestVO boardRestVO) {
		logger.info("delete() 호출");
		return sqlSession.delete(NAMESPACE + ".delete", boardRestVO);
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

	@Override
	public int insertBoardview(BoardRestVO boardRestVO) {
		logger.info("insertBoardview() 호출");
		return sqlSession.insert(NAMESPACE + ".insertBoardview", boardRestVO);
	}
}
