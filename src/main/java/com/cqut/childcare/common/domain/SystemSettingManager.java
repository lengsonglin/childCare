package com.cqut.childcare.common.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author Faiz
 * @ClassName SystemSettingManager
 * @Version 1.0
 */
@Component
@Data
public class SystemSettingManager {
    private Long fileMaxSize;

    private String fileType;

    private Integer shortMsgCount;

    private Integer shortMsgTime;
}
