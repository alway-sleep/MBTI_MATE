package com.cafe.mbti.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.mbti.domain.MessageVO;
import com.cafe.mbti.persistence.MessageDAO;

@Service
public class MessageServiceImple implements MessageService {
	private static final Logger logger = LoggerFactory.getLogger(MessageServiceImple.class);
	
	@Autowired
	private MessageDAO messageDAO;

	@Override
	public int create(MessageVO vo) {
		logger.info("create() 호출");
		return messageDAO.insert(vo);
	}

	@Override
	public List<MessageVO> readAllByRecipient(String memberId) {
		logger.info("readAllByRecipient() 호출");
		return messageDAO.selectAllByRecipient(memberId);
	}

	@Override
	public List<MessageVO> readAllBySender(int memberNumber) {
		logger.info("readAllBySender() 호출");
		return messageDAO.selectAllBySender(memberNumber);
	}

	@Override
	public List<MessageVO> readAllByBox(int messageBox) {
		logger.info("readAllByBox() 호출");
		return messageDAO.selectAllByBox(messageBox);
	}

	@Override
	public MessageVO read(int messageNumber) {
		logger.info("read() 호출");
		return messageDAO.select(messageNumber);
	}

	@Override
	public int updateIsRead(int messageNumber) {
		logger.info("updateIsRead() 호출");
		return messageDAO.updateIsRead(messageNumber);
	}

	@Override
	public int updateSenderIsDel(int messageNumber) {
		logger.info("updateSenderIsDel() 호출");
		return messageDAO.updateSenderIsDel(messageNumber);
	}

	@Override
	public int updateRecipientIsDel(int messageNumber) {
		logger.info("updateRecipientIsDel() 호출");
		return messageDAO.updateRecipientIsDel(messageNumber);
	}

	@Override
	public int updateBox(int messageNumber) {
		logger.info("updateBox() 호출");
		return messageDAO.updateBox(messageNumber);
	}

	@Override
	public int delete(int messageNumber) {
		logger.info("delete() 호출");
		return messageDAO.delete(messageNumber);
	}	
}
