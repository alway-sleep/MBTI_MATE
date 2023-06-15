package com.cafe.mbti.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {	
	@Resource(name = "resourcesPath")
	private String resourcesPath;
	
	public FileUtil() {}

	// 확장자가 여러개이기 때문에 해당 회원 관련 사진 모두 삭제
	public void deleteMemberPicture(int memberNumber) {
		String resourcePath = resourcesPath + "member/";
		for (File file : new File(resourcePath).listFiles()) {
			if (file.getName().contains("picture" + memberNumber + ".")) {
				FileSystemUtils.deleteRecursively(file);
			}
		}
	}
	
	public String saveMemberPicture(MultipartFile file, int memberNumber) throws IOException {
		String resourcePath = resourcesPath + "member/";
		String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
		String memberPicture = "picture" + memberNumber + "." + extension;
		file.transferTo(new File(resourcePath + memberPicture));
		return memberPicture;
	}
	
	public String saveFilesName(MultipartFile[] files) throws IOException {
		String resourcePath = resourcesPath + "board/";
		String filesName = "";
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
		String dirPath = resourcePath + simpleDateFormat.format(date) + "/";
		File dir = new File(dirPath);
		if (files.length != 0 && !dir.exists()) {		
			dir.mkdir();
			for (MultipartFile file : files) {
				file.transferTo(new File(dirPath + file.getOriginalFilename()));
				filesName = (filesName == "") ? file.getOriginalFilename() : filesName+"|"+file.getOriginalFilename();
			}
		} else {
			filesName = "|";
		}
		return filesName;
	}
}
