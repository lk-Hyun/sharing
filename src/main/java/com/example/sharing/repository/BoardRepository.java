package com.example.sharing.repository;

import com.example.sharing.domain.dto.BoardDTO;
import com.example.sharing.domain.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select new com.example.sharing.domain.dto.BoardDTO(" +
            "b.title, b.content, m.nickname, b.createdAt) from Board b join b.member m")
    List<BoardDTO> findBoardDTOs();

    Page<Board> findAll(Pageable pageable);
}
