package com.example.demo.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void boardCreate(BoardForm boardForm) {
        boardRepository.save(Board.builder()
                        .title(boardForm.getTitle())
                        .description(boardForm.getDescription())
                        .writer(boardForm.getWriter())
                        .build());
    }

    public void boardPut(BoardForm boardForm) {
        boardRepository.save(Board.builder()
                        .id(boardForm.getId())
                        .title(boardForm.getTitle())
                        .description(boardForm.getDescription())
                        .writer(boardForm.getWriter())
                        .build());
    }

    public List<BoardForm> findAll() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardForm> boardFormList = new ArrayList<>();
        for(Board list : boardList) {
            boardFormList.add(BoardForm.builder()
                            .id(list.getId())
                            .title(list.getTitle())
                            .description(list.getDescription())
                            .writer(list.getWriter())
                            .build());
        }
        return boardFormList;
    }

    public BoardForm boardRead(Long id) {
        Optional<Board> boardOptional = boardRepository.findById(id);
        if(!boardOptional.isPresent()) {
            return null;
        }
        Board board = boardOptional.get();
        return BoardForm.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .description(board.getDescription())
                    .writer(board.getWriter())
                    .build();
    }

    public void boardDelete(long id) {
        Optional<Board> boardOptional = boardRepository.findById(id);
        if(!boardOptional.isPresent()) {
            return;
        }
        Board board = boardOptional.get();
        boardRepository.delete(board);
    }
}
