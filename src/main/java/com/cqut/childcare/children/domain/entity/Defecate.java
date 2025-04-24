package com.cqut.childcare.children.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 排便记录实体类
 * @Author Faiz
 * @ClassName Defecate
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_defecate")
public class Defecate implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long babyId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date time;
    /*
    粪便状态  正常，偏软，偏硬，糊，稀
     */
    private String state;
    /*
    粪便颜色 正常，偏绿，偏黑，偏黄
     */
    private String color;
    /*
    排便量 正常，量少，量多
     */
    private String amount;

    private String photoUrl;

    private String description;
} 