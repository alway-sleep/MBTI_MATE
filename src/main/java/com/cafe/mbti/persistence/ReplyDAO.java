package com.cafe.mbti.persistence;

import java.util.List;

import com.cafe.mbti.domain.ReplyVO;

public interface ReplyDAO {
	// 답글 등록
	int insert(ReplyVO vo);
	// 답글 정렬 후 출력
	List<ReplyVO> selectAll(int commentsNumber);
	// 답글 등록/삭제 후 카운트
	int selectReplyCount(int commentsNumber);
	// 답글 수정
	int update(String replyContent, int replyNumber);
	// 답글 삭제
	int delete(int replyNumber);
	// 댓글 삭제 시 댓글 번호 초기화
	int updateDeleteOnComments(int commentsNumber);
	// 회원탈퇴
	int deleteOnMember(int memberNumber);
}
