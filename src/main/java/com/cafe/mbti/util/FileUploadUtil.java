package com.cafe.mbti.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);
	
	@Resource(name = "resourcesPath")
	private static String resourcesPath;
	
	public void saveFiles(MultipartFile[] files) throws IOException {
		String resourcePath = resourcesPath + "board/";
		List<String> filesName = new ArrayList<>();
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
		
		String dirPath = resourcePath + simpleDateFormat.format(date);
		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdir();
			for (MultipartFile file : files) {
				file.transferTo(new File(dirPath + file.getOriginalFilename()));
				filesName.add(file.getOriginalFilename());
			}
		}
	}
}
