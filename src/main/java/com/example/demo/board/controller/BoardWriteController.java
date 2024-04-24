package com.example.demo.board.controller;

import com.example.demo.board.BoardForm;
import com.example.demo.board.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/write")
@RequiredArgsConstructor
public class BoardWriteController {

    private final BoardService boardService;

    @GetMapping
    public String write(Model model, BoardForm boardForm) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        return "pages/board/write";
    }

    @PostMapping
    public String write(@ModelAttribute @Valid BoardForm boardForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "redirect:/board/write";
        }
        boardService.boardCreate(boardForm);
        return "redirect:/board/pages";
    }
}
