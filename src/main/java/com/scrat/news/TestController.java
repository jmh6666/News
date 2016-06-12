package com.scrat.news;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yixuanxuan on 16/6/12.
 */
@RestController
@RequestMapping("/")
public class TestController {
    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}
