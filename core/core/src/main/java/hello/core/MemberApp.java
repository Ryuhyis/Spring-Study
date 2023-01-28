package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// 테스트 코드 -> 순수 자바코드로만 이뤄진 코드ㅇㅇ, 매번 직접 적고 실행해야됨 -> Junit 으로 테스트 하는 방법
public class MemberApp {
    public static void main(String[] args) {

        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();
        //MemberService memberService = new MemberServiceImpl(memberRepository);

        // 스프링으로 하는 방법, 이렇게하면 AppConfig에 있는 환경 설정 정보를 가지고 스프링이 여기 안에 있는 @Bean 얘네들을 다 스프링 컨테이너에 넣어서 알아서 관리하게 해줌
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);


        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("New Member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
