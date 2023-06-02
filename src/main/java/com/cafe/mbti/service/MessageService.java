package com.cafe.mbti.service;

import java.util.List;

import com.cafe.mbti.domain.MessageVO;

public interface MessageService {
	// 쪽지 작성
	int create(MessageVO vo);
	// 받은 쪽지함
	List<MessageVO> readAllByRecipient(String memberId);
	// 보낸 쪽지함
	List<MessageVO> readAllBySender(int memberNumber);
	// 쪽지 보관함
	List<MessageVO> readAllByBox(int messageBox);
	// 쪽지 읽기
	MessageVO read(int messageNumber);
	// 쪽지 확인여부
	int updateIsRead(int messageNumber);
	// 발신회원 쪽지 삭제여부
	int updateSenderIsDel(int messageNumber);
	// 수신회원 쪽지 삭제여부
	int updateRecipientIsDel(int messageNumber);
	// 쪽지 보관여부
	int updateBox(int messageNumber);
	// 쪽지 삭제
	int delete(int messageNumber);
}
