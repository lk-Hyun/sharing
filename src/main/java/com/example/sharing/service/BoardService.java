package com.example.sharing.service;

import com.example.sharing.domain.dto.BoardDTO;
import com.example.sharing.domain.entity.Board;

import java.util.List;
import java.util.Optional;

/** 기능 :
 * 1. 게시판 조회
 * 2. 게시판 목록 조회
 * 3. 글 쓰기
 * 4. 글 수정하기
 * 5. 글 삭제하기
 */
public interface BoardService {

    Optional<Board> readPost(Long board_id);

    List<Board> readAllPost();

    Long writePost(BoardDTO boardDTO, String email);

    Long postModify(Board board);

    void deletePost(Board board);
}