package com.cafe.mbti.util;

// 브라우저에서 보여질 페이지 번호와
// 한 페이지에서 보여질 게시글의 개수를 저장하는 클래스
// -> paging 처리에 필요한 start와 end 번호를 알 수 있음
public class CmRpPageCriteria {
	private int memberNumber;
	private int commentsPage, replyPage; // 현재 페이지 번호
	private int commentsNumsPerPage, replyNumsPerPage; // 한 페이지의 댓글 개수
	private int boardNumber, commentsNumber;
	
	public CmRpPageCriteria() {
		this.memberNumber = 0;
		
		this.commentsPage = 1;
		this.commentsNumsPerPage = 10;
		this.boardNumber = 0;
		
		this.replyPage = 1;
		this.replyNumsPerPage = 10;
		this.commentsNumber =0;
	}
	
	public CmRpPageCriteria(int memberNumber, int commentsPage, int commentsNumsPerPage, int boardNumber, int replyPage, int replyNumsPerPage, int commentsNumber) {
		this.memberNumber = memberNumber;
		this.commentsPage = commentsPage;
		this.commentsNumsPerPage = commentsNumsPerPage;
		this.boardNumber = boardNumber;
		
		this.replyPage = replyPage;
		this.replyNumsPerPage = replyNumsPerPage;
		this.commentsNumber = commentsNumber;
	}

	public int getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}

	// getter/setter
	public int getCommentsPage() {
		return commentsPage;
	}

	public void setCommentsPage(int commentsPage) {
		this.commentsPage = commentsPage;
	}

	public int getCommentsNumsPerPage() {
		return commentsNumsPerPage;
	}

	public void setCommentsNumsPerPage(int commentsNumsPerPage) {
		this.commentsNumsPerPage = commentsNumsPerPage;
	}

	public int getBoardNumber() {
		return boardNumber;
	}

	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}

	public int getReplyPage() {
		return replyPage;
	}

	public void setReplyPage(int replyPage) {
		this.replyPage = replyPage;
	}

	public int getReplyNumsPerPage() {
		return replyNumsPerPage;
	}

	public void setReplyNumsPerPage(int replyNumsPerPage) {
		this.replyNumsPerPage = replyNumsPerPage;
	}

	public int getCommentsNumber() {
		return commentsNumber;
	}

	public void setCommentsNumber(int commentsNumber) {
		this.commentsNumber = commentsNumber;
	}

	// 현재 보여지는 페이지의 시작 글 일련번호(rn)
	public int getCommentsStart() {
		return (this.commentsPage - 1) * this.commentsNumsPerPage + 1;
	}
	
	// 현재 보여지는 페이지의 마지막 글 일련번호(rn)
	public int getCommentsEnd() {
		return this.commentsPage * this.commentsNumsPerPage;
	}
	
	// 현재 보여지는 페이지의 시작 글 일련번호(rn)
	public int getReplyStart() {
		return (this.replyPage - 1) * this.replyNumsPerPage + 1;
	}
	
	// 현재 보여지는 페이지의 마지막 글 일련번호(rn)
	public int getReplyEnd() {
		return this.replyPage * this.replyNumsPerPage;
	}
}
