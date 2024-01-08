package com.example.springpractice.repository;

import com.example.springpractice.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
}
