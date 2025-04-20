package com.cqut.childcare.common.constant;

/**
 * @Description
 * @Author Faiz
 * @ClassName MinioConstant
 * @Version 1.0
 */
public class MinioConstant {
    /*
    Amazon S3的存储桶名称必须满足以下条件：
    1. **长度限制**：存储桶名称必须在3到63个字符之间。
    2. **字符组成**：只能包含小写字母、数字、连字符（-）和点号（.）。
    3. **起始和结束字符**：名称必须以字母或数字开头和结尾，不能以连字符或点号开头或结尾。
    4. **连续字符限制**：不能包含两个相邻的点号，也不能同时包含点号和连字符相邻的情况。
    5. **IP地址格式**：不能格式化为IP地址（例如192.168.1.1）。
    6. **保留名称**：避免使用“xn--”开头，这用于国际化域名。
     */
    public static final String BABY_AVATAR_BUCKET = "baby-avatar";
    public static final String CUSTOMER_AVATAR_BUCKET = "customer-avatar";


}
