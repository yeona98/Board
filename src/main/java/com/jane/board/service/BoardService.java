package com.jane.board.service;

import com.jane.board.dto.BoardDto;
import com.jane.board.dto.PageMapper;
import com.jane.board.dto.PageRequestDto;

public interface BoardService {
    // 게시물 등록
    Long register(BoardDto dto);
    // 목록 처리
    PageMapper<BoardDto, Object[]> getList(PageRequestDto pageRequestDto);
}
