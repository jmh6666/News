package com.scrat.news.controller;

import com.scrat.news.dao.NewsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yixuanxuan on 16/6/12.
 */
@RestController
@RequestMapping("/api")
public class NewsController {
    private static final int MAX_LIST_SIZE = 100;
    private static final int INDEX_NO_MORE = -1;

    @Autowired
    private NewsDao newsDao;

    @RequestMapping("/list")
    public Map<String, Object> getNews(int index, int size) {
        List<Map<String, Object>> list;
        int responseIndex;
        if (index < 0 || size < 1) {
            list = Collections.emptyList();
            responseIndex = INDEX_NO_MORE;
        } else {
            if (size > MAX_LIST_SIZE) {
                size = MAX_LIST_SIZE;
            }

            list = newsDao.getNews(index, size);
            if (list == null || list.size() == 0 || list.size() < size) {
                responseIndex = INDEX_NO_MORE;
            } else {
                responseIndex = index + list.size();
            }
        }

        Map<String, Object> response = new HashMap<String, Object>();
        response.put("index", responseIndex);
        response.put("data", list);
        return response;
    }

    @RequestMapping("/news")
    public Map<String, Object> getNew(int nid) {
        Map<String, Object> data = newsDao.getNew(nid);
        if (data == null)
            return Collections.emptyMap();

        return data;
    }
}
