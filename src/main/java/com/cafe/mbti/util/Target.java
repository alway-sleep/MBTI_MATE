package com.cafe.mbti.util;

public class Target {
	private int boardPage, boardSection, boardList;
	private String boardName;
	private int boardNumber, searchOption;
	
	public Target() {
		boardPage = 1;
		boardSection = 0;
		boardList = 0;
		boardName = "";
		boardNumber = 0;
		searchOption = 0;
	}

	public Target(int boardPage, int boardSection, int boardList, String boardName, int boardNumber, int searchOption) {
		this.boardPage = boardPage;
		this.boardSection = boardSection;
		this.boardList = boardList;
		this.boardName = boardName;
		this.boardNumber = boardNumber;
		this.searchOption = searchOption;
	}

	public int getBoardPage() {
		return boardPage;
	}

	public void setBoardPage(int boardPage) {
		this.boardPage = boardPage;
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

	public int getBoardNumber() {
		return boardNumber;
	}

	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}

	public int getSearchOption() {
		return searchOption;
	}

	public void setSearchOption(int searchOption) {
		this.searchOption = searchOption;
	}

	@Override
	public String toString() {
		return "Target [BoardPage=" + boardPage + ", boardSection=" + boardSection + ", boardList=" + boardList + ", boardName="
				+ boardName + ", boardNumber=" + boardNumber + ", searchOption=" + searchOption + "]";
	}
}
