package com.cafe.mbti.domain;

import java.util.Date;

public class CmRpVO {
	private int commentsNumber, replyNumber, boardNumber, memberNumber;
	private String commentsContent, replyContent;
	private Date regdate;
	
	public CmRpVO() {}

	public CmRpVO(int commentsNumber, int replyNumber, int boardNumber, int memberNumber, String commentsContent, String replyContent, Date regdate) {
		super();
		this.commentsNumber = commentsNumber;
		this.replyNumber = replyNumber;
		this.boardNumber = boardNumber;
		this.memberNumber = memberNumber;
		this.commentsContent = commentsContent;
		this.replyContent = replyContent;
		this.regdate = regdate;
	}

	public int getCommentsNumber() {
		return commentsNumber;
	}

	public void setCommentsNumber(int commentsNumber) {
		this.commentsNumber = commentsNumber;
	}

	public int getReplyNumber() {
		return replyNumber;
	}

	public void setReplyNumber(int replyNumber) {
		this.replyNumber = replyNumber;
	}

	public int getBoardNumber() {
		return boardNumber;
	}

	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}

	public int getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getCommentsContent() {
		return commentsContent;
	}

	public void setCommentsContent(String commentsContent) {
		this.commentsContent = commentsContent;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "CmRpVO [commentsNumber=" + commentsNumber + ", replyNumber=" + replyNumber + ", boardNumber="
				+ boardNumber + ", memberNumber=" + memberNumber + ", commentsContent=" + commentsContent
				+ ", replyContent=" + replyContent + ", regdate=" + regdate + "]";
	}
}
