package com.jane.board.domain;

import com.jane.board.shared.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@ToString(exclude = "writer")
@Getter
@RequiredArgsConstructor
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    private String title;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;

    @Builder
    public Board(Long bno, String title, String content, Member writer) {
        this.bno = bno;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
