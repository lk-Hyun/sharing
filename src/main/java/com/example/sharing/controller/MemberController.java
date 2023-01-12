package com.example.sharing.controller;

import com.example.sharing.domain.dto.LoginForm;
import com.example.sharing.domain.entity.Member;
import com.example.sharing.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

//    @GetMapping("/login")
//    public String login(Model model) {
//        model.addAttribute("loginForm", new LoginForm());
//        return "views/login";
//    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model) {

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        model.addAttribute("loginForm", new LoginForm());

        return "/views/login";
    }

    @GetMapping("/new")
    public String register(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "views/register";
    }

    @PostMapping("/new")
    public String join(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return "views/register";
        }

        //not equal password
        if (!loginForm.getPassword().equals(loginForm.getPassword2())) {
            bindingResult.rejectValue("loginForm", "global", "비밀번호가 일치하지 않습니다");

            log.info("bindingResult = {}", bindingResult);
            return "views/register";
        }

        //register success, encoding password
        Member member = Member.builder()
                .username(loginForm.getUsername())
                .password(passwordEncoder.encode(loginForm.getPassword()))
                .nickname(loginForm.getNickname())
                .email(loginForm.getEmail())
                .registerDate(LocalDateTime.now()).build();

        memberService.join(member);

        return "redirect:/";
    }
}
