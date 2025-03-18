package com.chai.weblog.admin.message.consumer;

import com.chai.weblog.common.domain.dos.ArticleHotScoreDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.chai.weblog.admin.message.KafkaConstant.Hot_Article;

@Slf4j
@Service
public class HotArticleKafkaConsumer {
    @KafkaListener(topics = Hot_Article, groupId = "hot-article-group")
    public void listen(String hotTitle) {
        try {
            // 处理接收到的文章热度更新消息
            log.info("接收到文章热度更新消息: {}", hotTitle);
            // 这里可以添加更多处理逻辑，比如更新缓存、推送给用户等
        } catch (Exception e) {
            log.error("处理文章热度更新消息失败", e);
        }
    }
}
