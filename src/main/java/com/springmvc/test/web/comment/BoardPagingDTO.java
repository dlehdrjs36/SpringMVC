package com.springmvc.test.web.comment;

public class BoardPagingDTO extends BoardDTO {
	
	private Integer page = 1; // 현재 페이지, 초기에는 1로 셋팅
	private int pageRecord = 8; // 한 페이지에서 보여줄 레코드 수. 
	private int total; // board의 전체 수 
	private int totalPage; // board의 수를 통해 구한 전체 페이지의 길이.

	private Integer start; // 페이지에서 출력할 시작레코드
	private Integer end; // 페이지에서 출력할 마지막레코드
	
    private int displayPage = 5;   //화면에서 보여줄 페이지의 목록.

	private int beginPage;   //페이지의 시작
	private int endPage;    //페이지의 끝
	
	// 이전, 다음 버튼 보일지 안보일지 정함.
	private boolean prev; //prev 버튼이 보일지/안 보일지            [이전버튼]
	private boolean next; //총 페이지 수가 5개 넘는 경우만 true. [다음버튼]        
	
	// 이전, 다음 버튼을 눌렀을 때 이동할 페이지를 정함.
    private int prevPageno; // 이전페이지
    private int nextPageno; // 다음페이지
    
    // 이전, 다음 버튼을 눌렀을 때 이동할 페이지를 정하는 prevPageno, nextPageno를 구하는데 사용되는 변수들.
	private int pageBlock; // 한 페이지에서 보여줄 페이지 목록를 표현하기위해 그룹을 만듬.
	private int blockStartPage; // 현재 그룹에서의 시작 페이지를 나타냄
	private int blockEndPage; // 현재 그룹에서의 마지막 페이지를 나타냄

	
	public int getPageRecord() {
		return pageRecord;
	}
	public void setPageRecord(int pageRecord) {
		this.pageRecord = pageRecord;
	}
	public int getDisplayPage() {
		return displayPage;
	}
	public void setDisplayPage(int displayPage) {
		this.displayPage = displayPage;
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
		paging();
	}
	// 전체 페이지 길이
	public int getTotalPage() {

		return totalPage;
	}
	// 한 페이지에서 보여줄 페이지 목록을 표현하기위해 페이지당 그룹을 짓는다.
	public int getPageBlock() {
		return pageBlock;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	//페이지 마다 시작될 레코드를 구한다.
	public Integer getStart() {
		start = (page-1) * pageRecord + 1;
		System.out.println("start : " + start);
		return start;
	}
	public Integer getEnd() {
		end = page * pageRecord;
		System.out.println("start : " + end);
		return end;
	}
	public int getBlockStartPage() {

		return blockStartPage;
	}
	public void setBlockStartPage(int blockStartPage) {
		this.blockStartPage = blockStartPage;
	}
	public int getBlockEndPage() {
		return blockEndPage;
	}
	public void setBlockEndPage(int blockEndPage) {
		System.out.println("setBlockEndPage");
		this.blockEndPage = blockEndPage;
	}
	public int getPrevPageno() {
		return prevPageno;
	}
	public void setPrevPageno(int prevPageno) {
		this.prevPageno = prevPageno;
	}
	public int getNextPageno() {
		return nextPageno;
	}
	public void setNextPageno(int nextPageno) {
		this.nextPageno = nextPageno;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	
	
	public void paging() {
			endPage = ((page+(displayPage-1))/displayPage)*displayPage;
			beginPage = endPage - (displayPage-1);
			
			totalPage = (int) Math.ceil(total/pageRecord);
			System.out.println("TotalPage : " + totalPage);
	        if(page>totalPage){
		    	page = totalPage;
		    }
		    if(totalPage<=endPage){
		        endPage = totalPage;
		        next=false;
		    }else{
		        next=true;
		    }

		    prev=(beginPage == 1)? false : true;
		    
			pageBlock = page/ displayPage + (page%displayPage>0 ? 1 : 0);
			System.out.println("pageblock : " + pageBlock);

			blockEndPage = pageBlock*displayPage;
			System.out.println("getBlockEndPage : " + blockEndPage);
			blockStartPage = blockEndPage-(displayPage-1);
			System.out.println("getBlockStartPage : " + blockStartPage);
			
		    
		    if(blockEndPage>totalPage){	
		    	blockEndPage=totalPage; 										// 현재 그룹 끝 번호와 = 전체페이지 수를 같게
	     	}
		    prevPageno = blockStartPage-displayPage; 						   // <<  *[이전]* [21],[22],[23]... [30] [다음]  >>
	        nextPageno = blockStartPage+displayPage;							   // <<  [이전] [21],[22],[23]... [30] *[다음]*  >>
	        if(prevPageno<1){										
	        	prevPageno=1;
	        }
	        if(nextPageno>totalPage){	
	        	nextPageno=totalPage/displayPage*displayPage+1;	
			}   
	}
}
