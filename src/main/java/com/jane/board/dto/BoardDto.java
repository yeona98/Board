package com.jane.board.dto;

import com.jane.board.domain.Board;
import com.jane.board.domain.Member;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardDto {
    private Long bno;
    private String title;
    private String content;
    private String writerEmail;
    private String writerName;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private int replyCount;

    public Board toEntity() {
        Member member = Member.builder()
                                .email(this.getWriterEmail())
                                .build();
        Board board = Board.builder()
                            .bno(this.getBno())
                            .title(this.getTitle())
                            .content(this.getContent())
                            .writer(member)
                            .build();
        return board;
    }

    public static BoardDto of(Board board, Member member, Long replyCount) {
        BoardDto boardDto = BoardDto.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue()) // long -> int
                .build();
        return boardDto;
    }
}
