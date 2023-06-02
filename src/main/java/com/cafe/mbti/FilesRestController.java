package com.cafe.mbti;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/board/files")
public class FilesRestController {
	private static final Logger logger = LoggerFactory.getLogger(FilesRestController.class);

	@Autowired
	private ResourceLoader resourceLoader;

	@PostMapping("/restupload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
		logger.info("FileUploadRestController uploadFile() called");

		try {
			// 파일 저장할 경로 설정
			String uploadDir = resourceLoader.getResource("classpath:static/").getURL().getPath();
			String filesName = file.getOriginalFilename();
			String filesPath = uploadDir + filesName;
			// 파일 저장
			File dest = new File(filesPath);
			file.transferTo(dest);
			logger.info("File uploaded successfully");
		} catch (Exception e) {
			logger.error("Error uploading file: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
		}

		return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully");
	}
}

/*
위 코드에서 @PostMapping("/upload") 어노테이션을 통해 /file/upload 경로로 파일을 업로드할 수 있습니다.
@RequestParam("file") MultipartFile file 어노테이션을 통해 클라이언트에서 전송된 파일을 받아올 수 있습니다.
이후 파일 저장 경로를 설정하고 file.transferTo(dest) 메소드를 통해 파일을 저장합니다.
파일 저장에 실패한 경우 ResponseEntity 객체를 통해 에러 메시지를 반환합니다.
파일 저장에 성공한 경우 ResponseEntity 객체를 통해 성공 메시지를 반환합니다.
*/
