package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    // test는 그냥 한글로 바꿔도됨
    @Test
    void 회원가입() {
        // given 이런 상황이 주어졌을때
        Member member = new Member();
        member.setName("hello");

        // when 이렇게하면
        Long saveId = memberService.join(member); // 중복검증해보고 저장한 아이디

        // then 이렇게 나와야함
        Member findMember = memberService.findOne(saveId).get(); // 아이디로 찾으면 그 객체를 반환해줌
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName()); // 이ㄹ
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        try{
            memberService.join(member2);
            fail("");
        }catch (IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo(("이미"));
        }

        //org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2))

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}