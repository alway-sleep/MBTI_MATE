package com.cafe.mbti.service;

import java.util.List;

import com.cafe.mbti.domain.BoardVO;
import com.cafe.mbti.domain.MemberVO;

public interface MemberService {
	// 회원가입
	int create(MemberVO vo);
	// 회원가입 유효성검사
	int isDuplicatedId(String memberId);
	int isDuplicatedNickname(String memberNickname);
	int isDuplicatedRRN(String memberRRN);
	int isDuplicatedPhone(String memberPhone);
	int isDuplicatedEmail(String memberEmail);
	// 로그인
	int login(String memberId, String memberPw);
	int readNumberById(String memberId);
	// 아이디/비밀번호 찾기
	String readId(String memberName, String memberRRN, String memberPhone);
	int readPw(String memberId, String memberName, String memberRRN, String memberPhone, String memberEmail);
	int resetPw(String memberPwReset, String memberId, String memberName, String memberRRN, String memberPhone, String memberEmail);
	// 마이페이지
	MemberVO read(int memberNumber);
	// 회원정보 수정
	int update(String memberNickname, String memberPhone, String memberEmail, String memberRegion, String memberMBTI, int memberNumber);
	int updatePw(String memberPwNew, int memberNumber, String memberPw);	
	// 회원정보 수정 유효성검사
	String readNicknameStr(String memberNickname);
	int readNicknameInt(String memberNickname, int memberNumber);
	String readPhoneStr(String memberPhone);
	int readPhoneInt(String memberPhone, int memberNumber);
	String readEmailStr(String memberEmail);
	int readEmailInt(String memberEmail, int memberNumber);
	String readPwStr(int memberNumber);
	int readPwInt(String memberPw, int memberNumber);
	// 카페 정보 : 매니저 닉네임
	String readNicknameByGrade(int memberGrade);
	// 내가 쓴 게시글의 수
	int readByNumberOnBoard(int memberNumber);
	// 내가 쓴 댓글의 수
	int readByNumberOnCmRp(int memberNumber);
	// 전체 회원의 수
	int readMemberCount();
	// 회원 등업
	int updateGrade(int memberNumber);
	// 서비스 결제
	int updatePremium(int memberNumber);
	// 회원탈퇴
	int delete(int memberNumber) throws Exception;
	// 회원사진
	int updatePicture(MemberVO memberVO);
	// 회원 게시글의 수
	int readCountByMember(int memberNumber);
	// 회원 추천 게시글의 수
	int readCountByLike(int memberNumber);
	// 회원 게시글 정렬 후 출력
	List<BoardVO> readAllByMember(int memberNumber, int boardStart, int boardEnd);
	// 회원 추천 게시글 정렬 후 출력
	List<BoardVO> readAllByLike(int memberNumber, int boardStart, int boardEnd);
}
