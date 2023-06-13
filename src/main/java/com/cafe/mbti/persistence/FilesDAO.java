package com.cafe.mbti.persistence;

import com.cafe.mbti.domain.FilesVO;

public interface FilesDAO {
	int insert(FilesVO filesVO);
	int deleteOnBoard(int boardNumber);
}
