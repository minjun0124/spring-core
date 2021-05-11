package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) // TYPE : Class레벨에 붙는다 (Field 등등이 있다.)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {

}
