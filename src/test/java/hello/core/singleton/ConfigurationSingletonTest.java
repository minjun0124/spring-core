package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        /*
        *   result :
        *   memberService -> memberRepository = hello.core.member.MemoryMemberRepository@5f7b97da
        *   orderService -> memberRepository = hello.core.member.MemoryMemberRepository@5f7b97da
        *   memberRepository = hello.core.member.MemoryMemberRepository@5f7b97da
        */

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
        /* result : 클래스 타입
        * bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$8f4d31de
        * AppConfig가 그대로 인스터스로 등록이 되는 것이 아니라 CGLIB가 상속하여 구현, 등록됨
        * AppConfig@CGLIB 예상 코드
        @Bean
        public MemberRepository memberRepository(){
            if (memoryMemberRepository가 이미 스프링 컨테이너에 등록되어 있으면?){
                return 스프링 컨테이너에서 찾아서 반환;
            }else{
                기존 로직을 호출해서 MemoryMemberRepository를 생성하고 스프링 컨테이너에 등록
                return 반환
            }
        }
        *
        * AppConfig의 @Configuration을 빼고 실행해보면 Singleton이 깨지는 것을 볼 수 있다. (객체 중복 생성)
        * SpringContainer에서 제공하는 Bean 객체 관리에서 벗어난다.
         */
    }
}
