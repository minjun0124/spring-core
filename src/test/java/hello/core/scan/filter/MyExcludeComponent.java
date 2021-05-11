package hello.core.scan.filter;

import java.lang.annotation.*;

/**
 * 이 애노테이션이 붙으면 스캔에서 제외한다.
 */
@Target(ElementType.TYPE) // TYPE : Class레벨에 붙는다 (Field 등등이 있다.)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {

}
