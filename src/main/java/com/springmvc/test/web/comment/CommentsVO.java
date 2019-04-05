package com.springmvc.test.web.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CommentsVO {
	
	private String seq; //댓글번호
	private String name; //이름
	private String content; //내용
	private String boardId; //게시글번호(참조키)
	private String regdate;

	private int    pageUnit; // 페이징처리하지않고 바로쓸것

	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}	
	@JsonIgnore
	public int getPageUnit() {
		return pageUnit;
	}
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	@Override
	public String toString() {
		return "CommentsVO [seq=" + seq + ", name=" + name + ", content=" + content + ", boardId=" + boardId
				+ ", regdate=" + regdate + "]";
	}	
}
