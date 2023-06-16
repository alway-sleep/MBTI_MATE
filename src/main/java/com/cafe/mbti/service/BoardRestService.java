package com.cafe.mbti.service;

import com.cafe.mbti.domain.BoardRestVO;

public interface BoardRestService {
	int createBoardlike(BoardRestVO boardRestVO);
	int readBoardlikeCount(int boardNumber);
	int delete(BoardRestVO boardRestVO);
	int deleteOnBoard(int boardNumber);
	int createBoardview(BoardRestVO boardRestVO);
}
