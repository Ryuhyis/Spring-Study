package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member); // 회원이 저장소에 저장이되고
    Optional<Member> findById(Long ID); // 저장소에서 이렇게 값을 찾아옴
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
