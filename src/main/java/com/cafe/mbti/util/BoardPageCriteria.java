package com.cafe.mbti.util;

// 브라우저에서 보여질 페이지 번호와
// 한 페이지에서 보여질 게시글의 개수를 저장하는 클래스
// -> paging 처리에 필요한 start와 end 번호를 알 수 있음
public class BoardPageCriteria {
	private Integer boardPage; // 현재 페이지 번호
	private Integer boardNumsPerPage; // 한 페이지의 게시글 개수
	private Integer boardSection, boardList; // 접근한 게시판 구역, 위치
	private String keyword;
	
	public BoardPageCriteria() {
		boardPage = 1;
		boardNumsPerPage = 5;
		boardSection = 0;
		boardList = 0;
		keyword = "";
	}
	
	public BoardPageCriteria setBoardPageCriteria(BoardPageCriteria boardPageCriteria, Integer boardPage, Integer boardNumsPerPage, Integer boardSection, Integer boardList, String keyword) {
		boardPageCriteria.setBoardPage(boardPage != null ? boardPage : boardPageCriteria.getBoardPage());
		boardPageCriteria.setBoardNumsPerPage(boardNumsPerPage != null ? boardNumsPerPage : boardPageCriteria.getBoardNumsPerPage());
		boardPageCriteria.setBoardSection(boardSection != null ? boardSection : boardPageCriteria.getBoardSection());
		boardPageCriteria.setBoardList(boardList != null ? boardList : boardPageCriteria.getBoardList());
		boardPageCriteria.setKeyword(keyword != null ? keyword : boardPageCriteria.getKeyword());
		System.out.println(boardPageCriteria.toString());
		return boardPageCriteria;
	}

	// getter/setter
	public Integer getBoardPage() {
		return boardPage;
	}

	public void setBoardPage(Integer boardPage) {
		this.boardPage = boardPage;
	}

	public Integer getBoardNumsPerPage() {
		return boardNumsPerPage;
	}

	public void setBoardNumsPerPage(Integer boardNumsPerPage) {
		this.boardNumsPerPage = boardNumsPerPage;
	}
	
	public Integer getBoardSection() {
		return boardSection;
	}

	public void setBoardSection(Integer boardSection) {
		this.boardSection = boardSection;
	}

	public Integer getBoardList() {
		return boardList;
	}

	public void setBoardList(Integer boardList) {
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
	public Integer getBoardStart() {
		return (this.boardPage - 1) * this.boardNumsPerPage + 1;
	}
	
	// 현재 보여지는 페이지의 마지막 글 일련번호(rn)
	public Integer getBoardEnd() {
		return this.boardPage * this.boardNumsPerPage;
	}

	@Override
	public String toString() {
		return "BoardPageCriteria [boardPage=" + boardPage + ", boardNumsPerPage=" + boardNumsPerPage
				+ ", boardSection=" + boardSection + ", boardList=" + boardList + ", keyword=" + keyword + "]";
	}
}
