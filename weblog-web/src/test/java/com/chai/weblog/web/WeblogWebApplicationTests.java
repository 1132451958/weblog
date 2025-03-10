package com.chai.weblog.web;

import com.chai.weblog.common.domain.dos.UserDO;
import com.chai.weblog.common.domain.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@Slf4j
@SpringBootTest
class WeblogWebApplicationTests {
    @Test
    void contextLoads() {
    }
    @Test
    void logTest(){
        log.info("info");
        log.warn("warn");
        log.error("error");
    }
    @Autowired
    private UserMapper userMapper;

    @Test
    void insertTest() {
        // 构建数据库实体类
        UserDO userDO = UserDO.builder()
                .id(5l)
                .username("chai")
                .password("$2a$10$1g0SZZ1isgl1t6VV/yvHaOgKZN9MiTeJKVPqSbsJFW0ZwVuU/dKGi")
                .createTime(new Date())
                .updateTime(new Date())
                .isDeleted(false)
                .build();

        userMapper.updateById(userDO);
    }

}
