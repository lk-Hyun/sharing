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
    void forEach() {
        Member member = memberService.findOne("ww@mail");

        for (int i = 1; i <= 100; i++) {
            System.out.println("Hello");
            boardRepository.save(new Board(member, "테스트용 제목 - " + i, "내용 없음", member.getNickname(), LocalDateTime.now(), null));
        }
    }

    @Test
    void t() {
        Member member = new Member("Lee", "1234", "peed", "oo@mail", LocalDateTime.now());

//        memberService.join(member);

        Board board = new Board(member, "hi", "spring", member.getNickname(), LocalDateTime.now(), null);

//        boardRepository.save(board);

        List<BoardDTO> boards = boardRepository.findBoardDTOs();

        for (BoardDTO boardDTO : boards) {
            System.out.println("boardDTO = " + boardDTO);
        }

        assertEquals(2, boards.size());
    }

    @Test
    void paging() {

    }
}