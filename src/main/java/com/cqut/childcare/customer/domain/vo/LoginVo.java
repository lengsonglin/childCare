package com.cqut.childcare.customer.domain.vo;

import com.cqut.childcare.customer.domain.entity.Customer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description  用户登录返回信息
 * @Author Faiz
 * @ClassName LoginVo
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginVo {
    @ApiModelProperty("用户token")
    private String token;

    private CustomerInfoVo customerInfoVo;

}



