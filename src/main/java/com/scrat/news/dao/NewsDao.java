package com.scrat.news.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by yixuanxuan on 16/6/12.
 */
@Repository
public class NewsDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String TABLE = "news";
    private static final String PUBLIC_TS = "publish_ts";
    private static final String NID = "nid";
    private static final String TITLE = "title";
    private static final String TP = "tp";

    public List<Map<String, Object>> getNews(int index, int size) {
        String sql = "SELECT * FROM " + TABLE + " ORDER BY " + PUBLIC_TS + " LIMIT " + index + "," + size;
        return jdbcTemplate.queryForList(sql);
    }

    public Map<String, Object> getNew(int nid) {
        String sql = "SELECT * FROM " + TABLE + " WHERE " + NID + "=" + nid + " LIMIT 1";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        // 避免出现 org.springframework.dao.EmptyResultDataAccessException: Incorrect result size: expected 1, actual 0
        if (list == null || list.size() == 0)
            return Collections.emptyMap();

        return list.get(0);
    }
    
}
