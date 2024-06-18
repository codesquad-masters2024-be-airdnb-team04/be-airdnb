package com.airdnb.clone.domain.member.service;

import com.airdnb.clone.domain.member.entity.Member;
import com.airdnb.clone.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(Member member) {
        String hashPassword = passwordEncoder.encode(member.getPassword());
        member.changePassword(hashPassword);
        memberRepository.save(member);
    }
}
