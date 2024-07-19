package com.example.demo.test.board.service;

import com.example.demo.test.board.dto.BoardDto;
import com.example.demo.test.board.entity.Board;
import com.example.demo.test.board.repository.BoardRepository;
import com.example.demo.test.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> getList() {
        return boardRepository.findAll();
    }

    public Board insert(BoardDto boardDto, Member member) {

        Board board = Board.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .deleteYn("N")
                .author(member)
                .regDt(LocalDateTime.now())
                .build();

        return boardRepository.save(board);

    }

    public Board getBoard(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        if (board.isPresent()) {
            return board.get();
        } else {
            return null;
        }
    }

    public void update(Board board, BoardDto boardDto, Member member) {
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        board.setUpd(member);
        board.setUpdDt(LocalDateTime.now());
        boardRepository.save(board);
    }

    public void delete(Board board) {
        board.setDeleteYn("N");
        boardRepository.save(board);
    }

    public void increaseHit(Board board) {
        board.setHit(board.getHit() + 1);
        boardRepository.save(board);
    }
}
