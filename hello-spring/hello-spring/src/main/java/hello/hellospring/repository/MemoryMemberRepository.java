package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 8L;

    @Override
    public Member save(Member member) {
        //member의 아이디를 세팅하고 store에 저장을 하면 Map에 저장이 되겠죠
        member.setId(++sequence);
        store.put(member.getId(), member);
        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // return store.get(id)

        // null이 나올 가능성이 있을때
        return Optional.ofNullable(store.get(id)); // get했는데 null값이면 프론트에서 어케 처리할 수 있게,,해주는
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 같은 경우에만 필터링이됨, findAny : 하나라도 찾는거, 끝까지 돌렸는데 없으면 optional에 넣어짐
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store의 있는 values가 Member다
    }

    public void clearStore() {
        store.clear();
    }
}
