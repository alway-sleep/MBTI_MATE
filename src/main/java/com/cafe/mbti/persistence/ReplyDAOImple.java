package com.cafe.mbti.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe.mbti.domain.ReplyVO;
import com.cafe.mbti.util.CmRpPageCriteria;

@Repository
public class ReplyDAOImple implements ReplyDAO {
	private static final Logger logger = LoggerFactory.getLogger(ReplyDAOImple.class);
	private static final String NAMESPACE = "com.cafe.mbti.ReplyMapper";

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(ReplyVO vo) {
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<ReplyVO> selectAll(CmRpPageCriteria cmRpPageCriteria) {
		logger.info("selectAll() 호출");
		return sqlSession.selectList(NAMESPACE + ".selectAll", cmRpPageCriteria);
	}
	
	@Override
	public int selectCountOnComments(int commentsNumber) {
		logger.info("selectCountOnComments() 호출");
		return sqlSession.selectOne(NAMESPACE + ".selectCountOnComments", commentsNumber);
	}

	@Override
	public int update(String replyContent, int replyNumber) {
		logger.info("update() 호출");
		Map<String, Object> args = new HashMap<>();
		args.put("replyContent", replyContent);
		args.put("replyNumber", replyNumber);
		return sqlSession.update(NAMESPACE + ".update", args);
	}

	@Override
	public int delete(int replyNumber) {
		logger.info("delete() 호출");
		return sqlSession.delete(NAMESPACE + ".delete", replyNumber);
	}
	
	@Override
	public int updateDeleteOnComments(int commentsNumber) {
		logger.info("updateDeleteOnComments() 호출");
		return sqlSession.update(NAMESPACE + ".updateDeleteOnComments", commentsNumber);
	}
	
	@Override
	public int updateDeleteOnBoard(int boardNumber) {
		logger.info("updateDeleteOnComments() 호출");
		return sqlSession.update(NAMESPACE + ".updateDeleteOnBoard", boardNumber);
	}

	@Override
	public int deleteOnMember(int memberNumber) {
		logger.info("deleteOnMember() 호출");
		return sqlSession.delete(NAMESPACE + ".deleteOnMember", memberNumber);
	}
	
	@Override
	public int updateDeleteOnMember(int memberNumber) {
		logger.info("updateDeleteOnMember() 호출");
		return sqlSession.update(NAMESPACE + ".updateDeleteOnMember", memberNumber);
	}
} // end ReplyDAOImple
