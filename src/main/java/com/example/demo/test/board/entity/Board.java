package com.example.demo.test.board.entity;

import com.example.demo.test.base.entity.BaseEntity;
import com.example.demo.test.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String content;

    private String deleteYn;

    private int hit = 0;

    @ManyToOne
    @ToString.Exclude
    private Member author;

    private LocalDateTime regDt;

    @ManyToOne
    @ToString.Exclude
    private Member upd;

    private LocalDateTime updDt;

}
