package com.cqut.childcare.children.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description
 * @Author Faiz
 * @ClassName PickUpInfo
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_pickup_info")
public class PickUpInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("电话")
    @NotBlank(message = "电话不能为空")
    private String Phone;
    @NotBlank(message = "宝宝id不能为空")
    @ApiModelProperty("宝宝Id")
    private Long babyId;
    @ApiModelProperty("真实姓名")
    private String name;
    @ApiModelProperty("称谓(爸爸，妈妈。。。)")
    private String appellation;

}
