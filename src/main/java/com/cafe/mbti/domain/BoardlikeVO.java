package com.cafe.mbti.domain;

public class BoardlikeVO {
	private int boardlikeSeqNextVal, boardlikeNumber, boardNumber, memberNumber;

	public BoardlikeVO() {}

	public BoardlikeVO(int boardlikeNumber, int boardNumber, int memberNumber) {
		this.boardlikeNumber = boardlikeNumber;
		this.boardNumber = boardNumber;
		this.memberNumber = memberNumber;
	}

	public int getBoardlikeNumber() {
		return boardlikeNumber;
	}

	public void setBoardlikeNumber(int boardlikeNumber) {
		this.boardlikeNumber = boardlikeNumber;
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

	public int getBoardlikeSeqNextVal() {
		return boardlikeSeqNextVal;
	}

	public void setBoardlikeSeqNextVal(int boardlikeSeqNextVal) {
		this.boardlikeSeqNextVal = boardlikeSeqNextVal;
	}

	@Override
	public String toString() {
		return "BoardlikeVO [boardlikeNumber=" + boardlikeNumber + ", boardNumber=" + boardNumber + ", memberNumber="
				+ memberNumber + "]";
	}
}
