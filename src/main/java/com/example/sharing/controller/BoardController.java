package com.example.sharing.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    @GetMapping({"", "/"})
    public String index() {
        return "index";
    }

    @GetMapping("/writeArticle")
    public String articleView() { //param에 dto 추가 요망
        return "views/article";
    }
}
