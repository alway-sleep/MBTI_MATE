package com.cafe.mbti.util;

public class Target {
	private Integer boardPage, boardSection, boardList;
	private String boardName, keyword;
	private Integer boardNumber, searchOption, memberNumber, option;
	
	public Target() {
		boardPage = 1;
		boardSection = 0;
		boardList = 1;
		boardName = "";
		boardNumber = 0;
		searchOption = 0;
		keyword = "";
		memberNumber = 0;
		option = 0;
	}
	
	public Target setTarget(Target target, Integer boardPage, Integer boardSection, Integer boardList, String boardName, Integer boardNumber, Integer searchOption, String keyword, Integer memberNumber, Integer option) {
		target.setBoardPage(boardPage != null ? boardPage : target.getBoardPage());
		target.setBoardSection(boardSection != null ? boardSection : target.getBoardSection());
		target.setBoardList(boardList != null ? boardList : target.getBoardList());
		target.setBoardName(boardName != null ? boardName : target.getBoardName());
		target.setBoardNumber(boardNumber != null ? boardNumber : target.getBoardNumber());
		target.setSearchOption(searchOption != null ? searchOption : target.getSearchOption());
		target.setKeyword(keyword != null ? keyword : target.getKeyword());
		target.setMemberNumber(memberNumber != null ? memberNumber : target.getMemberNumber());
		target.setOption(option != null ? option : target.getOption());
		System.out.println(target.toString());
		return target;
	}

	public Integer getBoardPage() {
		return boardPage;
	}

	public void setBoardPage(Integer boardPage) {
		this.boardPage = boardPage;
	}

	public Integer getBoardSection() {
		return boardSection;
	}

	public void setBoardSection(Integer boardSection) {
		this.boardSection = boardSection;
	}

	public Integer getBoardList() {
		return boardList;
	}

	public void setBoardList(Integer boardList) {
		this.boardList = boardList;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public Integer getBoardNumber() {
		return boardNumber;
	}

	public void setBoardNumber(Integer boardNumber) {
		this.boardNumber = boardNumber;
	}

	public Integer getSearchOption() {
		return searchOption;
	}

	public void setSearchOption(Integer searchOption) {
		this.searchOption = searchOption;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(Integer memberNumber) {
		this.memberNumber = memberNumber;
	}

	public Integer getOption() {
		return option;
	}

	public void setOption(Integer option) {
		this.option = option;
	}

	@Override
	public String toString() {
		return "Target [boardPage=" + boardPage + ", boardSection=" + boardSection + ", boardList=" + boardList
				+ ", boardName=" + boardName + ", keyword=" + keyword + ", boardNumber=" + boardNumber
				+ ", searchOption=" + searchOption + ", memberNumber=" + memberNumber + ", option=" + option + "]";
	}
}
