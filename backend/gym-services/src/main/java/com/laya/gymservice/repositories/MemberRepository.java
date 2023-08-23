package com.laya.gymservice.repositories;

import com.laya.gymservice.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // You can add custom methods if needed
}

