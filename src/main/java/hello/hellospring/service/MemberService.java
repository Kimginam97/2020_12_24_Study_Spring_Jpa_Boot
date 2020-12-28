package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {

    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member){
        //같은 이름이 있는 중복회원 x

        /*//DB안의 name 을  Parameter getName넣어서 찾는다
        Optional<Member> result = memberRepository.findByName(member.getName());

        //값을 꺼내온다 (객체)
        Member member1 = result.get();

        //result의 값이 있을경우
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        } );*/


        //메소드로 뽑아서 쓴다 ctrl + alt + m
        //중복회원검증
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                } );
    }

    //전체회원조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //하나 조회
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
