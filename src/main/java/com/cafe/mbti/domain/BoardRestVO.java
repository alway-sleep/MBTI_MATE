package com.cafe.mbti.domain;

public class BoardRestVO {
	private int boardviewSeqNextVal, boardlikeSeqNextVal, boardviewNumber, boardlikeNumber, boardNumber, memberNumber;

	public BoardRestVO() {}

	public BoardRestVO(int boardviewNumber, int boardlikeNumber, int boardNumber, int memberNumber) {
		this.boardviewNumber = boardviewNumber;
		this.boardlikeNumber = boardlikeNumber;
		this.boardNumber = boardNumber;
		this.memberNumber = memberNumber;
	}

	public int getBoardviewNumber() {
		return boardviewNumber;
	}

	public void setBoardviewNumber(int boardviewNumber) {
		this.boardviewNumber = boardviewNumber;
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
	
	public int getBoardviewSeqNextVal() {
		return boardviewSeqNextVal;
	}

	public void setBoardviewSeqNextVal(int boardviewSeqNextVal) {
		this.boardviewSeqNextVal = boardviewSeqNextVal;
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
