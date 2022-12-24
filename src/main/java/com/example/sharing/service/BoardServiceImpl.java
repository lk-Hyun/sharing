package com.example.sharing.service;

import com.example.sharing.domain.dto.BoardDTO;
import com.example.sharing.domain.entity.Board;
import com.example.sharing.domain.entity.Member;
import com.example.sharing.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final MemberService memberService;

    @Override
    public Optional<Board> readPost(Long board_id) {
        return boardRepository.findById(board_id);
    }

    @Override
    public List<Board> readAllPost() {
        return boardRepository.findAll();
    }

    @Override
    public Long writePost(BoardDTO boardDTO, String email) {
        Member member = memberService.findOne(email);

        Board board = Board.builder()
                .member(member)
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .createdBy(member.getNickname())
                .createdAt(LocalDateTime.now())
                .lastModify(null).build();

        return boardRepository.save(board).getId();
    }

    public List<BoardDTO> getBoardDTOs() {
        return boardRepository.findBoardDTOs();
    }

    @Override
    public Long postModify(Board board) {
        return null;
    }

    @Override
    public void deletePost(Board board) {

    }
}
