package cn.zjy.study.security.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.FIELD})// 注解可以标注在什么上面，目前设置为可标注在方法和字段上面
@Retention(RetentionPolicy.RUNTIME)//运行时注解
@Constraint(validatedBy = MyConstraintValidator.class)// 当前的注解需要用什么类中的逻辑进行校验
public @interface MyConstraint {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
