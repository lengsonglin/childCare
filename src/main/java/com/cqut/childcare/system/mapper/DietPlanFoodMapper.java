package com.cqut.childcare.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqut.childcare.system.domain.entity.DietPlanFood;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DietPlanFoodMapper extends BaseMapper<DietPlanFood> {
    int insertBatch(@Param("list") List<DietPlanFood> list);
} 