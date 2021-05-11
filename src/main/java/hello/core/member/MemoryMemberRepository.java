package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository {

    // 동시성 이슈 -> concurrent hashmap 으로 변경해야함
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        // 오류처리는 나중에...
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
