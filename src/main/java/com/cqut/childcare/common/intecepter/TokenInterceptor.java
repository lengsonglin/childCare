package com.cqut.childcare.common.intecepter;

import cn.hutool.json.JSONUtil;
import com.cqut.childcare.common.domain.dto.RequestInfo;
import com.cqut.childcare.common.domain.vo.ApiResult;
import com.cqut.childcare.common.exception.CommonErrorEnum;
import com.cqut.childcare.common.utils.RequestHolder;
import com.cqut.childcare.customer.service.CustomerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Description
 * @Author Faiz
 * @ClassName tokenInterceptor
 * @Version 1.0
 */
@Order(-2)
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    public static final String AUTHORIZATION_HEADER = "TOKEN";

    @Autowired
    private CustomerInfoService customerInfoService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(AUTHORIZATION_HEADER);
        RequestInfo info =new RequestInfo();
        Long validCid = customerInfoService.getValidCid(token);
        if (Objects.nonNull(validCid)) {//有登录态
            info.setCid(validCid);
            RequestHolder.set(info);
        }else{
            boolean isPublicURI = isPublicURI(request.getRequestURI());
            boolean isSystemURI = isSystemURI(request.getRequestURI());
            if (!isPublicURI && !isSystemURI) {//又没有登录态，又不是公开路径，直接401
                response.getWriter().write(JSONUtil.toJsonStr(ApiResult.fail(CommonErrorEnum.INVALID_TOKEN)));
                return false;
            }
            // TODO 后面管理系统的接口单独处理
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        RequestHolder.remove();
    }

    private boolean isPublicURI(String requestURI){
        String[] split = requestURI.split("/");
        return split.length > 2 && "public".equals(split[3]);
    }

    private boolean isSystemURI(String requestURI){
        String[] split = requestURI.split("/");
        return split.length > 2 && "system".equals(split[2]);
    }


}
