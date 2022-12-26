package com.example.sharing.controller;

import com.example.sharing.domain.dto.BoardDTO;
import com.example.sharing.domain.entity.Board;
import com.example.sharing.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping({"", "/"})
    public String index(Model model, @RequestParam(required = false) String keyword,
                        @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Board> paging;

        if (keyword == null) {
            paging = boardService.getList(pageable);
        } else {
            paging = boardService.getSearchList(pageable, keyword);
        }

        model.addAttribute("paging", paging);

        return "index";
    }
//    @GetMapping({"", "/"})
//    public String index(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam String keyword) {
//        Page<Board> paging = boardService.getList(page);
//        model.addAttribute("paging", paging);
//
//        return "index";
//    }

    @GetMapping("/view/{board_id}")
    public String getArticle(@PathVariable Long board_id, Model model) {
        Board board = boardService.readPost(board_id).orElse(null);
        model.addAttribute("article", board);

        return "views/article";
    }

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
