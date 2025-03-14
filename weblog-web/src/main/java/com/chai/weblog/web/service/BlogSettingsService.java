package com.chai.weblog.web.service;

import com.chai.weblog.common.utils.Response;

public interface BlogSettingsService {
    /**
     * 获取博客设置信息
     * @return
     */
    Response findDetail();
}
