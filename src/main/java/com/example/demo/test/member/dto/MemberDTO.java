package com.example.demo.test.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {

    private String username;

    private String password;

    private String passwordCnf;

    private String name;

    private String email;

    private String ph;

    private String addr1;

    private String addr2;
}
