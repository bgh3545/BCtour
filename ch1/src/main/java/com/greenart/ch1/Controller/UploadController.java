package com.greenart.ch1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
@Controller
public class UploadController {

	
	// UploadController- 게시판과 연동 전 실제 upload 사이트에서 제대로 동작하는지 확인 
	@GetMapping("/upload2")
	public String uploadAjax() {
		return "file";
	} 
	
	@ResponseBody
	@PostMapping("/upload2")
							  // 스프링에서 지원하는 파일 객체
	public void uploadAjaxPost( MultipartFile[] uploadFile, Integer bno) {

		System.out.println("ajax post update!");
        // 저장되는 경로 ( 자신의 파일 위치에 맞게 수정할 것)
		String uploadFolder = "C:\\Users\\green\\git\\ch1\\ch1\\src\\main\\webapp\\resources\\img";

		for (MultipartFile multipartFile : uploadFile) { // 여러개의 파일일 경우 향상된 for문 이용

			System.out.println("------------------------");
			System.out.println("Upload file name : " + multipartFile.getOriginalFilename()); // 파일 이름
			System.out.println("Upload file size : " + multipartFile.getSize()); // 파일 크기

			String uploadFileName = multipartFile.getOriginalFilename(); 
			System.out.println("uplodaFileName : "+uploadFileName);
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1); // 경로가 있다면 원래 이름만 가져올 수 있도록
			System.out.println("last file name : " + uploadFileName);
			File saveFile = new File(uploadFolder, uploadFileName); //uploadFolder 위치에 uploadFileName으로 생성

			try {
				multipartFile.transferTo(saveFile); // 스프링에서 제공하는 파일 객체를 자바 파일 객체로 변환

			} catch (Exception e) {
				e.getMessage();
			}
		}
		
	} // uploadAjaxPost END
	
	@ResponseBody
	@PostMapping("/delete2")
							  // 스프링에서 지원하는 파일 객체
	public void deleteAjaxPost( MultipartFile[] deleteFile, Integer bno) {

		System.out.println("ajax post update!");

		String uploadFolder = "E:\\code\\MyBatisProject2\\src\\main\\webapp\\resources";

		for (MultipartFile multipartFile : deleteFile) { // 여러개의 파일일 경우 향상된 for문 이용

			System.out.println("------------------------");
			System.out.println("Upload file name : " + multipartFile.getOriginalFilename()); // 파일 이름
			System.out.println("Upload file size : " + multipartFile.getSize()); // 파일 크기

			String deleteFileName = multipartFile.getOriginalFilename(); 
			System.out.println("uplodaFileName : "+deleteFile);
			deleteFileName = deleteFileName.substring(deleteFileName.lastIndexOf("\\") + 1); // 경로가 있다면 원래 이름만 가져올 수 있도록
			System.out.println("last file name : " + deleteFileName);
			File delFile = new File(uploadFolder, deleteFileName); //uploadFolder 위치에 uploadFileName으로 생성

			try {	
				if(delFile.exists()) { // 파일이 존재하면
					delFile.delete(); // 파일 삭제	
				}

			} catch (Exception e) {
				e.getMessage();
			}
			
		}
		
	} // uploadAjaxPost END
}