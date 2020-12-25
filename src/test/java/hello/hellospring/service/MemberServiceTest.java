package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    public void beforeEach(){
        //같은 MemoryMemberRepository 를 사용한다 이럴경우 같은 DB를 공유한다
        memoryMemberRepository = new MemoryMemberRepository();
        memberService=new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void afterEach(){
        memoryMemberRepository.clearStore();  // DB값 초기화
    }

    @Test
    void 회원가입() {

        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId=memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    void 중복회원예외() {

        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);


        //then
        IllegalStateException result = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));  // member2 가 회원가입할때 IllegalStateException 오류가 나옴
        assertThat(result.getMessage()).isEqualTo("이미 존재하는 회원입니다");

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}