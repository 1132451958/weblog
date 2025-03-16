package com.chai.weblog.admin.service;

import com.chai.weblog.common.domain.dos.ArticleDO;
import com.chai.weblog.common.utils.Response;

public interface ArticleHotService {
    public Response getTopTenArticle();

    /**
     * 更新热度表
     */
    boolean updateHotArticle(ArticleDO articleDO);
}
