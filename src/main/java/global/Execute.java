package global;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Execute {

    public enum  On{
        CLIENT, MEMBER, ALL, ONE
    }

    On where() default On.ALL;
    On with() default On.ALL;
    boolean bench() default false;
}
