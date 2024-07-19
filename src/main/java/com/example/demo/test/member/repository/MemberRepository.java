package com.example.demo.test.member.repository;

import com.example.demo.test.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByUsernameAndPassword(String username, String password);

    Optional<Member> findByUsername(String username);

}
