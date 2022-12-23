package com.example.sharing.service;

import com.example.sharing.domain.entity.Member;

import java.util.List;

/**
 * 로그인 - spring security에서 해결
 * 회원 가입
 * 회원 정보 수정 - data jpa dirty check 로 해결
 * 회원 목록 조회
 * 단일 회원 조회
 * 회원 삭제
 */
public interface MemberService {

    /* 조심해야 할 점 : 유효성 검사 */
    Long join(Member member);

    List<Member> findMembers();

    Member findOne(String mail);

    void deleteMember(Long id);

}
