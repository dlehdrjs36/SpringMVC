package com.springmvc.test.fileupload;

import java.util.List;
import java.util.Map;

public interface FileUploadService {

	void insertBoard(FileUploadDTO dto);
	//전체조회
	public List<FileUploadDTO> getBoards();
	
	//iReport 조회
	public List<Map<String, Object>> getReportBoard();
	
	//iReport 조회2
	public List<Map<String, Object>> getReportBoard2();
	
	//iReport 조회3, Chart
	public List<Map<String, Object>> getReportBoard3();
	
}
