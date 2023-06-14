package com.cafe.mbti;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cafe.mbti.domain.MemberVO;
import com.cafe.mbti.service.BoardService;
import com.cafe.mbti.service.MemberService;

@Controller
public class IndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BoardService boardService;
	
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
}