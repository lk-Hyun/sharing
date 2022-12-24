package com.example.sharing.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@ToString(of = {"title", "content", "createdBy", "createdAt", "lastModify"})
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    @Lob
    private String content;
    private String createdBy;

    private LocalDateTime createdAt;
    private LocalDateTime lastModify;

    public Board(Member member, String title, String content, String createdBy, LocalDateTime createdAt, LocalDateTime lastModify) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.lastModify = lastModify;
    }
}
