package com.cafe.mbti.util;

import java.io.File;
import java.io.IOException;

import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {	
	public FileUtil() {}

	public String saveMemberPicture(String resourcesPath, MultipartFile file, int memberNumber) throws IOException {
		String resourcePath = resourcesPath + "member/";
		String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
		String memberPicture = "picture" + memberNumber + "." + extension;
		file.transferTo(new File(resourcePath + memberPicture));
		return memberPicture;
	}
	
	// 확장자가 여러개이기 때문에 해당 회원 관련 사진 모두 삭제
	public void deleteMemberPicture(String resourcesPath, int memberNumber) {
		String resourcePath = resourcesPath + "member/";
		for (File file : new File(resourcePath).listFiles()) {
			if (file.getName().contains("picture" + memberNumber + ".")) {
				FileSystemUtils.deleteRecursively(file);
			}
		}
	}
	
	public void saveBoardFiles(String resourcesPath, MultipartFile[] files, int boardSeqNextVal) throws IOException {
		String resourcePath = resourcesPath + "board/";
		String dirPath = resourcePath + Integer.toString(boardSeqNextVal);
		new File(dirPath).mkdir();
		for (MultipartFile file : files) {
			file.transferTo(new File(dirPath + "/" + file.getOriginalFilename()));
		}
	}
	
	public void deleteBoardFiles(String resourcesPath, int boardNumber) {
	    String resourcePath = resourcesPath + "board/";
	    String dirPath = resourcePath + Integer.toString(boardNumber);
	    File folder = new File(dirPath);
	    deleteFolder(folder);
	}

	public void deleteFolder(File folder) {
	    if (folder.exists()) {
	        File[] files = folder.listFiles();
	        if (files != null) {
	            for (File file : files) {
	                if (file.isDirectory()) {
	                    deleteFolder(file); // 재귀적으로 하위 폴더 삭제
	                } else {
	                    file.delete(); // 파일 삭제
	                }
	            }
	        }
	        folder.delete(); // 폴더 삭제
	    }
	}
}
