package com.example.demo.board.controller;

import com.example.demo.board.BoardForm;
import com.example.demo.board.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board/pages")
@RequiredArgsConstructor
public class BoardPagesController {

    private final BoardService boardService;

    @GetMapping
    public String pages(Model model) {
        List<BoardForm> boardFormList = boardService.findAll();
        model.addAttribute("boards", boardFormList);
        return "pages/board/pages";
    }

    @GetMapping("/{id}")
    public String boardPage(@PathVariable("id") Long id, Model model) {
        BoardForm boardForm = boardService.boardRead(id);
        model.addAttribute("board", boardForm);
        return "pages/board/boardpage";
    }

    //수정이 필요하다. 사용자가 html코드를 수정시 다른 페이지를 수정이 가능해짐
    @GetMapping("/put/{id}")
    public String boardPut(@PathVariable("id") Long id,
                           Model model) {
        BoardForm boardRead = boardService.boardRead(id);
        model.addAttribute("board", boardRead);
        return "pages/board/put";
    }

    @PostMapping("/put")
    public String boardPut(@ModelAttribute @Valid BoardForm boardForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "redirect:/";
        }
        boardService.boardPut(boardForm);
        return "redirect:/board/pages";
    }

    //수정이 필요하다. 사용자가 html코드를 수정시 다른 페이지도 삭제가 가능해짐
    @PostMapping("/delete")
    public String deletePage(@RequestParam(value="id") long id) {
        boardService.boardDelete(id);
        return "redirect:/board/pages";
    }
}
