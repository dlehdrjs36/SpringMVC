package com.springmvc.test.fileupload.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springmvc.test.fileupload.FileUploadDTO;


@Repository
public class FileUploadDAO {

	@Autowired 
	SqlSessionTemplate mybatis;
	
	public void insertBoard(FileUploadDTO dto) {
		mybatis.insert("FileUploadDAO.insertBoard", dto);
	}
	//전체조회
	public List<FileUploadDTO> getBoards() {
		return mybatis.selectList("FileUploadDAO.getBoards");
	}
	//iReport 조회
	public List<Map<String, Object>> getReportBoard() {
		return mybatis.selectList("FileUploadDAO.getReportBoard");
	}
	//iReport 조회2
	public List<Map<String, Object>> getReportBoard2() {
		return mybatis.selectList("FileUploadDAO.getReportBoard2");
	}
	//iReport 조회3
	public List<Map<String, Object>> getReportBoard3() {
		return mybatis.selectList("FileUploadDAO.getReportBoard3");
	}
}
