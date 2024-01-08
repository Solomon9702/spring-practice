package com.example.springpractice.dto;

import com.example.springpractice.entity.Member;

public class MemberForm {
    private String email;  // 이메일을 받을 필드
    private String password;  // 비번을 받을 필드

    public MemberForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Member{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Member toEntity(){
        return new Member(null, email, password);
    }
}
