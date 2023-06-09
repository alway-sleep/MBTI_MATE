package com.cafe.mbti;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cafe.mbti.service.BoardService;
import com.cafe.mbti.service.MemberService;
import com.cafe.mbti.util.MediaUtil;

@Controller
public class IndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BoardService boardService;
	
	@Resource(name = "indexPath")
	private String indexPath;
	
	@GetMapping
	public String index(HttpServletRequest request, Model model) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());

		model.addAttribute("memberTotalCount", memberService.readMemberCount()); // 전체 회원의 수
		model.addAttribute("manager", memberService.readNicknameByGrade(5)); // 매니저 닉네임
		model.addAttribute("cafelogo", "cafelogo.png"); // 카페 로고 이미지
		model.addAttribute("cafebanner", "cafebanner.png"); // 카페 배너 이미지
		model.addAttribute("cafeindex00", "cafeindex00.png"); // 카페 인덱스 이미지 00
		model.addAttribute("cafeindex01", "cafeindex01.png"); // 카페 인덱스 이미지 01
		model.addAttribute("countOnBoard", boardService.readCountOnBoard()); // 전체 게시글의 수
		return "index";
	}
	
	@GetMapping("/display")
	private ResponseEntity<byte[]> display(String filesName) {		
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;
		
		String filePath = indexPath + filesName;
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
}