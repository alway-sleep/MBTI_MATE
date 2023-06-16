package com.cafe.mbti.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe.mbti.domain.MemberVO;
import com.cafe.mbti.persistence.BoardDAO;
import com.cafe.mbti.persistence.BoardRestDAO;
import com.cafe.mbti.persistence.CommentsDAO;
import com.cafe.mbti.persistence.MemberDAO;
import com.cafe.mbti.persistence.ReplyDAO;

@Service
public class MemberServiceImple implements MemberService {
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImple.class);
	
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private BoardDAO boardDAO;
	@Autowired
	private BoardRestDAO boardlikeDAO;
	@Autowired
	private CommentsDAO commentsDAO;
	@Autowired
	private ReplyDAO replyDAO;

	@Override
	public int create(MemberVO memberVO) {
		logger.info("create() 호출");
		return memberDAO.insert(memberVO);
	}

	@Override
	public int isDuplicatedId(String memberId) {
		logger.info("isDuplicatedId() 호출");
		return memberDAO.isDuplicatedId(memberId);
	}

	@Override
	public int isDuplicatedNickname(String memberNickname) {
		logger.info("isDuplicatedNickname() 호출");
		return memberDAO.isDuplicatedNickname(memberNickname);
	}

	@Override
	public int isDuplicatedRRN(String memberRRN) {
		logger.info("isDuplicatedRRN() 호출");
		return memberDAO.isDuplicatedRRN(memberRRN);
	}

	@Override
	public int isDuplicatedPhone(String memberPhone) {
		logger.info("isDuplicatedPhone() 호출");
		return memberDAO.isDuplicatedPhone(memberPhone);
	}

	@Override
	public int isDuplicatedEmail(String memberEmail) {
		logger.info("isDuplicatedEmail() 호출");
		return memberDAO.isDuplicatedEmail(memberEmail);
	}

	@Override
	public int login(String memberId, String memberPw) {
		logger.info("login() 호출");
		return memberDAO.login(memberId, memberPw);
	}

	@Override
	public String readId(String memberName, String memberRRN, String memberPhone) {
		logger.info("readId() 호출");
		return memberDAO.selectId(memberName, memberRRN, memberPhone);
	}

	@Override
	public int readPw(String memberId, String memberName, String memberRRN, String memberPhone, String memberEmail) {
		logger.info("readPw() 호출");
		return memberDAO.selectPw(memberId, memberName, memberRRN, memberPhone, memberEmail);
	}

	@Override
	public int resetPw(String memberPwReset, String memberId, String memberName, String memberRRN, String memberPhone, String memberEmail) {
		logger.info("resetPw() 호출");
		return memberDAO.resetPw(memberPwReset, memberId, memberName, memberRRN, memberPhone, memberEmail);
	}

	@Override
	public MemberVO read(int memberNumber) {
		logger.info("read() 호출");
		return memberDAO.select(memberNumber);
	}

	@Override
	public int update(String memberNickname, String memberPhone, String memberEmail, String memberRegion,
			String memberMBTI, int memberNumber) {
		logger.info("update() 호출");
		return memberDAO.update(memberNickname, memberPhone, memberEmail, memberRegion, memberMBTI, memberNumber);
	}

	@Override
	public int updatePw(String memberPwNew, int memberNumber, String memberPw) {
		logger.info("updatePw() 호출");
		return memberDAO.updatePw(memberPwNew, memberNumber, memberPw);
	}

	@Override
	public String readNicknameStr(String memberNickname) {
		logger.info("readNicknameStr() 호출");
		return memberDAO.selectNicknameStr(memberNickname);
	}

	@Override
	public int readNicknameInt(String memberNickname, int memberNumber) {
		logger.info("readNicknameInt() 호출");
		return memberDAO.selectNicknameInt(memberNickname, memberNumber);
	}

	@Override
	public String readPhoneStr(String memberPhone) {
		logger.info("readPhoneStr() 호출");
		return memberDAO.selectPhoneStr(memberPhone);
	}

	@Override
	public int readPhoneInt(String memberPhone, int memberNumber) {
		logger.info("readPhoneInt() 호출");
		return memberDAO.selectPhoneInt(memberPhone, memberNumber);
	}

	@Override
	public String readEmailStr(String memberEmail) {
		logger.info("readEmailStr() 호출");
		return memberDAO.selectEmailStr(memberEmail);
	}

	@Override
	public int readEmailInt(String memberEmail, int memberNumber) {
		logger.info("readEmailInt() 호출");
		return memberDAO.selectEmailInt(memberEmail, memberNumber);
	}

	@Override
	public String readPwStr(int memberNumber) {
		logger.info("readPwStr() 호출");
		return memberDAO.selectPwStr(memberNumber);
	}

	@Override
	public int readPwInt(String memberPw, int memberNumber) {
		logger.info("readPwInt() 호출");
		return memberDAO.selectPwInt(memberPw, memberNumber);
	}

	@Override
	public String readNicknameByGrade(int memberGrade) {
		logger.info("readNicknameByGrade() 호출");
		return memberDAO.selectNicknameByGrade(memberGrade);
	}
	
	@Override
	public int readByNumberOnBoard(int memberNumber) {
		logger.info("readByNumberOnBoard() 호출");
		return memberDAO.selectByNumberOnBoard(memberNumber);
	}

	@Override
	public int readByNumberOnCmRp(int memberNumber) {
		logger.info("readByNumberOnCmRp() 호출");
		return memberDAO.selectByNumberOnCmRp(memberNumber);
	}

	@Override
	public int readMemberCount() {
		logger.info("readMemberCount() 호출");
		return memberDAO.selectMemberCount();
	}

	@Override
	public int updateGrade(int memberNumber) {
		logger.info("updateGrade() 호출");
		return memberDAO.updateGrade(memberNumber);
	}

	@Override
	public int updatePremium(int memberNumber) {
		logger.info("updatePremium() 호출");
		return memberDAO.updatePremium(memberNumber);
	}

	@Transactional(value = "transactionManager") // - root-context.xml에서 설정
	@Override
	public int delete(int memberNumber) throws Exception {
		logger.info("delete() 호출");
		boardDAO.deleteOnMember(memberNumber);
		boardlikeDAO.deleteOnMember(memberNumber);
		commentsDAO.deleteOnMember(memberNumber);
		replyDAO.deleteOnMember(memberNumber);
		return memberDAO.delete(memberNumber);
	}

	@Override
	public int updatePicture(MemberVO memberVO) {
		logger.info("updatePicture() 호출");
		return memberDAO.updatePicture(memberVO);
	}
	
	@Override
	public int readNumberByNickname(String memberNickname) {
		logger.info("readNumberByNickname() 호출");
		return memberDAO.selectNumberByNickname(memberNickname);
	}

	@Override
	public int readNumberById(String memberId) {
		logger.info("readNumberById() 호출");
		return memberDAO.selectNumberById(memberId);
	}
}
