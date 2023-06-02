package com.cafe.mbti.service;

import java.util.List;

import com.cafe.mbti.domain.CommentsVO;

public interface CommentsService {
	// 댓글 등록
	int create(CommentsVO commentsVO);
	// 댓글 정렬 후 출력
	List<CommentsVO> readAll(int boardNumber);
	// 댓글 등록/삭제 후 카운트
	int readCommentsCount(int boardNumber);
	// 댓글 수정
	int update(String commentsContent, int commentsNumber);
	// 댓글 삭제
	int delete(int commentsNumber);
}
