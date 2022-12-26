package com.example.sharing.controller;

import com.example.sharing.domain.entity.Board;
import com.example.sharing.domain.entity.Member;
import com.example.sharing.service.BoardService;
import com.example.sharing.service.MemberService;
import com.example.sharing.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reply")
@Slf4j
public class ReplyController {

    private final BoardService boardService;
    private final ReplyService replyService;
    private final MemberService memberService;

    @PostMapping("/create/{id}")
    public String createReply(@PathVariable Long id, @RequestParam String content, Principal principal) {
        log.info("content = {}", content);
        Board board = boardService.readPost(id).get();

        Member member = memberService.findOne(principal.getName());

        replyService.create(board, member.getNickname(), content);

        return String.format("redirect:/view/%s", board.getId());
    }
}
