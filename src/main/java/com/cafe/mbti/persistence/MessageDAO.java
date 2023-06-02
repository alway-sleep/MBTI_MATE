package com.cafe.mbti.persistence;

import java.util.List;

import com.cafe.mbti.domain.MessageVO;

public interface MessageDAO {
	// 쪽지 작성
	int insert(MessageVO vo);
	// 받은 쪽지함
	List<MessageVO> selectAllByRecipient(String memberId);
	// 보낸 쪽지함
	List<MessageVO> selectAllBySender(int memberNumber);
	// 쪽지 보관함
	List<MessageVO> selectAllByBox(int messageBox);
	// 쪽지 읽기
	MessageVO select(int messageNumber);
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
