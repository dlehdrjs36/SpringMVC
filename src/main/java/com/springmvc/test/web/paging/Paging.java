package com.springmvc.test.web.paging;
public class Paging {
	int pageUnit=10 ; 		//한 페이지 출력할 레코드 건수
	int pageSize=10 ; 		//한 페이지에 출력할 페이지 번호 수 (5) 1~ 5까지
	int lastPage;     		//마지막 페이지번호
	int totalRecord;		//전체 레코드건수
	Integer page = 1;		//현재 페이지
	// 총 페이지가 19이고 페이지당 10개의 번호만 출력한다고 가정할때,
	int startPage;			//한 페이지에 출력할 시작 페이지 1
	int endPage;			//한 페이지에 출력할 마지막 페이지 10
	int first;				//현재 페이지의 시작 레코드 번호
	int last;				//현재 페이지의 마지막 레코드 번호
	
	/*	 작업중 
	 *  int pageGroup; 			//페이지 그룹을 구분하는 변수.
		
		public void setPageGroup(int PageGroup) {
			pageGroup = page / pageSize + (page % pageSize > 0 ? 1 : 0);
		}
		public int getPageGroup() {
			endPage = pageGroup*pageSize; 			
			startPage = endPage-(pageSize-1);
			
			return pageGroup;
		}
	*/
	public int getFirst() {
		first = (getPage() - 1) * getPageUnit() + 1;
		return first;
	}
	public int getLast() {
		last = getPage() * getPageUnit();
		return last;
	}
	public int getPageUnit() {
		return pageUnit;
	}
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getLastPage() {
		lastPage = totalRecord / pageUnit + 
				   ( totalRecord % pageUnit>0 ? 1 : 0 );
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public int getStartPage() {
		startPage = (page-1)/pageSize * pageSize + 1;
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		endPage = (page-1)/pageSize  * pageSize  + pageSize ;
		if ( endPage > getLastPage() )
			endPage = getLastPage() ;
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
}
