package com.chai.weblog.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
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

}
