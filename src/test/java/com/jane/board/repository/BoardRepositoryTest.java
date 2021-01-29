package com.jane.board.repository;

import com.jane.board.domain.Board;
import com.jane.board.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    @Test
    public void board_등록() { // 실행 전 memberDummies_등록() 실행해서 DB table 에 데이터 생성 --> 이후에는 @BeforeEach @AfterEach 활용해서 Test 목적대로 해 보기
        //given
        String title = "Title";
        String content = "Content";
        String requestMemberEmail = "user1@naver.com";
        Member member = memberRepository.findById(requestMemberEmail).get();

        //when
        Board board = Board.builder()
                .title("Title")
                .content("Content")
                .writer(member)
                .build();

        boardRepository.save(board);

        //then
        assertNotNull(board);
        assertEquals(board.getTitle(),title);
        assertEquals(board.getContent(),content);
        assertEquals(board.getWriter(), member);
    }

    @Test
    public void boardDummies_등록() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder().email("user" + i + "@naver.com").build();

            Board board = Board.builder()
                                .title("Title" + i)
                                .content("Content" + i)
                                .writer(member)
                                .build();

            boardRepository.save(board);
        });
    }
}