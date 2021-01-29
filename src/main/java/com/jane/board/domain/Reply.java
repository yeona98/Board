package com.jane.board.domain;

import com.jane.board.shared.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@ToString(exclude = "board")
@Getter
@RequiredArgsConstructor
@Entity
public class Reply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    private String text;
    private String replier;
    @ManyToOne
    private Board board;

    @Builder
    public Reply(Long rno, String text, String replier, Board board) {
        this.rno = rno;
        this.text = text;
        this.replier = replier;
        this.board = board;
    }
}
