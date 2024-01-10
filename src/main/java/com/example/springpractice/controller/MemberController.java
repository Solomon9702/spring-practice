package com.example.springpractice.controller;

import com.example.springpractice.dto.MemberForm;
import com.example.springpractice.entity.Member;
import com.example.springpractice.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("members/new")
    public String join(){
        return "members/new";
    }

    @PostMapping("join")
    public String createMember(MemberForm memberForm){

        log.info(memberForm.toString());
        // 1. DTO를 엔티티로 변환
        Member member = memberForm.toEntity();
        log.info(member.toString());
        // 2. 리파지터리로 엔티티를 DB에 저장
        Member saved = memberRepository.save(member);
        log.info(saved.toString());

        return "redirect:/members/" + saved.getId();
    }

    @GetMapping("/members/{id}") // 데이터 조회 요청 접수
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);
        // 1. id를 조회해 DB에서 해당 데이터 가져오기
        Member memberEntity = memberRepository.findById(id).orElse(null);
        // 2. 가져온 데이터를 모델에 등록하기
        model.addAttribute("member", memberEntity);
        // 3. 뷰 페이지 반환하기
        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model){
        // 1. 모든 데이터 가져오기
        ArrayList<Member> memberEntityList = memberRepository.findAll();
        // 2. 모델에 데이터 등록하기
        model.addAttribute("memberList", memberEntityList);
        // 3. 뷰 페이지 설정하기
        return "members/index";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // 수정할 데이터 가져오기
        Member memberEntity = memberRepository.findById(id).orElse(null);
        // 모델에 데이터 등록하기
        model.addAttribute("member",memberEntity);
        // 뷰 페이지 설정하기
        return "members/edit";
    }

    @PostMapping("/members/update")
    public String update(MemberForm form){
        log.info(form.toString());
        // 1. DTO를 엔티티로 변환하기
        Member memberEntity = form.toEntity();
        log.info(memberEntity.toString());
        // 2. 엔티티를 DB에 저장하기
        // 2-1. DB에서 기존 데이터 가져오기
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);

        // 2-2. 기존 데이터의 값 변경하기
        if(target != null){
            memberRepository.save(memberEntity); // 엔티티를 DB에 갱신
        }

        // 3. 수정 결과 페이지로 리다이렉트하기
        return "redirect:/members/" + memberEntity.getId();
    }
}
