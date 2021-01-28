package com.jane.board.domain;

import com.jane.board.shared.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@RequiredArgsConstructor
@Entity
public class Reply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    private String text;
    private String replier;

    @Builder
    public Reply(Long rno, String text, String replyer) {
        this.rno = rno;
        this.text = text;
        this.replier = replyer;
    }
}