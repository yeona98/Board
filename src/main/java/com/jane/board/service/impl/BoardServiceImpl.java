package com.jane.board.service.impl;

import com.jane.board.domain.Board;
import com.jane.board.domain.Member;
import com.jane.board.dto.BoardDto;
import com.jane.board.dto.PageMapper;
import com.jane.board.dto.PageRequestDto;
import com.jane.board.repository.BoardRepository;
import com.jane.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Log4j2
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public Long register(BoardDto dto) {
        Board board = dto.toEntity();
        boardRepository.save(board);
        return board.getBno();
    }

    @Override
    public PageMapper<BoardDto, Object[]> getList(PageRequestDto pageRequestDto) {
        Function<Object[], BoardDto> fn = (en -> BoardDto.of((Board) en[0], (Member) en[1], (Long) en[2]));
        Page<Object[]> result = boardRepository.getBoardWithReplyCount(
                pageRequestDto.getPageable(Sort.by("bno").descending())
        );
        return new PageMapper<>(result, fn);
    }


}
