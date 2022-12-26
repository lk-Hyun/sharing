package com.example.sharing.service;

import com.example.sharing.domain.entity.Board;

public interface ReplyService {

    void create(Board board, String writer, String content);

    void delete(Long id);

}
