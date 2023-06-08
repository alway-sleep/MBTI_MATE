package com.cafe.mbti.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe.mbti.domain.BoardVO;
import com.cafe.mbti.util.PageCriteria;

// TODO @Repository @Component : 영속 계층(Persistence Layer)의 DB 관련 기능을 담당
// Spring Component bean으로 등록함
// servlet-context.xml의 component-scan을 통해 설정된 Component를 찾아와 bean으로 등록
// <context:component-scan ../>
@Repository // 저장소
public class BoardDAOImple implements BoardDAO {
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOImple.class);
	private static final String NAMESPACE = "com.cafe.mbti.BoardMapper";
	
	// MyBatis의 SqlSession을 사용하기 위해 Spring Framework가 생성한 bean을 주입(injection)받음
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(BoardVO boardVO) {
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", boardVO);
	}
	
	@Override
	public int selectBoardCount() {
		logger.info("selectBoardCount()  호출");
		return sqlSession.selectOne(NAMESPACE + ".selectBoardCount");
	}
	
	@Override
	public List<BoardVO> selectAll(PageCriteria pageCriteria) {
		logger.info("selectAll()  호출");
		return sqlSession.selectList(NAMESPACE + ".selectAll", pageCriteria);
	}
	
	@Override
	public List<BoardVO> selectBoard(PageCriteria pageCriteria) {
		logger.info("selectBoard()  호출");
		return sqlSession.selectList(NAMESPACE + ".selectBoard", pageCriteria);
	}

	@Override
	public BoardVO select(int boardNumber, int memberNumber) {
		logger.info("select() 호출");
		Map<String, Object> args = new HashMap<>();
		args.put("boardNumber", boardNumber);
		args.put("memberNumber", memberNumber);
		return sqlSession.selectOne(NAMESPACE + ".select", args);
	}

	@Override
	public List<BoardVO> selectByBoardTitle(PageCriteria pageCriteria) {
		logger.info("selectByBoardTitle()  호출");
		return sqlSession.selectList(NAMESPACE + ".selectByBoardTitle", pageCriteria);
	}

	@Override
	public List<BoardVO> selectByBoardContent(PageCriteria pageCriteria) {
		logger.info("selectByBoardContent()  호출");
		return sqlSession.selectList(NAMESPACE + ".selectByBoardContent", pageCriteria);
	}

	@Override
	public List<BoardVO> selectByNicknameOnBoard(PageCriteria pageCriteria) {
		logger.info("selectByNicknameOnBoard()  호출");
		return sqlSession.selectList(NAMESPACE + ".selectByNicknameOnBoard", pageCriteria);
	}

	@Override
	public List<BoardVO> selectByCmRpContent(PageCriteria pageCriteria) {
		logger.info("selectByCmRpContent()  호출");
		return sqlSession.selectList(NAMESPACE + ".selectByCmRpContent", pageCriteria);
	}

	@Override
	public List<BoardVO> selectByNicknameOnCmRp(PageCriteria pageCriteria) {
		logger.info("selectByNicknameOnCmRp()  호출");
		return sqlSession.selectList(NAMESPACE + ".selectByNicknameOnCmRp", pageCriteria);
	}

	@Override
	public int update(int boardSection, int boardList, String boardName, String boardTitle, String boardContent, int boardNumber) {
		logger.info("update() 호출");
		Map<String, Object> args = new HashMap<>();
		args.put("boardSection", boardSection);
		args.put("boardList", boardList);
		args.put("boardName", boardName);
		args.put("boardTitle", boardTitle);
		args.put("boardContent", boardContent);
		args.put("boardNumber",boardNumber);
		return sqlSession.update(NAMESPACE + ".update", args);
	}
	
	@Override
	public int updateType(int boardType, int boardNumber) {
		logger.info("updateType() 호출");
		Map<String, Object> args = new HashMap<>();
		args.put("boardType", boardType);
		args.put("boardNumber",boardNumber);
		return sqlSession.update(NAMESPACE + ".updateType", args);
	}

	@Override
	public int delete(int boardNumber) {
		logger.info("delete() 호출");
		return sqlSession.delete(NAMESPACE + ".delete", boardNumber);
	}
	
	@Override
	public int deleteOnMember(int memberNumber) {
		logger.info("deleteOnMember() 호출");
		return sqlSession.delete(NAMESPACE + ".deleteOnMember", memberNumber);
	}
}
