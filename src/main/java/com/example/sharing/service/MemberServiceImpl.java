package com.example.sharing.service;

import com.example.sharing.domain.dto.SecurityMember;
import com.example.sharing.domain.entity.Member;
import com.example.sharing.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService, UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public Long join(Member member) {
        Member save = memberRepository.save(member);
        return save.getId();
    }

    @Override
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member findOne(String mail) {
        return memberRepository.findByEmail(mail).orElse(null);
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> findMember = memberRepository.findByEmail(username);

        if (findMember.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다");
        }

        SecurityMember member = new SecurityMember(findMember.get());
        log.info("login member = {}", member);

        return new User(member.getUsername(), member.getPassword(), member.getAuthorities());
    }
}
