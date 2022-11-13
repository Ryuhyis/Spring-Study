package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {

    // 기존에는 19번째줄처럼 새로운 레파지토리를 생성하게했는데, 이제는 외부에서 주입하도록
    //
    private final MemberRepository memberRepository;
    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 왜 인터페이스로 감싸는지??
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 회원가입
    public Long join(Member member) {
//        // 같은 이름이 있는 중복 회원은 안된다
//        Optional<Member> result = memberRepository.findByName(member.getName()); // 먼저 name이 있는지 찾아보고
//        // optional로 반환이 됐으니
//        result.ifPresent(m -> { // 만약 값이 있으면 (이건 Optional이라서 가능, Optional 아니였으면 if null 약간 이런식으로 했겠지)
//            throw new IllegalStateException("이미 존재하는 회원입니다");
//        });

        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member); // 검증 통과하면 저장
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미존재하는회원");
                        });
    }


    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {

        return memberRepository.findById(memberId);
    }
}
