package com.cafe.mbti.persistence;

import java.util.List;

import com.cafe.mbti.domain.CommentsVO;

public interface CommentsDAO {
	// 댓글 등록
	int insert(CommentsVO vo);
	// 댓글 정렬 후 출력
	List<CommentsVO> selectAll(int boardNumber);
	// 댓글 등록/삭제 후 카운트
	int selectCommentsCount(int boardNumber);
	// 댓글 수정
	int update(String commentsContent, int commentsNumber);
	// 댓글 삭제
	int delete(int commentsNumber);
	// 게시글 삭제 시 게시글 번호 초기화
	int updateDeleteOnBoard(int boardNumber);
	// 회원탈퇴
	int deleteOnMember(int memberNumber);
}
