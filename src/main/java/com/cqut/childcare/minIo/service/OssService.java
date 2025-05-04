package com.cqut.childcare.minIo.service;

import cn.hutool.core.lang.UUID;
import com.cqut.childcare.minIo.MinIOTemplate;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
    public String uploadFile(MultipartFile avatarFile,String bucketName) {
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
            throw new RuntimeException("文件上传失败："+e.getMessage(),e);
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
    public String generateFileUrl(String objectName,String bucketName) throws Exception {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(objectName)
                        .expiry(1, TimeUnit.DAYS) // 有效期1天
                        .build()
        );
    }

    /**
     * 生成公开存储桶中文件的永久 URL
     * @param endpoint Minio服务地址（如 http://localhost:9000）
     * @param bucketName 存储桶名称
     * @param objectName 对象名称（如 images/photo.jpg）
     * @return 可直接访问的URL
     */
    public String generatePublicFileUrl(String endpoint, String bucketName, String objectName) {
        try {
            // 1. 处理 endpoint 格式（去除末尾斜杠）
            String baseUrl = endpoint.replaceAll("/+$", "");

            // 2. 对 objectName 进行分段编码（保留路径分隔符 "/"）
            String encodedObjectName = Arrays.stream(objectName.split("/"))
                    .map(part -> encodePart(part))
                    .collect(Collectors.joining("/"));

            // 3. 拼接完整 URL
            return String.format("%s/%s/%s", baseUrl, bucketName, encodedObjectName);
        } catch (Exception e) {
            throw new RuntimeException("生成 URL 失败", e);
        }
    }
    public String findObjectAcrossBuckets(String targetObjectName) throws Exception {
        // 1. 获取所有桶
        List<Bucket> buckets = minioClient.listBuckets();

        // 2. 遍历每个桶，检查对象是否存在
        for (Bucket bucket : buckets) {
            String bucketName = bucket.name();
            try {
                // 检查对象是否存在（不抛出异常则存在）
                minioClient.statObject(StatObjectArgs.builder()
                        .bucket(bucketName)
                        .object(targetObjectName)
                        .build());
                // 对象存在，生成预签名URL
                return generateFileUrl(targetObjectName, bucketName);
            } catch (Exception e) {
                // 对象不存在，继续下一个桶
                continue;
            }
        }

        // 3. 未找到对象
        throw new RuntimeException("Object not found in any bucket");
    }

    // 对路径的每一部分进行编码（处理特殊字符）
    private String encodePart(String part) {
        try {
            return URLEncoder.encode(part, StandardCharsets.UTF_8.toString())
                    .replaceAll("\\+", "%20");  // 将 "+" 转为 "%20"（符合 URL 规范）
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("编码失败", e);
        }
    }

    public void removeFile(String objectName,String bucketName){
        try {
            minioClient.removeObject(
                    RemoveObjectArgs
                            .builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("文件删除失败："+e.getMessage(),e);
        }
    }


}
