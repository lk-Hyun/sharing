package com.example.sharing.service;

import com.example.sharing.domain.entity.Board;
import com.example.sharing.domain.entity.Reply;
import com.example.sharing.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    @Override
    public Long create(Board board, String writer, String content) {
        Reply reply = Reply.builder()
                .board(board)
                .content(content)
                .createdBy(writer)
                .createdAt(LocalDateTime.now()).build();

        return replyRepository.save(reply).getId();
    }

    @Override
    public Long update() {
        return null;
    }

    @Override
    public void delete(Long id) {
        replyRepository.findById(id).ifPresent(replyRepository::delete);
    }

}
