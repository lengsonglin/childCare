package com.cqut.childcare.children.domain.dto.babyEvent;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @Description
 * @Author Faiz
 * @ClassName DiningDto
 * @Version 1.0
 */
@Data
public class DiningDto {
    @ApiModelProperty("宝宝id")
    private Long babyId;

    @ApiModelProperty("开始时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date beginTime;
    @ApiModelProperty("结束时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;

    @ApiModelProperty("饭量")
    private String amount;
    /*
    食物类型，甜点，可以自己输入
     */
    @ApiModelProperty("种类(正餐，甜点)")
    private String type;
    @ApiModelProperty("记录照片")
    private MultipartFile photo;
    @ApiModelProperty("备注")
    private String remark;
}
