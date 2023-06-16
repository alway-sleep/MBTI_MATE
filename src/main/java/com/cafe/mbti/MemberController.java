package com.cafe.mbti;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cafe.mbti.domain.MemberVO;
import com.cafe.mbti.service.MemberService;
import com.cafe.mbti.util.FileUtil;
import com.cafe.mbti.util.Target;

@Controller // @Component
@RequestMapping(value = "/member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;
	
	@Resource(name = "resourcesPath")
	private String resourcesPath;
	
	@GetMapping("/join")
	public void joinGET(HttpServletRequest request) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
	}

	@PostMapping("/join")
	public String joinPOST(HttpServletRequest request, RedirectAttributes redirectAttributes, MemberVO memberVO) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		memberVO.setMemberMBTI( memberVO.getMemberMBTI().split(":")[0]);
		if (memberService.create(memberVO) == 1) {
			redirectAttributes.addFlashAttribute("message", "회원가입이 완료되었습니다.\\n로그인해 주세요.");
			return "redirect:..";
		} else {
			redirectAttributes.addFlashAttribute("message", "회원가입에 실패했습니다.\\n다시 한번 정확하게 작성해 주세요.");
			return "redirect:/member/join";
		}
	}

	@GetMapping("/login")
	public void loginGET(HttpServletRequest request, Model model) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
	}

	@PostMapping("/login")
	public String loginPOST(HttpServletRequest request, RedirectAttributes redirectAttributes, String memberId, String memberPw, String targetURL, Integer boardNumber) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		if (memberService.login(memberId, memberPw) == 1) {
			MemberVO memberVO =  memberService.read(memberService.readNumberById(memberId));
			request.getSession().setAttribute("memberVO", memberVO);
			logger.info("Session 생성: 회원정보{}", request.getSession().getAttribute("memberVO").toString());
			redirectAttributes.addFlashAttribute("message", memberVO.getMemberNickname()+"님, 환영합니다.");
			if (targetURL != "index") {
				redirectAttributes.addFlashAttribute("targetURL", targetURL);
				if (boardNumber != 0) {
					Target target = (Target) request.getSession().getAttribute("target");
					target.setBoardNumber(boardNumber);
				}
			}
		} else {
			redirectAttributes.addFlashAttribute("message", "등록되지 않은 아이디 또는 비밀번호입니다.");
		}
		return "redirect:..";
	}
	
	@GetMapping("/logout")
	public String logoutGET (HttpServletRequest request, RedirectAttributes redirectAttributes) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		request.getSession().removeAttribute("memberVO");
		request.getSession().removeAttribute("target");
		redirectAttributes.addFlashAttribute("message", "정상적으로 로그아웃했습니다.");
		return "redirect:..";
	}

	@GetMapping("/selectId")
	public void selectIdGET(HttpServletRequest request) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
	}

	@PostMapping("/selectId")
	public String selectIdPOST(HttpServletRequest request, RedirectAttributes redirectAttributes, String memberName, String memberRRN, String memberPhone) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		String selectId = memberService.readId(memberName, memberRRN, memberPhone);
		if (selectId != null) {
			selectId = selectId.substring(0, selectId.length() - 3) + "***";
			redirectAttributes.addFlashAttribute("message", "아이디 : " + selectId);
			return "redirect:..";
		} else {
			redirectAttributes.addFlashAttribute("message", "입력하신 정보와 일치하는 아이디가 존재하지 않습니다.");
			return "redirect:/member/selectId";
		}
	}

	@GetMapping("/selectPw")
	public void selectPwGET(HttpServletRequest request) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
	}

	@PostMapping("/selectPw")
	public String selectPwPOST(HttpServletRequest request, RedirectAttributes redirectAttributes, String memberId, String memberName, String memberRRN, String memberPhone, String memberEmail) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		if (memberService.readPw(memberId, memberName, memberRRN, memberPhone, memberEmail) == 1) {
			String resetPw = "reset123!@#";
			if (memberService.resetPw(resetPw, memberId, memberName, memberRRN, memberPhone, memberEmail) == 1) {
				redirectAttributes.addFlashAttribute("message", "비밀번호를 초기화하였습니다.\\n로그인 후 비밀변호를 변경해주세요.\\n비밀번호 : " + resetPw);
				return "redirect:..";				
			} else {
				redirectAttributes.addFlashAttribute("message", "비밀번호 찾기에 실패했습니다.\\n다시 시도해주세요.");
				return "redirect:/member/selectPw";
			}
		} else {
			redirectAttributes.addFlashAttribute("message", "입력하신 정보가 올바르지 않습니다.");
			return "redirect:/member/selectPw";
		}
	}

	@GetMapping("/mypage")
	public void mypageGET(HttpServletRequest request, Model model) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
	}

	@GetMapping("/update")
	public void updateGET(HttpServletRequest request) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
	}

	@PostMapping("/update")
	public String updatePOST(HttpServletRequest request, RedirectAttributes redirectAttributes, String memberNickname, String memberPhone, String memberEmail, String memberRegion, String memberMBTI) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("memberVO");
		if (memberService.update(memberNickname, memberPhone, memberEmail, memberRegion, memberMBTI.split(":")[0], memberVO.getMemberNumber()) == 1) {
			redirectAttributes.addFlashAttribute("message", "회원정보를 수정했습니다.");
			if (request.getSession().getAttribute("memberVO") != null) {
				request.getSession().removeAttribute("memberVO");
				request.getSession().setAttribute("memberVO", memberService.read(memberVO.getMemberNumber()));				
			}
		} else {			
			redirectAttributes.addFlashAttribute("message", "회원정보 수정을 실패했습니다.");
		}	
		return "redirect:/member/mypage";
	}
	
	@PostMapping("/updatepw")
	public String updatepwPOST(HttpServletRequest request, RedirectAttributes redirectAttributes, String memberPwNew, String memberPwOri) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("memberVO");
		if (memberService.updatePw(memberPwNew, memberVO.getMemberNumber(), memberPwOri) == 1) {
			redirectAttributes.addFlashAttribute("message", "비밀번호가 변경되었습니다. 다시 로그인해 주세요.");
			request.getSession().removeAttribute("memberVO");			
			request.getSession().removeAttribute("target");
			return "redirect:..";
		} else {
			redirectAttributes.addFlashAttribute("message", "비밀번호 변경에 실패했습니다.");
			return "redirect:/member/mypage";
		}
	}
	
	@PostMapping("/delete")
	public String deletePOST(HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("memberVO");
		if (memberService.delete(memberVO.getMemberNumber()) == 1) {
			redirectAttributes.addFlashAttribute("message", "정상적으로 탈퇴되었습니다.");
			request.getSession().removeAttribute("memberVO");			
			request.getSession().removeAttribute("target");
		} else {
			redirectAttributes.addFlashAttribute("message", "회원탈퇴에 실패했습니다.");
		}
		return "redirect:..";
	}
	
	@GetMapping("/picture")
	public void pictureGET(HttpServletRequest request) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
	}
	
	@PostMapping("/picture")
	public String picturePOST(HttpServletRequest request, RedirectAttributes redirectAttributes, MultipartFile file) throws IllegalStateException, IOException {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("memberVO");
		FileUtil fileUtil = new FileUtil();
		int memberNumber = memberVO.getMemberNumber();
		
		fileUtil.deleteMemberPicture(resourcesPath, memberNumber);			
		if (file == null) { // 기본 프로필 설정
			memberVO.setMemberPicture("picture.jpg");
			memberService.updatePicture(memberVO);
			redirectAttributes.addFlashAttribute("message", "기본 프로필로 설정했습니다.");
			redirectAttributes.addFlashAttribute("picturePOSTResult", "1");		
		} else { // 프로필 사진 선택
			memberVO.setMemberPicture(fileUtil.saveMemberPicture(resourcesPath, file, memberNumber));
			memberService.updatePicture(memberVO);
			redirectAttributes.addFlashAttribute("message", "프로필 사진을 변경했습니다.");
			redirectAttributes.addFlashAttribute("picturePOSTResult", "1");
		}
		return "redirect:/member/picture";
	}
} // end MemberController
