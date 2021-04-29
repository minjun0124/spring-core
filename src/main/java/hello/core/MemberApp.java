package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // 기본적으로 Bean은 이름으로 등록이 되기 때문에 AppConfig-memberService()을 가져오기 위해
        // memberService를 파라미터로 넣어준다.
        // key : memberService / value : AppConfig에서 생성된 객체 new MemberServiceImpl(memberRepository());
        // 로 스프링 컨테이너에 등록이 된다. 꺼낼때는 이름(memberService)으로 꺼내면 됨.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
