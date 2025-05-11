package com.cqut.childcare.customer.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author Faiz
 * @ClassName CustomerInfoVo
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerInfoVo {
    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("用户电话")
    private String telPhone;
    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty("用户性别")
    private String gender;

    @ApiModelProperty("用户年龄")
    private int age;

    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("用户真实姓名")
    private String realName;
    @ApiModelProperty("身份证号码")
    private String IDNumber;
    @ApiModelProperty("用户邮箱")
    private String email;
    @ApiModelProperty("用户居住地址")
    private String address;
    @ApiModelProperty("用户类型  宝宝的家人或者是托育工作人员(family/childcareWorker)")
    private String type;
}
