package com.cafe.mbti.util;

// 브라우저에서 보여질 페이지 번호와
// 한 페이지에서 보여질 게시글의 개수를 저장하는 클래스
// -> paging 처리에 필요한 start와 end 번호를 알 수 있음
public class PageCriteria {
	private int page; // 현재 페이지 번호
	private int numsPerPage; // 한 페이지의 게시글 개수
	private int boardSection, boardList; // 접근한 게시판 구역, 위치
	private String keyword;
	
	public PageCriteria() {
		this.page = 1;
		this.numsPerPage = 5;
		this.boardSection=0;
		this.boardList=0;
		this.keyword = "";
	}
	
	public PageCriteria(int page, int numsPerPage, int boardSection, int boardList, String keyword) {
		this.page = page;
		this.numsPerPage = numsPerPage;
		this.boardSection = boardSection;
		this.boardList = boardList;
		this.keyword = keyword;
	}

	// getter/setter
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNumsPerPage() {
		return numsPerPage;
	}

	public void setNumsPerPage(int numsPerPage) {
		this.numsPerPage = numsPerPage;
	}
	
	public int getBoardSection() {
		return boardSection;
	}

	public void setBoardSection(int boardSection) {
		this.boardSection = boardSection;
	}

	public int getBoardList() {
		return boardList;
	}

	public void setBoardList(int boardList) {
		this.boardList = boardList;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	// 검색 키워드
	public String getSqlKeyword() {
		return "%"+keyword+"%";
	}

	// 현재 보여지는 페이지의 시작 글 일련번호(rn)
	public int getStart() {
		return (this.page - 1) * this.numsPerPage + 1;
	}
	
	// 현재 보여지는 페이지의 마지막 글 일련번호(rn)
	public int getEnd() {
		return this.page * this.numsPerPage;
	}
}
