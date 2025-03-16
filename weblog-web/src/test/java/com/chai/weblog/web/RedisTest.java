package com.chai.weblog.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
public class RedisTest {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * set key value
     */
    @Test
    void testSetKeyValue() {
        // 添加一个 key 为 name, value 值为 犬小哈
        redisTemplate.opsForValue().set("name", "犬小哈");
    }
}
