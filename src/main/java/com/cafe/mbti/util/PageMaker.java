package com.cafe.mbti.util;

// 페이지 번호들의 링크를 만들기 위한 유틸리티 클래스
public class PageMaker {
	private BoardPageCriteria boardPageCriteria;
	private CmRpPageCriteria cmRpPageCriteria;
	private int boardTotalCount, commentsTotalCount, replyTotalCount; // 전체 게시글 개수
	private int boardNumsOfPage, commentsNumsOfPage, replyNumsOfPage; // 페이지 번호 링크의 개수
	private int boardStartPage, commentsStartPage, replyStartPage; // 시작 페이지 링크 번호
	private int boardEndPage, commentsEndPage, replyEndPage; // 끝 페이지 링크 번호
	private boolean boardHasPrev, commentsHasPrev, replyHasPrev; // 화면에 보이는 시작 페이지 번호보다 작은 숫자의 페이지가 있는 지
	private boolean boardHasNext, commentsHasNext, replyHasNext; // 화면에 보이는 끝 페이지 번호보다 큰 숫자의 페이지가 있는 지
	private int boardTotalPage, commentsTotalPage, replyTotalPage;
	
	public PageMaker() {
		this.boardNumsOfPage = 5;
		this.commentsNumsOfPage = 5;
		this.replyNumsOfPage = 5;
	}
	
	public BoardPageCriteria getBoardPageCriteria() {
		return boardPageCriteria;
	}
	
	public void setBoardPageCriteria(BoardPageCriteria boardPageCriteria) {
		this.boardPageCriteria = boardPageCriteria;
	}
	
	public CmRpPageCriteria getCmRpPageCriteria() {
		return cmRpPageCriteria;
	}
	
	public void setCmRpPageCriteria(CmRpPageCriteria cmrpPageCriteria) {
		this.cmRpPageCriteria = cmrpPageCriteria;
	}

	public int getBoardTotalCount() {
		return boardTotalCount;
	}

	public void setBoardTotalCount(int boardTotalCount) {
		this.boardTotalCount = boardTotalCount;
	}

	public int getCommentsTotalCount() {
		return commentsTotalCount;
	}

	public void setCommentsTotalCount(int commentsTotalCount) {
		this.commentsTotalCount = commentsTotalCount;
	}

	public int getReplyTotalCount() {
		return replyTotalCount;
	}

	public void setReplyTotalCount(int replyTotalCount) {
		this.replyTotalCount = replyTotalCount;
	}

	public int getBoardNumsOfPage() {
		return boardNumsOfPage;
	}

	public void setBoardNumsOfPage(int boardNumsOfPage) {
		this.boardNumsOfPage = boardNumsOfPage;
	}

	public int getCommentsNumsOfPage() {
		return commentsNumsOfPage;
	}

	public void setCommentsNumsOfPage(int commentsNumsOfPage) {
		this.commentsNumsOfPage = commentsNumsOfPage;
	}

	public int getReplyNumsOfPage() {
		return replyNumsOfPage;
	}

	public void setReplyNumsOfPage(int replyNumsOfPage) {
		this.replyNumsOfPage = replyNumsOfPage;
	}

	public int getBoardStartPage() {
		return boardStartPage;
	}

	public int getCommentsStartPage() {
		return commentsStartPage;
	}

	public int getReplyStartPage() {
		return replyStartPage;
	}

	public int getBoardEndPage() {
		return boardEndPage;
	}

	public int getCommentsEndPage() {
		return commentsEndPage;
	}

	public int getReplyEndPage() {
		return replyEndPage;
	}

	public boolean isBoardHasPrev() {
		return boardHasPrev;
	}

	public boolean isCommentsHasPrev() {
		return commentsHasPrev;
	}

	public boolean isReplyHasPrev() {
		return replyHasPrev;
	}

	public boolean isBoardHasNext() {
		return boardHasNext;
	}

	public boolean isCommentsHasNext() {
		return commentsHasNext;
	}

	public boolean isReplyHasNext() {
		return replyHasNext;
	}

	public int getBoardTotalPage() {
		return boardTotalPage;
	}

	public void setBoardTotalPage(int boardTotalPage) {
		this.boardTotalPage = boardTotalPage;
	}

	public int getCommentsTotalPage() {
		return commentsTotalPage;
	}

	public void setCommentsTotalPage(int commentsTotalPage) {
		this.commentsTotalPage = commentsTotalPage;
	}

	public int getReplyTotalPage() {
		return replyTotalPage;
	}

	public void setReplyTotalPage(int replyTotalPage) {
		this.replyTotalPage = replyTotalPage;
	}

