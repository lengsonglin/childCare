package com.cqut.childcare.minIo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author Faiz
 * @ClassName MinIoProperties
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinIoProperties {
    /**
     * OSS 访问端点，集群时需提供统一入口
     */
    private String endpoint;

    /**
     * 用户名
     */
    private String accessKey;

    /**
     * 密码
     */
    private String secretKey;

    /**
     * 存储桶
     */
    private String bucket;
}
