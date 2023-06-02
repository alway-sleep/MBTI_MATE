package com.cafe.mbti.persistence;

import com.cafe.mbti.domain.BoardlikeVO;

public interface BoardlikeDAO {
	// 게시글 추천 등록
	int insert(BoardlikeVO boardlikeVO);
	// 게시글 추천 등록/취소 후 카운트
	int selectBoardlikeCount(int boardNumber);
	// 게시글 추천 취소
	int delete(BoardlikeVO boardlikeVO);
	// 게시글 삭제 시 해당 게시글 추천 삭제
	int deleteOnBoard(int boardNumber);
	// 회원탈퇴 시 해당 회원 게시글 추천 삭제
	int deleteOnMember(int memberNumber);
}
