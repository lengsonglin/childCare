package com.cqut.childcare.common.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @Description
 * @Author Faiz
 * @ClassName JsonToMapHandler
 * @Version 1.0
 */
public class JsonToMapHandler extends BaseTypeHandler<Map<String,Object>> {

    private final TypeReference<Map<String, Object>> typeReference =
            new TypeReference<Map<String, Object>>() {};
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Map<String, Object> stringObjectMap, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, JSON.toJSONString(stringObjectMap));
    }

    @Override
    public Map<String, Object> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String json = resultSet.getString(s);
        return json == null ? null : JSON.parseObject(json,typeReference);
    }

    @Override
    public Map<String, Object> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String json = resultSet.getString(i);
        return json == null ? null : JSON.parseObject(json,typeReference);

    }

    @Override
    public Map<String, Object> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String json = callableStatement.getString(i);
        return json == null ? null : JSON.parseObject(json,typeReference);
    }
}
