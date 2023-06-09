package com.cafe.mbti.service;

import java.util.List;

import com.cafe.mbti.domain.BoardVO;
import com.cafe.mbti.util.PageCriteria;

// CRUD(Create, Read, Update, Delete)
public interface BoardService {
	// 게시글 작성
	int create(BoardVO boardVO);
	// 전체 게시글의 수
	int readCountOnBoard();
	// 해당 게시판의 게시글의 수
	int readCountByBoardName(PageCriteria pageCriteria);
	// 게시글 제목으로 검색된 게시글의 수
	int readCountByBoardTitle(PageCriteria pageCriteria);
	// 게시글 내용으로 검색된 게시글의 수
	int readCountByBoardContent(PageCriteria pageCriteria);
	// 게시글 작성자로 검색된 게시글의 수
	int readCountByNicknameOnBoard(PageCriteria pageCriteria);
	// 댓글 내용으로 검색된 게시글의 수
	int readCountByCmRpContent(PageCriteria pageCriteria);
	// 댓글 작성자로 검색된 게시글의 수
	int readCountByNicknameOnCmRp(PageCriteria pageCriteria);
	// 전체 게시글 정렬 후 출력
	List<BoardVO> readAll(PageCriteria pageCriteria);
	// 게시판 정렬 후 출력
	List<BoardVO> readBoard(PageCriteria pageCriteria);
	// 게시글 출력
	BoardVO read(int boardNumber, int memberNumber);
	// 검색 조건 : 게시글 제목
	List<BoardVO> readByBoardTitle(PageCriteria pageCriteria);
	// 검색 조건 : 게시글 내용
	List<BoardVO> readByBoardContent(PageCriteria pageCriteria);
	// 검색 조건 : 게시글 작성자
	List<BoardVO> readByNicknameOnBoard(PageCriteria pageCriteria);
	// 검색 조건 : 댓글 내용
	List<BoardVO> readByCmRpContent(PageCriteria pageCriteria);
	// 검색 조건 : 댓글 작성자
	List<BoardVO> readByNicknameOnCmRp(PageCriteria pageCriteria);
	// 검색 조건 : 게시글 제목
	List<BoardVO> readByBoardTitle2(PageCriteria pageCriteria);
	// 검색 조건 : 게시글 내용
	List<BoardVO> readByBoardContent2(PageCriteria pageCriteria);
	// 검색 조건 : 게시글 작성자
	List<BoardVO> readByNicknameOnBoard2(PageCriteria pageCriteria);
	// 검색 조건 : 댓글 내용
	List<BoardVO> readByCmRpContent2(PageCriteria pageCriteria);
	// 검색 조건 : 댓글 작성자
	List<BoardVO> readByNicknameOnCmRp2(PageCriteria pageCriteria);
	// 게시글 수정
	int update(int boardSection, int boardList, String boardName, String boardTitle, String boardContent, int boardNumber);
	// 인기글 업데이트
	int updateType(int boardType, int boardNumber);
	// 회원탈퇴
	int delete(int boardNumber) throws Exception;
}
