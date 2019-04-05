package com.springmvc.test.web.comment;

public class BoardDTO {
	private String boardId;
	private String boardWriter;
	private String boardHit;
	private String boardTitle;
	private String boardDate;
	private String boardGroup;
	private String boardStep;
	private String boardSeq;
	private String boardContent;
	private String boardFile; // 첨부파일명
	
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public String getBoardHit() {
		return boardHit;
	}
	public void setBoardHit(String boardHit) {
		this.boardHit = boardHit;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}
	public String getBoardGroup() {
		return boardGroup;
	}
	public void setBoardGroup(String boardGroup) {
		this.boardGroup = boardGroup;
	}
	public String getBoardStep() {
		return boardStep;
	}
	public void setBoardStep(String boardStep) {
		this.boardStep = boardStep;
	}
	public String getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(String boardSeq) {
		this.boardSeq = boardSeq;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardFile() {
		return boardFile;
	}
	public void setBoardFile(String boardFile) {
		this.boardFile = boardFile;
	}
	@Override
	public String toString() {
		return "BoardDTO [boardId=" + boardId + ", boardWriter=" + boardWriter + ", boardHit=" + boardHit
				+ ", boardTitle=" + boardTitle + ", boardDate=" + boardDate + ", boardGroup=" + boardGroup
				+ ", boardStep=" + boardStep + ", boardSeq=" + boardSeq + ", boardContent=" + boardContent
				+ ", boardFile=" + boardFile + "]";
	}
	
	
}
