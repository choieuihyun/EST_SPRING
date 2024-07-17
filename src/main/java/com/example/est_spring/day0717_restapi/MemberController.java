package com.example.est_spring.day0717_restapi;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private List<Member> members = new ArrayList<>();
    private long nextId = 1;

    @GetMapping
    public List<Member> getAllMembers() {
        return members;
    }

    @PostMapping
    public Member createMember(@RequestBody Member member) {
        member.setId(nextId++);
        members.add(member);

        // 여기에서 return값은 객체인데 우리가 보는 것은 json인 이유는 jackson 라이브러리가 json으로 변환해서 쏴준다.
        return member;
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable long id) {
        return members.stream()
                .filter(member -> member.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /*
    정상적으로 반환하더라도 404 반환하는 변형 메서드. 실무에서는 이런 식으로 직접 반환해주는 경우가 많다.
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable("id") Long id) {
        Member member1 = members.stream()
                .filter(member -> member.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("뭐시여 이건. 에러여?"));
        return ResponseEntity.status(404).body(member1);
    }*/

    @PutMapping("/{id}")
    public Member updateMember(@PathVariable("id") Long id, @RequestBody Member updateMember) {
        Member member = members.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("멤버를 찾지 못하였습니다."));

        member.setName(updateMember.getName());
        member.setEmail(updateMember.getEmail());
        return member;

    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable long id) {
        members.removeIf(m -> m.getId() == id);
    }

}
