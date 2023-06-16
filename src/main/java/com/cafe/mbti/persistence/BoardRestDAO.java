package com.cafe.mbti.persistence;

import com.cafe.mbti.domain.BoardRestVO;

public interface BoardRestDAO {
	// 게시글 추천 등록
	int insertBoardlike(BoardRestVO boardRestVO);
	// 게시글 추천 등록/취소 후 카운트
	int selectBoardlikeCount(int boardNumber);
	// 게시글 추천 취소
	int delete(BoardRestVO boardRestVO);
	// 게시글 삭제 시 해당 게시글 추천 삭제
	int deleteOnBoard(int boardNumber);
	// 회원탈퇴 시 해당 회원 게시글 추천 삭제
	int deleteOnMember(int memberNumber);
	
	// 게시글 조회수
	int insertBoardview(BoardRestVO boardRestVO);
}
