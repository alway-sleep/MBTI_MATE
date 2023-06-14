package com.cafe.mbti.util;

// 브라우저에서 보여질 페이지 번호와
// 한 페이지에서 보여질 게시글의 개수를 저장하는 클래스
// -> paging 처리에 필요한 start와 end 번호를 알 수 있음
public class BoardPageCriteria {
	private int boardPage; // 현재 페이지 번호
	private int boardNumsPerPage; // 한 페이지의 게시글 개수
	private int boardSection, boardList; // 접근한 게시판 구역, 위치
	private String keyword;
	
	public BoardPageCriteria() {
		this.boardPage = 1;
		this.boardNumsPerPage = 5;
		this.boardSection=0;
		this.boardList=0;
		this.keyword = "";
	}
	
	public BoardPageCriteria(int boardPage, int boardNumsPerPage, int boardSection, int boardList, String keyword) {
		this.boardPage = boardPage;
		this.boardNumsPerPage = boardNumsPerPage;
		this.boardSection = boardSection;
		this.boardList = boardList;
		this.keyword = keyword;
	}

	// getter/setter
	public int getBoardPage() {
		return boardPage;
	}

	public void setBoardPage(int boardPage) {
		this.boardPage = boardPage;
	}

	public int getBoardNumsPerPage() {
		return boardNumsPerPage;
	}

	public void setBoardNumsPerPage(int boardNumsPerPage) {
		this.boardNumsPerPage = boardNumsPerPage;
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
	public int getBoardStart() {
		return (this.boardPage - 1) * this.boardNumsPerPage + 1;
	}
	
	// 현재 보여지는 페이지의 마지막 글 일련번호(rn)
	public int getBoardEnd() {
		return this.boardPage * this.boardNumsPerPage;
	}
}
