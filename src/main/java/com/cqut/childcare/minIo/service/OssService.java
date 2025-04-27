package com.cqut.childcare.minIo.service;

import cn.hutool.core.lang.UUID;
import com.cqut.childcare.minIo.MinIOTemplate;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author Faiz
 * @ClassName OssService
 * @Version 1.0
 */
@Service
public class OssService {

    @Autowired
    MinIOTemplate minIOTemplate;

    @Autowired
    MinioClient minioClient;

    /**
     * 上传头像
     * @param avatarFile
     * @param bucketName
     * @return
     */
    public String uploadAvatar(MultipartFile avatarFile,String bucketName) {
        minIOTemplate.makeBucket(bucketName);
        String originalFilename = avatarFile.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = UUID.randomUUID() + fileExtension;

        try (InputStream inputStream = avatarFile.getInputStream()) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(inputStream, avatarFile.getSize(), -1)
                            .contentType(avatarFile.getContentType())
                            .build()
            );
        }catch (Exception e){
            throw new RuntimeException("头像上传失败："+e.getMessage(),e);
        }
        return objectName;
    }

    /**
     *  生成预签名的Url
     * @param objectName
     * @param bucketName
     * @return
     * @throws Exception
     */
    public String generateAvatarUrl(String objectName,String bucketName) throws Exception {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(objectName)
                        .expiry(1, TimeUnit.DAYS) // 有效期1天
                        .build()
        );
    }


}
