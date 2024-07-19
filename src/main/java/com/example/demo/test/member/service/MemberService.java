package com.example.demo.test.member.service;

import com.example.demo.test.exception.service.DataNotFoundException;
import com.example.demo.test.member.dto.MemberDTO;
import com.example.demo.test.member.entity.Member;
import com.example.demo.test.member.repository.MemberRepository;
import com.example.demo.test.member.role.MemberRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    public void signup(MemberDTO memberDTO) {

        if (memberDTO.getUsername().startsWith("admin")) {
            Member member = Member.builder()
                    .username(memberDTO.getUsername())
                    .password(passwordEncoder.encode(memberDTO.getPassword()))
                    .name(memberDTO.getName())
                    .email(memberDTO.getEmail())
                    .addr1(memberDTO.getAddr1())
                    .addr2(memberDTO.getAddr2())
                    .ph(memberDTO.getPh())
                    .role(MemberRole.ADMIN)
                    .regDt(LocalDate.now())
                    .build();
            memberRepository.save(member);
        } else {
            Member member = Member.builder()
                    .username(memberDTO.getUsername())
                    .password(passwordEncoder.encode(memberDTO.getPassword()))
                    .name(memberDTO.getName())
                    .email(memberDTO.getEmail())
                    .addr1(memberDTO.getAddr1())
                    .addr2(memberDTO.getAddr2())
                    .ph(memberDTO.getPh())
                    .role(MemberRole.MEMBER)
                    .regDt(LocalDate.now())
                    .build();
            memberRepository.save(member);
        }
    }

    public Member getByUsernameAndPassword(String username, String password) {
        return memberRepository.findByUsernameAndPassword(username, password);
    }

    public Member getMemberByUsername(String username) throws DataNotFoundException {
        Optional<Member> optionalMember = memberRepository.findByUsername(username);
        if (optionalMember.isPresent()) {
            return optionalMember.get();
        } else {
            throw new DataNotFoundException("사용자를 찾을 수 없습니다.");
        }
    }
}
