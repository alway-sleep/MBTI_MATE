package com.cafe.mbti.domain;

import java.util.Date;

public class FilesVO {
	private int filesSeqNextVal, filesNumber, boardNumber;
	private String filesName;
	private Date filesRegdate;
	
	public FilesVO() {}

	public FilesVO(int filesNumber, int boardNumber, String filesName, Date filesRegdate) {
		this.filesNumber = filesNumber;
		this.boardNumber = boardNumber;
		this.filesName = filesName;
		this.filesRegdate = filesRegdate;
	}

	public int getFilesNumber() {
		return filesNumber;
	}

	public void setFilesNumber(int filesNumber) {
		this.filesNumber = filesNumber;
	}

	public int getBoardNumber() {
		return boardNumber;
	}

	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}

	public String getFilesName() {
		return filesName;
	}

	public void setFilesName(String filesName) {
		this.filesName = filesName;
	}

	public Date getFilesRegdate() {
		return filesRegdate;
	}

	public void setFilesRegdate(Date filesRegdate) {
		this.filesRegdate = filesRegdate;
	}
	
	public int getFilesSeqNextVal() {
		return filesSeqNextVal;
	}

	public void setFilesSeqNextVal(int filesSeqNextVal) {
		this.filesSeqNextVal = filesSeqNextVal;
	}

	@Override
	public String toString() {
		return "FilesVO [filesNumber=" + filesNumber + ", boardNumber=" + boardNumber + ", filesName=" + filesName
				+ ", filesRegdate=" + filesRegdate + "]";
	}
}
