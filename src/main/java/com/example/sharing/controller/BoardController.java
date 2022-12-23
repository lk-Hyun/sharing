package com.example.sharing.controller;

import com.example.sharing.domain.dto.BoardDTO;
import com.example.sharing.domain.entity.Board;
import com.example.sharing.domain.entity.Member;
import com.example.sharing.service.BoardService;
import com.example.sharing.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping({"", "/"})
    public String index(Model model) {
        List<BoardDTO> boards = boardService.getBoardDTOs();
        log.info("boards = {}", boards);

        model.addAttribute("articles", boards);

        return "index";
    }

//    @GetMapping("/view/{board_id}")
//    public String getArticle(@PathVariable Long board_id, Model model) {
//
//    }

    @GetMapping("/writeArticle")
    public String writeArticle(Model model) {
        model.addAttribute("article", new BoardDTO());

        return "views/writeArticle";
    }

    @PostMapping("/writeArticle")
    public String post(@Valid @ModelAttribute BoardDTO boardDTO, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return "views/writeArticle";
        }

        //save board entity after converting dto
        boardService.writePost(boardDTO, principal.getName());

        return "redirect:/";
    }
}
