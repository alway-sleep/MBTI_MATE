package com.cafe.mbti.persistence;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe.mbti.domain.MemberVO;

// TODO @Repository @Component : 영속 계층(Persistence Layer)의 DB 관련 기능을 담당
@Repository // 저장소
public class MemberDAOImple implements MemberDAO {
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImple.class);
	private static final String NAMESPACE = "com.cafe.mbti.MemberMapper";
	
	// MyBatis의 SqlSession을 사용하기 위해 Spring Framework가 생성한 bean을 주입(injection)받음
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(MemberVO vo) {
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public int isDuplicatedId(String memberId) {
		logger.info("isDuplicatedId() 호출");
		return sqlSession.selectOne(NAMESPACE + ".isDuplicatedId", memberId);
	}
	
	@Override
	public int isDuplicatedNickname(String memberNickname) {
		logger.info("isDuplicatedNickname() 호출");
		return sqlSession.selectOne(NAMESPACE + ".isDuplicatedNickname", memberNickname);
	}

	@Override
	public int isDuplicatedRRN(String memberRRN) {
		logger.info("isDuplicatedRRN() 호출");
		return sqlSession.selectOne(NAMESPACE + ".isDuplicatedRRN", memberRRN);
	}

	@Override
	public int isDuplicatedPhone(String memberPhone) {
		logger.info("isDuplicatedPhone() 호출");
		return sqlSession.selectOne(NAMESPACE + ".isDuplicatedPhone", memberPhone);
	}

	@Override
	public int isDuplicatedEmail(String memberEmail) {
		logger.info("isDuplicatedEmail() 호출");
		return sqlSession.selectOne(NAMESPACE + ".isDuplicatedEmail", memberEmail);
	}
	
	@Override
	public int login(String memberId, String memberPw) {
		logger.info("login() 호출");
		Map<String, Object> args = new HashMap<>();
		args.put("memberId", memberId);
		args.put("memberPw", memberPw);
		return sqlSession.selectOne(NAMESPACE + ".login", args);
	}

	@Override
	public MemberVO select(int memberNumber) {
		logger.info("select() 호출");
		return sqlSession.selectOne(NAMESPACE + ".select", memberNumber);
	}

	@Override
	public int update(String memberNickname, String memberPhone, String memberEmail, String memberRegion,
			String memberMBTI, int memberNumber) {
		logger.info("MEMBER update() 호출");
		Map<String, Object> args = new HashMap<>();
		args.put("memberNickname", memberNickname);
		args.put("memberPhone", memberPhone);
		args.put("memberEmail", memberEmail);
		args.put("memberRegion", memberRegion);
		args.put("memberMBTI", memberMBTI);
		args.put("memberNumber", memberNumber);
		return sqlSession.update(NAMESPACE + ".update", args);
	}
	
	@Override
	public int updatePw(String memberPwNew, int memberNumber, String memberPw) {
		logger.info("MEMBER updatePw() 호출");
		Map<String, Object> args = new HashMap<>();
		args.put("memberPwNew", memberPwNew);
		args.put("memberNumber", memberNumber);
		args.put("memberPw", memberPw);
		return sqlSession.update(NAMESPACE + ".updatePw", args);
	}

	@Override
	public String selectId(String memberName, String memberRRN, String memberPhone) {
		logger.info("selectId() 호출");
		Map<String, Object> args = new HashMap<>();
		args.put("memberName", memberName);
		args.put("memberRRN", memberRRN);
		args.put("memberPhone", memberPhone);
		return sqlSession.selectOne(NAMESPACE + ".selectId", args);
	}

	@Override
	public int selectPw(String memberId, String memberName, String memberRRN, String memberPhone, String memberEmail) {
		logger.info("selectPw() 호출");
		Map<String, Object> args = new HashMap<>();
		args.put("memberId", memberId);
		args.put("memberName", memberName);
		args.put("memberRRN", memberRRN);
		args.put("memberPhone", memberPhone);
		args.put("memberEmail", memberEmail);
		return sqlSession.selectOne(NAMESPACE + ".selectPw", args);
	}

	@Override
	public int resetPw(String memberPwReset, String memberId, String memberName, String memberRRN, String memberPhone, String memberEmail) {
		logger.info("MEMBER resetPw() 호출");
		Map<String, Object> args = new HashMap<>();
		args.put("memberPwReset", memberPwReset);
		args.put("memberId", memberId);
		args.put("memberName", memberName);
		args.put("memberRRN", memberRRN);
		args.put("memberPhone", memberPhone);
		args.put("memberEmail", memberEmail);
		return sqlSession.update(NAMESPACE + ".resetPw", args);
	}
	
	@Override
	public String selectNicknameStr(String memberNickname) {
		logger.info("selectNicknameStr() 호출");
		return sqlSession.selectOne(NAMESPACE + ".selectNicknameStr", memberNickname);
	}
	
	@Override
	public int selectNicknameInt(String memberNickname, int memberNumber) {
		logger.info("selectNicknameInt() 호출");
		Map<String, Object> args = new HashMap<>();
		args.put("memberNickname", memberNickname);
		args.put("memberNumber", memberNumber);
		return sqlSession.selectOne(NAMESPACE + ".selectNicknameInt", args);
	}

	@Override
	public String selectPhoneStr(String memberPhone) {
		logger.info("selectPhoneStr() 호출");
		return sqlSession.selectOne(NAMESPACE + ".selectPhoneStr", memberPhone);
	}
	
	@Override
	public int selectPhoneInt(String memberPhone, int memberNumber) {
		logger.info("selectPhoneInt() 호출");
		Map<String, Object> args = new HashMap<>();
		args.put("memberPhone", memberPhone);
		args.put("memberNumber", memberNumber);
		return sqlSession.selectOne(NAMESPACE + ".selectPhoneInt", args);
	}

	@Override
	public String selectEmailStr(String memberEmail) {
		logger.info("selectEmailStr() 호출");
		return sqlSession.selectOne(NAMESPACE + ".selectEmailStr", memberEmail);
	}
	
	@Override
	public int selectEmailInt(String memberEmail, int memberNumber) {
		logger.info("selectEmailInt() 호출");
		Map<String, Object> args = new HashMap<>();
		args.put("memberEmail", memberEmail);
		args.put("memberNumber", memberNumber);
		return sqlSession.selectOne(NAMESPACE + ".selectEmailInt", args);
	}
	
	@Override
	public String selectPwStr(int memberNumber) {
		logger.info("selectPwStr() 호출");
		return sqlSession.selectOne(NAMESPACE + ".selectPwStr", memberNumber);
	}
	
	@Override
	public int selectPwInt(String memberPw, int memberNumber) {
		logger.info("selectPwInt() 호출");
		Map<String, Object> args = new HashMap<>();
		args.put("memberPw", memberPw);
		args.put("memberNumber", memberNumber);
		return sqlSession.selectOne(NAMESPACE + ".selectPwInt", args);
	}
	
	@Override
	public String selectNicknameByGrade(int memberGrade) {
		logger.info("selectNicknameByGrade() 호출");
		return sqlSession.selectOne(NAMESPACE + ".selectNicknameByGrade", memberGrade);
	}

	@Override
	public int selectByNumberOnBoard(int memberNumber) {
		logger.info("selectByNumberOnBoard() 호출");
		return sqlSession.selectOne(NAMESPACE + ".selectByNumberOnBoard", memberNumber);
	}

	@Override
	public int selectByNumberOnCmRp(int memberNumber) {
		logger.info("selectByNumberOnCmRp() 호출");
		return sqlSession.selectOne(NAMESPACE + ".selectByNumberOnCmRp", memberNumber);
	}
	
	@Override
	public int selectMemberCount() {
		logger.info("selectMemberCount() 호출");
		return sqlSession.selectOne(NAMESPACE + ".selectMemberCount");
	}
	
	@Override
	public int updateGrade(int memberNumber) {
		logger.info("MEMBER updateGrade() 호출");
		return sqlSession.update(NAMESPACE + ".updateGrade", memberNumber);
	}
	
	@Override
	public int updatePremium(int memberNumber) {
		logger.info("updatePremium() 호출");
		return sqlSession.update(NAMESPACE + ".updatePremium", memberNumber);
	}
	
	@Override
	public int delete(int memberNumber) {
		logger.info("delete() 호출");
		return sqlSession.delete(NAMESPACE + ".delete", memberNumber);
	}
	
	@Override
	public int updatePicture(int memberNumber) {
		logger.info("updatePicture() 호출");
		return sqlSession.update(NAMESPACE + ".updatePicture", memberNumber);
	}
	
	@Override
	public int updatePictureDefault(int memberNumber) {
		logger.info("updatePictureDefault() 호출");
		return sqlSession.update(NAMESPACE + ".updatePictureDefault", memberNumber);
	}
	
	@Override
	public int selectNumberByNickname(String memberNickname) {
		logger.info("selectNumberByNickname() 호출");
		return sqlSession.selectOne(NAMESPACE + ".selectNumberByNickname", memberNickname);
	}
	
	@Override
	public int selectNumberById(String memberId) {
		logger.info("selectNumberById() 호출");
		return sqlSession.selectOne(NAMESPACE + ".selectNumberById", memberId);
	}
} // end MemberDAOImple
