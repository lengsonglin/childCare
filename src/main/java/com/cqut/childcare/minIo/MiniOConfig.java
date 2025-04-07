package com.cqut.childcare.minIo;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Description
 * @Author Faiz
 * @ClassName MinIOconfig
 * @Version 1.0
 */
@Configuration
public class MiniOConfig {
    @Resource
    MinIoProperties minIoProperties;

    @Bean
    public MinioClient minioClient(){
        return MinioClient.builder()
                .endpoint(minIoProperties.getEndpoint())
                .credentials(minIoProperties.getAccessKey(), minIoProperties.getSecretKey())
                .build();
    }

    @Bean
    public MinIOTemplate MinioTemplate(MinioClient minioClient){
        return new MinIOTemplate(minioClient,minIoProperties);
    }


}


