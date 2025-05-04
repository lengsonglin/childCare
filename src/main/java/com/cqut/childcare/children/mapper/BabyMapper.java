package com.cqut.childcare.children.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqut.childcare.children.domain.entity.Baby;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description
 * @Author Faiz
 * @ClassName BabyMapper
 * @Version 1.0
 */
public interface BabyMapper extends BaseMapper<Baby> {

    @Select("SELECT a.* FROM t_baby a inner join t_customer_baby_relation b on a.id = b.baby_id WHERE b.customer_id = #{cid} AND a.is_delete = 0")
    List<Baby> getRelatedBaby(Long cid);
}
