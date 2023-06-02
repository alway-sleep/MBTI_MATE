package com.cafe.mbti;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.cafe.mbti.util.FileUploadUtil;
import com.cafe.mbti.util.MediaUtil;

@Controller // @Component
@RequestMapping(value="/board/files")
public class FilesController {
	private static final Logger logger = LoggerFactory.getLogger(FilesController.class);

		// servlet-context.xml 파일에 설정된 문자열 리소스 주입
		@Resource(name = "filesPath")
		private String filesPath;
		
		@GetMapping("/upload")
		public void uploadGET() {
			logger.info("uploadGET() 호출 : " + filesPath);
		}
		
		@PostMapping("/upload")
		public void uploadPOST(String userid, String email, MultipartFile file) {
			logger.info("uploadPOST() 호출");
			logger.info("파일 이름 : " + file.getOriginalFilename());
			logger.info("파일 크기 : " + file.getSize());
			logger.info("userid : " + userid);
			logger.info("email : " + email);
			
			String savedFileName = saveUploadFile(file);
		}
		
		@PostMapping("/upload2")
		public String uploadPOST2(MultipartFile[] files) {
			logger.info("uploadPOST2() 호출");
			String result = "";
			for(MultipartFile f : files) {
				result += saveUploadFile(f) + " ";
			}
			logger.info("result = " + result);
			return "upload";
		}
		
		@GetMapping("/upload-ajax")
		public void uploadAjaxGET() {
			logger.info("uploadAjaxGET() 호출");
		}
		
		@PostMapping("/upload-ajax")
		public ResponseEntity<String> uploadAjaxPOST(MultipartFile[] files) {
			logger.info("uploadAjaxPOST() 호출");
			
			// 파일 하나만 저장
			String result = null; // result : 파일 경로 및 썸네일 이미지 이름
			try {
				result = FileUploadUtil.saveUploadedFile(filesPath, files[0].getOriginalFilename(), files[0].getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return new ResponseEntity<String>(result,HttpStatus.OK);
		}

		private String saveUploadFile(MultipartFile file) {
			// UUID : 업로드하는 파일 이름이 중복되지 않도록 값 생성
			UUID uuid = UUID.randomUUID();
			String savedName = uuid + "_" + file.getOriginalFilename();
			File target = new File(filesPath, savedName);
			
			try {
				FileCopyUtils.copy(file.getBytes(), target);
				logger.info("파일 저장 성공");
				return savedName;
			} catch (IOException e) {
				logger.error("파일 저장 실패");
				return null;
			}
		}
		
		// display 함수를 호출하면 서버에서 이미지를 확인할 수 있음
		// - 파일 경로를 전송해야함
		@GetMapping("/display")
		public ResponseEntity<byte[]> display(String filesName) {
			logger.info("display() 호출");
			
			ResponseEntity<byte[]> entity = null;
			InputStream in = null;
			
			String filePath = filesPath + filesName;
			try {
				in = new FileInputStream(filePath);
				
				// 파일 확장자
				String extension = filePath.substring(filePath.lastIndexOf(".") + 1);
				
				// 응답 헤더(response header)에 Content-Type 설정
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.setContentType(MediaUtil.getMediaType(extension));
				
				// 데이터 전송
				entity = new ResponseEntity<byte[]>(
						IOUtils.toByteArray(in), // 파일에서 읽은 데이터
						httpHeaders, // 응답 헤더
						HttpStatus.OK
						);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return entity;
		}
} // end FileController
