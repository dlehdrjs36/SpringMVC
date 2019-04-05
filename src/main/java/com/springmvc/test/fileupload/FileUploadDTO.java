package com.springmvc.test.fileupload;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadDTO {
	private int boardId;
	private MultipartFile uploadFile; //단일 파일 업로드
	private MultipartFile[] multiUploadFile; // 다중 파일 업로드
	private String boardFile;
	
	//iReport Chart에 사용되는 데이터
	private int count;
	private String boardDate;
	
	
	public String getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public MultipartFile[] getMultiUploadFile() {
		return multiUploadFile;
	}

	public void setMultiUploadFile(MultipartFile[] multiUploadFile) {
		this.multiUploadFile = multiUploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getBoardFile() {
		return boardFile;
	}

	public void setBoardFile(String boardFile) {
		this.boardFile = boardFile;
	}

}
