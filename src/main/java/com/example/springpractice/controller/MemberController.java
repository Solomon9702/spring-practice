package com.example.springpractice.controller;

import com.example.springpractice.dto.MemberForm;
import com.example.springpractice.entity.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @GetMapping("/new")
    public String join(){
        return "members/new";
    }

    @PostMapping("join")
    public String createMember(MemberForm memberForm){
        System.out.println(memberForm.toString());
        // 1. DTO를 엔티티로 변환
        Member member = memberForm.toEntity();
        // 2. 리파지터리로 엔티티를 DB에 저장
        Member saved = memberRepository.save(member);

        return "";
    }


}
