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
	public List<ReplyVO> selectAll(int commentsNumber) {
		logger.info("selectAll() 호출");
		return sqlSession.selectList(NAMESPACE + ".selectAll", commentsNumber);
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
	public int deleteOnMember(int memberNumber) {
		logger.info("deleteOnMember() 호출");
		return sqlSession.delete(NAMESPACE + ".deleteOnMember", memberNumber);
	}
} // end ReplyDAOImple