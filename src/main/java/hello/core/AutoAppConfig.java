package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        /**
         * @Configuration 도 @component에 해당한다.
         * 따라서 중복 스캔을 방지해준다.
         * (예제 코드를 살리기 위함이다. 실무에서는 이런 식으로 사용하지 않는다.)
        */
        /**
         * basePackages : Scan할 대상 파일의 위치를 지정. 복수 지정도 가능하다.
         * basePackageClasses : 이 클래스에 해당하는 package를 최상단으로 스캔
         * 권장 : AppConfig를 프로젝트 최상단에 위치시키고 @ComponentScan 걸어준다.
         * -> Default가 현 클래스의 패키지를 기준으로 스캔하기 때문에 따로 지정하지 않는다.
         *
         * **애노테이션은 메타정보이며 자바가 인식하고 처리하는 것이 아니라
         *   스프링 프레임워크가 인식하고 처리한다.
        */
        basePackages = "hello.core.member",
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

        /**
         * for test : 수동으로 같은 이름의 빈을 등록해준다.
         * AutoAppConfigTest를 실행시켜 로그를 보면 중복된 이름의 빈에 대해
         * 오버라이딩 시키는 것을 볼 수 있다.
         * 이렇게 수동이 우선인 것을 볼 수 있지만, 실무에서는 이런 식의 개발은
         * 디버깅하기 어려운 이슈를 초래하게 되기 때문에 이런 상황은 기피하는 것이 좋다.
         *
         * 따라서 Boot에서는 수동으로라도 중복된 이름의 빈이 등록되면 error를 발생시키는 것이 Default이다.
         */
/*        @Bean(name = "memoryMemberRepository")
        MemberRepository memberRepository() {
                return new MemoryMemberRepository();
        }
*/
}
