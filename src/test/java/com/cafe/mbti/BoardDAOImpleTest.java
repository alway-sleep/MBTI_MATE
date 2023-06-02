package com.cafe.mbti;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cafe.mbti.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class BoardDAOImpleTest {
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOImpleTest.class);
	
	@Autowired
	private BoardDAO dao;
	
	@Test
	public void testDAO() {
	} // end testDAO()

} // end BoardDAOTest
