package com.cqut.childcare.minIo.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OssReq {
    @ApiModelProperty(value = "文件存储路径")
    private String filePath;
    @ApiModelProperty(value = "文件名")
    private String fileName;
    @ApiModelProperty(value = "请求的uid")
    private Long uid;
    @ApiModelProperty(value = "自动生成地址")
    @Builder.Default
    private boolean autoPath = true;
}
