package com.cqut.childcare.common.annotation.validator;

import com.cqut.childcare.common.annotation.FileSize;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Description
 * @Author Faiz
 * @ClassName FileSizeValidator
 * @Version 1.0
 */
public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {
    private long max;
    private long min;

    @Override
    public void initialize(FileSize constraintAnnotation) {
        this.max = constraintAnnotation.max();
        this.min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        if (file == null || file.isEmpty()) {
            return true; // 允许文件为空（如需必填，可返回 false）
        }
        long size = file.getSize();
        return size >= min && size <= max;
    }
}
