package com.cafe.mbti.util;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {	
	@Resource(name = "resourcesPath")
	private String resourcesPath;
	
	public FileUtil() {}

	public String saveMemberPicture(MultipartFile file, int memberNumber) throws IOException {
		String resourcePath = resourcesPath + "member/";
		String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
		String memberPicture = "picture" + memberNumber + "." + extension;
		file.transferTo(new File(resourcePath + memberPicture));
		return memberPicture;
	}
	
	// 확장자가 여러개이기 때문에 해당 회원 관련 사진 모두 삭제
	public void deleteMemberPicture(int memberNumber) {
		String resourcePath = resourcesPath + "member/";
		for (File file : new File(resourcePath).listFiles()) {
			if (file.getName().contains("picture" + memberNumber + ".")) {
				FileSystemUtils.deleteRecursively(file);
			}
		}
	}
	
	public void saveBoardFiles(MultipartFile[] files, int boardSeqNextVal) throws IOException {
		String resourcePath = resourcesPath + "board/";
		String dirPath = resourcePath + Integer.toString(boardSeqNextVal);
		new File(dirPath).mkdir();
		for (MultipartFile file : files) {
			file.transferTo(new File(dirPath + file.getOriginalFilename()));
		}
	}
	
	public void deleteBoardFiles(int boardNumber) {
		String resourcePath = resourcesPath + "board/";
		String dirPath = resourcePath + Integer.toString(boardNumber);
		new File(dirPath).delete();
	}
}
