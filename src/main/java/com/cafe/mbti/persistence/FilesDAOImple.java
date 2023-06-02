package com.cafe.mbti.persistence;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// TODO @Repository @Component : 영속 계층(Persistence Layer)의 DB 관련 기능을 담당
@Repository // 저장소
public class FilesDAOImple implements FilesDAO {
	private static final Logger logger = LoggerFactory.getLogger(FilesDAOImple.class);
	private static final String NAMESPACE = "com.cafe.mbti.FileMapper";
	
	// MyBatis의 SqlSession을 사용하기 위해 Spring Framework가 생성한 bean을 주입(injection)받음
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int deleteOnBoard(int boardNumber) {
		logger.info("deleteOnBoard() 호출");
		return sqlSession.delete(NAMESPACE + ".deleteOnBoard(boardNumber)", boardNumber);
	}
} // end FileDAOImple
