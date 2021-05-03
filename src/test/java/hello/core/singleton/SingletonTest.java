package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조 값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // result : 서로 다른 객체를 계속 생성 -> TPS가 계속 올라간다. -> 비효율
        // memberService1 = hello.core.member.MemberServiceImpl@6f45df59
        // memberService2 = hello.core.member.MemberServiceImpl@38e79ae
        // 추가로 memberServicer 객체를 생성하면서 repository도 생성니까 현재 4개의 객체를 생성한 것
        // sol : 객체를 하나만 생성해서 공유하면 된다. -> singleton
        // 사실 GC(Garbage Collector)가 굉장히 빨라서 큰 문제는 없지만 효율적인 설계가 아무래도 좋지 않겠나
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void SingletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

//        same : 레퍼런스 비교
//        equal : 밸류 비교
        assertThat(singletonService1).isSameAs(singletonService2);

//        result
//        singletonService1 = hello.core.singleton.SingletonService@4e3958e7
//        singletonService2 = hello.core.singleton.SingletonService@4e3958e7
    }

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void springContainer() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);
    }

}
