package com.jane.board.repository;

import com.jane.board.domain.Board;
import com.jane.board.domain.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void replyDummies_등록() { // 실행 전 boardDummies_등록() 실행해서 DB table 에 데이터 생성 --> 이후에는 @BeforeEach @AfterEach 활용해서 Test 목적대로 해 보기

        IntStream.rangeClosed(1, 300).forEach(i -> {
            long bno = (long)(Math.random() * 100) + 1;
            Board board = Board.builder().bno(bno).build();
            Reply reply = Reply.builder()
                    .text("Reply" + i)
                    .board(board)
                    .replier("guest")
                    .build();

            replyRepository.save(reply);
        });
    }
}