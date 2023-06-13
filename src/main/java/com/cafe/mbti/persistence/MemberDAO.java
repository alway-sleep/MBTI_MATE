package com.cafe.mbti.persistence;

import com.cafe.mbti.domain.MemberVO;

public interface MemberDAO {
	// 회원가입
	int insert(MemberVO memberVO);
	// 회원가입 유효성검사
	int isDuplicatedId(String memberId);
	int isDuplicatedNickname(String memberNickname);
	int isDuplicatedRRN(String memberRRN);
	int isDuplicatedPhone(String memberPhone);
	int isDuplicatedEmail(String memberEmail);
	// 로그인
	int login(String memberId, String memberPw);
	// 아이디/비밀번호 찾기
	String selectId(String memberName, String memberRRN, String memberPhone);
	int selectPw(String memberId, String memberName, String memberRRN, String memberPhone, String memberEmail);
	int resetPw(String memberPwReset, String memberId, String memberName, String memberRRN, String memberPhone, String memberEmail);
	// 마이페이지
	MemberVO select(int memberNumber);
	// 회원정보 수정
	int update(String memberNickname, String memberPhone, String memberEmail, String memberRegion, String memberMBTI, int memberNumber);
	int updatePw(String memberPwNew, int memberNumber, String memberPw);	
	// 회원정보 수정 유효성검사
	String selectNicknameStr(String memberNickname);
	int selectNicknameInt(String memberNickname, int memberNumber);
	String selectPhoneStr(String memberPhone);
	int selectPhoneInt(String memberPhone, int memberNumber);
	String selectEmailStr(String memberEmail);
	int selectEmailInt(String memberEmail, int memberNumber);
	String selectPwStr(int memberNumber);
	int selectPwInt(String memberPw, int memberNumber);
	// 카페 정보 : 매니저 닉네임
	String selectNicknameByGrade(int memberGrade);
	// 내가 쓴 게시글의 수
	int selectByNumberOnBoard(int memberNumber);
	// 내가 쓴 댓글의 수
	int selectByNumberOnCmRp(int memberNumber);
	// 전체 회원의 수
	int selectMemberCount();
	// 회원 등업
	int updateGrade(int memberNumber);
	// 서비스 결제
	int updatePremium(int memberNumber);
	// 회원탈퇴
	int delete(int memberNumber);
	// 회원사진
	int updatePicture(MemberVO memberVO);
	//	
	int selectNumberByNickname(String memberNickname);
	int selectNumberById(String memberId);
}
