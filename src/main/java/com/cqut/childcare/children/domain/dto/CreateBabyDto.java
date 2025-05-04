package com.cqut.childcare.children.domain.dto;

import com.cqut.childcare.common.annotation.FileSize;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @Description
 * @Author Faiz
 * @ClassName babyCreateDto
 * @Version 1.0
 */
@Data
public class CreateBabyDto {
    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotBlank(message = "性别不能为空")
    @Pattern(regexp = "男|女|未知", message = "性别格式错误")
    private String gender;

    @NotNull(message = "出生日期不能为空")
    private Date birthday;
    private String introduce;

    @FileSize(max = 5 * 1024 * 1024, message = "头像不能超过5MB")
    private MultipartFile avatarFile; // 接收文件对象

}
