package com.springmvc.test.web.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springmvc.test.fileupload.FileUploadDTO;
import com.springmvc.test.fileupload.FileUploadService;

@Controller
public class FileUploadController {

	@Autowired
	FileUploadService fileUploadService;

	// 업로드 폼
	@RequestMapping("/fileUploadView.do")
	public String fileUploadView() {
		return "fileUpload";
	}
	
	// 드래그&드랍 업로드 폼
	@RequestMapping("/fileUploadView2.do")
	public String fileUploadView2() {
		return "fileUpload2";
	}

	// 업로드 처리
	@RequestMapping("/fileUpload.do")
	public String fileUpload(HttpServletRequest request, FileUploadDTO dto, Model model) {
		// MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)
		// request;
		MultipartFile multipart = dto.getUploadFile();

		if (!multipart.isEmpty()) {
			String fileName = multipart.getOriginalFilename();
			dto.setBoardFile(fileName);
			System.out.println(fileName);
			try {
				multipart.transferTo(new File("D:/", fileName));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			fileUploadService.insertBoard(dto);
			List<FileUploadDTO> list = fileUploadService.getBoards();
			model.addAttribute("datas", list);
			model.addAttribute("path", fileName);
		}
		return "fileDownload";
	}

	//Ajax 파일 다중 업로드
	@RequestMapping("/ajaxFileUpload.do")
	@ResponseBody
	public List<FileUploadDTO> ajaxFileUpload(HttpServletRequest request, FileUploadDTO dto) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> list = multipartRequest.getFiles("multiUploadFile");
		MultipartFile[] multipart = list.toArray(new MultipartFile[list.size()]);
		String fileName = "";
		String totalFileName = "";
		for ( int i = 0 ; i < list.size(); i++ ) {
			if (!multipart[i].isEmpty()) {
				fileName = multipart[i].getOriginalFilename();
				totalFileName += fileName+",";
//				System.out.println(fileName);
//				System.out.println(totalFileName);
				try {
					multipart[i].transferTo(new File("D:/uploadTest", fileName));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}		
			}
		}
		//마지막파일의 ,를 제거
		totalFileName = totalFileName.substring(0, totalFileName.length()-1);
		dto.setBoardFile(totalFileName);
		fileUploadService.insertBoard(dto);
		
		List<FileUploadDTO> list2 = fileUploadService.getBoards();
				
		return list2;
	}
}
