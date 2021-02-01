package com.jane.board.service.impl;

import com.jane.board.domain.Board;
import com.jane.board.dto.BoardDto;
import com.jane.board.repository.BoardRepository;
import com.jane.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

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
}
