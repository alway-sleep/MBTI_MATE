package com.cafe.mbti.util;

// 브라우저에서 보여질 페이지 번호와
// 한 페이지에서 보여질 게시글의 개수를 저장하는 클래스
// -> paging 처리에 필요한 start와 end 번호를 알 수 있음
public class CommentsPageCriteria {
	private int page; // 현재 페이지 번호
	private int numsPerPage; // 한 페이지의 댓글 개수
	private int boardNumber;
	
	public CommentsPageCriteria() {
		this.page = 1;
		this.numsPerPage = 10;
	}
	
	public CommentsPageCriteria(int page, int numsPerPage, int boardNumber) {
		this.page = page;
		this.numsPerPage = numsPerPage;
		this.boardNumber = boardNumber;
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

	public int getBoardNumber() {
		return boardNumber;
	}

	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
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