package com.jane.board.repository;

import com.jane.board.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {
    
    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    @Test
    public void member_등록() {
        //given
        String email = "jane@naver.com";
        String password = "1234";
        String name = "jane";

        //when
        Member member = memberRepository.save(Member.builder()
                                    .email(email)
                                    .password(password)
                                    .name(name)
                                    .build());

        //when
        assertNotNull(member);
        assertEquals(member.getEmail(), email);
        assertEquals(member.getPassword(), password);
        assertEquals(member.getName(), name);
    }

    @Test
    public void memberDummies_등록() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                                    .email("user" + i + "@naver.com")
                                    .password("1234")
                                    .name("USER" + i)
                                    .build();

            memberRepository.save(member);
        });
    }
}