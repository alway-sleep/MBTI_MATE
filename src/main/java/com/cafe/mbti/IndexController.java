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

import com.cafe.mbti.domain.MemberVO;
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
	
	@Resource(name = "resourcesPath")
	private String resourcesPath;
	
	@GetMapping
	public String index(HttpServletRequest request, Model model) {
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("memberVO");
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());

		if (memberVO != null) {
			model.addAttribute("countByNumberOnBoard", memberService.readByNumberOnBoard(memberVO.getMemberNumber()));
			model.addAttribute("countByNumberOnCmRp", memberService.readByNumberOnCmRp(memberVO.getMemberNumber()));			
		}
		model.addAttribute("memberTotalCount", memberService.readMemberCount()); // 전체 회원의 수
		model.addAttribute("manager", memberService.readNicknameByGrade(5)); // 매니저 닉네임
		model.addAttribute("countOnBoard", boardService.readCountOnBoard()); // 전체 게시글의 수
		return "index";
	}
	
	@GetMapping("/resource")
	private ResponseEntity<byte[]> resource(String resource) {		
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;
		
		String resourcePath = resourcesPath + resource;
		logger.info("{}",resourcePath);
		try {
			in = new FileInputStream(resourcePath);
			
			// 파일 확장자
			String extension = resourcePath.substring(resourcePath.lastIndexOf(".") + 1);
			
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