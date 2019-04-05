package com.springmvc.test.fileupload.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.test.fileupload.FileUploadDTO;
import com.springmvc.test.fileupload.FileUploadService;


@Service
public class FileUploadServiceImpl implements FileUploadService {

	@Autowired 
	FileUploadDAO dao;

	@Override
	public void insertBoard(FileUploadDTO dto) {
		dao.insertBoard(dto);
	}

	@Override
	public List<FileUploadDTO> getBoards() {
		System.out.println("사용자 목록 조회"); 
		return dao.getBoards(); 
	}

	@Override
	public List<Map<String, Object>> getReportBoard() {
		System.out.println("iReport 조회"); 
		return dao.getReportBoard();
	}
	
	@Override
	public List<Map<String, Object>> getReportBoard2() {
		System.out.println("iReport 조회2"); 
		return dao.getReportBoard2();
	}

	@Override
	public List<Map<String, Object>> getReportBoard3() {
		System.out.println("iReport 조회3 - Chart"); 
		return dao.getReportBoard3();
	}
}
