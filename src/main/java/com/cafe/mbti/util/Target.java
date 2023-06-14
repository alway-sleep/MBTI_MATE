package com.cafe.mbti.util;

import javax.servlet.http.HttpServletRequest;

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
	
	public Target(HttpServletRequest request, Integer boardPage, Integer boardSection, Integer boardList, String boardName, Integer boardNumber, Integer searchOption) {
		Target target = (Target) request.getSession().getAttribute("target");
		if (target == null) {
			request.getSession().setAttribute("target", new Target());
			target = (Target) request.getSession().getAttribute("target");
			target.setBoardPage(boardPage != null ? boardPage : target.getBoardPage());
			target.setBoardSection(boardSection != null ? boardSection : target.getBoardSection());
			target.setBoardList(boardList != null ? boardList : target.getBoardList());
			target.setBoardName(boardName != null ? boardName : target.getBoardName());
			target.setBoardNumber(boardNumber != null ? boardNumber : target.getBoardNumber());
			target.setSearchOption(searchOption != null ? searchOption : target.getSearchOption());
		} else {
			target.setBoardPage(boardPage != null ? boardPage : target.getBoardPage());
			target.setBoardSection(boardSection != null ? boardSection : target.getBoardSection());
			target.setBoardList(boardList != null ? boardList : target.getBoardList());
			target.setBoardName(boardName != null ? boardName : target.getBoardName());
			target.setBoardNumber(boardNumber != null ? boardNumber : target.getBoardNumber());
			target.setSearchOption(searchOption != null ? searchOption : target.getSearchOption());
		}
		target.toString();
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
