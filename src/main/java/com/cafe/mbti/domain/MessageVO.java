package com.cafe.mbti.domain;

import java.util.Date;

public class MessageVO {
	private int messageNumber, messageSender;
	private String messageRecipient, messageTitle, messageContent;
	private int messageIsread, messageSenderIsdel, messageRecipientIsdel, messageBox;
	private Date messageRegdate;

	public MessageVO() {}

	public MessageVO(int messageNumber, int messageSender, String messageRecipient, String messageTitle,
			String messageContent, int messageIsread, int messageSenderIsdel, int messageRecipientIsdel,
			int messageBox, Date messageRegdate) {
		this.messageNumber = messageNumber;
		this.messageSender = messageSender;
		this.messageRecipient = messageRecipient;
		this.messageTitle = messageTitle;
		this.messageContent = messageContent;
		this.messageIsread = messageIsread;
		this.messageSenderIsdel = messageSenderIsdel;
		this.messageRecipientIsdel = messageRecipientIsdel;
		this.messageBox = messageBox;
		this.messageRegdate = messageRegdate;
	}

	public int getMessageNumber() {
		return messageNumber;
	}

	public void setMessageNumber(int messageNumber) {
		this.messageNumber = messageNumber;
	}

	public int getMessageSender() {
		return messageSender;
	}

	public void setMessageSender(int messageSender) {
		this.messageSender = messageSender;
	}

	public String getMessageRecipient() {
		return messageRecipient;
	}

	public void setMessageRecipient(String messageRecipient) {
		this.messageRecipient = messageRecipient;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public int getMessageIsread() {
		return messageIsread;
	}

	public void setMessageIsread(int messageIsread) {
		this.messageIsread = messageIsread;
	}

	public int getMessageSenderIsdel() {
		return messageSenderIsdel;
	}

	public void setMessageSenderIsdel(int messageSenderIsdel) {
		this.messageSenderIsdel = messageSenderIsdel;
	}

	public int getMessageRecipientIsdel() {
		return messageRecipientIsdel;
	}

	public void setMessageRecipientIsdel(int messageRecipientIsdel) {
		this.messageRecipientIsdel = messageRecipientIsdel;
	}

	public int getMessageBox() {
		return messageBox;
	}

	public void setMessageBox(int messageBox) {
		this.messageBox = messageBox;
	}

	public Date getMessageRegdate() {
		return messageRegdate;
	}

	public void setMessageRegdate(Date messageRegdate) {
		this.messageRegdate = messageRegdate;
	}

	@Override
	public String toString() {
		return "MessageVO [messageNumber=" + messageNumber + ", messageSender=" + messageSender + ", messageRecipient="
				+ messageRecipient + ", messageTitle=" + messageTitle + ", messageContent=" + messageContent
				+ ", messageIsread=" + messageIsread + ", messageSenderIsdel=" + messageSenderIsdel
				+ ", messageRecipientIsdel=" + messageRecipientIsdel + ", messageBox=" + messageBox
				+ ", messageRegdate=" + messageRegdate + "]";
	}	
}
