package com.chai.weblog.common.domain.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 文章热度表DO类
 */
@Builder
@Data
@TableName(value = "article_hot_score")
public class ArticleHotScoreDO {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文章ID
     */
    private Long articleId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 阅读量
     */
    private Integer views = 0;

    /**
     * 点赞数
     */
    private Integer likes = 0;

    /**
     * 评论数
     */
    private Integer comments = 0;

    /**
     * 热度值
     */
    private Double hotScore;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
}