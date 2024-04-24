package com.example.demo.account;

import com.example.demo.account.service.MemberService;
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
@RequestMapping("/account")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/signin")
    public String signin() {
        return "/pages/account/signin";
    }


    @GetMapping("/signup")
    public String signup(Signup signup) {
        return "/pages/account/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute @Valid Signup signup, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "redirect:/account/signup";
        }
        memberService.signup(signup);
        return "redirect:/account/signin";
    }
}
