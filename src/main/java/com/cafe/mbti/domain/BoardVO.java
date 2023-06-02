package com.cafe.mbti.domain;

import java.util.Arrays;
import java.util.Date;

public class BoardVO {
	private int boardNumber, memberNumber, boardSection, boardList, boardType;
	private String boardName, boardTitle, boardContent;
	private int boardViews, boardLikes, boardComments;
	private Date boardRegdate;
	// JOIN
	private String memberNickname, memberPicture;
	private int boardlikeNumber;
	private String[] filesName;	

	public BoardVO() {}

	public BoardVO(int boardNumber, int memberNumber, int boardSection, int boardList, int boardType, String boardName,
			String boardTitle, String boardContent, int boardViews, int boardLikes, int boardComments,
			Date boardRegdate, String memberNickname, String memberPicture, int boardlikeNumber, String[] filesName) {
		this.boardNumber = boardNumber;
		this.memberNumber = memberNumber;
		this.boardSection = boardSection;
		this.boardList = boardList;
		this.boardType = boardType;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardViews = boardViews;
		this.boardLikes = boardLikes;
		this.boardComments = boardComments;
		this.boardRegdate = boardRegdate;
		this.memberNickname = memberNickname;
		this.memberPicture = memberPicture;
		this.boardlikeNumber = boardlikeNumber;
		this.filesName = filesName;
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

	public int getBoardType() {
		return boardType;
	}

	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public int getBoardViews() {
		return boardViews;
	}

	public void setBoardViews(int boardViews) {
		this.boardViews = boardViews;
	}

	public int getBoardLikes() {
		return boardLikes;
	}

	public void setBoardLikes(int boardLikes) {
		this.boardLikes = boardLikes;
	}

	public int getBoardComments() {
		return boardComments;
	}

	public void setBoardComments(int boardComments) {
		this.boardComments = boardComments;
	}

	public Date getBoardRegdate() {
		return boardRegdate;
	}

	public void setBoardRegdate(Date boardRegdate) {
		this.boardRegdate = boardRegdate;
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

	public int getBoardlikeNumber() {
		return boardlikeNumber;
	}

	public void setBoardlikeNumber(int boardlikeNumber) {
		this.boardlikeNumber = boardlikeNumber;
	}

	public String[] getFilesName() {
		return filesName;
	}

	public void setFilesName(String[] filesName) {
		this.filesName = filesName;
	}

	@Override
	public String toString() {
		return "BoardVO [boardNumber=" + boardNumber + ", memberNumber=" + memberNumber + ", boardSection="
				+ boardSection + ", boardList=" + boardList + ", boardType=" + boardType + ", boardName=" + boardName
				+ ", boardTitle=" + boardTitle + ", boardContent=" + boardContent + ", boardViews=" + boardViews
				+ ", boardLikes=" + boardLikes + ", boardComments=" + boardComments + ", boardRegdate=" + boardRegdate
				+ ", memberNickname=" + memberNickname + ", memberPicture=" + memberPicture + ", boardlikeNumber="
				+ boardlikeNumber + ", filesName=" + Arrays.toString(filesName) + "]";
	}
}
