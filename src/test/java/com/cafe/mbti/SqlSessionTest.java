package com.cafe.mbti;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cafe.mbti.domain.BoardVO;
import com.cafe.mbti.domain.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class SqlSessionTest {
	private static final Logger logger = LoggerFactory.getLogger(SqlSessionTest.class);
	private static final String NAMESPACE = "com.cafe.mbti.BoardMapper";
	private static final String NAMESPACE2 = "com.cafe.mbti.MemberMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void testInsert() {
//		adminInsert();
		boardInsert();
	} // end testInsert()

	private void boardInsert() {
//		BoardVO vo = new BoardVO(0, 0, "공지사항", "첫 글", 0, 0, 0, null, 2);
//		// .insert : *.xml의 id값
//		int result = sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	private void adminInsert() {
//		MemberVO vo = new MemberVO(0, "admin", "관리자", "1234", "김준우", "960119-1234567", 0, "a", "010-3947-2465", "test@test.com", "서울", "cool", 0, 0, null, null);
//		int result = sqlSession.insert(NAMESPACE2 + ".insert", vo);
//		logger.info(result + "행 삽입");		
	}
	
	
} // end SqlSessionTest
