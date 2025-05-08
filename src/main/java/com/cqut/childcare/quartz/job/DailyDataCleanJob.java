package com.cqut.childcare.quartz.job;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cqut.childcare.quartz.dao.QrtzJobDao;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;

/**
 * @Description 定定期清理事件记录，避免数据库表持续增长
 * @Author Faiz
 * @ClassName DailyDataCleanJob
 * @Version 1.0
 */
@Component
public class DailyDataCleanJob extends QuartzJobBean {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private QrtzJobDao qrtzJobDao;

    private static final Logger logger = LoggerFactory.getLogger(DailyDataCleanJob.class);
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        try {
            // 1. 解析任务参数
            JSONObject params = new JSONObject(jobDataMap.getWrappedMap());
            int months = params.getInteger("months");
            JSONArray tables = params.getJSONArray("tables");
            // 2. 计算时间阈值
            LocalDateTime thresholdTime = LocalDateTime.now().minusMonths(months);
            Timestamp thresholdTimestamp = Timestamp.valueOf(thresholdTime);

            // 3. 遍历处理所有表
            for (int i = 0; i < tables.size(); i++) {
                JSONObject tableConfig = tables.getJSONObject(i);
                String tableName = tableConfig.getString("name");
                String timeType = tableConfig.getString("timeType"); // single/range

                // 验证表是否存在
                if (!isTableExists(tableName)) {
                    logger.warn("表 {} 不存在，跳过处理", tableName);
                    continue;
                }

                // 构建删除条件
                String deleteSql = buildDeleteSql(tableName, timeType);

                // 执行删除
                try (Connection conn = dataSource.getConnection();
                     PreparedStatement ps = conn.prepareStatement(deleteSql)) {

                    ps.setTimestamp(1, thresholdTimestamp);

                    int affectedRows = ps.executeUpdate();
                    logger.info("表 {} 清理完成，删除 {} 条记录", tableName, affectedRows);
                }
            }
        } catch (Exception e) {
            logger.error("数据清理任务执行失败", e);
            throw new JobExecutionException(e);
        }
        qrtzJobDao.updateAchieved(context.getJobDetail().getKey().getName());
    }
    private boolean isTableExists(String tableName) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData meta = conn.getMetaData();
            try (ResultSet rs = meta.getTables(null, null, tableName, new String[]{"TABLE"})) {
                return rs.next();
            }
        }
    }

    private String buildDeleteSql(String tableName, String timeType) {
        String condition;
        switch (timeType) {
            case "single":
                condition = "time < ?";
                break;
            case "range":
                condition = "end_time < ? ";
                break;
            default:
                throw new IllegalArgumentException("不合法的时间类型: " + timeType);
        }

        return String.format("DELETE FROM %s WHERE %s", tableName, condition);
    }
}

