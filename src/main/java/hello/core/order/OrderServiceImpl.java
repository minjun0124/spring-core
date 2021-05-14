package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    DIP위반 : 인터페이스 뿐만이 아니라 구체화까지 모두 의존하는 상태
//    OCP위반 : FixDiscount -> RateDiscount 하는 순간 OrderServiceImpl도 수정
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

/*
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    @Autowired  //(required = false) : 필수가 아님을 명시, 선택적 주입
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

*/
    // Replace with DI : DIP, OCP를 모두 잘 지켜진다.
    // 생성자 주입
    // final : 생성자 주입을 통해 반드시 값을 넣어줘야한다. (필수 의존 관계)
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
//    private final DiscountPolicy lombok; : 이런식으로 필드를 그냥 추가해도 @RequiredArgsConstructor 가 알아서 수정.

    // DiscountPolicy의 필드 값을 rateDiscountPolicy 으로 지정 -> 타입으로 빈을 조회 -> 중복되는 타입이 있으면 필드명으로 조회
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = rateDiscountPolicy;
    }

/*
  // @RequiredArgsConstructor 를 사용하면 필드값을 사용하여 생성자를 만들어 준다. : 아래를 생략할 수 있다.
    @Autowired  // 생성자가 하나인 경우 생략이 가능하다.
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = rateDiscountPolicy;
    }
*/
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        // 단일체계원칙 : OrderService 입장에서는 discount는 신경쓸게 X
        // 할인 쪽에 수정사항이 있으면 discount만 고치면 돼
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // for test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
