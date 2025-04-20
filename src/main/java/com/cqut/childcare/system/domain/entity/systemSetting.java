package com.cqut.childcare.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author Faiz
 * @ClassName systemSetting
 * @Version 1.0
 */
@Data
@TableName("t_system_setting")
public class systemSetting implements Serializable {

    private Long id;  // 系统ID（唯一记录，初始化时需设置id=1）

    private String websiteName;  // 站点名称

    private String fileMaxSize;  // 上传文件大小限制（如：10MB）

    private String fileType;  // 允许上传的文件类型（如：jpg,png）

    private String siteTitle;  // 站点标题

    private String copyrightInfo;  // 版权声明

    private String email;  // 管理员邮箱

    private String phone;  // 联系电话

    private String userManual;  // 系统使用手册

    private Integer shortMsgCount;  // 每日短信发送上限

    private Integer shortMsgTime;  // 短信验证码有效期（单位：分）

    // 构造函数（带初始化id=1）
    public systemSetting() {
        this.id = 1L;  // 根据注释初始化系统ID
    }
}