	// startPageNo, endPageNo, hasPrev, hasNext 값을 계산 및 세팅
	public void setBoardPageData() {
		// Math.ceil (올림)
		// Math.floor (버림)
		// 전체 게시글의 수 ÷ 출력되는 게시글의 수 = 총 페이지 수(올림)
		boardTotalPage = (int) Math.ceil((double) boardTotalCount/ boardPageCriteria.getBoardNumsPerPage());
		// 현재 페이지 수 ÷ 출력되는 페이지 수 = temp(올림)
		int temp = (int) Math.ceil((double) boardPageCriteria.getBoardPage() / boardNumsOfPage) * boardNumsOfPage;	
		// temp가 총 페이지 수 보다 크다면 마지막 페이지 = 전체 페이지, 작다면 마지막 페이지 = temp
		boardEndPage = (temp > boardTotalPage) ? boardTotalPage : temp;
		// (마지막 페이지 - 1) ÷ 출력되는 페이지 수 =  결과 값(버림) + 1 = 시작 페이지
		boardStartPage = (int) Math.floor((double) (boardEndPage - 1) / boardNumsOfPage) + 1;		
		// 시작 페이지가 1이라면 이전페이지는 존재하지 않음. 1이 아니라면 이전페이지가 존재함.
		boardHasPrev = (boardStartPage == 1) ? false : true;
		// 마지막 페이지가 전체 페이지보다 크다면 다음페이지는 존재하지 않음. 작다면 다음페이지가 존재함.
		boardHasNext = (boardEndPage >= boardTotalPage) ? false : true;
	}
	
	// startPageNo, endPageNo, hasPrev, hasNext 값을 계산 및 세팅
	public void setCommentsPageData() {
		// Math.ceil (올림)
		// Math.floor (버림)
		// 전체 게시글의 수 ÷ 출력되는 게시글의 수 = 총 페이지 수(올림)
		commentsTotalPage = (int) Math.ceil((double) commentsTotalCount/ cmRpPageCriteria.getCommentsNumsPerPage());
		// 현재 페이지 수 ÷ 출력되는 페이지 수 = temp(올림)
		int temp = (int) Math.ceil((double) cmRpPageCriteria.getCommentsPage() / commentsNumsOfPage) * commentsNumsOfPage;
		// temp가 총 페이지 수 보다 크다면 마지막 페이지 = 전체 페이지, 작다면 마지막 페이지 = temp
		commentsEndPage = (temp > commentsTotalPage) ? commentsTotalPage : temp;
		// (마지막 페이지 - 1) ÷ 출력되는 페이지 수 =  결과 값(버림) + 1 = 시작 페이지
		commentsStartPage = ((commentsEndPage - 1) / commentsNumsOfPage) * commentsNumsOfPage + 1;
		// 시작 페이지가 1이라면 이전페이지는 존재하지 않음. 1이 아니라면 이전페이지가 존재함.
		commentsHasPrev = (commentsStartPage == 1) ? false : true;
		// 마지막 페이지가 전체 페이지보다 크다면 다음페이지는 존재하지 않음. 작다면 다음페이지가 존재함.
		commentsHasNext = (commentsEndPage >= commentsTotalPage) ? false : true;
	}
	
	// startPageNo, endPageNo, hasPrev, hasNext 값을 계산 및 세팅
	public void setReplyPageData() {
		// Math.ceil (올림)
		// Math.floor (버림)
		// 전체 게시글의 수 ÷ 출력되는 게시글의 수 = 총 페이지 수(올림)
		replyTotalPage = (int) Math.ceil((double) replyTotalCount/ cmRpPageCriteria.getReplyNumsPerPage());
		// 현재 페이지 수 ÷ 출력되는 페이지 수 = temp(올림)
		int temp = (int) Math.ceil((double) cmRpPageCriteria.getReplyPage() / replyNumsOfPage) * replyNumsOfPage;
		// temp가 총 페이지 수 보다 크다면 마지막 페이지 = 전체 페이지, 작다면 마지막 페이지 = temp
		replyEndPage = (temp > replyTotalPage) ? replyTotalPage : temp;
		// (마지막 페이지 - 1) ÷ 출력되는 페이지 수 =  결과 값(버림) + 1 = 시작 페이지
		replyStartPage = ((replyEndPage - 1) / replyNumsOfPage) * replyNumsOfPage + 1;
		// 시작 페이지가 1이라면 이전페이지는 존재하지 않음. 1이 아니라면 이전페이지가 존재함.
		replyHasPrev = (replyStartPage == 1) ? false : true;
		// 마지막 페이지가 전체 페이지보다 크다면 다음페이지는 존재하지 않음. 작다면 다음페이지가 존재함.
		replyHasNext = (replyEndPage >= replyTotalPage) ? false : true;
	}
} // end PageMaker
