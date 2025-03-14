package com.chai.weblog.web.service;

import com.chai.weblog.common.utils.Response;

public interface CategoryService {
    /**
     * 获取分类列表
     * @return
     */
    Response findCategoryList();
}
