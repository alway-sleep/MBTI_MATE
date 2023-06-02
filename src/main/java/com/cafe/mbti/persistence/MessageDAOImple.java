package com.cafe.mbti.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe.mbti.domain.MessageVO;

// TODO @Repository @Component : 영속 계층(Persistence Layer)의 DB 관련 기능을 담당
@Repository // 저장소
public class MessageDAOImple implements MessageDAO {
	private static final Logger logger = LoggerFactory.getLogger(MessageDAOImple.class);
	private static final String NAMESPACE = "com.cafe.mbti.MessageMapper";
	
	// MyBatis의 SqlSession을 사용하기 위해 Spring Framework가 생성한 bean을 주입(injection)받음
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(MessageVO vo) {
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<MessageVO> selectAllByRecipient(String memberId) {
		logger.info("selectAllByRecipient() 호출");
		return sqlSession.selectList(NAMESPACE + ".selectAllByRecipient", memberId);
	}

	@Override
	public List<MessageVO> selectAllBySender(int memberNumber) {
		logger.info("selectAllBySender() 호출");
		return sqlSession.selectList(NAMESPACE + ".selectAllBySender", memberNumber);
	}

	@Override
	public List<MessageVO> selectAllByBox(int messageBox) {
		logger.info("selectAllByBox() 호출");
		return sqlSession.selectList(NAMESPACE + ".selectAllByBox", messageBox);
	}

	@Override
	public MessageVO select(int messageNumber) {
		logger.info("select() 호출");
		return sqlSession.selectOne(NAMESPACE + ".select", messageNumber);
	}

	@Override
	public int updateIsRead(int messageNumber) {
		logger.info("updateIsRead() 호출");
		return sqlSession.update(NAMESPACE + ".updateIsRead", messageNumber);
	}

	@Override
	public int updateSenderIsDel(int messageNumber) {
		logger.info("updateSenderIsDel() 호출");
		return sqlSession.update(NAMESPACE + ".updateSenderIsDel", messageNumber);
	}

	@Override
	public int updateRecipientIsDel(int messageNumber) {
		logger.info("updateRecipientIsDel() 호출");
		return sqlSession.update(NAMESPACE + ".updateRecipientIsDel", messageNumber);
	}

	@Override
	public int updateBox(int messageNumber) {
		logger.info("updateBox() 호출");
		return sqlSession.update(NAMESPACE + ".updateBox", messageNumber);
	}

	@Override
	public int delete(int messageNumber) {
		logger.info("delete() 호출");
		return sqlSession.delete(NAMESPACE + ".delete", messageNumber);
	}
} // end MessageDAOImple
