package com.example.demo.test.board.controller;

import com.example.demo.test.board.dto.BoardDto;
import com.example.demo.test.board.entity.Board;
import com.example.demo.test.board.service.BoardService;
import com.example.demo.test.error.exception.DataNotFoundException;
import com.example.demo.test.member.entity.Member;
import com.example.demo.test.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    private final MemberService memberService;

    private static final Logger log = LoggerFactory.getLogger(BoardController.class);

    @GetMapping("/list")
    public String list(Model model) {

        List<Board> boardList = boardService.getList();

        model.addAttribute("boardList", boardList);

        return "board/list";
    }

    @GetMapping("/insert")
    public String insertForm() {
        return "board/insertForm";
    }

    @PostMapping("/insert")
    public String insert(@Valid @ModelAttribute BoardDto boardDto,
                         BindingResult bindingResult,
                         Principal principal) throws DataNotFoundException {

        log.debug("POST /insert called with BoardDto: {}", boardDto);

        if (bindingResult.hasErrors()) {
            return "board/insertForm";
        }

        Member member = memberService.getMemberByUsername(principal.getName());

        boardService.insert(boardDto, member);

        return "redirect:/board/list";

    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model) throws DataNotFoundException {

        Board board = boardService.getBoard(id);

        Member member = memberService.getMemberByUsername(board.getAuthor().getUsername());

        boardService.increaseHit(board);

        model.addAttribute("board", board);
        model.addAttribute("member", member);

        return "board/detail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) throws DataNotFoundException {

        Board board = boardService.getBoard(id);

        Member member = memberService.getMemberByUsername(board.getAuthor().getUsername());

        model.addAttribute("member", member);

        model.addAttribute("board", board);

        return "board/updateForm";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid @ModelAttribute BoardDto boardDto,
                         BindingResult bindingResult,
                         Principal principal) throws DataNotFoundException {
        if (bindingResult.hasErrors()) {
            return "board/updateForm";
        }

        Board board = boardService.getBoard(id);

        Member member = memberService.getMemberByUsername(principal.getName());

        boardService.update(board, boardDto, member);

        return "redirect:/board/" + id;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        Board board = boardService.getBoard(id);

        boardService.delete(board);

        return "redirect:/board/list";
    }
}
