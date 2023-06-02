package com.cafe.mbti.service;

import java.util.List;

import com.cafe.mbti.domain.BoardVO;
import com.cafe.mbti.util.PageCriteria;

// CRUD(Create, Read, Update, Delete)
public interface BoardService {
	int create(BoardVO boardVO);
	int readBoardCount();
	List<BoardVO> readAll(PageCriteria pageCriteria);
	List<BoardVO> readBoard(PageCriteria pageCriteria);
	BoardVO read(int boardNumber, int memberNumber);
	int update(int boardSection, int boardList, String boardName, String boardTitle, String boardContent, int boardNumber);
	int updateType(int boardType, int boardNumber);
	int delete(int boardNumber) throws Exception;
}
