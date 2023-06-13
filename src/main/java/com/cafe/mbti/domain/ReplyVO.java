package com.cafe.mbti.domain;

import java.util.Date;

public class ReplyVO {
	private int replySeqNextVal, replyNumber, commentsNumber, memberNumber;
	private String replyContent;
	private Date replyRegdate;
	// JOIN
	private String memberNickname, memberPicture;

	public ReplyVO() {}

	public ReplyVO(int replyNumber, int commentsNumber, int memberNumber, String replyContent, Date replyRegdate,
			String memberNickname, String memberPicture) {
		this.replyNumber = replyNumber;
		this.commentsNumber = commentsNumber;
		this.memberNumber = memberNumber;
		this.replyContent = replyContent;
		this.replyRegdate = replyRegdate;
		this.memberNickname = memberNickname;
		this.memberPicture = memberPicture;
	}

	public int getReplyNumber() {
		return replyNumber;
	}

	public void setReplyNumber(int replyNumber) {
		this.replyNumber = replyNumber;
	}

	public int getCommentsNumber() {
		return commentsNumber;
	}

	public void setCommentsNumber(int commentsNumber) {
		this.commentsNumber = commentsNumber;
	}

	public int getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getReplyRegdate() {
		return replyRegdate;
	}

	public void setReplyRegdate(Date replyRegdate) {
		this.replyRegdate = replyRegdate;
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

	public int getReplySeqNextVal() {
		return replySeqNextVal;
	}

	public void setReplySeqNextVal(int replySeqNextVal) {
		this.replySeqNextVal = replySeqNextVal;
	}

	@Override
	public String toString() {
		return "ReplyVO [replyNumber=" + replyNumber + ", commentsNumber=" + commentsNumber + ", memberNumber="
				+ memberNumber + ", replyContent=" + replyContent + ", replyRegdate=" + replyRegdate
				+ ", memberNickname=" + memberNickname + ", memberPicture=" + memberPicture + "]";
	}
}
