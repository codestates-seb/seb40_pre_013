package com.sebbe013.stackoverflowclone.member.repository;

import com.sebbe013.stackoverflowclone.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
  //TODO 조회?
    Optional<Member> findById(Long memberId);

}
