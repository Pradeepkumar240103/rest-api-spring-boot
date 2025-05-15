package com.api.book.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
    private final String UPLOAD_DIR="C:\\Users\\prade\\Documents\\workspace-spring-tools-for-eclipse-4.30.0.RELEASE\\bootrestbook\\src\\main\\resources\\static\\image";
//	private final String UPLOAD_DIR=new ClassPathResource("static/image/").getFile().getAbsolutePath();
//
//	
//	public FileUploadHelper() throws IOException {
//		
// 	}
	


  

   public boolean uploadFile(MultipartFile file) {
	   boolean f=false;
	   try {
//		   Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR+File.pathSeparator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
//		   f=true;
		   String filePath = UPLOAD_DIR + File.separator + file.getOriginalFilename();

           // Copy the file to the destination
           Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
           f = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
	   return f;
   }
}
