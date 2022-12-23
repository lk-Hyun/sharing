package com.example.sharing.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username; //실 사용자 이름
    private String password;

    @Column(unique = true)
    private String nickname; //회원 닉네임

    @Column(unique = true)
    private String email;

    private LocalDateTime registerDate;
}
