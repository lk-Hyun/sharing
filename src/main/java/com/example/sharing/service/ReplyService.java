package com.example.sharing.service;

import com.example.sharing.domain.entity.Board;

public interface ReplyService {

    Long create(Board board, String writer, String content);

    Long update();

    void delete(Long id);

}
