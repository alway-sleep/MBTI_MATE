package com.cafe.mbti;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/resources")
public class FilesRestController {
	@Resource(name = "resourcesPath")
	private String resourcesPath;

	@GetMapping("/index")
	private ResponseEntity<byte[]> index(String fileName) throws IOException {		
		String resourcePath = resourcesPath + "/index/" + fileName;
		String mimeType = URLConnection.guessContentTypeFromName(fileName);

	    HttpHeaders httpHeaders = new HttpHeaders();
	    mimeType = (mimeType != null) ? mimeType : "application/octet-stream";
	    httpHeaders.setContentType(MediaType.parseMediaType(mimeType));
        return new ResponseEntity<>(IOUtils.toByteArray(new FileInputStream(resourcePath)), httpHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/member")
	private ResponseEntity<byte[]> member(String fileName) throws IOException {		
		String resourcePath = resourcesPath + "/member/" + fileName;
		String mimeType = URLConnection.guessContentTypeFromName(fileName);

	    HttpHeaders httpHeaders = new HttpHeaders();
	    mimeType = (mimeType != null) ? mimeType : "application/octet-stream";
	    httpHeaders.setContentType(MediaType.parseMediaType(mimeType));
		return new ResponseEntity<>(IOUtils.toByteArray(new FileInputStream(resourcePath)), httpHeaders, HttpStatus.OK);
	}
}
