package com.example.demo.test.member.entity;

import com.example.demo.test.board.entity.Board;
import com.example.demo.test.member.role.MemberRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String ph;

    @NotNull
    private String addr1;

    private String addr2;

    @NotNull
    private LocalDate regDt;

    private LocalDate udpDt;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @OneToMany(mappedBy = "author")
    @ToString.Exclude
    private List<Board> boardList = new ArrayList<>();
}
