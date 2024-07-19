package com.example.demo.test.member.controller;

import com.example.demo.test.member.dto.MemberDTO;
import com.example.demo.test.member.entity.Member;
import com.example.demo.test.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("signup")
    public String signup() {
        return "member/signupForm";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute MemberDTO memberDTO,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "member/signupForm";
        }

        memberService.signup(memberDTO);
        return "redirect:/";
    }

    @GetMapping("login")
    public String login() {
        return "member/loginForm";
    }

    @PostMapping("login")
    public String login(HttpSession session,
                        @ModelAttribute MemberDTO memberDTO) {

        Member member = memberService.getByUsernameAndPassword(memberDTO.getUsername(), memberDTO.getPassword());

        if (member == null) {
            return "redirect:/";
        }

        if (!memberDTO.getPassword().equals(member.getPassword())) {
            return "member/loginForm";
        }

        session.setAttribute("username", member.getUsername());
        session.setAttribute("member", member);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/member/login";
    }
}
