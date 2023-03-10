package com.ga.jpatest.lunch.repository;

import com.ga.jpatest.lunch.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member,Long> {


    Member findByName(@Param("name") String name);
}
