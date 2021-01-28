package com.jane.board.domain;

import com.jane.board.shared.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@RequiredArgsConstructor
@Entity
public class Member extends BaseTimeEntity {

    @Id
    private String email;
    private String password;
    private String name;

    @Builder
    public Member(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
