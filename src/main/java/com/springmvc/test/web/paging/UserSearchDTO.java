package com.springmvc.test.web.paging;

import java.util.Arrays;

//검색을 위한 DTO
public class UserSearchDTO extends UserDTO{
	// 검색조건
	private String searchCondition;
	private String searchKeyword;
	// 페이징을 위한 조건 , int : null 일떄 0으로 들어감. Integer는 null일떄 null로들어가서 null 체크 가능.
	private Integer start;
	private Integer end;
	// data를 담고 넘겨줄 배열
	private String[] ids;
	
	//정렬을 위한 필드 , 정렬기준
	private String sort;
	
	
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	
	@Override
	public String toString() {
		return "UserSearchDTO [searchCondition=" + searchCondition + ", searchKeyword=" + searchKeyword + ", start="
				+ start + ", end=" + end + ", ids=" + Arrays.toString(ids) + "]";
	}
}
