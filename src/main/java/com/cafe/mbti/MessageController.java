package com.cafe.mbti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // @Component
@RequestMapping(value="/message")
public class MessageController {
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

	@GetMapping("/received")
	public void receivedGET() {
		
	}
	
	@PostMapping("/received")
	public void receivedPOST() {
		
	}
	
	@GetMapping("/sent")
	public void sentGET() {
		
	}
	
	@PostMapping("/sent")
	public void sentPOST() {
		
	}
	
	@GetMapping("/write")
	public void writeGET() {
		
	}
	
	@PostMapping("/write")
	public void writePOST() {
		
	}
} // end MessageController
