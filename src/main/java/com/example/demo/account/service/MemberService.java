package com.example.demo.account.service;

import com.example.demo.account.Member;
import com.example.demo.account.MemberRepository;
import com.example.demo.account.Signup;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(Signup signup) {
        memberRepository.save(Member.builder()
                .username(signup.getUsername())
                .password(passwordEncoder.encode(signup.getPassword()))
                .name(signup.getName())
                .build());
    }
}
