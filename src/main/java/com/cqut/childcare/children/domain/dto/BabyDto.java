package com.cqut.childcare.children.domain.dto;

import com.cqut.childcare.common.annotation.FileSize;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description
 * @Author Faiz
 * @ClassName BabyDto
 * @Version 1.0
 */
@Data
public class BabyDto {
    private Long id;
    @NotBlank(message = "姓名不能为空")
    private String name;

    private String gender;

    @NotNull(message = "出生日期不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    private String introduce;

    @FileSize(max = 50 * 1024 * 1024, message = "头像不能超过50MB")
    private MultipartFile avatarFile; // 接收文件对象
    private String avatarFileName;
}
