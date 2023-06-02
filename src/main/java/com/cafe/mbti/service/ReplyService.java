package com.cafe.mbti.service;

import java.util.List;

import com.cafe.mbti.domain.ReplyVO;

public interface ReplyService {
	int create(ReplyVO replyVO);
	List<ReplyVO> readAll(int commentsNumber);
	int readReplyCount(int commentsNumber);
	int update(String replyContent, int replyNumber);
	int delete(int replyNumber);
}
