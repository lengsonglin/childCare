package com.cqut.childcare.common.annotation;

import com.cqut.childcare.common.annotation.validator.FileSizeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @Description
 * @Author Faiz
 * @ClassName FileSize
 * @Version 1.0
 */
@Documented
@Constraint(validatedBy = FileSizeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileSize {

    String message() default "文件大小不符合要求";

    /**
     *   在 Java Bean Validation 中，自定义约束注解时，groups 和 payload 是规范要求必须定义的参数。
     *
     */
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    long max() default 1024 * 1024 * 10; // 默认最大文件大小
    long min() default 0;              // 默认最小文件大小
}
