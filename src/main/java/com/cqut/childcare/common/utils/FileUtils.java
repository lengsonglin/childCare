package com.cqut.childcare.common.utils;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Description
 * @Author Faiz
 * @ClassName FileUtils
 * @Version 1.0
 */
public class FileUtils {

    public static boolean checkBlankFile(MultipartFile avatarFile){
        if (avatarFile == null || avatarFile.isEmpty() || avatarFile.getOriginalFilename() == null || !avatarFile.getOriginalFilename().contains(".")) {
            return true;
        }
        return false;
    }
}
