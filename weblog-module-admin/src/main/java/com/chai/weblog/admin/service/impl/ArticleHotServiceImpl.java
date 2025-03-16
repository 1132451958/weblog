package com.chai.weblog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chai.weblog.admin.model.vo.hot.ArticleHotTenRspVo;
import com.chai.weblog.admin.service.ArticleHotService;
import com.chai.weblog.common.domain.dos.ArticleDO;
import com.chai.weblog.common.domain.dos.ArticleHotScoreDO;
import com.chai.weblog.common.domain.mapper.ArticleHotScoreMapper;
import com.chai.weblog.common.enums.ResponseCodeEnum;
import com.chai.weblog.common.exception.BizException;
import com.chai.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.chai.weblog.common.redisKey.RedisConstant.HOT_KEY;
@Slf4j
@Service
public class ArticleHotServiceImpl implements ArticleHotService {
    @Autowired
    ArticleHotScoreMapper articleHotScoreMapper;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean updateHotArticle(ArticleDO articleDO) {
        // 2. 更新文章热度表
        ArticleHotScoreDO hotScoreDO = articleHotScoreMapper.selectOne(
                new QueryWrapper<ArticleHotScoreDO>().eq("article_id", articleDO.getId())
        );

        if (hotScoreDO != null) {
            // 如果热度表记录存在，更新相关字段
            hotScoreDO.setTitle(articleDO.getTitle()); // 更新标题
            hotScoreDO.setUpdatedAt(articleDO.getUpdateTime());       // 更新修改时间
            int updateHotResult = articleHotScoreMapper.updateById(hotScoreDO);
            return updateHotResult > 0;
        } else {
            ArticleHotScoreDO articleHotScoreDO = ArticleHotScoreDO.builder()
                    .articleId(articleDO.getId())
                    .title(articleDO.getTitle())
                    .createdAt(articleDO.getCreateTime())
                    .updatedAt(articleDO.getUpdateTime())
                    .build();
            articleHotScoreMapper.insert(articleHotScoreDO);
        }
        return true;
    }

    /**
     * 定时计算文章热度值（每10分钟执行一次）
     */
    @Scheduled(fixedRate = 60000) // 每1分钟执行一次
    public void calculateHotScore() {
        // 1. 查询所有文章的热度数据
        List<ArticleHotScoreDO> hotScores = articleHotScoreMapper.selectList(null);

        // 2. 遍历热度数据，计算热度值
        for (ArticleHotScoreDO hotScoreDO : hotScores) {
            // 计算时间衰减因子
            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime createdAt = hotScoreDO.getCreatedAt();
            long timeDiff = Duration.between(createdAt, currentTime).getSeconds(); // 时间差（秒）

            // 计算热度值
            double hotScore = 100 + (hotScoreDO.getViews() * 1) + (hotScoreDO.getLikes() * 2) + (hotScoreDO.getComments() * 3) - (timeDiff / 100);

            // 3. 更新热度值
            hotScoreDO.setHotScore(hotScore);
            hotScoreDO.setUpdatedAt(LocalDateTime.now());
            articleHotScoreMapper.updateById(hotScoreDO);

            // 1. 查询热度值最高的 10 篇文章
            List<ArticleHotScoreDO> hotArticles = articleHotScoreMapper.selectList(
                    new QueryWrapper<ArticleHotScoreDO>().orderByDesc("hot_score").last("LIMIT 10")
            );
            System.out.println("查询到的文章数量: " + hotArticles.size());

// 2. 清空旧数据
            redisTemplate.delete(HOT_KEY);

// 3. 将最新数据插入 Redis
            for (ArticleHotScoreDO article : hotArticles) {
                Boolean added = redisTemplate.opsForZSet().add(HOT_KEY, "article:" + article.getArticleId(), article.getHotScore());
                System.out.println("文章ID: " + article.getArticleId() + ", 是否插入成功: " + added);
            }
        }
    }
    /**
     * 获得前10条热度最高的文章信息
     *
     * @return
     */
    @Override
    public Response getTopTenArticle() {
        Set<ZSetOperations.TypedTuple<Object>> redisHotScoreDOs = redisTemplate.opsForZSet().reverseRangeWithScores(HOT_KEY, 0, 9);
        List<ArticleHotTenRspVo> articleHotTenRspVos = new ArrayList<>();
        articleHotTenRspVos = redisHotScoreDOs.stream().map(
                redisHotDo -> ArticleHotTenRspVo.builder()
                        .articleName((String)redisHotDo.getValue())
                        .hot(redisHotDo.getScore()).build()

        ).collect(Collectors.toList());
        return Response.success(articleHotTenRspVos);
    }
}
