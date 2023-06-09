package com.cafe.mbti.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe.mbti.domain.CommentsVO;
import com.cafe.mbti.util.CmRpPageCriteria;

// TODO @Repository @Component : 영속 계층(Persistence Layer)의 DB 관련 기능을 담당
@Repository // 저장소
public class CommentsDAOImple implements CommentsDAO {
	private static final Logger logger = LoggerFactory.getLogger(CommentsDAOImple.class);
	private static final String NAMESPACE = "com.cafe.mbti.CommentsMapper";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(CommentsVO vo) {
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<CommentsVO> selectAll(CmRpPageCriteria cmRpPageCriteria) {
		logger.info("selectAll() 호출");
		return sqlSession.selectList(NAMESPACE + ".selectAll", cmRpPageCriteria);
	}
	
	@Override
	public int selectCountOnBoard(int boardNumber) {
		logger.info("selectCountOnBoard() 호출");
		return sqlSession.selectOne(NAMESPACE + ".selectCountOnBoard", boardNumber);
	}

	@Override
	public int selectBoardComments(int boardNumber) {
		logger.info("selectBoardComments() 호출");
		return sqlSession.selectOne(NAMESPACE + ".selectBoardComments", boardNumber);
	}
	
	@Override
	public int update(String commentsContent, int commentsNumber) {
		logger.info("update() 호출");
		Map<String, Object> args = new HashMap<>();
		args.put("commentsContent", commentsContent);
		args.put("commentsNumber", commentsNumber);
		return sqlSession.update(NAMESPACE + ".update", args);
	}

	@Override
	public int delete(int commentsNumber) {
		logger.info("delete() 호출");
		return sqlSession.delete(NAMESPACE + ".delete", commentsNumber);
	}
	
	@Override
	public int updateDeleteOnBoard(int boardNumber) {
		logger.info("updateDeleteOnBoard() 호출");
		return sqlSession.update(NAMESPACE + ".updateDeleteOnBoard", boardNumber);
	}

	@Override
	public int updateDeleteOnMember(int memberNumber) {
		logger.info("updateDeleteOnMember() 호출");
		return sqlSession.update(NAMESPACE + ".updateDeleteOnMember", memberNumber);
	}

	@Override
	public int deleteOnMember(int memberNumber) {
		logger.info("deleteOnMember() 호출");
		return sqlSession.delete(NAMESPACE + ".deleteOnMember", memberNumber);
	}
	
}