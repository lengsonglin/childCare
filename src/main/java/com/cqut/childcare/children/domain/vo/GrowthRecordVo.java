package com.cqut.childcare.children.domain.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @Description
 * @Author Faiz
 * @ClassName GrowthRecordVo
 * @Version 1.0
 */
@Data
@Builder
public class GrowthRecordVo {

    private Long babyId;

    private String height;

    private String weight;

    private String headCircumference;

    private String age;


}
