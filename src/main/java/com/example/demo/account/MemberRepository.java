package com.example.demo.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    //SELECT * FROM Member WHERE username=[username]
    Optional<Member> findByUsername(String username);
}
