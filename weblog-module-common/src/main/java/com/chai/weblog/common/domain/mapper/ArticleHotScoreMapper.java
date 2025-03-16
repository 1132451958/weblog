package com.chai.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chai.weblog.common.domain.dos.ArticleHotScoreDO;

import java.sql.Wrapper;
import java.util.List;

public interface ArticleHotScoreMapper extends BaseMapper<ArticleHotScoreDO> {

    default List<ArticleHotScoreDO> listTenHotScoresOrderByHotScore() {
        QueryWrapper<ArticleHotScoreDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("hot_score");
        queryWrapper.last("Limit 10");// 按热度值降序排序
        return selectList(queryWrapper);
    }
    default List<ArticleHotScoreDO> listAllHotScoresOrderByHotScore() {
        return selectList(null);
    }
}
