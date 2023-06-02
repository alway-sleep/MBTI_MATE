package com.cafe.mbti.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.mbti.persistence.FilesDAO;

@Service
public class FilesServiceImple implements FilesService {
	private static final Logger logger = LoggerFactory.getLogger(FilesServiceImple.class);

	@Autowired
	private FilesDAO fileDAO;
	
	
}
