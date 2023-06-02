package com.cafe.mbti.util;

public class Target {
	private int page, boardSection, boardList;
	private String boardName, encodedBoardName;
	private int boardNumber;
	
	public Target() {
		page = 1;
		boardSection = 0;
		boardList = 0;
		boardName = "";
		encodedBoardName = "";
		boardNumber = 0;
	}

	public Target(int page, int boardSection, int boardList, String boardName, int boardNumber) {
		this.page = page;
		this.boardSection = boardSection;
		this.boardList = boardList;
		this.boardName = boardName;
		this.boardNumber = boardNumber;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getBoardSection() {
		return boardSection;
	}

	public void setBoardSection(int boardSection) {
		this.boardSection = boardSection;
	}

	public int getBoardList() {
		return boardList;
	}

	public void setBoardList(int boardList) {
		this.boardList = boardList;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getEncodedBoardName() {
		return encodedBoardName;
	}

	public void setEncodedBoardName(String encodedBoardName) {
		this.encodedBoardName = encodedBoardName;
	}

	public int getBoardNumber() {
		return boardNumber;
	}

	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}

	@Override
	public String toString() {
		return "Target [page=" + page + ", boardSection=" + boardSection + ", boardList=" + boardList + ", boardName="
				+ boardName + ", encodedBoardName=" + encodedBoardName + ", boardNumber=" + boardNumber + "]";
	}
}
