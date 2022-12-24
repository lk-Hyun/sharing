package com.example.sharing.repository;

import com.example.sharing.domain.dto.BoardDTO;
import com.example.sharing.domain.dto.LoginForm;
import com.example.sharing.domain.entity.Board;
import com.example.sharing.domain.entity.Member;
import com.example.sharing.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberService memberService;

    @Test
    void q() {
        Member member = new Member("Lee", "1234", "peed", "oo@mail", LocalDateTime.now());

        memberService.join(member);

        Board board = new Board(member, "hi", "spring", member.getNickname(), LocalDateTime.now(), null);

        boardRepository.save(board);

        List<BoardDTO> boards = boardRepository.findBoardDTOs();

        for (BoardDTO boardDTO : boards) {
            System.out.println("boardDTO = " + boardDTO);
        }

        assertEquals(1, boards.size());
    }
}