package com.cafe.mbti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import oracle.jdbc.driver.OracleDriver;

// Spring TestContext Framework를 사용하기 위한 선언
@RunWith(SpringJUnit4ClassRunner.class)
// Context 환경 설정 파일이 있는 위치를 선언, 경로 디렉토리와 하위 디렉토리에 있는 모든 xml 파일을 검색(root-context.xml, servlet-context.xml)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
//WebApplicationContext 객체를 로드하여 사용하겠다는 선언
@WebAppConfiguration
public class OracleJDBCTest {
	private static final Logger logger = LoggerFactory.getLogger(OracleJDBCTest.class);
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "scott";
	private static final String PASSWORD = "tiger";
	
	@Test
	public void testOracleConnect() {
		Connection conn = null;
		try {
			DriverManager.registerDriver(new OracleDriver());
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			logger.info("Oracle 연결 성공");
		} catch (SQLException e) {
			e.printStackTrace();
			logger.info("Oracle 연결 실패 : " + e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	} // end testOracleConnect()
} // end OracleJDBCTest
