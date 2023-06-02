package com.cafe.mbti.domain;

import java.util.Date;

public class CommentsVO {
	private int commentsNumber, boardNumber, memberNumber;
	private String commentsContent;
	private Date commentsRegdate;
	// JOIN
	private String memberNickname, memberPicture;

	public CommentsVO() {}

	public CommentsVO(int commentsNumber, int boardNumber, int memberNumber, String commentsContent,
			Date commentsRegdate, String memberNickname, String memberPicture) {
		this.commentsNumber = commentsNumber;
		this.boardNumber = boardNumber;
		this.memberNumber = memberNumber;
		this.commentsContent = commentsContent;
		this.commentsRegdate = commentsRegdate;
		this.memberNickname = memberNickname;
		this.memberPicture = memberPicture;
	}

	public int getCommentsNumber() {
		return commentsNumber;
	}

	public void setCommentsNumber(int commentsNumber) {
		this.commentsNumber = commentsNumber;
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

	public Date getCommentsRegdate() {
		return commentsRegdate;
	}

	public void setCommentsRegdate(Date commentsRegdate) {
		this.commentsRegdate = commentsRegdate;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public String getMemberPicture() {
		return memberPicture;
	}

	public void setMemberPicture(String memberPicture) {
		this.memberPicture = memberPicture;
	}

	@Override
	public String toString() {
		return "CommentsVO [commentsNumber=" + commentsNumber + ", boardNumber=" + boardNumber + ", memberNumber="
				+ memberNumber + ", commentsContent=" + commentsContent + ", commentsRegdate=" + commentsRegdate
				+ ", memberNickname=" + memberNickname + ", memberPicture=" + memberPicture + "]";
	}
}
