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
import com.cafe.mbti.util.BoardPageCriteria;

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
	public int selectCountOnBoard() {
		logger.info("selectCountOnBoard()  호출");
		return sqlSession.selectOne(NAMESPACE + ".selectCountOnBoard");
	}

	@Override
	public int selectCountOnBoard2(BoardPageCriteria boardPageCriteria) {
		logger.info("selectCountOnBoard2()  호출");
		return sqlSession.selectOne(NAMESPACE + ".selectCountOnBoard2", boardPageCriteria);
	}

	@Override
	public int selectCountByBoardTitle(BoardPageCriteria boardPageCriteria) {
		logger.info("selectCountByBoardTitle()  호출");
		return sqlSession.selectOne(NAMESPACE + ".selectCountByBoardTitle", boardPageCriteria);
	}

	@Override
	public int selectCountByBoardContent(BoardPageCriteria boardPageCriteria) {
		logger.info("selectCountByBoardContent()  호출");
		return sqlSession.selectOne(NAMESPACE + ".selectCountByBoardContent", boardPageCriteria);
	}

	@Override
	public int selectCountByNicknameOnBoard(BoardPageCriteria boardPageCriteria) {
		logger.info("selectCountByNicknameOnBoard()  호출");
		return sqlSession.selectOne(NAMESPACE + ".selectCountByNicknameOnBoard", boardPageCriteria);
	}

	@Override
	public int selectCountByCmRpContent(BoardPageCriteria boardPageCriteria) {
		logger.info("selectCountByCmRpContent()  호출");
		return sqlSession.selectOne(NAMESPACE + ".selectCountByCmRpContent", boardPageCriteria);
	}

	@Override
	public int selectCountByNicknameOnCmRp(BoardPageCriteria boardPageCriteria) {
		logger.info("selectCountByNicknameOnCmRp()  호출");
		return sqlSession.selectOne(NAMESPACE + ".selectCountByNicknameOnCmRp", boardPageCriteria);
	}
	
	@Override
	public int selectCountByBoardTitle2(BoardPageCriteria boardPageCriteria) {
		logger.info("selectCountByBoardTitle2()  호출");
		return sqlSession.selectOne(NAMESPACE + ".selectCountByBoardTitle2", boardPageCriteria);
	}

	@Override
	public int selectCountByBoardContent2(BoardPageCriteria boardPageCriteria) {
		logger.info("selectCountByBoardContent2()  호출");
		return sqlSession.selectOne(NAMESPACE + ".selectCountByBoardContent2", boardPageCriteria);
	}

	@Override
	public int selectCountByNicknameOnBoard2(BoardPageCriteria boardPageCriteria) {
		logger.info("selectCountByNicknameOnBoard2()  호출");
		return sqlSession.selectOne(NAMESPACE + ".selectCountByNicknameOnBoard2", boardPageCriteria);
	}

	@Override
	public int selectCountByCmRpContent2(BoardPageCriteria boardPageCriteria) {
		logger.info("selectCountByCmRpContent2()  호출");
		return sqlSession.selectOne(NAMESPACE + ".selectCountByCmRpContent2", boardPageCriteria);
	}

	@Override
	public int selectCountByNicknameOnCmRp2(BoardPageCriteria boardPageCriteria) {
		logger.info("selectCountByNicknameOnCmRp2()  호출");
		return sqlSession.selectOne(NAMESPACE + ".selectCountByNicknameOnCmRp2", boardPageCriteria);
	}
	
	@Override
	public List<BoardVO> selectAll(BoardPageCriteria boardPageCriteria) {
		logger.info("selectAll()  호출");
		return sqlSession.selectList(NAMESPACE + ".selectAll", boardPageCriteria);
	}
	
	@Override
	public List<BoardVO> selectBoard(BoardPageCriteria boardPageCriteria) {
		logger.info("selectBoard()  호출");
		return sqlSession.selectList(NAMESPACE + ".selectBoard", boardPageCriteria);
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
	public List<BoardVO> selectByBoardTitle(BoardPageCriteria boardPageCriteria) {
		logger.info("selectByBoardTitle()  호출");
		return sqlSession.selectList(NAMESPACE + ".selectByBoardTitle", boardPageCriteria);
	}

	@Override
	public List<BoardVO> selectByBoardContent(BoardPageCriteria boardPageCriteria) {
		logger.info("selectByBoardContent()  호출");
		return sqlSession.selectList(NAMESPACE + ".selectByBoardContent", boardPageCriteria);
	}

	@Override
	public List<BoardVO> selectByNicknameOnBoard(BoardPageCriteria boardPageCriteria) {
		logger.info("selectByNicknameOnBoard()  호출");
		return sqlSession.selectList(NAMESPACE + ".selectByNicknameOnBoard", boardPageCriteria);
	}

	@Override
	public List<BoardVO> selectByCmRpContent(BoardPageCriteria boardPageCriteria) {
		logger.info("selectByCmRpContent()  호출");
		return sqlSession.selectList(NAMESPACE + ".selectByCmRpContent", boardPageCriteria);
	}

	@Override
	public List<BoardVO> selectByNicknameOnCmRp(BoardPageCriteria boardPageCriteria) {
		logger.info("selectByNicknameOnCmRp()  호출");
		return sqlSession.selectList(NAMESPACE + ".selectByNicknameOnCmRp", boardPageCriteria);
	}
	
	@Override
	public List<BoardVO> selectByBoardTitle2(BoardPageCriteria boardPageCriteria) {
		logger.info("selectByBoardTitle2()  호출");
		return sqlSession.selectList(NAMESPACE + ".selectByBoardTitle2", boardPageCriteria);
	}

	@Override
	public List<BoardVO> selectByBoardContent2(BoardPageCriteria boardPageCriteria) {
		logger.info("selectByBoardContent2()  호출");
		return sqlSession.selectList(NAMESPACE + ".selectByBoardContent2", boardPageCriteria);
	}

	@Override
	public List<BoardVO> selectByNicknameOnBoard2(BoardPageCriteria boardPageCriteria) {
		logger.info("selectByNicknameOnBoard2()  호출");
		return sqlSession.selectList(NAMESPACE + ".selectByNicknameOnBoard2", boardPageCriteria);
	}

	@Override
	public List<BoardVO> selectByCmRpContent2(BoardPageCriteria boardPageCriteria) {
		logger.info("selectByCmRpContent2()  호출");
		return sqlSession.selectList(NAMESPACE + ".selectByCmRpContent2", boardPageCriteria);
	}

	@Override
	public List<BoardVO> selectByNicknameOnCmRp2(BoardPageCriteria boardPageCriteria) {
		logger.info("selectByNicknameOnCmRp2()  호출");
		return sqlSession.selectList(NAMESPACE + ".selectByNicknameOnCmRp2", boardPageCriteria);
	}

	@Override
	public int selectBoardViews(int boardNumber) {
		logger.info("selectBoardViews()  호출");
		return sqlSession.selectOne(NAMESPACE + ".selectBoardViews", boardNumber);
	}

	@Override
	public int update(int boardSection, int boardList, String boardName, String boardTitle, String boardContent, String boardFiles, int boardNumber) {
		logger.info("update() 호출");
		Map<String, Object> args = new HashMap<>();
		args.put("boardSection", boardSection);
		args.put("boardList", boardList);
		args.put("boardName", boardName);
		args.put("boardTitle", boardTitle);
		args.put("boardContent", boardContent);
		args.put("boardFiles", boardFiles);
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
