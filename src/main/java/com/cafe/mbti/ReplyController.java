package com.cafe.mbti;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReplyController {
	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	@GetMapping("/reply")
	public void replyGET(HttpServletRequest request) {
		logger.info("Request URL (GET)  : {}", request.getRequestURI());
	}
} // end ReplyController