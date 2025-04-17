package io.kanghyun.form_auth_prac.dao;

import io.kanghyun.form_auth_prac.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //jpql 쿼리 메서드 (username으로 조회)
    Optional<Member> findByUsername(String username);
}
