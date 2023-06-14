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
		boardTotalPage = (int) Math.ceil((double) boardTotalCount/ boardPageCriteria.getBoardNumsPerPage());
		int temp = (int) Math.ceil((double) boardPageCriteria.getBoardPage() / boardNumsOfPage) * boardNumsOfPage;
		
		if (temp > boardTotalPage) {
			boardEndPage = boardTotalPage;
		} else {
			boardEndPage = temp;
		}
		
		boardStartPage = ((boardEndPage - 1) / boardNumsOfPage) * boardNumsOfPage + 1;
		
		if (boardStartPage == 1) {
			boardHasPrev = false;
		} else {
			boardHasPrev = true;
		}
		
		if (boardEndPage >= boardTotalPage) {
			boardHasNext = false;
		} else {
			boardHasNext = true;
		}
		// Math.ceil (올림)
		// Math.floor (버림)		
	}
	
	// startPageNo, endPageNo, hasPrev, hasNext 값을 계산 및 세팅
	public void setCommentsPageData() {
		commentsTotalPage = (int) Math.ceil((double) commentsTotalCount/ cmRpPageCriteria.getCommentsNumsPerPage());
		int temp = (int) Math.ceil((double) cmRpPageCriteria.getCommentsPage() / commentsNumsOfPage) * commentsNumsOfPage;
		
		if (temp > commentsTotalPage) {
			commentsEndPage = commentsTotalPage;
		} else {
			commentsEndPage = temp;
		}
		
		commentsStartPage = ((commentsEndPage - 1) / commentsNumsOfPage) * commentsNumsOfPage + 1;
		
		if (commentsStartPage == 1) {
			commentsHasPrev = false;
		} else {
			commentsHasPrev = true;
		}
		
		if (commentsEndPage >= commentsTotalPage) {
			commentsHasNext = false;
		} else {
			commentsHasNext = true;
		}
		// Math.ceil (올림)
		// Math.floor (버림)		
	}
	
	// startPageNo, endPageNo, hasPrev, hasNext 값을 계산 및 세팅
	public void setReplyPageData() {
		replyTotalPage = (int) Math.ceil((double) replyTotalCount/ cmRpPageCriteria.getReplyNumsPerPage());
		int temp = (int) Math.ceil((double) cmRpPageCriteria.getReplyPage() / replyNumsOfPage) * replyNumsOfPage;
		
		if (temp > replyTotalPage) {
			replyEndPage = replyTotalPage;
		} else {
			replyEndPage = temp;
		}
		
		replyStartPage = ((replyEndPage - 1) / replyNumsOfPage) * replyNumsOfPage + 1;
		
		if (replyStartPage == 1) {
			replyHasPrev = false;
		} else {
			replyHasPrev = true;
		}
	
		if (replyEndPage >= replyTotalPage) {
			replyHasNext = false;
		} else {
			replyHasNext = true;
		}
		// Math.ceil (올림)
		// Math.floor (버림)		
	}
} // end PageMaker
