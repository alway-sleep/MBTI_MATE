package com.cafe.mbti.service;

import java.util.List;

import com.cafe.mbti.domain.ReplyVO;
import com.cafe.mbti.util.CmRpPageCriteria;

public interface ReplyService {
	int create(ReplyVO replyVO);
	List<ReplyVO> readAll(CmRpPageCriteria cmRpPageCriteria);
	int readCountOnComments(int commentsNumber);
	int update(String replyContent, int replyNumber);
	int delete(int replyNumber);
}
