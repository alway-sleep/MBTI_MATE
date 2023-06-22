package com.cafe.mbti.persistence;

import java.util.List;

import com.cafe.mbti.domain.BoardVO;
import com.cafe.mbti.util.BoardPageCriteria;

public interface BoardDAO {
	// 게시글 작성
	int insert(BoardVO boardVO);
	// 전체 게시글의 수
	int selectCountOnBoard();
	// 해당 게시판 게시글의 수
	int selectCountOnBoard2(BoardPageCriteria boardPageCriteria);
	// 게시글 제목으로 검색된 게시글의 수
	int selectCountByBoardTitle(BoardPageCriteria boardPageCriteria);
	// 게시글 내용으로 검색된 게시글의 수
	int selectCountByBoardContent(BoardPageCriteria boardPageCriteria);
	// 게시글 작성자로 검색된 게시글의 수
	int selectCountByNicknameOnBoard(BoardPageCriteria boardPageCriteria);
	// 댓글 내용으로 검색된 게시글의 수
	int selectCountByCmRpContent(BoardPageCriteria boardPageCriteria);
	// 댓글 작성자로 검색된 게시글의 수
	int selectCountByNicknameOnCmRp(BoardPageCriteria boardPageCriteria);
	// 게시글 제목으로 검색된 게시글의 수
	int selectCountByBoardTitle2(BoardPageCriteria boardPageCriteria);
	// 게시글 내용으로 검색된 게시글의 수
	int selectCountByBoardContent2(BoardPageCriteria boardPageCriteria);
	// 게시글 작성자로 검색된 게시글의 수
	int selectCountByNicknameOnBoard2(BoardPageCriteria boardPageCriteria);
	// 댓글 내용으로 검색된 게시글의 수
	int selectCountByCmRpContent2(BoardPageCriteria boardPageCriteria);
	// 댓글 작성자로 검색된 게시글의 수
	int selectCountByNicknameOnCmRp2(BoardPageCriteria boardPageCriteria);
	// 전체 게시글 정렬 후 출력
	List<BoardVO> selectAll(BoardPageCriteria boardPageCriteria);
	// 게시판 정렬 후 출력
	List<BoardVO> selectBoard(BoardPageCriteria boardPageCriteria);
	// 게시글 출력
	BoardVO select(int boardNumber, int memberNumber);
	// 검색 조건 : 게시글 제목
	List<BoardVO> selectByBoardTitle(BoardPageCriteria boardPageCriteria);
	// 검색 조건 : 게시글 내용
	List<BoardVO> selectByBoardContent(BoardPageCriteria boardPageCriteria);
	// 검색 조건 : 게시글 작성자
	List<BoardVO> selectByNicknameOnBoard(BoardPageCriteria boardPageCriteria);
	// 검색 조건 : 댓글 내용
	List<BoardVO> selectByCmRpContent(BoardPageCriteria boardPageCriteria);
	// 검색 조건 : 댓글 작성자
	List<BoardVO> selectByNicknameOnCmRp(BoardPageCriteria boardPageCriteria);
	// 검색 조건 : 게시글 제목2
	List<BoardVO> selectByBoardTitle2(BoardPageCriteria boardPageCriteria);
	// 검색 조건 : 게시글 내용2
	List<BoardVO> selectByBoardContent2(BoardPageCriteria boardPageCriteria);
	// 검색 조건 : 게시글 작성자2
	List<BoardVO> selectByNicknameOnBoard2(BoardPageCriteria boardPageCriteria);
	// 검색 조건 : 댓글 내용2
	List<BoardVO> selectByCmRpContent2(BoardPageCriteria boardPageCriteria);
	// 검색 조건 : 댓글 작성자2
	List<BoardVO> selectByNicknameOnCmRp2(BoardPageCriteria boardPageCriteria);
	// 게시글 조회수
	int selectBoardViews(int boardNumber);
	// 게시글 수정
	int update(int boardSection, int boardList, String boardName, String boardTitle, String boardContent, String boardFiles, int boardNumber);
	// 인기글 업데이트
	int updateType(int boardType, int boardNumber);
	// 게시글 삭제
	int delete(int boardNumber);
	// 회원탈퇴
	int deleteOnMember(int memberNumber);
}
