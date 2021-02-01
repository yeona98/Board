package com.jane.board.service;

import com.jane.board.dto.BoardDto;

public interface BoardService {
    // 게시물 등록
    Long register(BoardDto dto);
}
