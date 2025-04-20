package com.cqut.childcare.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqut.childcare.system.domain.entity.Function;

/**
 * @Description 功能Service接口
 * @Author Faiz
 * @ClassName FunctionService
 * @Version 1.0
 */
public interface FunctionService extends IService<Function> {
    /**
     * 添加功能
     * @param function 功能信息
     * @return 是否成功
     */
    boolean addFunction(Function function);

    /**
     * 更新功能
     * @param function 功能信息
     * @return 是否成功
     */
    boolean updateFunction(Function function);

    /**
     * 删除功能
     * @param id 功能ID
     * @return 是否成功
     */
    boolean deleteFunction(Long id);

    /**
     * 更新功能状态
     * @param id 功能ID
     * @param onState 状态
     * @return 是否成功
     */
    boolean updateFunctionState(Long id, Byte onState);
} 