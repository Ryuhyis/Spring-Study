package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // 실제 할당하는 부분 (new MemoryMemberRepository)이 이 구현체를 의존하고 있음 (MemberServiceImpl)
    // 다른 저장소로 변경하려할때 어쨋든 7번째 코드를 변경해야함 (DIP 위반)
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public void join(Member member) {

        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {

        return memberRepository.findById(memberId);
    }
}
