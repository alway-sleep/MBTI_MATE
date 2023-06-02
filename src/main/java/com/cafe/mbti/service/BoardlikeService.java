package com.cafe.mbti.service;

import com.cafe.mbti.domain.BoardlikeVO;

public interface BoardlikeService {
	int create(BoardlikeVO boardlikeVO);
	int readBoardlikeCount(int boardNumber);
	int delete(BoardlikeVO boardlikeVO);
	int deleteOnBoard(int boardNumber);
}
