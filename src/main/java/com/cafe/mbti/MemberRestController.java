package com.cafe.mbti;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.mbti.service.MemberService;

@RestController
@RequestMapping(value = "/memberrest")
public class MemberRestController {
	private static final Logger logger = LoggerFactory.getLogger(MemberRestController.class);

	@Autowired
	private MemberService memberService;

	@GetMapping("/memberId")
	public ResponseEntity<Integer> memberIdGET(HttpServletRequest request,@RequestParam String memberId) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());		
		return new ResponseEntity<Integer>(memberService.isDuplicatedId(memberId), HttpStatus.OK);
	}
	
	@GetMapping("/memberNickname")
	public ResponseEntity<Integer> memberNicknameGET(HttpServletRequest request, @RequestParam String memberNickname) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());		
		return new ResponseEntity<Integer>(memberService.isDuplicatedNickname(memberNickname), HttpStatus.OK);
	}
	
	@GetMapping("/memberRRN")
	public ResponseEntity<Integer> memberRRNGET(HttpServletRequest request, @RequestParam String memberRRN) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		return new ResponseEntity<Integer>(memberService.isDuplicatedRRN(memberRRN), HttpStatus.OK);
	}
	
	@GetMapping("/memberPhone")
	public ResponseEntity<Integer> memberPhoneGET(HttpServletRequest request, @RequestParam String memberPhone) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());		
		return new ResponseEntity<Integer>(memberService.isDuplicatedPhone(memberPhone), HttpStatus.OK);
	}
	
	@GetMapping("/memberEmail")
	public ResponseEntity<Integer> memberEmailGET(HttpServletRequest request, @RequestParam String memberEmail) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());		
		return new ResponseEntity<Integer>(memberService.isDuplicatedEmail(memberEmail), HttpStatus.OK);
	}
	
	@GetMapping("/memberNicknameString")
	public ResponseEntity<String> memberNicknameString(HttpServletRequest request, @RequestParam String memberNickname, @RequestParam int memberNumber) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		
		String memberNicknameString = memberService.readNicknameStr(memberNickname);
		String memberNicknameStringResult = "same";
		if (memberNicknameString == null) {
	        memberNicknameStringResult = "usable";
	    } else {
	    	if (memberService.readNicknameInt(memberNickname, memberNumber) == 1) {
	    		memberNicknameStringResult = "same";	        	
	    	} else {
	    		memberNicknameStringResult = "unusable";	    		
	    	}
	    }
		return new ResponseEntity<String>(memberNicknameStringResult, HttpStatus.OK);
	}
	
	@GetMapping("/memberPhoneString")
	public ResponseEntity<String> memberPhoneString(HttpServletRequest request, @RequestParam String memberPhone, @RequestParam int memberNumber) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		
		String memberPhoneString = memberService.readPhoneStr(memberPhone);
		String memberPhoneStringResult = "same";
		if (memberPhoneString == null) {
			memberPhoneStringResult = "usable";	        	
	    } else {
	    	if (memberService.readPhoneInt(memberPhone, memberNumber) == 1) {
	    		memberPhoneStringResult = "same";	        	
	    	} else {
	    		memberPhoneStringResult = "unusable";
	    	}
	    }
		return new ResponseEntity<String>(memberPhoneStringResult, HttpStatus.OK);
	}
	
	@GetMapping("/memberEmailString")
	public ResponseEntity<String> memberEmailString(HttpServletRequest request, @RequestParam String memberEmail, @RequestParam int memberNumber) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());

	    String memberEmailString = memberService.readEmailStr(memberEmail);
	    String memberEmailStringResult = "same";
	    logger.info(memberEmailStringResult);
	    logger.info(memberEmail);

	    if (memberEmailString == null) {
	    	memberEmailStringResult = "usable";	        	
	    } else {
	    	if (memberService.readEmailInt(memberEmail, memberNumber) == 1) {
	    		memberEmailStringResult = "same";	        	
	    	} else {
	    		memberEmailStringResult = "unusable";
	    	}
	    }
	    return new ResponseEntity<String>(memberEmailStringResult, HttpStatus.OK);
	}
	
	@GetMapping("/memberPwString")
	public ResponseEntity<String> memberPwString(HttpServletRequest request, @RequestParam int memberNumber) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());		
		return new ResponseEntity<String>(memberService.readPwStr(memberNumber), HttpStatus.OK);
	}
} // end MemberController
