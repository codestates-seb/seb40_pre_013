package com.sebbe013.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {QuestionPatchValidator.class})
public @interface NotSpace {
    String message() default "질문 제목이나 내용은 공백이 아니어야 합니다";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
