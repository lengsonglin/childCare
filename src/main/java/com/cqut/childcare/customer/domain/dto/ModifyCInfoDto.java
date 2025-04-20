package com.cqut.childcare.customer.domain.dto;

import com.cqut.childcare.common.annotation.FileSize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @Description
 * @Author Faiz
 * @ClassName ModifyCInfoDto
 * @Version 1.0
 */
@Data
public class ModifyCInfoDto {

    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("性别 男|女")
    @Pattern(regexp = "男|女|",message = "性别格式错误")
    private String gender;

    @ApiModelProperty("年龄")
    @Max(value = 100,message = "年龄过大")
    private Integer age;
    @ApiModelProperty("头像")
    @FileSize(max = 5 * 1024 * 1024, message = "头像不能超过5MB")
    private MultipartFile avatarFile; // 接收文件对象
    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("身份证号码")
    private String IDNumber;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("家庭住址")
    private String address;

}
