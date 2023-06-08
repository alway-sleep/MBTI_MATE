package com.cafe.mbti.persistence;

import java.util.List;

import com.cafe.mbti.domain.BoardVO;
import com.cafe.mbti.util.PageCriteria;

public interface BoardDAO {
	// 게시글 작성
	int insert(BoardVO boardVO);
	// 전체 게시글의 수
	int selectBoardCount();
	// 전체 게시글 정렬 후 출력
	List<BoardVO> selectAll(PageCriteria pageCriteria);
	// 게시판 정렬 후 출력
	List<BoardVO> selectBoard(PageCriteria pageCriteria);
	// 게시글 출력
	BoardVO select(int boardNumber, int memberNumber);
	// 검색 조건 : 게시글 제목
	List<BoardVO> selectByBoardTitle(PageCriteria pageCriteria);
	// 검색 조건 : 게시글 내용
	List<BoardVO> selectByBoardContent(PageCriteria pageCriteria);
	// 검색 조건 : 게시글 작성자
	List<BoardVO> selectByNicknameOnBoard(PageCriteria pageCriteria);
	// 검색 조건 : 댓글 내용
	List<BoardVO> selectByCmRpContent(PageCriteria pageCriteria);
	// 검색 조건 : 댓글 작성자
	List<BoardVO> selectByNicknameOnCmRp(PageCriteria pageCriteria);
	// 게시글 수정
	int update(int boardSection, int boardList, String boardName, String boardTitle, String boardContent, int boardNumber);
	// 인기글 업데이트
	int updateType(int boardType, int boardNumber);
	// 게시글 삭제
	int delete(int boardNumber);
	// 회원탈퇴
	int deleteOnMember(int memberNumber);
}
