package com.jane.board.service.impl;

import com.jane.board.domain.Board;
import com.jane.board.dto.BoardDto;
import com.jane.board.repository.BoardRepository;
import com.jane.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceImplTest {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {
        //given
        String title = "test title";
        String content = "test content";
        String writerEmail = "user55@naver.com"; // DB에 존재하는 회원 이메일

        BoardDto dto = BoardDto.builder()
                            .title(title)
                            .content(content)
                            .writerEmail(writerEmail)
                            .build();

        //when
        Long bno = boardService.register(dto);
        Board board = boardRepository.findById(bno).get();

        //then
        assertNotNull(board);
        assertEquals(title, board.getTitle());
        assertEquals(content, board.getContent());
        assertEquals(writerEmail, board.getWriter().getEmail());
    }
}