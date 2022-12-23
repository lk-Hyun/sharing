package com.example.sharing.repository;

import com.example.sharing.domain.dto.BoardDTO;
import com.example.sharing.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select new com.example.sharing.domain.dto.BoardDTO(b.title, b.content, b.createdBy, b.createdAt) from Board b")
    List<BoardDTO> findBoardDTOs();
}
