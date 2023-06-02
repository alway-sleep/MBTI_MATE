package com.cafe.mbti.persistence;

import java.util.List;

import com.cafe.mbti.domain.ReplyVO;

public interface ReplyDAO {
	// 대댓글 등록
	int insert(ReplyVO vo);
	// 대댓글 정렬 후 출력
	List<ReplyVO> selectAll(int commentsNumber);
	// 대댓글 수정
	int update(String replyContent, int replyNumber);
	// 대댓글 삭제
	int delete(int replyNumber);
	// 회원탈퇴
	int deleteOnMember(int memberNumber);
}
