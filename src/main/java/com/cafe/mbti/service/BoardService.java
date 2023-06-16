package com.cafe.mbti.service;

import java.util.List;

import com.cafe.mbti.domain.BoardVO;
import com.cafe.mbti.util.BoardPageCriteria;

// CRUD(Create, Read, Update, Delete)
public interface BoardService {
	// 게시글 작성
	int create(BoardVO boardVO);
	// 전체 게시글의 수
	int readCountOnBoard();
	// 해당 게시판의 게시글의 수
	int readCountOnBoard2(BoardPageCriteria boardPageCriteria);
	// 게시글 제목으로 검색된 게시글의 수
	int readCountByBoardTitle(BoardPageCriteria boardPageCriteria);
	// 게시글 내용으로 검색된 게시글의 수
	int readCountByBoardContent(BoardPageCriteria boardPageCriteria);
	// 게시글 작성자로 검색된 게시글의 수
	int readCountByNicknameOnBoard(BoardPageCriteria boardPageCriteria);
	// 댓글 내용으로 검색된 게시글의 수
	int readCountByCmRpContent(BoardPageCriteria boardPageCriteria);
	// 댓글 작성자로 검색된 게시글의 수
	int readCountByNicknameOnCmRp(BoardPageCriteria boardPageCriteria);
	// 게시글 제목으로 검색된 게시글의 수
	int readCountByBoardTitle2(BoardPageCriteria boardPageCriteria);
	// 게시글 내용으로 검색된 게시글의 수
	int readCountByBoardContent2(BoardPageCriteria boardPageCriteria);
	// 게시글 작성자로 검색된 게시글의 수
	int readCountByNicknameOnBoard2(BoardPageCriteria boardPageCriteria);
	// 댓글 내용으로 검색된 게시글의 수
	int readCountByCmRpContent2(BoardPageCriteria boardPageCriteria);
	// 댓글 작성자로 검색된 게시글의 수
	int readCountByNicknameOnCmRp2(BoardPageCriteria boardPageCriteria);
	// 전체 게시글 정렬 후 출력
	List<BoardVO> readAll(BoardPageCriteria boardPageCriteria);
	// 게시판 정렬 후 출력
	List<BoardVO> readBoard(BoardPageCriteria boardPageCriteria);
	// 게시글 출력
	BoardVO read(int boardNumber, int memberNumber);
	// 검색 조건 : 게시글 제목
	List<BoardVO> readByBoardTitle(BoardPageCriteria boardPageCriteria);
	// 검색 조건 : 게시글 내용
	List<BoardVO> readByBoardContent(BoardPageCriteria boardPageCriteria);
	// 검색 조건 : 게시글 작성자
	List<BoardVO> readByNicknameOnBoard(BoardPageCriteria boardPageCriteria);
	// 검색 조건 : 댓글 내용
	List<BoardVO> readByCmRpContent(BoardPageCriteria boardPageCriteria);
	// 검색 조건 : 댓글 작성자
	List<BoardVO> readByNicknameOnCmRp(BoardPageCriteria boardPageCriteria);
	// 검색 조건 : 게시글 제목2
	List<BoardVO> readByBoardTitle2(BoardPageCriteria boardPageCriteria);
	// 검색 조건 : 게시글 내용2
	List<BoardVO> readByBoardContent2(BoardPageCriteria boardPageCriteria);
	// 검색 조건 : 게시글 작성자2
	List<BoardVO> readByNicknameOnBoard2(BoardPageCriteria boardPageCriteria);
	// 검색 조건 : 댓글 내용2
	List<BoardVO> readByCmRpContent2(BoardPageCriteria boardPageCriteria);
	// 검색 조건 : 댓글 작성자2
	List<BoardVO> readByNicknameOnCmRp2(BoardPageCriteria boardPageCriteria);
	// 게시글 조회수
	int readBoardViews(int boardNumber);
	// 게시글 수정
	int update(int boardSection, int boardList, String boardName, String boardTitle, String boardContent, int boardNumber);
	// 인기글 업데이트
	int updateType(int boardType, int boardNumber);
	// 회원탈퇴
	int delete(int boardNumber) throws Exception;
}
