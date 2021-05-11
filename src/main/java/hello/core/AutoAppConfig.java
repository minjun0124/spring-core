package hello.core;

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


}
