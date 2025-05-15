package com.api.book.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.book.helper.FileUploadHelper;

@RestController
public class FileUploadController {
     @Autowired
     private FileUploadHelper fileUploadHelper;
	
	@PostMapping("/upload-file")
	public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file){
//		System.out.println(file.getOriginalFilename());
//		System.out.println(file.getName());
//		System.out.println(file.getSize());
		try {

			if(file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
			}
//			if(!(file.getContentType().equals("image/jpg"))) {
//				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only jpge files are allowed");	
//			}
			
			boolean uploadFile = fileUploadHelper.uploadFile(file);
			if(uploadFile) {
//				return ResponseEntity.ok("File Uploaded Successfully");
				return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong try again");
	}

}
