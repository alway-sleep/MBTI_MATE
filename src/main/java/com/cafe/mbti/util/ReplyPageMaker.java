package com.cafe.mbti.util;

// 페이지 번호들의 링크를 만들기 위한 유틸리티 클래스
public class ReplyPageMaker {
	private ReplyPageCriteria replyPageCriteria;
	private int totalCount; // 전체 게시글 개수
	private int numsOfPageLinks; // 페이지 번호 링크의 개수
	private int startPageNo; // 시작 페이지 링크 번호
	private int endPageNo; // 끝 페이지 링크 번호
	private boolean hasPrev; // 화면에 보이는 시작 페이지 번호보다 작은 숫자의 페이지가 있는 지
	private boolean hasNext; // 화면에 보이는 끝 페이지 번호보다 큰 숫자의 페이지가 있는 지
	private int totalLinkNo;
	
	public ReplyPageMaker() {
		this.numsOfPageLinks = 5;
	}
	
	public ReplyPageCriteria getReplyPageCriteria() {
		return replyPageCriteria;
	}
	
	public void setCriteria(ReplyPageCriteria replyPageCriteria) {
		this.replyPageCriteria = replyPageCriteria;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getNumsOfPageLinks() {
		return numsOfPageLinks;
	}
	
	public int getStartPageNo() {
		return startPageNo;
	}
	
	public int getEndPageNo() {
		return endPageNo;
	}
	
	public boolean isHasPrev() {
		return hasPrev;
	}
	
	public boolean isHasNext() {
		return hasNext;
	}
	
	public int getTotalLinkNo() {
		return totalLinkNo;
	}

	public void setTotalLinkNo(int totalLinkNo) {
		this.totalLinkNo = totalLinkNo;
	}

	// startPageNo, endPageNo, hasPrev, hasNext 값을 계산 및 세팅
	public void setPageData() {
		totalLinkNo = (int) Math.ceil((double) totalCount / replyPageCriteria.getNumsPerPage());
		int temp = (int) Math.ceil((double) replyPageCriteria.getPage() / numsOfPageLinks) * numsOfPageLinks;
		
		if (temp > totalLinkNo) {
			endPageNo = totalLinkNo;
		} else {
			endPageNo = temp;
		}
		
		startPageNo = ((endPageNo - 1) / numsOfPageLinks) * numsOfPageLinks + 1;
		
		if (startPageNo == 1) {
			hasPrev = false;
		} else {
			hasPrev = true;
		}
		
		if (endPageNo >= totalLinkNo) {
			hasNext = false;
		} else {
			hasNext = true;
		}
		// Math.ceil (올림)
		// Math.floor (버림)		
	}
	
} // end PageMaker